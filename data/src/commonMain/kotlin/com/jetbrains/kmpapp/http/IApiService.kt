package com.jetbrains.kmpapp.http

import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse

//TODO Error handing just nullable?
interface IApiService {
    suspend fun getCharactersPage(page: Int): List<CharacterResponse>

    /**
     * @return Character with id [id], or null if there is some error
     */
    suspend fun getCharacter(id: Int): CharacterResponse?
}