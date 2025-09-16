package com.jetbrains.kmpapp

import androidx.compose.ui.graphics.Color
import coil3.network.R
import kotlin.random.Random

private val darkPurples by lazy {
    listOf (
        Color(0xFF673AB7),
        Color(0xFF3916A4),
        Color(0xFF190E4F),
        Color(0xFF8A70EC),
        Color(0xFF483591)
    )
}

private val darkGreens by lazy {
    listOf (
        Color(0xFF054D0A),
        Color(0xFF1F9424),
        Color(0xFF1C6E1E),
        Color(0xFF0B9310),
        Color(0xFF1D3A20)
    )
}

private val darkReds by lazy {
    listOf (
        Color(0xFF4B0202),
        Color(0xFF832929),
        Color(0xFF341010),
    )
}

fun Color.Companion.randomDarkPurple(id: Int): Color {
    return darkPurples.random(Random(id))
}

fun Color.Companion.randomDarkGreen(id: Int): Color {
    return darkGreens.random(Random(id))
}

fun Color.Companion.randomDarkRed(id: Int): Color {
    return darkReds.random(Random(id))
}


