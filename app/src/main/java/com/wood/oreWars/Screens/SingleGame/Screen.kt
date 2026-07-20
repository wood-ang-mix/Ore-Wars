package com.wood.oreWars.Screens.SingleGame

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.wood.oreWars.backend.GameMap
import com.wood.oreWars.composable.MapGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(nav: NavController, gameMap: GameMap){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("剧情模式") }
            )
        }
    ) { innerPadding ->
        MapGrid(gameMap = gameMap)
    }
}
