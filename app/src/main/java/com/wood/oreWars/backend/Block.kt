package com.wood.oreWars.backend

import com.wood.oreWars.backend.item.Item
import com.wood.oreWars.backend.ore.Ore
import com.wood.oreWars.backend.ore.RedStone

enum class BlockStatus { Normal, Burning }

open class Block (
    /** 内容物字段，初始值为传入的参数，为一个Ore对象*/
    var contentOre: Ore? = RedStone(),
    var contentItem: MutableList<Item> = mutableListOf(),
    val isLand: Boolean = true
){
    /** 归属玩家 id，null 表示中立 */
    var ownerId: String? = null
    /** 方块最大生命值，由矿石类型决定 */
    val maxHealth: Int = contentOre?.maxHealth ?: 50
    /** 当前生命值 */
    var health: Int = maxHealth
    /** 方块异常状态 */
    var status: BlockStatus = BlockStatus.Normal

    fun addItem(item: Item){
        contentItem += item
    }
}
