package com.jetbrains.kmpapp.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun SFTitleText(font: FontFamily, text: String) {
    Text(
        text = text,
        fontFamily = font,
        fontWeight = FontWeight.Normal,

        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(vertical = 8.dp),
        maxLines = 1
    )
}
/*

@Preview
@Composable
private fun Preview() {
    SFTitleText("Test")
}*/
