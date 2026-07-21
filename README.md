# Ore-Wars

*矿石大陆二创同人游戏*

基于抖音"矿石大陆"系列改编的即时战斗策略游戏。选择你的矿石阵营，在区块地图上争夺领土！

## 玩法

- **六国阵营**：红石、青金石、煤炭、金、铜、钻石六大矿石国家，每个国家拥有独特技能
- **即时战斗**：地图由 23×23 方形区块构成，玩家点击争夺区块归属权
- **阵营联动**：国家间有增益加成（如青金石为红石国提供附魔加成）
- **物品系统**：地图上可放置物品（书、金苹果），点击物品与点击区块触发不同交互
- **技能差异化**：红石国→TNT 爆炸 / 煤炭国→火攻燃烧 / 金国→金苹果恢复

## 技术栈

- Kotlin + Jetpack Compose
- Material 3 · Navigation Compose · 页面过渡动画
- WebView + HTML5 Canvas（地图渲染引擎）
- MVVM 架构（规划中）

## 进度

- [x] 地图引擎（WebView Canvas + Base64 贴图渲染）
- [x] 六国阵营系统（RedStone / Lapis / Coal / Gold / Copper / Diamond）
- [x] 区块点击交互 + Snackbar 调试面板
- [x] 物品系统（Item 基类 + Book + GoldApple）
- [x] ClickTarget 区分区块/物品点击
- [x] 页面路由与过渡动画（Home / 剧情模式 / 多人模式 / 设置）
- [x] 即时战斗架构设计文档（notes.md）
- [ ] 即时战斗系统实装（GameLoop + ActionQueue）
- [ ] 国家技能实装
- [ ] AI 对手
- [ ] 资源经济系统

**抖音交流群号**
```
962512662896
```

**抖音**
```
https://v.douyin.com/j6NuYekj72Q/
```
