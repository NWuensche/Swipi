package com.jetbrains.kmpapp.images

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CircleTab(color: Color, title: String, text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GradientCircle(color, title)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text)
    }
}

@Composable
private fun GradientCircle(color: Color, text: String) {

    val brush = Brush.linearGradient(listOf(color, Color.Black))

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .size(64.dp) // This is the total size of the circle
            .drawBehind {
                val radius = size.minDimension / 2
                drawCircle(
                    brush = brush,
                    radius = radius - 4.dp.toPx()
                )
                drawCircle(
                    color = Color.White,
                    style = Stroke(width = 4f),
                    radius = radius
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        CircleTab(
            color = Color.Blue,
            title = "F1",
            text = "Film 1",
            onClick = {}
        )
    }
}