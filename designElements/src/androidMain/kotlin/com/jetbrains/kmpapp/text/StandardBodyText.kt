package com.jetbrains.kmpapp.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun StandardBodyText(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(bottom = 8.dp),
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Preview
@Composable
private fun Preview() {
    StandardBodyText("Test")
}