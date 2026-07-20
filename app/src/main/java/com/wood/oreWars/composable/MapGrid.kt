package com.wood.oreWars.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wood.oreWars.backend.GameMap

private val BLOCK_SIZE = 40.dp

@Composable
fun MapGrid(
    gameMap: GameMap,
    modifier: Modifier = Modifier
){
    val scrollStateH = rememberScrollState()
    val scrollStateV = rememberScrollState()

    Box(
        modifier = modifier
            .horizontalScroll(scrollStateH)
            .verticalScroll(scrollStateV)
    ) {
        WoodBox(modifier = modifier.padding(8.dp)) {
            Column {
                for (y in 0 until gameMap.size) {
                    Row {
                        for (x in 0 until gameMap.size) {
                            gameMap[x, y].Composable(
                                modifier = Modifier.size(BLOCK_SIZE)
                            )
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier.height(126.dp))
    }
}
