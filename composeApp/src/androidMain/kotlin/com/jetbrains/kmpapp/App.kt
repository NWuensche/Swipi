package com.jetbrains.kmpapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jetbrains.kmpapp.screen.CharacterListScreen
import kotlinx.serialization.Serializable

@Serializable
object ListDestination

@Serializable
data class DetailDestination(val characterId: Int)

@Composable
fun App(onLicensesButtonPressed: () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme() //It's Star Wars, we always want the dark side!
    ) {
        Surface {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = ListDestination) {
                composable<ListDestination> {
                    CharacterListScreen(
                        sfFontFamily = sfFont,
                        navigateToCharacter = { navController.navigate(DetailDestination(it)) },
                        onLicensesButtonPressed = onLicensesButtonPressed
                    )
                }
                composable<DetailDestination> { backStackEntry ->
                    CharacterDetailScreen(
                        sfFontfamily = sfFont,
                        characterId = backStackEntry.toRoute<DetailDestination>().characterId,
                        navigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
