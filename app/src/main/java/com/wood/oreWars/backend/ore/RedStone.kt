package com.wood.oreWars.backend.ore

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.wood.oreWars.R

open class RedStone : Ore() {
    override val name: String = "RedStone"
    override val imageId: Int = R.drawable.red_stone
    @Composable
    override fun Composable(){
        Image(painter = painterResource(id = imageId), contentDescription = name)
    }
}