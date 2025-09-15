package com.jetbrains.kmpapp.vm

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jetbrains.kmpapp.di.entities.Character
import com.jetbrains.kmpapp.di.useCases.GetCharacterPageUseCase


// TODO COmment + Update other comments
internal class MyPagingSource(
    private val getCharacterPageUseCase: GetCharacterPageUseCase
) : PagingSource<Int, Character>() {

    companion object {
        const val STARTING_KEY = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val firstKey = params.key ?: STARTING_KEY // If null, then loading first time -> Start with STARTING_KEY

        val pageNumbersToLoad = firstKey.until(firstKey + params.loadSize) //TODO Always from Startkey?

        val characters = pageNumbersToLoad.flatMap {
            getCharacterPageUseCase.execute(it)
        }

        return LoadResult.Page(
            data = characters,
            prevKey = when (firstKey) {
                STARTING_KEY -> null
                else -> when (val prevKey = ensureValidKey(key = pageNumbersToLoad.first - params.loadSize)) {
                    STARTING_KEY -> null //No key before STARTING_KEY
                    else -> prevKey
                }
            },
            nextKey = pageNumbersToLoad.last + 1
        )
    }

    /**
     * If Page is invalidated, load the closest one to the current anchor-position
     */
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val item = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = item.id - (state.config.pageSize / 2))
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = key.coerceAtLeast(STARTING_KEY)
}