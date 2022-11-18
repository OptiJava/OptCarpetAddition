# Opt Carpet Addition Mod

这是一个CapetMod的扩展模组，目标是添加一些没有太大改变原版游戏机制但优化玩家体验/~~降低腐竹血压~~的一些功能。

**本模组需要前置：`Carpet` `fabric-api`**

**使用前一定仔细阅读README.md！！！！**

## Rules

### commandTpToFakePlayer

是否允许玩家通过/player xxx tp 指令传送到假人

- 有些服务器需要玩家之间互相tp的功能，如果使用tpa模组，就没法tp到假人（因为假人不会同意tpa），所以加入了这个功能

- Default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

### commandTpHereFakePlayer

是否允许玩家通过/player xxx tphere 指令把假人传送到自己

- 有些服务器需要玩家之间互相tp的功能，如果使用tpa模组，就没法tphere假人（因为假人不会同意tpa），所以加入了这个功能

- Default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

### allowTpToRealPlayer

是否允许玩家通过/player xxx tp 指令传送到真人

- Default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

### allowTpHereRealPlayer

是否允许玩家通过/player xxx tphere 指令把真人传送到自己

- Default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

### fixXpLevelBug

修复在玩家跨越维度的时候经验值假归零的bug（在跨越维度时刷新玩家的经验条）

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `FixBug`

### forceFakePlayerGameMode

强制设定所有假人的游戏模式

只能强行设为生存(survival)、创造(creative)、冒险(adventure)，无法设为旁观者模式，因为在测试过程中强行设为旁观者模式会出现bug，目前不清楚具体原因

- Default value: `false`
- Acceptable value: `survival` `creative` `adventure`
- Categories: `Feature`

### enableTpPrefixBlacklist

_**感谢 @[_Water_Bucket_](https://github.com/Water-Buckets) 提供的灵感**_

开启假人Tp前缀黑名单功能
\
开启这个功能后，如果你要tp到的假人名字前缀在黑名单中，会阻止你tp到他并显示：You can't tp to this player because of tp
limit.
\
_比如你要tp到的假人叫做anti_mob，而黑名单中有一个anti，那你就不能tp到这个假人_
\
假人的黑名单可以使用/tpmanager指令编辑（这个指令具体的用法看后面）

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### enableTpPrefixWhitelist

开启假人Tp前缀白名单功能
\
开启这个功能后，如果你要tp到的假人名字前缀不在白名单中，会阻止你tp到他并显示：You can't tp to this player because of tp
limit.
\
_比如你要tp到的假人叫做anti_mob，而白名单中没有anti，那你就不能tp到这个假人_
\
假人的白名单可以使用/tpmanager指令编辑（这个指令具体的用法看后面）

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### enableTpHerePrefixBlacklist

开启假人Tphere前缀黑名单功能
\
开启这个功能后，如果你要tphere的假人名字的前缀在黑名单中，会阻止你tphere并显示：You can't tp to this player because of tp
limit.
\
_比如你要tphere的假人叫做anti_mob，而黑名单中有一个anti，那你就不能tphere这个假人_
\
假人的黑名单可以使用/tpmanager指令编辑（这个指令具体的用法看后面）

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### disabledEnderManPickupGoal

禁止小黑搬起方块~~在末地施工小黑老搬方块好烦qwq~~

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### disabledEnderManPlaceBlockGoal

禁止小黑放下方块

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### disabledLayEggs

禁止鸡下蛋

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### enableTpHerePrefixWhitelist

开启假人Tphere前缀白名单功能
\
开启这个功能后，如果你要tphere的假人名字前缀不在白名单中，会阻止你tphere并显示：You can't tp to this player because of tp
limit.
\
_比如你要tphere的假人叫做anti_mob，而白名单中有一个anti，那你就能tp到这个假人_
\
假人白名单可以使用/tpmanager指令编辑（这个指令具体的用法看后面）

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

## Command

### player xxx tp

- tp到一个人，可以使用`commandTpToFakePlayer` `allowTpToRealPlayer`规则管理此指令

### player xxx tphere

- 把一个人tp到自己，可以使用`commandTpHereFakePlayer` `allowTpHereRealPlayer`规则管理此指令

### list -advance

- 显示每一个玩家的详细信息，包括uuid，游戏模式等

### tpmanager

- tpmanager tp whitelist add xxx 将前缀xxx添加到tp白名单
- tpmanager tp blacklist add xxx 将前缀xxx添加到tp黑名单
- tpmanager tphere whitelist add xxx 将前缀xxx添加到tphere白名单
- tpmanager tphere blacklist add xxx 将前缀xxx添加到tphere黑名单
- remove就是移除后缀，以此类推  ~~我都说这么细了，应该都能理解吧awa~~