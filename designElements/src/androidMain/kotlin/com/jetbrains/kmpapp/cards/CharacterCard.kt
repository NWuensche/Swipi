package com.jetbrains.kmpapp.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.images.LargePortrait
import com.jetbrains.kmpapp.text.SFTitleText
import com.jetbrains.kmpapp.text.StandardBodyText
import io.ktor.client.request.invoke
import io.ktor.http.invoke
import kmp_app_template_native.designelements.generated.resources.Res
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource


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
    birthday: String,
    height: Int,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
    ) {

        LargePortrait(
            color = Color.RandomDarkPurple(id),
            text = "Character $id"
        )

        SFTitleText(name)

        StandardBodyText("\uD83D\uDCC5 $birthday")
        StandardBodyText("\uD83D\uDCCF $height") //TODO add cm
    }
}

@Preview
@Composable
private fun Preview() {
    CharacterCard(
        id = 1,
        name ="Ich",
        birthday = "123 BYD",
        height = 150
    )
}