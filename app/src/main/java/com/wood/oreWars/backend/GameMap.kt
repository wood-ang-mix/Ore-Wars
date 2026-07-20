package com.wood.oreWars.backend

class GameMap(val size: Int = 0){
    private val map = Array(size) { Array(size) { Block() } }
}