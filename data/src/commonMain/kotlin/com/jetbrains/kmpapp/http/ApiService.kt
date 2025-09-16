package com.jetbrains.kmpapp.http

import com.jetbrains.kmpapp.http.responseTypes.CharacterPageResponse
import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse
import com.jetbrains.kmpapp.http.responseTypes.FilmResponse
import com.jetbrains.kmpapp.http.responseTypes.SpeciesResponse
import com.jetbrains.kmpapp.http.responseTypes.StarshipResponse
import com.jetbrains.kmpapp.http.responseTypes.VehicleResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


internal class ApiService(
    private val baseUrl: String,
    private val httpClient: HttpClient
): IApiService {

    /**
     * Page starts at 1
     */
    override suspend fun getCharactersPage(page: Int): List<CharacterResponse> {
        return httpClient.get("$baseUrl/people?page=$page").body<CharacterPageResponse>().results //TODO Handle error
    }

    override suspend fun getCharacter(id: Int): CharacterResponse? {
        return try {
            httpClient.get("$baseUrl/people/$id").body()
        } catch (_: Exception) {
            null
        }
    }

    override suspend fun getFilm(id: Int): FilmResponse? {
        return try {
            httpClient.get("$baseUrl/films/$id").body()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getSpecies(id: Int): SpeciesResponse? {
        return try {
            httpClient.get("$baseUrl/species/$id").body()
        } catch (_: Exception) {
            null
        }
    }

    override suspend fun getVehicle(id: Int): VehicleResponse? {
        return try {
            httpClient.get("$baseUrl/vehicles/$id").body()
        } catch (_: Exception) {
            null
        }
    }

    override suspend fun getStarship(id: Int): StarshipResponse? {
        return try {
            httpClient.get("$baseUrl/starships/$id").body()
        } catch (_: Exception) {
            null
        }
    }
}