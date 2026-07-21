package com.wood.oreWars.backend

sealed class ClickTarget {
    data class Block(val x: Int, val y: Int) : ClickTarget()
    data class Item(val x: Int, val y: Int, val itemName: String) : ClickTarget()
}
