package com.jetbrains.kmpapp.states

import com.jetbrains.kmpapp.di.entities.CharacterDetail

sealed interface CharacterDetailState {
    object Loading: CharacterDetailState
    object Error: CharacterDetailState
    data class CharacterDetailView(
        val characterDetail: CharacterDetail
    ): CharacterDetailState
}