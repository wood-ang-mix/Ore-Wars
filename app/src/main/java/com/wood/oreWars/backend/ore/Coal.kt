package com.wood.oreWars.backend.ore

import com.wood.oreWars.R

open class Coal : Ore() {
    override val name: String = "Coal"
    override val imageId: Int = R.drawable.coal
    override val maxHealth: Int = 80

}
