package com.jetbrains.kmpapp.http.responseTypes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmResponse(
    val title: String,
    @SerialName("episode_id")
    val episodeId: Int,
    val director: String,
    val producer: String,
    @SerialName("release_date")
    val releaseDate: String,
)