package com.wood.oreWars.Screens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.wood.oreWars.backend.Action
import com.wood.oreWars.backend.ClickTarget
import com.wood.oreWars.backend.GameViewModel
import com.wood.oreWars.composable.MapGrid
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(nav: NavController, gameViewModel: GameViewModel = viewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val gameState by gameViewModel.gameState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("剧情模式 — Tick ${gameState.tickCount}") }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .height(61.dp))
        MapGrid(
            gameMap = gameState.map,
            onClick = { target ->
                when (target) {
                    is ClickTarget.Block -> {
                        gameViewModel.sendAction(Action.Attack(target.x, target.y, "player1"))
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "攻击 (${target.x + 1}, ${target.y + 1})"
                            )
                        }
                    }
                    is ClickTarget.Item -> {
                        gameViewModel.sendAction(Action.UseItem(target.itemName, target.x, target.y, "player1"))
                        scope.launch {
                            snackbarHostState.showSnackbar("使用物品: ${target.itemName}")
                        }
                    }
                }
            }
        )
    }
}
