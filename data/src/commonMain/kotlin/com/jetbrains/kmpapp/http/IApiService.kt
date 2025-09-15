package com.jetbrains.kmpapp.http

import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse

interface IApiService {
    suspend fun getCharactersPage(page: Int): List<CharacterResponse>
}