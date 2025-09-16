package com.jetbrains.kmpapp.http.responseTypes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StarshipResponse(
    val name: String,
    val model: String,
    @SerialName("starship_class")
    val starshipClass: String,
    val manufacturer: String,
    val length: String //TODO To int
)