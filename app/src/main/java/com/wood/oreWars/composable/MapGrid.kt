package com.wood.oreWars.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wood.oreWars.backend.GameMap

val BLOCK_SIZE = 21.dp

@Composable
fun MapGrid(
    gameMap: GameMap,
    modifier: Modifier = Modifier,
    onClick: (Int, Int) -> Unit = gameMap.onClick
){
    val scrollStateH = rememberScrollState()
    val scrollStateV = rememberScrollState()

    Column(
        modifier = modifier.windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(63.dp))

        Box(
            modifier = Modifier
                .horizontalScroll(scrollStateH)
                .verticalScroll(scrollStateV)
        ) {
            WoodBox(modifier = Modifier.padding(8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    for (y in 0 until gameMap.size) {
                        Row {
                            for (x in 0 until gameMap.size) {
                                Box(
                                    modifier = Modifier
                                        .size(BLOCK_SIZE)
                                        .clickable { onClick(x, y) }
                                ) {
                                    gameMap[x, y].Composable(
                                        modifier = Modifier.size(BLOCK_SIZE)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Box(modifier = Modifier.height(126.dp))
        }
    }
}
