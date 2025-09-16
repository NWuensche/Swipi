package com.jetbrains.kmpapp.di.useCases

import com.jetbrains.kmpapp.di.entities.Character
import com.jetbrains.kmpapp.di.mappers.toCharacter
import com.jetbrains.kmpapp.http.IApiService
import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharacterPageUseCase(
    private val apiService: IApiService
) : UseCase<Int, List<Character>> {
    override suspend fun execute(input: Int): List<Character> = withContext(Dispatchers.Default) {
        apiService
            .getCharactersPage(page = input)
            .map(CharacterResponse::toCharacter)
    }
}