package io.github.optijava.opt_carpet_addition.mixins.rule.forceLoadPets;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.McUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FollowOwnerGoal.class)
public class FollowOwnerGoal_Mixin {

    @Final
    @Shadow
    private TamableAnimal tamable;

    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    public void tick(CallbackInfo ci) {
        if (OptCarpetSettings.forceLoadPets && this.tamable.level() instanceof ServerLevel serverLevel) {
            if (ShouldTriggerImmediateLoadPets.triggerImmediateLoadPets || (McUtils.MINECRAFT_SERVER.getTickCount() % 35 == 0)) {
                serverLevel.getChunkSource().addTicketWithRadius(TicketType.ENDER_PEARL, this.tamable.chunkPosition(), 2);
            }
            ShouldTriggerImmediateLoadPets.triggerImmediateLoadPets = false;
        }
    }

    public static class ShouldTriggerImmediateLoadPets {
        public static boolean triggerImmediateLoadPets = false;
    }
}
