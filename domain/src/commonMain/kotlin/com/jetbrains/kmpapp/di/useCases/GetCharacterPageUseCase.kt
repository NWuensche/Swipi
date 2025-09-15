package com.jetbrains.kmpapp.di.useCases

import com.jetbrains.kmpapp.di.entities.Character
import com.jetbrains.kmpapp.di.mappers.toCharacter
import com.jetbrains.kmpapp.http.IApiService
import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse

class GetCharacterPageUseCase(
    private val apiService: IApiService
): UseCase<Int, List<Character>> {
    override suspend fun execute(input: Int): List<Character> {
        return apiService
            .getCharactersPage(page = input)
            .map(CharacterResponse::toCharacter)
    }
}