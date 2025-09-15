package com.jetbrains.kmpapp.di.mappers

import com.jetbrains.kmpapp.di.entities.Character
import com.jetbrains.kmpapp.di.entities.FilmId
import com.jetbrains.kmpapp.di.id
import com.jetbrains.kmpapp.http.responseTypes.CharacterResponse
import kotlin.String


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
        filmIDs = filmIDs.map(::FilmId)
    )
}