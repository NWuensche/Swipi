package com.jetbrains.kmpapp.http.responseTypes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleResponse(
    val name: String,
    val model: String,
    @SerialName("vehicle_class")
    val vehicleClass: String,
    val manufacturer: String,
    val length: String
)