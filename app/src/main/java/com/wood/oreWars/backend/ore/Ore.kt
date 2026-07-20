package com.wood.oreWars.backend.ore

import androidx.compose.runtime.Composable

abstract class Ore {
    protected abstract val name: String
    protected abstract val imageId: Int

    @Composable
    abstract fun Composable()
}