package com.wood.oreWars.backend

data class GameState(
    val map: GameMap,
    val players: List<Player> = emptyList(),
    val tickCount: Long = 0
)
