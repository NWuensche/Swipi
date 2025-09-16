package com.jetbrains.kmpapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.di.entities.ContentId
import com.jetbrains.kmpapp.di.useCases.GetCharacterUseCase
import com.jetbrains.kmpapp.di.useCases.GetContentUseCase
import com.jetbrains.kmpapp.states.BottomSheetState
import com.jetbrains.kmpapp.states.CharacterDetailState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val characterId: Int,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getContentUseCase: GetContentUseCase
) : ViewModel() {
    private val _characterDetailState =
        MutableStateFlow<CharacterDetailState>(CharacterDetailState.Loading)
    val characterDetailState: StateFlow<CharacterDetailState> = _characterDetailState

    private val _bottomSheetState = MutableStateFlow<BottomSheetState?>(null)

    /**
     * null means bottom sheet is not visible
     */
    val bottomSheetState: StateFlow<BottomSheetState?> = _bottomSheetState

    init {
        onRefresh()
    }

    /**
     * Control load content job in case user dismisses loading
     */
    private var contentLoadJob: Job? = null
        set(value) {
            field?.cancel()
            field = value
        }

    fun onItemClicked(id: ContentId) {
        _bottomSheetState.value = BottomSheetState.Loading

        contentLoadJob = viewModelScope.launch {
            val content = getContentUseCase.execute(id)

            _bottomSheetState.value = content?.let(BottomSheetState::ContentView)
                ?: BottomSheetState.Error
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            _characterDetailState.value = CharacterDetailState.Loading
            _characterDetailState.value = getCharacterUseCase.execute(characterId)
                ?.let(CharacterDetailState::CharacterDetailView)
                ?: CharacterDetailState.Error
        }
    }

    fun onBottomSheetDismiss() {
        contentLoadJob = null
        _bottomSheetState.value = null
    }
}