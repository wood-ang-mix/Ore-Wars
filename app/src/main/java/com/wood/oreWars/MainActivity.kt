package com.wood.oreWars

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wood.oreWars.Screens.SingleGame.GameScreen
import com.wood.oreWars.Screens.Home.HomeScreen
import com.wood.oreWars.Screens.MultiGameScreen
import com.wood.oreWars.Screens.Setting.SettingScreen
import com.wood.oreWars.backend.GameMap
import com.wood.oreWars.ui.theme.OreWarsTheme

class MainActivity : ComponentActivity() {

    var gameMap = GameMap(23) // 23x23 map

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OreWarsTheme {
                Surface(color = Color.Black){ OreWarsApp(gameMap = gameMap) }
            }
        }
    }
}

@Composable
fun OreWarsApp(gameMap: GameMap) {
    val navController = rememberNavController()
    val activity = LocalContext.current as Activity
    NavHost(
        navController,
        startDestination = "home",
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(550, easing = FastOutSlowInEasing))
        },
        exitTransition = {
            fadeOut(tween(550, easing = FastOutSlowInEasing)) +
                    slideOutVertically(tween(550, easing = FastOutSlowInEasing)) { -it / 4 }
        },
        popEnterTransition = {
            fadeIn(tween(550, easing = FastOutSlowInEasing)) +
                    slideInVertically(tween(550, easing = FastOutSlowInEasing)) { -it / 4 }
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(550, easing = FastOutSlowInEasing))
        }
    ){
        composable("home") { HomeScreen(navController, activity) }
        composable("single_game") { GameScreen(navController, gameMap) }
        composable("multi_game") { MultiGameScreen(navController) }
        composable("setting") { SettingScreen(navController) }
    }
}
