package com.jetbrains.kmpapp.vm

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jetbrains.kmpapp.di.entities.Character
import com.jetbrains.kmpapp.di.useCases.GetCharacterPageUseCase


/**
 * Logic to load pages of characters
 */
internal class CharacterPagingSource(
    private val getCharacterPageUseCase: GetCharacterPageUseCase
) : PagingSource<Int, Character>() {

    companion object {
        const val STARTING_KEY = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val firstKey = params.key
            ?: STARTING_KEY // If null, then loading first time -> Start with STARTING_KEY

        val pageNumbersToLoad = firstKey.until(firstKey + params.loadSize)

        val characters = try {
            pageNumbersToLoad.flatMap {
                getCharacterPageUseCase.execute(it)
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }


        return LoadResult.Page(
            data = characters,
            prevKey = when (firstKey) {
                STARTING_KEY -> null
                else -> if (firstKey <= STARTING_KEY) {
                    null
                } else {
                    firstKey - 1
                }
            },
            nextKey = pageNumbersToLoad.last + 1
        )
    }

    /**
     * We don't use anchor because when we invalidate Paging, we want to start at the beginning again
     * to show user all of the latest data
     */
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }

}