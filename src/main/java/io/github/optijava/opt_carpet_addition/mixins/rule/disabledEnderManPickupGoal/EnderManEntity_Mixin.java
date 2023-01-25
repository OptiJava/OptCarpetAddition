package io.github.optijava.opt_carpet_addition.mixins.rule.disabledEnderManPickupGoal;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndermanEntity.PickUpBlockGoal.class)
public class EnderManEntity_Mixin {
    /**
     * Mixin EndermanEntity.PickUpBlockGoal.tick()V
     *
     * @author OptiJava
     * @reason rule: disabledEnderManPickupGoal
     */
    @Inject(
            method = "tick()V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void injectTick(CallbackInfo ci) {
        if (OptCarpetSettings.disabledEnderManPickupGoal) {
            ci.cancel();
        }
    }
}
