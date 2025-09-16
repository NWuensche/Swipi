package com.jetbrains.kmpapp.di.mappers

import com.jetbrains.kmpapp.di.absentToNull
import com.jetbrains.kmpapp.di.entities.Character
import com.jetbrains.kmpapp.di.entities.CharacterDetail
import com.jetbrains.kmpapp.di.entities.Content
import com.jetbrains.kmpapp.di.entities.ContentId.FilmId
import com.jetbrains.kmpapp.di.entities.ContentId.SpeciesId
import com.jetbrains.kmpapp.di.entities.ContentId.StarshipId
import com.jetbrains.kmpapp.di.entities.ContentId.VehicleId
import com.jetbrains.kmpapp.di.id
import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse
import com.jetbrains.kmpapp.http.responseTypes.FilmResponse
import com.jetbrains.kmpapp.http.responseTypes.SpeciesResponse
import com.jetbrains.kmpapp.http.responseTypes.StarshipResponse
import com.jetbrains.kmpapp.http.responseTypes.VehicleResponse


private const val unknown = "Find out yourself!"

internal fun CharacterResponse.toCharacter(): Character {
    return Character(
        id = url.id,
        name = name,
        heightText = height.toIntOrNull()?.let { "$it cm" } ?: unknown,
        birthYearText = birthYear.absentToNull() ?: unknown
    )
}

internal fun CharacterResponse.toCharacterDetail(): CharacterDetail {
    return CharacterDetail(
        id = url.id,
        name = name,
        heightText = height.toIntOrNull()?.let { "$it cm" } ?: unknown,
        massText = mass.toIntOrNull()?.let { "$it kg" } ?: unknown,
        genderText = gender.absentToNull() ?: unknown,
        skinColorText = skinColor.absentToNull() ?: unknown,
        hairColorText = hairColor.absentToNull() ?: unknown,
        birthYearText = birthYear.absentToNull() ?: unknown,
        eyeColorText = eyeColor.absentToNull() ?: unknown,
        filmIDs = filmUrls
            .map(String::id)
            .map(::FilmId),
        speciesIds = speciesUrls
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

internal fun FilmResponse.toContent(): Content {
    return Content(
        name = this.title,
        info1 = "Director: ${this.director}",
        info2 = "Producer: ${this.producer}",
        info3 = "Episode: ${this.episodeId}",
        info4 = "Release: ${this.releaseDate}"
    )
}

internal fun SpeciesResponse.toContent(): Content {
    return Content(
        name = this.name,
        info1 = "Language: ${this.language}",
        info2 = "Avg. Height: ${this.averageHeight}",
        info3 = "Class: ${this.classification}",
        info4 = "Designation: ${this.designation}"
    )
}

internal fun VehicleResponse.toContent(): Content {
    return Content(
        name = this.name,
        info1 = "Model: ${this.model}",
        info2 = "Length: ${this.length}m",
        info3 = "Class: ${this.vehicleClass}",
        info4 = "Producer: ${this.manufacturer}"
    )
}

internal fun StarshipResponse.toContent(): Content {
    return Content(
        name = this.name,
        info1 = "Model: ${this.model}",
        info2 = "Length: ${this.length}m",
        info3 = "Class: ${this.starshipClass}",
        info4 = "Producer: ${this.manufacturer}"
    )
}