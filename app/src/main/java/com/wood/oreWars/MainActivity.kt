package com.wood.oreWars

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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

    var gameMap = GameMap(32) // 32x32 map

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OreWarsTheme {
                Surface{ OreWarsApp(gameMap = gameMap) }
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
        startDestination = "home"
    ){
        composable("home") { HomeScreen(navController, activity) }
        composable("single_game") { GameScreen(navController, gameMap) }
        composable("multi_game") { MultiGameScreen(navController) }
        composable("setting") { SettingScreen(navController) }
    }
}
