# Opt Carpet Addition Mod

Language: [English](https://github.com/OptiJava/OptCarpetAddition/blob/master/README-en.md) | 中文

这是一个CarpetMod的扩展模组，目标是添加一些没有太大改变原版游戏机制但优化玩家体验/~~降低腐竹血压~~的一些功能。

使用[Preprocessor](https://github.com/ReplayMod/preprocessor)构建~~可供大家学习~~，目前支持`1.17.1` `1.18.2` `1.19.3` `1.20.1`四个版本，其他的大部分大版本相同小版本不同的版本也可直接安装使用

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

### disabledNetherPortalSpawn

禁止猪人生成在地狱门方块上

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### disabledEntityTick

**_注意！此规则可能会导致世界上所有的下落的方块实体无限回弹！！_**

此规则会跳过世界上一切实体的运算，不过玩家是正常的，开启此规则后，所有的生物全都会卡住，就算他们在空中也不会掉下来

此规则的目的是在服务器因为实体问题严重卡顿时，可以暂时缓解问题，但这不是最好的方案

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Experimental`

### disabledUpdateSuppressionErrorStackTrace

**_此功能仅限1.17和1.18版本_**

**_此功能必须搭配carpet规则updateSuppressionCrashFix使用，否则无效_**

此规则可以让所有非致命的因为更新抑制导致的异常不刷屏，只输出一条日志：Update Suppression.
\
必须搭配carpet规则updateSuppressionCrashFix规则使用，否则无效
\
1.16和1.19版本无此规则，因为1.16和1.19的carpet没有updateSuppressionCrashFix规则
\
致命异常会正常输出并打印异常栈（致命异常指在不开启updateSuppressionCrashFix情况下会导致服务器崩溃的异常）

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `BugFix`

### unescapeChatMessage  

**此规则仅客户端有效，服务端无效**

此规则会把客户端发出的所有应当转义的文字转义

例如在聊天栏输入：`你好，\u4e2d\u6587`
\
实际发送的消息是：`你好，中文`

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature` `CLIENT`

### optimizePlayerConnect

**_仅1.17及1.18有此功能_**

优化玩家进服逻辑

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Optimization` `Experimential`

### optimizeTeleport

**_仅1.17及1.18有此功能_**

优化玩家传送逻辑

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Optimization` `Experimential`

### commandLogger

这个规则可以记录每一个玩家执行的每一个指令

CommandLogger.json配置文件可以配置白名单、黑名单等，修改配置文件后应执行/commandlogger reload指令热重载

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Command` `Feature`

### dropperCrashFix

修复了方块实体替换导致投掷器激活时崩服的bug

详情请看[kygo_life的视频](https://www.bilibili.com/video/BV1HM411z7jz/?spm_id_from=333.999.0.0)

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `BugFix`

### optimizeFakePlayerSpawn

如果你的服务器连接mojang服务器的时间长，spawn假人的时候就会卡顿一会，此规则强制所有假人使用离线uuid，解决这一问题（但是也许引发新的问题

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Optimization`

### cceSuppressionCrashFix

防止cce更新抑制器导致服务器崩溃，详情请看[Void0的视频](https://www.bilibili.com/video/BV1Hu4y1o71G/)

**实验性规则**

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `BugFix` `Experimential`

### customItemDiscardTime

自定义物品消失时间

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

### crash

- 顾名思义  ~~别问我有什么用，我猜在切完光照更新抑制刷怪塔之后这个命令应该有点用~~
- 打完/crash之后需要再打/crash confirm才会崩服

## Logger

### disk

- 是HUD Logger，实时查看硬盘使用情况

### updateSuppression

**仅1.17和1.18**

- 记录每一次更新抑制

### cceSuppressionCrash
- CCE更新抑制器崩溃记录，类似于carpet的`updateSuppressedCrash`

## 模组兼容性

| 模组                     | 模组版本            | 兼容性  |
|------------------------|-----------------|------|
| carpet                 | 1.4.57-3613865  | 完全兼容 |
| c2me                   | 0.2.0+alpha.5.0 | 部分兼容 |
| lithium                | 0.7.5           | 完全兼容 |
| Carpet-TIS-Addition    | 1.43.0          | 完全兼容 |
| plusls-carpet-addition | 0.2.5           | 完全兼容 |
| sodium                 | 0.3.4           | 完全兼容 |
| starlight              | 1.0.0           | 完全兼容 |
