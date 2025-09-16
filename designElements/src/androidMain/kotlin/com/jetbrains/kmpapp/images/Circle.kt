package com.jetbrains.kmpapp.images

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Circle(color: Color, text: String) {

    Text(
        modifier = Modifier
            .padding(16.dp)
            .size(16.dp)
            .drawBehind {
                drawCircle(
                    color = color,
                    radius = this.size.maxDimension
                )
            },
        color = Color.White,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
        text = text
    )
}

@Preview
@Composable
private fun Preview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        Circle(
            color = Color.Blue,
            text = "F1",
        )
    }
}