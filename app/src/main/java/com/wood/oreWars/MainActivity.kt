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
import com.wood.oreWars.backend.Block
import com.wood.oreWars.backend.GameMap
import com.wood.oreWars.backend.ore.RedStone as R
import com.wood.oreWars.backend.ore.Lapis as L
import com.wood.oreWars.backend.ore.Coal as C
import com.wood.oreWars.backend.ore.Gold as G
import com.wood.oreWars.ui.theme.OreWarsTheme

class MainActivity : ComponentActivity() {

    var gameMap = GameMap(
        size = 23,
        map = Array(23) { y ->
            Array(23) { x ->
                when (x) {
                    in 1..5 if y in 1..5 -> R().Block()   // 红石国 (左上)
                    in 6..10 if y in 1..5 -> L().Block()   // 青金石国 (紧挨红石)
                    in 13..17 if y in 1..5 -> C().Block()   // 煤炭国 (右上)
                    in 8..12 if y in 8..12 -> G().Block()   // 金国 (中下)
                    else -> Block(contentOre = null)          // 空地
                }
            }
        }
    ) // 23x23: 红石↔青金挨着, 四国间有间隔

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
