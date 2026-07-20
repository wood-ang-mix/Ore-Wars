package com.wood.oreWars.backend.ore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wood.oreWars.composable.BLOCK_SIZE
import kotlin.contracts.ExperimentalContracts

abstract class Ore {
    protected abstract val name: String
    protected abstract val imageId: Int

    @Composable
    abstract fun Composable(modifier: Modifier = Modifier)

    @Composable
    protected fun Render(imageId: Int, modifier: Modifier = Modifier.size(BLOCK_SIZE)){
//        Box{
            Image(
                painter = painterResource(id = imageId),
                contentDescription = name,
                modifier = modifier
            )
//        }
    }

    override fun toString() = name
}
