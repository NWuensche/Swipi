package com.jetbrains.kmpapp.di.entities

data class CharacterDetail(
    val id: Int,
    val name: String,
    val filmIDs: List<FilmId>,

    /**
     * null if and only if unknown
     */
    val height: Int?,

    /**
     * null if and only if unknown
     */
    val birthYear: String?
)