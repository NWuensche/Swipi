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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterListViewModel(
    private val getCharacterPageUseCase: GetCharacterPageUseCase
): ViewModel() {
    private companion object {
        const val PAGE_SIZE = 1
    }

    private var pagingSource = MyPagingSource(getCharacterPageUseCase)
    val characterPagingDataFlow: Flow<PagingData<Character>>

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        val pager = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
            pagingSource = MyPagingSource(getCharacterPageUseCase)
            pagingSource
        }
        characterPagingDataFlow = pager.flow
            .cachedIn(viewModelScope)
    }

    fun reload() {
        _isLoading.value = true
        pagingSource.invalidate()
    }

    fun firstLoadFinished() {
        _isLoading.value = false
    }

}