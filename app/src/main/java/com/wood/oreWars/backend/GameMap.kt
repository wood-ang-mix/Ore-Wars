package com.wood.oreWars.backend

class GameMap(
    val size: Int = 0,
    val onClick: (Int, Int) -> Unit = { _, _ -> },
    private val map: Array<Array<Block>> = Array(size) { Array(size) { Block() } }
){
    operator fun get(x: Int, y: Int): Block = map[y][x]

    fun toJson(): String {
        val sb = StringBuilder()
        sb.append("[")
        for (y in 0 until size) {
            for (x in 0 until size) {
                if (x != 0 || y != 0) sb.append(",")
                val block = map[y][x]
                val oreName = block.contentOre?.toString()?.lowercase() ?: "stone"
                sb.append("{\"x\":$x,\"y\":$y,\"ore\":\"$oreName\"}")
            }
        }
        sb.append("]")
        return sb.toString()
    }
}
