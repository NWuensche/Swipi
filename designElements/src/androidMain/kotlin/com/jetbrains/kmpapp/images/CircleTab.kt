package com.jetbrains.kmpapp.images

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import com.jetbrains.kmpapp.di.entities.MiscId


@Composable
fun CircleTab(color: Color, title: String, text: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Circle(color, title)
        Text(text)
    }
}

@Composable
private fun Circle(color: Color, text: String) {

    val brush = Brush.linearGradient(listOf(color, Color.Black))
    Text(
        modifier = Modifier
            .padding(32.dp)
            .size(32.dp) //TODO Center Text
            .drawBehind {
                drawCircle(
                    brush = brush,
                    radius = this.size.maxDimension - 8
                )
                drawCircle(
                    color = Color.White,
                    style = Stroke(width = 4f),
                    radius = this.size.maxDimension
                )
            },
        textAlign = TextAlign.Center,
        text = text
    )
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