package com.wood.oreWars.backend

sealed class Action {
    data class Attack(
        val x: Int,
        val y: Int,
        val playerId: String
    ) : Action()

    data class Skill(
        val skillType: String,
        val targetX: Int,
        val targetY: Int,
        val playerId: String
    ) : Action()

    data class UseItem(
        val itemType: String,
        val targetX: Int,
        val targetY: Int,
        val playerId: String
    ) : Action()
}
