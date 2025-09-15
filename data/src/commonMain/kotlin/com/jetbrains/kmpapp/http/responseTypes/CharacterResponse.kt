package com.jetbrains.kmpapp.http.responseTypes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val name: String,

    val url: String,

    @SerialName("films")
    val filmIDs: List<String>,

    val height: Int, //TODO can also be unknown

    @SerialName("birth_year")
    val birthYear: String //TODO Check is unknown - Small joke like "find out yourself"
)