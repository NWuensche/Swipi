package com.jetbrains.kmpapp.di.useCases

import com.jetbrains.kmpapp.di.entities.Content
import com.jetbrains.kmpapp.di.entities.ContentId
import com.jetbrains.kmpapp.di.mappers.toCharacterDetail
import com.jetbrains.kmpapp.di.mappers.toContent
import com.jetbrains.kmpapp.http.IApiService
import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetContentUseCase(
    private val apiService: IApiService
): UseCase<ContentId, Content?> {
    override suspend fun execute(input: ContentId): Content? = withContext(Dispatchers.Default) {
        when(input) {
            is ContentId.FilmId -> apiService.getFilm(input.id)?.toContent()
            is ContentId.SpeciesId -> apiService.getSpecies(input.id)?.toContent()
            is ContentId.StarshipId -> apiService.getStarship(input.id)?.toContent()
            is ContentId.VehicleId -> apiService.getVehicle(input.id)?.toContent()
        }
    }
}