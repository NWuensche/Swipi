package com.jetbrains.kmpapp.di.entities


sealed interface ContentId {
    val id: Int

    data class FilmId(override val id: Int) : ContentId
    data class VehicleId(override val id: Int) : ContentId
    data class StarshipId(override val id: Int) : ContentId
    data class SpeciesId(override val id: Int) : ContentId
}