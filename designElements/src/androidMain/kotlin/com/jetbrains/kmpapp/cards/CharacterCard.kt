package com.jetbrains.kmpapp.cards

import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.jetbrains.kmpapp.images.LargePortrait
import com.jetbrains.kmpapp.randomDarkPurple
import com.jetbrains.kmpapp.text.SFTitleText
import com.jetbrains.kmpapp.text.StandardBodyText


@Composable
fun CharacterCard(
    id: Int,
    name: String,
    birthYear: String,
    height: String,
    titleFontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
    ) {

        LargePortrait(
            color = Color.randomDarkPurple(id),
            text = "C$id"
        )

        SFTitleText(font= titleFontFamily, name)

        StandardBodyText("\uD83D\uDCC5 $birthYear")
        StandardBodyText("\uD83D\uDCCF $height")
    }
}

/*
@Preview
@Composable
private fun Preview() {
    CharacterCard(
        id = 1,
        name ="Ich",
        birthYear = "123 BYD",
        height = 150
    )
}*/
