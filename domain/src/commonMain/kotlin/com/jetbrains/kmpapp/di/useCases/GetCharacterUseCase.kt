package com.jetbrains.kmpapp.di.useCases

import com.jetbrains.kmpapp.di.entities.Character
import com.jetbrains.kmpapp.di.entities.CharacterDetail
import com.jetbrains.kmpapp.di.mappers.toCharacter
import com.jetbrains.kmpapp.di.mappers.toCharacterDetail
import com.jetbrains.kmpapp.http.IApiService
import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse

class GetCharacterUseCase(
    private val apiService: IApiService
): UseCase<Int, CharacterDetail?> {
    override suspend fun execute(input: Int): CharacterDetail? {
        return apiService
            .getCharacter(id = input)
            ?.let(CharacterResponse::toCharacterDetail)
    }
}