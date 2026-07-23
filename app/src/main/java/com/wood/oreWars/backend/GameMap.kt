package com.wood.oreWars.backend

class GameMap(
    val size: Int = 0,
    val onClick: (ClickTarget) -> Unit = {},
    private val map: Array<Array<Block>> = Array(size) { Array(size) { Block() } }
){
    operator fun get(x: Int, y: Int): Block = map[y][x]

    /** 原地修改某个方块（避免全量替换数组） */
    fun updateBlock(x: Int, y: Int, transform: (Block) -> Unit) {
        transform(map[y][x])
    }

    /** 遍历所有方块 */
    fun forEachBlock(action: (x: Int, y: Int, block: Block) -> Unit) {
        for (y in 0 until size) {
            for (x in 0 until size) {
                action(x, y, map[y][x])
            }
        }
    }

    fun toJson(): String {
        val sb = StringBuilder()
        sb.append("[")
        for (y in 0 until size) {
            for (x in 0 until size) {
                if (x != 0 || y != 0) sb.append(",")
                val block = map[y][x]
                val oreName = block.contentOre?.toString()?.lowercase() ?: "stone"
                val itemsJson = block.contentItem.joinToString(",") { "\"${it.name}\"" }
                sb.append("{\"x\":$x,\"y\":$y,\"ore\":\"$oreName\",\"items\":[$itemsJson]}")
            }
        }
        sb.append("]")
        return sb.toString()
    }
}
