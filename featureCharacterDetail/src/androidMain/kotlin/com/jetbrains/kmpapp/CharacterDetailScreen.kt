package com.jetbrains.kmpapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jetbrains.kmpapp.di.entities.CharacterDetail
import com.jetbrains.kmpapp.di.entities.ContentId
import com.jetbrains.kmpapp.iconButtons.BackIconButton
import com.jetbrains.kmpapp.iconButtons.RefreshIconButton
import com.jetbrains.kmpapp.images.Circle
import com.jetbrains.kmpapp.images.CircleTab
import com.jetbrains.kmpapp.states.BottomSheetState
import com.jetbrains.kmpapp.states.CharacterDetailState
import com.jetbrains.kmpapp.text.LargeText
import com.jetbrains.kmpapp.text.SFTitleText
import com.jetbrains.kmpapp.text.StandardBodyText
import com.jetbrains.kmpapp.text.VeryLargeText
import com.jetbrains.kmpapp.views.ContentView
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    sfFontfamily: FontFamily,
    characterId: Int,
    navigateBack: () -> Unit,
    viewModel: CharacterDetailViewModel = koinViewModel { parametersOf(characterId) }
) {
    // Don't use delegate because smart cast doesn't work in this case
    val characterDetail = viewModel.characterDetailState.collectAsStateWithLifecycle().value

    val bottomSheetState = viewModel.bottomSheetState.collectAsStateWithLifecycle().value

    if (bottomSheetState != null) {
        ModalBottomSheet(
            onDismissRequest = viewModel::onBottomSheetDismiss
        ) {
            when (bottomSheetState) {
                BottomSheetState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                BottomSheetState.Error -> StandardBodyText(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Error while loading item, please reload!"
                )
                is BottomSheetState.ContentView -> ContentView(
                    title = bottomSheetState.content.name,
                    infoTopLeft = bottomSheetState.content.info1,
                    infoTopRight = bottomSheetState.content.info2,
                    infoBottomLeft = bottomSheetState.content.info3,
                    infoBottomRight = bottomSheetState.content.info4,
                )
            }
        }
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    //Show error when paging has error
    LaunchedEffect(characterDetail) {
        if (characterDetail is CharacterDetailState.Error) {
            scope.launch {
                snackbarHostState.showSnackbar("Error loading the character. Reload the screen!")
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
                        SFTitleText(
                            font = sfFontfamily,
                            text = (characterDetail as? CharacterDetailState.CharacterDetailView)?.characterDetail?.name.orEmpty()
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        RefreshIconButton(onClick = viewModel::onRefresh)
                    }

                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            isRefreshing = characterDetail == CharacterDetailState.Loading,
            onRefresh = viewModel::onRefresh
        ) {
            if (characterDetail is CharacterDetailState.CharacterDetailView) {
                CharacterContent(characterDetail.characterDetail, viewModel::onItemClicked)
            }
        }
    }
}


@Composable
private fun CharacterContent(characterDetail: CharacterDetail, onItemClick: (id: ContentId) -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        LargeText("\uD83D\uDCC5 " + characterDetail.birthYearText)
        ItemSpacer()
        LargeText("\uD83D\uDCCF " + characterDetail.heightText)
        ItemSpacer()
        LargeText("⚖ " + characterDetail.massText)
        ItemSpacer()
        LargeText("\uD83E\uDDB1 " + characterDetail.hairColorText)
        ItemSpacer()
        LargeText("\uD83D\uDC41 " + characterDetail.eyeColorText)
        ItemSpacer()
        LargeText("\uD83E\uDDD1\uD83C\uDFFE " + characterDetail.skinColorText)

        if (characterDetail.filmIDs.isNotEmpty()) {
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                thickness = 2.dp
            )
            VeryLargeText("Films")
            LazyRow {
                items(characterDetail.filmIDs) {
                    CircleTab(
                        color = Color.randomDarkGreen(it.id),
                        title = "F${it.id}",
                        text = "Film ${it.id}",
                    ) {
                        onItemClick(it)
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
            VeryLargeText("Vehicles")
            LazyRow {
                items(characterDetail.vehicleIds) {
                    CircleTab(
                        color = Color.randomDarkRed(it.id),
                        title = "V${it.id}",
                        text = "Vehicle ${it.id}"
                    ) {
                        onItemClick(it)
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
            VeryLargeText("Starships")
            LazyRow {
                items(characterDetail.starshipIds) {
                    CircleTab(
                        color = Color.randomDarkGreen(it.id),
                        title = "P${it.id}",
                        text = "Starship ${it.id}"
                    ) {
                        onItemClick(it)
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
            VeryLargeText("Species")
            LazyRow {
                items(characterDetail.speciesIds) {
                    CircleTab(
                        color = Color.randomDarkRed(it.id),
                        title = "S${it.id}",
                        text = "Species ${it.id}"
                    ) {
                        onItemClick(it)
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