package com.jetbrains.kmpapp.http.responseTypes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val url: SwapiUrl,

    val name: String,

    /**
     * in cm, Either an int or "unknown"
     */
    val height: String,

    /**
     * in kg, Either an int or "unknown"
     */
    val mass: String,

    /**
     * Can be unknown or "n/a"
     */
    @SerialName("hair_color")
    val hairColor: String,

    /**
     * Can be unknown or "n/a"
     */
    @SerialName("skin_color")
    val skinColor: String,

    /**
     * Can be unknown or "n/a"
     */
    @SerialName("eye_color")
    val eyeColor: String,

    //The gender of this person. Either "Male", "Female" or "unknown", "n/a" if the person does not have a gender.
    val gender: String,


    @SerialName("birth_year")
    val birthYear: String, //TODO Check is unknown - Small joke like "find out yourself"

    @SerialName("films")
    val filmUrls: List<SwapiUrl>,

    @SerialName("species")
    val specieUrls: List<SwapiUrl>,

    @SerialName("starships")
    val starshipUrls: List<SwapiUrl>,

    @SerialName("vehicles")
    val vehicleUrls: List<SwapiUrl>,
)