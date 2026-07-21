package com.wood.oreWars.backend

import com.wood.oreWars.backend.item.Item
import com.wood.oreWars.backend.ore.Ore
import com.wood.oreWars.backend.ore.RedStone

open class Block (
    /** 内容物字段，初始值为传入的参数，为一个Ore对象*/
    var contentOre: Ore? = RedStone(),
    var contentItem: MutableList<Item> = mutableListOf(),
    val isLand: Boolean = true
){

    fun addItem(item: Item){
        contentItem += item
    }
}
