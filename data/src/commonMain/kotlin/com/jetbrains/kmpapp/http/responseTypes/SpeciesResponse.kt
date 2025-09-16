package com.jetbrains.kmpapp.http.responseTypes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesResponse(
    val name: String,
    val classification: String,
    val designation: String,
    val language: String,
    @SerialName("average_height")
    val averageHeight: String //TODO To int
)