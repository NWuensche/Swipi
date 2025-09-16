package com.jetbrains.kmpapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.jetbrains.kmpapp.cards.CharacterCard
import com.jetbrains.kmpapp.iconButtons.InfoIconButton
import com.jetbrains.kmpapp.iconButtons.RefreshIconButton
import com.jetbrains.kmpapp.vm.CharacterListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel



@OptIn(ExperimentalMaterial3Api::class) //Needed for TopAppBar
@Composable
fun CharacterListScreen(
    sfFontFamily: FontFamily,
    navigateToCharacter: (id: Int) -> Unit,
    onLicensesButtonPressed: () -> Unit,
    viewModel: CharacterListViewModel = koinViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val characterList = viewModel.characterPagingDataFlow.collectAsLazyPagingItems()
    val loadingState = characterList.loadState.refresh
    val loadingAppendState = characterList.loadState.append
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    //Show error when paging has error
    LaunchedEffect(loadingState, loadingAppendState) {
        if (loadingState is LoadState.Error || loadingAppendState is LoadState.Error) {
            scope.launch {
                snackbarHostState.showSnackbar("Error loading the characters. Reload the screen!")
            }
        }
    }

    // Stop Pull to refresh when first load done
    LaunchedEffect(loadingState) {
        if (loadingState !is LoadState.Loading) {
            viewModel.firstLoadFinished()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("SWIPI") //TODO Use App name
                        Spacer(modifier = Modifier.weight(1f))
                        RefreshIconButton(onClick = viewModel::reload)
                        InfoIconButton(onClick = onLicensesButtonPressed)
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        val pagerState = rememberPagerState(pageCount = {
            characterList.itemCount
        })
        val fling = PagerDefaults.flingBehavior(
            state = pagerState,
            pagerSnapDistance = PagerSnapDistance.atMost(5)
        )

        PullToRefreshBox(
            modifier =  Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            isRefreshing = isLoading,
            onRefresh = {}
        ) {
            HorizontalPager(
                modifier = Modifier.align(Alignment.Center), //Need box for align to be available
                state = pagerState,
                flingBehavior = fling,
                snapPosition = SnapPosition.Center,
                pageSize =  threePagesPerViewport
                ) { i ->
                val character = characterList[i] ?: return@HorizontalPager
                    CharacterCard(
                        modifier = Modifier
                            .fillMaxHeight(0.6f)
                            .fillMaxWidth(fraction = 0.9f) //Show other pages instead of the buttons to show swipe
                            .clickable {
                                navigateToCharacter(character.id)
                            },
                        id = character.id,
                        titleFontFamily = sfFontFamily,
                        name = character.name,
                        height = character.height ?: 0, //TODO
                        birthYear = character.birthYear ?: "TODO"
                    )
                }
        }


    }
}

private val threePagesPerViewport = object : PageSize {
    override fun Density.calculateMainAxisPageSize(
        availableSpace: Int,
        pageSpacing: Int
    ): Int {
        return ((availableSpace - 2 * pageSpacing) / 1.4).toInt() // Show part of the previous and next page
    }
}