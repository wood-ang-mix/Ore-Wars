package com.wood.oreWars.backend

class GameMap(
    val size: Int = 0,
    val onClick: (Int, Int) -> Unit = { _, _ -> }
){
    private val map = Array(size) { Array(size) { Block() } }
    operator fun get(x: Int, y: Int): Block = map[y][x]
}
