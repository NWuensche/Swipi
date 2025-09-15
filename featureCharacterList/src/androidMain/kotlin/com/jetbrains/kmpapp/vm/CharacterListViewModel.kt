package com.jetbrains.kmpapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jetbrains.kmpapp.di.entities.Character
import com.jetbrains.kmpapp.di.useCases.GetCharacterPageUseCase
import kotlinx.coroutines.flow.Flow

class CharacterListViewModel(getCharacterPageUseCase: GetCharacterPageUseCase): ViewModel() {
    private companion object {
        const val PAGE_SIZE = 1
    }

    val characterPagingDataFlow: Flow<PagingData<Character>>

    init {
        val pager = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            MyPagingSource(getCharacterPageUseCase)
        }

        characterPagingDataFlow = pager.flow
            .cachedIn(viewModelScope)
    }

}