package com.jetbrains.kmpapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jetbrains.kmpapp.cards.CharacterCard
import com.jetbrains.kmpapp.cards.RandomDarkPurple
import com.jetbrains.kmpapp.iconButtons.BackIconButton
import com.jetbrains.kmpapp.images.Circle
import com.jetbrains.kmpapp.images.CircleTab
import com.jetbrains.kmpapp.text.StandardBodyText
import io.ktor.http.parametersOf
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    navigateBack: () -> Unit,
    viewModel: CharacterDetailViewModel = koinViewModel { parametersOf(characterId) }
) {
    // Don't use delegate because smart cast doesn't work in this case
    val characterDetail = viewModel.characterDetail.collectAsStateWithLifecycle().value

    if (characterDetail == null) return //TODO Show loading or error

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    BackIconButton(onClick = navigateBack)
                },
                title = {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Circle(
                            color = Color.RandomDarkPurple(characterId),
                            text = "C$characterId"
                        )
                        Text(characterDetail.name)
                    }

                }
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {
            StandardBodyText(characterDetail.height.toString()) //TODO Update toString
            StandardBodyText(characterDetail.mass.toString())
        }
    }
}