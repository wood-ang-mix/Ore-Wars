package com.wood.oreWars.backend.ore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.contracts.ExperimentalContracts

abstract class Ore {
    protected abstract val name: String
    protected abstract val imageId: Int

    @Composable
    abstract fun Composable(modifier: Modifier = Modifier)

    @Composable
    protected fun Render(imageId: Int, modifier: Modifier = Modifier.size(21.dp)){
        Image(painter = painterResource(id = imageId), contentDescription = name, modifier = modifier)
    }
}
