package com.wood.oreWars.composable

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wood.oreWars.backend.GameMap

@Composable
fun MapGrid(
    gameMap: GameMap,
    modifier: Modifier = Modifier
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(gameMap.size),
    ) {
        //TODO("在此处绘制地图，用网格规划排列，绘制用gameMap里边的Block的composable绘制，在地图外部套一层WoodBox作为地图的边框，在WoodBox外边套一层可滚动的组件让地图过大时可以滚动内部的WoodBox查看")
    }
}