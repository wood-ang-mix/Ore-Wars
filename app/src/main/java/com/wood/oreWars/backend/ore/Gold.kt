package com.wood.oreWars.backend.ore

import com.wood.oreWars.R

open class Gold : Ore() {
    override val name: String = "Gold"
    override val imageId: Int = R.drawable.gold
    override val maxHealth: Int = 120

}
