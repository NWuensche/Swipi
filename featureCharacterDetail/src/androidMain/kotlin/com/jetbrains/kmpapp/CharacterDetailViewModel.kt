package com.jetbrains.kmpapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.di.entities.CharacterDetail
import com.jetbrains.kmpapp.di.entities.ContentId
import com.jetbrains.kmpapp.di.useCases.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface BottomSheetState {
    object Loading: BottomSheetState
    data class Content(
        val title: String,
        val info1: String,
        val info2: String,
        val info3: String,
        val info4: String
    )
}

class CharacterDetailViewModel(
    characterId: Int,
    getCharacterUseCase: GetCharacterUseCase
): ViewModel() {
    //TODO private
    private val _characterDetail = MutableStateFlow<CharacterDetail?>(null)
    val characterDetail: StateFlow<CharacterDetail?> = _characterDetail

    init {
        viewModelScope.launch {
            _characterDetail.value = getCharacterUseCase.execute(characterId)
        }
    }

    fun onItemClicked(id: ContentId) {
        TODO()
    }
}