package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.http.ApiService
import com.jetbrains.kmpapp.http.IApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<IApiService> {
        ApiService(baseUrl = "https://swapi.py4e.com/api", httpClient = get())
    }
}
