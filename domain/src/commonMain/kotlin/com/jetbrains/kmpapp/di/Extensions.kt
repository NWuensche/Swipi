package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.http.responseTypes.SwapiUrl

/**
 * Parse the id from swapi-urls
 * e.g. https://swapi.py4e.com/api/people/1/ -> 1
 *
 * It seems like there really is no better way to get the id of a resource
 */
internal val SwapiUrl.id: Int
    get() = this
        .split('/')
        .last { it.toIntOrNull() != null }
        .toInt()

internal fun String.absentToNull(): String? {
    return if (this == "unknown" || this == "n/a") {
        null
    } else {
        this
    }
}