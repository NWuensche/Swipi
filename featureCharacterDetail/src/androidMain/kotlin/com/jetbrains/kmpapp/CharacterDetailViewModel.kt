package com.jetbrains.kmpapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.di.entities.CharacterDetail
import com.jetbrains.kmpapp.di.entities.Content
import com.jetbrains.kmpapp.di.entities.ContentId
import com.jetbrains.kmpapp.di.useCases.GetCharacterUseCase
import com.jetbrains.kmpapp.di.useCases.GetContentUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface BottomSheetState {
    object Loading: BottomSheetState
    object Error: BottomSheetState
    data class ContentView(
        val content: Content
    ): BottomSheetState
}

class CharacterDetailViewModel(
    characterId: Int,
    getCharacterUseCase: GetCharacterUseCase,
    private val getContentUseCase: GetContentUseCase
): ViewModel() {
    //TODO private
    private val _characterDetail = MutableStateFlow<CharacterDetail?>(null)
    val characterDetail: StateFlow<CharacterDetail?> = _characterDetail

    private val _bottomSheetState = MutableStateFlow<BottomSheetState?>(null)

    /**
     * null means bottom sheet is dismissed
     */
    val bottomSheetState: StateFlow<BottomSheetState?> = _bottomSheetState

    init {
        viewModelScope.launch {
            _characterDetail.value = getCharacterUseCase.execute(characterId)
        }
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

    fun onBottomSheetDismiss() {
        contentLoadJob = null
        _bottomSheetState.value = null
    }
}