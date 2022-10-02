# Opt Carpet Addition Mod

这是一个CapetMod的扩展模组，目标是添加一些没有太大改变原版游戏机制但优化玩家体验/优化性能/~~降低腐竹血压~~的一些功能。

## Rules

### commandTpToFakePlayer

是否允许玩家通过/player xxx tp 指令传送到假人
- 有些服务器需要玩家之间互相tp的功能，如果使用tpa模组，就没法tp到假人（因为假人不会同意tpa），所以加入了这个功能

- default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

### commandTpHereFakePlayer

是否允许玩家通过/player xxx tphere 指令把假人传送到自己
- 有些服务器需要玩家之间互相tp的功能，如果使用tpa模组，就没法tphere假人（因为假人不会同意tpa），所以加入了这个功能

- default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

### allowTpToRealPlayer

是否允许玩家通过/player xxx tp 指令传送到真人

- default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

### allowTpHereRealPlayer

是否允许玩家通过/player xxx tphere 指令把真人传送到自己

- default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

## Command

### player xxx tp
- tp到一个人，可以使用`commandTpToFakePlayer``allowTpToRealPlayer`规则管理此指令

### player xxx tphere
- 把一个人tp到自己，可以使用`commandTpHereFakePlayer``allowTpHereRealPlayer`规则管理此指令