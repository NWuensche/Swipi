package com.jetbrains.kmpapp.di.useCases

import com.jetbrains.kmpapp.di.entities.CharacterDetail
import com.jetbrains.kmpapp.di.mappers.toCharacterDetail
import com.jetbrains.kmpapp.http.IApiService
import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharacterUseCase(
    private val apiService: IApiService
) : UseCase<Int, CharacterDetail?> {
    override suspend fun execute(input: Int): CharacterDetail? = withContext(Dispatchers.Default) {
        apiService
            .getCharacter(id = input)
            ?.let(CharacterResponse::toCharacterDetail)
    }
}