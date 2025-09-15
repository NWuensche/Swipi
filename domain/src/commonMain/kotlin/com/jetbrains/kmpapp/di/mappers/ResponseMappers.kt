package com.jetbrains.kmpapp.di.mappers

import com.jetbrains.kmpapp.di.absentToNull
import com.jetbrains.kmpapp.di.entities.Character
import com.jetbrains.kmpapp.di.entities.CharacterDetail
import com.jetbrains.kmpapp.di.entities.MiscId.FilmId
import com.jetbrains.kmpapp.di.entities.MiscId.SpeciesId
import com.jetbrains.kmpapp.di.entities.MiscId.StarshipId
import com.jetbrains.kmpapp.di.entities.MiscId.VehicleId
import com.jetbrains.kmpapp.di.id
import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse


//TODO Test
internal fun CharacterResponse.toCharacter(): Character {
    return Character(
        id = url.id,
        name = name,
        height = height.toIntOrNull(),
        birthYear = if (birthYear != "unknown") {
            birthYear
        } else {
            null
        },
    )
}

internal fun CharacterResponse.toCharacterDetail(): CharacterDetail {
    return CharacterDetail(
        id = url.id,
        name = name,
        height = height.toIntOrNull(),
        mass = mass.toIntOrNull(),
        gender = gender.absentToNull(),
        skinColor = skinColor.absentToNull(),
        hairColor = hairColor.absentToNull(),
        birthYear = birthYear.absentToNull(),
        eyeColor = eyeColor.absentToNull(),
        filmIDs = filmUrls
            .map(String::id)
            .map(::FilmId),
        speciesIds = specieUrls
            .map(String::id)
            .map(::SpeciesId),
        vehicleIds = vehicleUrls
            .map(String::id)
            .map(::VehicleId),
        starshipIds = starshipUrls
            .map(String::id)
            .map(::StarshipId),
    )
}