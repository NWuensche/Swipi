package com.jetbrains.kmpapp.http

import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse
import com.jetbrains.kmpapp.http.responseTypes.FilmResponse
import com.jetbrains.kmpapp.http.responseTypes.SpeciesResponse
import com.jetbrains.kmpapp.http.responseTypes.StarshipResponse
import com.jetbrains.kmpapp.http.responseTypes.VehicleResponse

//TODO Error handing just nullable?
interface IApiService {
    suspend fun getCharactersPage(page: Int): List<CharacterResponse>

    /**
     * @return Character with id [id], or null if there is some error
     */
    suspend fun getCharacter(id: Int): CharacterResponse?

    /**
     * @return Film with id [id], or null if there is some error
     */
    suspend fun getFilm(id: Int): FilmResponse?

    /**
     * @return Vehicle with id [id], or null if there is some error
     */
    suspend fun getVehicle(id: Int): VehicleResponse?


    /**
     * @return Species with id [id], or null if there is some error
     */
    suspend fun getSpecies(id: Int): SpeciesResponse?


    /**
     * @return Starship with id [id], or null if there is some error
     */
    suspend fun getStarship(id: Int): StarshipResponse?
}