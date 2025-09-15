package com.jetbrains.kmpapp

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.di.entities.CharacterDetail
import com.jetbrains.kmpapp.di.entities.MiscId
import com.jetbrains.kmpapp.di.useCases.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

    fun onItemClicked(id: MiscId) {
        TODO()
    }
}