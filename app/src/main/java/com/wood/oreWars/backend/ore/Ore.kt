package com.wood.oreWars.backend.ore

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlin.contracts.ExperimentalContracts

abstract class Ore {
    protected abstract val name: String
    protected abstract val imageId: Int

    @Composable
    abstract fun Composable(modifier: Modifier = Modifier.fillMaxSize())
}