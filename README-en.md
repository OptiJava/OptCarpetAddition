# Opt Carpet Addition Mod

Language: English | [中文](https://github.com/OptiJava/OptCarpetAddition/blob/master/README.md)

> This is an extension module of `Carpet`. The goal is to add some functions that do not greatly change the original game
mechanism but optimize the player experience~~reduce the blood pressure of server owner~~.

Build with [Preprocessor](https://github.com/ReplayMod/preprocessor)

**Dependencies:`Carpet` `fabric-api`**

## Support Status

Most other MC versions that are the same as the supported major versions but have different minor versions can be directly installed and work properly, but **the stability cannot be guaranteed. _Please use them with caution_**

| Minecraft Version | Support Status                      |
|-------------------|-------------------------------------|
| 1.20.1            | Supporting, developing actively     |
| 1.19.3            | Supporting, developing actively     |
| 1.18.2            | Supporting, developing actively     |
| 1.17.1            | Supporting, developing actively     |
| 1.16.5            | Not supporting, development stopped |

## Rules

### commandTpToFakePlayer

Enable '/player xxx tp' command to teleport to that fake player.

- Default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

### commandTpHereFakePlayer

Enable '/player xxx tphere' command to teleport that fake player to me.

- Default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

### allowTpToRealPlayer

Enable '/player xxx tp' command to teleport to that real player.

- Default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

### allowTpHereRealPlayer

Enable '/player xxx tphere' command to teleport that real player to me.

- Default value: `false`
- Acceptable value: `true` `false` `ops`
- Categories: `Command`

### fixXpLevelBug

Fix bug: xp level return to zero when player change dimension

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `FixBug`

### forceFakePlayerGameMode

Force gamemode for every fake player.

**You can't force gamemode to 'spectator'**

- Default value: `false`
- Acceptable value: `survival` `creative` `adventure`
- Categories: `Feature`

### enableTpPrefixBlacklist

_**@[_Water_Bucket_](https://github.com/Water-Buckets) provided this great idea!**_

Enable fake player Tp prefix blacklist
\
After this function is enabled, if the fake player name prefix you want to tp to is in the blacklist, it will prevent
you from tp to him and display:You can't tp to this player because of tp
limit.
\
_For example: You want to tp to fake player: anti_mob,but 'anti' is in blacklist,so you can't tp to it_
\
Fake player prefix blacklist can modify by command:/tpmanager

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### enableTpPrefixWhitelist

Enable fake player tp prefix whitelist

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### enableTpHerePrefixBlacklist

Enable fake player Tphere prefix blacklist

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### enableTpHerePrefixWhitelist

Enable fake player Tp prefix whitelist

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### disabledEnderManPickupGoal

Enderman will not pick up any block

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### disabledEnderManPlaceBlockGoal

Enderman will not place any block

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### disabledLayEggs

Chicken will not lay eggs

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### disabledEntityTick

**_Warning! This rule may cause all falling_block entity infinite rebound!_**

This rule will ignore all entity tick,but players are normal.

When there are a lot of entity in your server,you can use this rule temporarily relieve the stuck.

_This rule is experimental._

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Experimental`

### disabledNetherPortalSpawn

Zombified piglin will not spawn in nether portal.

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

### disabledEntityTick

**_Warning! This rule may cause all falling_block entity infinite rebound!_**

This rule will ignore all entity tick,but players are normal.

When there are a lot of entity in your server,you can use this rule temporarily relieve the stuck.

_This rule is experimental._

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Experimental`

### disabledUpdateSuppressionErrorStackTrace

**_This rule is just in 1.17 and 1.18_**

**_This function must be used together with the carpet rule updateSuppressionCrashFix, otherwise it is invalid_**

This rule allows all non-fatal exceptions caused by update suppression not to be refreshed, and only one log is output:
Update Suppression
\
1.16 and 1.19 hasn't this rule，because carpet-1.16&1.19 has not updateSuppressionCrashFix rule.
\
Fatal Exception will be printed normally（Fatal Exceptions mean the exception that will cause the server to crash when
updateSuppressionCrashFix is not enabled）

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `BugFix`

### unescapeChatMessage

**This rule is valid only on the client side, and invalid on the server side**

This rule will escape all text sent by the client that should be escaped.

For example,type `你好，\u4e2d\u6587` in chat
\
And you will see `你好，中文` in chat.

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature` `CLIENT`

### optimizePlayerConnect

**_This rule is just in 1.17 and 1.18_**

Optimize player login.

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Optimization` `Experimential`

### optimizeTeleport

**_This rule is just in 1.17 and 1.18_**

Optimize player teleport.

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Optimization` `Experimential`

### commandLogger

Command Logger can records command execute events.

CommandLogger.json is the config file, type: `/commandlogger reload` to reload the config file.

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Command` `Feature`

### dropperCrashFix

Fix bug: dropper may cause server crash

Please watch [kygo_life's bilibili video](https://www.bilibili.com/video/BV1HM411z7jz/?spm_id_from=333.999.0.0)

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `BugFix`

### optimizeFakePlayerSpawn

Fix bug: lagging when spawn bot because mojang server is too slow :(

This rule force any bot use offline uuid.

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Optimization`

### cceSuppressionCrashFix

Prevent CCE Suppression Crashes，watch [Void0's bilibili video](https://www.bilibili.com/video/BV1Hu4y1o71G/) for more infomation.

**Experimential rule**

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `BugFix` `Experimential`

### customItemDiscardTime

Custom item discard time. :)

- Default value: `false`
- Acceptable value: `true` `false`
- Categories: `Feature`

## Command

### player xxx tp

- tp to a player. `commandTpToFakePlayer` `allowTpToRealPlayer` can manage this command

### player xxx tphere

- teleport here a player. `commandTpHereFakePlayer` `allowTpHereRealPlayer` can manage this command

### list -advance

- display detail of every player

### tpmanager

- 'tpmanager tp whitelist add xxx' add prefix xxx to tp whitelist
- 'tpmanager tp blacklist add xxx' add prefix xxx to tp blacklist
- 'tpmanager tphere whitelist add xxx' add prefix xxx to tphere whitelist
- 'tpmanager tphere blacklist add xxx' add prefix xxx to tphere blacklist
- 'tpmanager tp/tphere blacklist/whitelist remove' remove a prefix

### crash

- crash!
- `/crash confirm` to confirm crash

## Logger

### disk

- A HUD logger，show disk usage in real time

### updateSuppression

**_This logger is just in 1.17 and 1.18_**

- Log every update suppression

### cceSuppressionCrash

- CCE suppressed crashes logger，is simular to carpet rule `updateSuppressedCrash`
