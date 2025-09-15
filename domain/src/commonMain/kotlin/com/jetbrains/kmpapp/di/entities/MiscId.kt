package com.jetbrains.kmpapp.di.entities


sealed interface MiscId {
    val id: Int

    data class FilmId(override val id: Int): MiscId
    data class VehicleId(override val id: Int): MiscId
    data class StarshipId(override val id: Int): MiscId
    data class SpeciesId(override val id: Int): MiscId
}