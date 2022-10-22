package io.github.optijava.opt_carpet_addition.events;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class FixExperienceBug implements ServerEntityWorldChangeEvents.AfterPlayerChange {
    @Override
    public void afterChangeWorld(ServerPlayerEntity player, ServerWorld origin, ServerWorld destination) {
        if (OptCarpetSettings.fixXpLevelBug) {
            player.addExperience(0);
            // refresh xp level
        }
    }
}
