package com.jetbrains.kmpapp.cards

import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jetbrains.kmpapp.images.LargePortrait
import com.jetbrains.kmpapp.text.SFTitleText
import com.jetbrains.kmpapp.text.StandardBodyText


//TODO Move
fun Color.Companion.RandomDarkPurple(id: Int): Color {
    val i = id % 5

    return when(i) {
        0 -> Color(0xFF673AB7) //TODO Don't recompute all the time
        1 -> Color(0xFF3916A4) //TODO Don't recompute all the time
        2 -> Color(0xFF190E4F) //TODO Don't recompute all the time
        3 -> Color(0xFF8A70EC) //TODO Don't recompute all the time
        4 -> Color(0xFF483591) //TODO Don't recompute all the time
        else -> TODO()
    }
}

@Composable
fun CharacterCard(
    id: Int,
    name: String,
    birthYear: String,
    height: Int,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
    ) {

        LargePortrait(
            color = Color.RandomDarkPurple(id),
            text = "C$id"
        )

        SFTitleText(name)

        StandardBodyText("\uD83D\uDCC5 $birthYear")
        StandardBodyText("\uD83D\uDCCF $height") //TODO add cm
    }
}

@Preview
@Composable
private fun Preview() {
    CharacterCard(
        id = 1,
        name ="Ich",
        birthYear = "123 BYD",
        height = 150
    )
}