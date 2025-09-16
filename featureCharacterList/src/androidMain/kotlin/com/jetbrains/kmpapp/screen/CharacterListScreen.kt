package com.jetbrains.kmpapp.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Density
import androidx.paging.compose.collectAsLazyPagingItems
import com.jetbrains.kmpapp.cards.CharacterCard
import com.jetbrains.kmpapp.iconButtons.InfoIconButton
import com.jetbrains.kmpapp.vm.CharacterListViewModel
import org.koin.androidx.compose.koinViewModel



@OptIn(ExperimentalMaterial3Api::class) //Needed for TopAppBar
@Composable
fun CharacterListScreen(
    navigateToCharacter: (id: Int) -> Unit,
    onLicensesButtonPressed: () -> Unit,
    viewModel: CharacterListViewModel = koinViewModel()
) {

    val characterList = viewModel.characterPagingDataFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("SWIPI") //TODO Use App name
                        Spacer(modifier = Modifier.weight(1f))
                        InfoIconButton(onClick = onLicensesButtonPressed)
                    }
                }
            )
        }
    ) { innerPadding ->
        val pagerState = rememberPagerState(pageCount = {
            characterList.itemCount
        })
        val fling = PagerDefaults.flingBehavior(
            state = pagerState,
            pagerSnapDistance = PagerSnapDistance.atMost(5)
        )

        Box(
          modifier =  Modifier
              .padding(innerPadding)
              .fillMaxSize()
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