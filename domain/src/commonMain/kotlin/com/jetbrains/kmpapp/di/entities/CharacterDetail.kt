package com.jetbrains.kmpapp.di.entities

/**
 * all of the *text-Fields contain either the value or some unknown-text
 * Should only be used to display data
 */
data class CharacterDetail(
    val id: Int,
    val name: String,
    val heightText: String,
    val birthYearText: String,
    val massText: String,
    val hairColorText: String,
    val skinColorText: String,
    val eyeColorText: String,
    val genderText: String,

    val filmIDs: List<ContentId.FilmId>,

    val speciesIds: List<ContentId.SpeciesId>,

    val starshipIds: List<ContentId.StarshipId>,

    val vehicleIds: List<ContentId.VehicleId>,
)