package com.wood.oreWars.backend.ore

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.wood.oreWars.backend.Block

abstract class Ore {
    protected abstract val name: String
    protected abstract val imageId: Int
    protected abstract val maxHealth: Int
    protected var health = maxHealth

    @Composable
    protected fun Render(imageId: Int, modifier: Modifier = Modifier){
        Image(
            painter = painterResource(id = imageId),
            contentDescription = name,
            modifier = modifier
        )
    }

    override fun toString() = name

    fun Block() = Block(this)
}
