package com.wood.oreWars.backend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wood.oreWars.backend.item.Book
import com.wood.oreWars.backend.item.GoldApple
import com.wood.oreWars.backend.ore.Coal
import com.wood.oreWars.backend.ore.Copper
import com.wood.oreWars.backend.ore.Diamond
import com.wood.oreWars.backend.ore.Gold
import com.wood.oreWars.backend.ore.Lapis
import com.wood.oreWars.backend.ore.RedStone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val _gameState = MutableStateFlow(
        GameState(map = createDefaultMap())
    )
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private val actionChannel = Channel<Action>(Channel.UNLIMITED)

    init {
        startGameLoop()
    }

    fun sendAction(action: Action) {
        actionChannel.trySend(action)
    }

    // ═══════════════════════════════════════════
    //  GameLoop — 20 tick/秒
    // ═══════════════════════════════════════════

    private fun startGameLoop() {
        viewModelScope.launch(Dispatchers.Default) {
            while (isActive) {
                val frameStart = System.nanoTime()

                processActions()
                updateCooldowns()
                updateStatusEffects()

                val current = _gameState.value
                _gameState.value = current.copy(tickCount = current.tickCount + 1)

                val elapsed = (System.nanoTime() - frameStart) / 1_000_000L
                if (elapsed < 50L) delay(50L - elapsed)
            }
        }
    }

    // ── 动作处理 ──

    private fun processActions() {
        while (true) {
            val action = actionChannel.tryReceive().getOrNull() ?: break
            when (action) {
                is Action.Attack  -> handleAttack(action)
                is Action.Skill   -> handleSkill(action)
                is Action.UseItem -> handleItem(action)
            }
        }
    }

    private fun handleAttack(action: Action.Attack) {
        val map = _gameState.value.map
        val block = map[action.x, action.y]
        if (block.health <= 0) return
        block.health -= 10
        if (block.health <= 0) {
            block.health = 0
            block.ownerId = action.playerId
        }
    }

    private fun handleSkill(action: Action.Skill) {
        // TODO: 检查冷却 → 执行技能效果 → 设冷却
    }

    private fun handleItem(action: Action.UseItem) {
        // TODO: 检查物品库存 → 放置/使用
    }

    // ── 状态更新 ──

    private fun updateCooldowns() {
        // TODO: 遍历所有玩家，每个技能冷却减 1 tick
    }

    private fun updateStatusEffects() {
        val map = _gameState.value.map
        map.forEachBlock { _, _, block ->
            if (block.status == BlockStatus.Burning) {
                block.health -= 2
                if (block.health <= 0) {
                    block.health = 0
                    block.ownerId = null
                    block.status = BlockStatus.Normal
                }
            }
        }
    }

    // ── 地图初始化 ──

    private fun createDefaultMap(): GameMap {
        return GameMap(
            size = 23,
            map = Array(23) { y ->
                Array(23) { x ->
                    when (x) {
                        in 1..5 if y in 1..5   -> RedStone().Block()
                        in 6..10 if y in 1..5  -> Block(Lapis(), mutableListOf(Book(), GoldApple()))
                        in 13..17 if y in 1..5 -> Coal().Block()
                        in 8..12 if y in 8..12 -> Block(Gold(), mutableListOf(GoldApple()))
                        in 1..5 if y in 14..18 -> Copper().Block()
                        in 14..18 if y in 14..18 -> Diamond().Block()
                        else -> Block(contentOre = null)
                    }
                }
            }
        )
    }

    override fun onCleared() {
        actionChannel.close()
    }
}
