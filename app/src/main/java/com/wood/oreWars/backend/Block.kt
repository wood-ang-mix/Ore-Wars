package com.wood.oreWars.backend

import androidx.annotation.Nullable
import androidx.compose.runtime.Composable
import com.wood.oreWars.backend.item.Item
import com.wood.oreWars.backend.ore.Ore
import com.wood.oreWars.backend.ore.RedStone

open class Block (
    contentOre: Ore? = RedStone(),
    contentItem: MutableList<Item> = mutableListOf(),
    val isLand: Boolean = true
){

    /** 内容物字段，初始值为传入的参数，为一个Ore对象*/
    protected var contentOre: Ore? = contentOre
        get() = field
        set(value) {
            field = value
        }

    protected var contentItem = contentItem
        get() = field
        set(value) {
            field = value
        }

    fun addItem(item: Item){
        contentItem += item
    }

    @Composable
    fun Composable(){
        if (contentOre) {
            contentOre.Composable()
        }else{

        }
    }
}