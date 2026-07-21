package com.wood.oreWars.backend.ore

import com.wood.oreWars.R

open class Lapis : Ore() {
    override val name: String = "Lapis"
    override val imageId: Int = R.drawable.lapis
    override val maxHealth: Int = 100

}
