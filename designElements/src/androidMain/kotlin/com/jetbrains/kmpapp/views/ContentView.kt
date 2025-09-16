package com.jetbrains.kmpapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.images.LargePortrait
import com.jetbrains.kmpapp.randomDarkPurple
import com.jetbrains.kmpapp.text.LargeText
import com.jetbrains.kmpapp.text.SFTitleText
import com.jetbrains.kmpapp.text.StandardBodyText
import com.jetbrains.kmpapp.text.VeryLargeText


@Composable
fun ContentView(
    title: String,
    infoTopLeft: String,
    infoTopRight: String,
    infoBottomLeft: String,
    infoBottomRight: String
) {
    Column (modifier = Modifier.padding(16.dp)) {
        VeryLargeText(title)
        Row {
            StandardBodyText(infoTopLeft)
            Spacer(modifier = Modifier.weight(1f))
            StandardBodyText(infoTopRight)
        }

        Row {
            StandardBodyText(infoBottomLeft)
            Spacer(modifier = Modifier.weight(1f))
            StandardBodyText(infoBottomRight)
        }
    }

}

@Preview
@Composable
private fun Preview() {
    ContentView(
        "t",
        "\uD83D\uDCC5tl",
        "tr",
        "bl",
        "br"
    )
}