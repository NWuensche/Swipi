package com.jetbrains.kmpapp.di.entities

data class Character(
    val id: Int,
    val name: String,
    val filmIDs: List<FilmId>,

    /**
     * null if and only if unknown
     */
    val height: Int?, //TODO can also be unknown

    /**
     * null if and only if unknown
     */
    val birthYear: String? //TODO Check is unknown - Small joke like "find out yourself"
)