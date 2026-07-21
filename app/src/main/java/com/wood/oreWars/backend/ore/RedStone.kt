package com.wood.oreWars.backend.ore

import com.wood.oreWars.R

open class RedStone : Ore() {
    override val name: String = "RedStone"
    override val imageId: Int = R.drawable.red_stone
    override val maxHealth: Int = 100
}