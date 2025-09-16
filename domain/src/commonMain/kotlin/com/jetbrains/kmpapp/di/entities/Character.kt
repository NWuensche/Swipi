package com.jetbrains.kmpapp.di.entities

/**
 * all of the *text-Fields contain either the value or some unknown-text
 * Should only be used to display data
 */
data class Character(
    val id: Int,
    val name: String,

    val heightText: String,

    val birthYearText: String
)

