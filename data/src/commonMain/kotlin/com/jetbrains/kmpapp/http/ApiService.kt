package com.jetbrains.kmpapp.http

import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse
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
        return httpClient.get("$baseUrl/person?page=$page").body()
    }

}