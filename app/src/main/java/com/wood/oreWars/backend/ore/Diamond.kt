package com.wood.oreWars.backend.ore

import com.wood.oreWars.R

open class Diamond : Ore() {
    override val name: String = "Diamond"
    override val imageId: Int = R.drawable.diamond
    override val maxHealth: Int = 120

}
