package com.wood.oreWars.Screens.SingleGame

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wood.oreWars.backend.ClickTarget
import com.wood.oreWars.backend.GameMap
import com.wood.oreWars.composable.MapGrid
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(nav: NavController, gameMap: GameMap){
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("剧情模式") }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .height(61.dp))
        MapGrid(
            gameMap = gameMap,
            onClick = { target ->
                scope.launch {
                    when (target) {
                        is ClickTarget.Block -> {
                            snackbarHostState.showSnackbar(
                                "调试信息:点击了区块\n" +
                                        "[位置] (${target.x + 1}, ${target.y + 1})\n" +
                                        "[内容] ${gameMap[target.x, target.y].contentOre}\n" +
                                        "[是否为陆地] ${gameMap[target.x, target.y].isLand}"
                            )
                        }
                        is ClickTarget.Item -> {
                            snackbarHostState.showSnackbar(
                                "调试信息:点击了物品\n" +
                                        "[位置] (${target.x + 1}, ${target.y + 1})\n" +
                                        "[物品] ${target.itemName}"
                            )
                        }
                    }
                }
            }
        )
    }
}
