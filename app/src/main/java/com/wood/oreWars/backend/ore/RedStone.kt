package com.wood.oreWars.backend.ore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.wood.oreWars.R

open class RedStone : Ore() {
    override val name: String = "RedStone"
    override val imageId: Int = R.drawable.red_stone
    @Composable
    override fun Composable(modifier: Modifier){
        Image(painter = painterResource(id = imageId), contentDescription = name, modifier = modifier)
    }
}