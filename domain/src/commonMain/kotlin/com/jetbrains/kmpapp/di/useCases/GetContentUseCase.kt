package com.jetbrains.kmpapp.di.useCases

import com.jetbrains.kmpapp.di.entities.Content
import com.jetbrains.kmpapp.di.entities.ContentId
import com.jetbrains.kmpapp.di.mappers.toCharacterDetail
import com.jetbrains.kmpapp.http.IApiService
import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse

class GetContentUseCase(
    private val apiService: IApiService
): UseCase<ContentId, Content?> {
    override suspend fun execute(input: ContentId): Content? {
        val content = when(input) {
            is ContentId.FilmId -> apiService.getFilm(input.id)
        }
        return apiService
            .getCharacter(id = input)
            ?.let(CharacterResponse::toCharacterDetail)
    }
}