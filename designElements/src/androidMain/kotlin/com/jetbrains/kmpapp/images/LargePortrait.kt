package com.jetbrains.kmpapp.images

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.cards.RandomDarkPurple


@Composable
fun LargePortrait(color: Color, text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .background(color),
        contentAlignment = Alignment.Center) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Preview
@Composable
private fun Preview() {
    LargePortrait(
        color = Color.Red,
        text = "Test"
    )
}