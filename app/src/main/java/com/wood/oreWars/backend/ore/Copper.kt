package com.wood.oreWars.backend.ore

import com.wood.oreWars.R

open class Copper : Ore() {
    override val name: String = "Copper"
    override val imageId: Int = R.drawable.copper
    override val maxHealth: Int = 80

}
