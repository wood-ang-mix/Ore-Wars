package com.wood.oreWars.backend

import androidx.annotation.Nullable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.wood.oreWars.R
import com.wood.oreWars.backend.item.Item
import com.wood.oreWars.backend.ore.Ore
import com.wood.oreWars.backend.ore.RedStone
import kotlin.contracts.ExperimentalContracts

open class Block (
    /** 内容物字段，初始值为传入的参数，为一个Ore对象*/
    var contentOre: Ore? = RedStone(),
    var contentItem: MutableList<Item> = mutableListOf(),
    val isLand: Boolean = true
){

    fun addItem(item: Item){
        contentItem += item
    }

    @Composable
    fun Composable(modifier: Modifier = Modifier.fillMaxSize()){
        if (contentOre != null) {
            contentOre!!.Composable(modifier)
        }else{
            Image(
                painter = painterResource(id = R.drawable.stone),
                contentDescription = "stone",
                modifier = modifier
            )
        }
    }
}
