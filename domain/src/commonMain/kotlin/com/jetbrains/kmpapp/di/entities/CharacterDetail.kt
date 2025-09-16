package com.jetbrains.kmpapp.di.entities

data class CharacterDetail(
    val id: Int,
    val name: String,
    /**
     * null if and only if unknown
     */
    val height: Int?,

    /**
     * null if and only if unknown
     */
    val birthYear: String?,

    /**
     * null if and only if unknown
     */
    val mass: Int?,

    /**
     * null if and only if unknown or n/a
     */
    val hairColor: String?,

    /**
     * null if and only if unknown or n/a
     */
    val skinColor: String?,

    /**
     * null if and only if unknown or n/a
     */
    val eyeColor: String?,

    /**
     * null if and only if unknown or n/a
     */
    val gender: String?,

    val filmIDs: List<ContentId.FilmId>,

    val speciesIds: List<ContentId.SpeciesId>,

    val starshipIds: List<ContentId.StarshipId>,

    val vehicleIds: List<ContentId.VehicleId>,
)