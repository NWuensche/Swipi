package com.jetbrains.kmpapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jetbrains.kmpapp.iconButtons.BackIconButton
import com.jetbrains.kmpapp.images.Circle
import com.jetbrains.kmpapp.images.CircleTab
import com.jetbrains.kmpapp.text.LargeText
import com.jetbrains.kmpapp.text.VeryLargeText
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

    val bottomSheetState = viewModel.bottomSheetState.collectAsStateWithLifecycle().value

    if (bottomSheetState != null) {
        ModalBottomSheet(
            onDismissRequest = viewModel::onBottomSheetDismiss
        ) {
            when (bottomSheetState) {
                BottomSheetState.Loading -> CircularProgressIndicator()
                BottomSheetState.Error -> TODO()
                is BottomSheetState.ContentView -> Text("TODO")
            }
        }
    }

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
                            color = Color.randomDarkPurple(characterId),
                            text = "C$characterId"
                        )
                        Text(characterDetail.name)
                    }

                }
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            LargeText("\uD83D\uDCC5 " + characterDetail.birthYear.toString())
            ItemSpacer()
            LargeText("\uD83D\uDCCF " + characterDetail.height.toString()) //TODO Update toString
            ItemSpacer()
            LargeText("⚖ " + characterDetail.mass.toString())
            ItemSpacer()
            LargeText("\uD83E\uDDB1 " + characterDetail.hairColor.toString())
            ItemSpacer()
            LargeText("\uD83D\uDC41 " + characterDetail.eyeColor.toString())
            ItemSpacer()
            LargeText("\uD83E\uDDD1\uD83C\uDFFE " + characterDetail.skinColor.toString())

            if (characterDetail.filmIDs.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 4.dp),
                    thickness = 2.dp
                )
                VeryLargeText("Films") //TODO Strings
                LazyRow {
                    items(characterDetail.filmIDs) {
                        CircleTab(
                            color = Color.randomDarkGreen(it.id), //TODO Different color
                            title = "F${it.id}",
                            text = "Film ${it.id}"
                        ) {
                            viewModel.onItemClicked(it)
                        }
                    }
                }
            }


            if (characterDetail.vehicleIds.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 4.dp),
                    thickness = 2.dp
                )
                VeryLargeText("Vehicles") //TODO Strings
                LazyRow {
                    items(characterDetail.vehicleIds) {
                        CircleTab(
                            color = Color.randomDarkRed(it.id), //TODO Different color
                            title = "V${it.id}",
                            text = "Vehicle ${it.id}"
                        ) {
                            viewModel.onItemClicked(it)
                        }
                    }
                }
            }


            if (characterDetail.starshipIds.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 4.dp),
                    thickness = 2.dp
                )
                VeryLargeText("Starships") //TODO Strings
                LazyRow {
                    items(characterDetail.starshipIds) {
                        CircleTab(
                            color = Color.randomDarkGreen(it.id), //TODO Different color
                            title = "SS${it.id}",
                            text = "Starship ${it.id}"
                        ) {
                            viewModel.onItemClicked(it)
                        }
                    }
                }
            }


            if (characterDetail.speciesIds.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 4.dp),
                    thickness = 2.dp
                )
                VeryLargeText("Species") //TODO Strings
                LazyRow {
                    items(characterDetail.speciesIds) {
                        CircleTab(
                            color = Color.randomDarkRed(it.id), //TODO Different color
                            title = "S${it.id}",
                            text = "Species ${it.id}"
                        ) {
                            viewModel.onItemClicked(it)
                        }
                    }
                }
            }

        }
    }
}

@Composable
private fun ItemSpacer() {
    Spacer(modifier = Modifier.height(8.dp))
}