package io.github.optijava.opt_carpet_addition.events.fixExperienceBug;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
//? if > 1.21.11 {
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityLevelChangeEvents;
//?} else {
/*import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
*///?}
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

//? if > 1.21.11 {
public class FixExperienceBug implements ServerEntityLevelChangeEvents.AfterPlayerChange {
    @Override
    public void afterChangeLevel(ServerPlayer player, ServerLevel origin, ServerLevel destination) {
        if (OptCarpetSettings.fixXpLevelBug) {
            // refresh player's experience bar
            player.giveExperiencePoints(0);
        }
    }
}
//?} else {
/*public class FixExperienceBug implements ServerEntityWorldChangeEvents.AfterPlayerChange {
    @Override
    public void afterChangeWorld(ServerPlayer player, ServerLevel origin, ServerLevel destination) {
        if (OptCarpetSettings.fixXpLevelBug) {
            // refresh player's experience bar
            player.giveExperiencePoints(0);
        }
    }
}
*///?}
