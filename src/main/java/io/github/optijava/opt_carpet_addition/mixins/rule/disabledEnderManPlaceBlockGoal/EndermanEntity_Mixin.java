package io.github.optijava.opt_carpet_addition.mixins.rule.disabledEnderManPlaceBlockGoal;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.world.entity.monster.EnderMan;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderMan.EndermanLeaveBlockGoal.class)
public class EndermanEntity_Mixin {
    /**
     * Mixin EndermanEntity.PlaceBlockGoal.tick()V
     *
     * @author OptiJava
     * @reason rule: disabledEnderManPlaceBlockGoal
     */
    @Inject(
            method = "tick",
            at = @At("HEAD"),
            cancellable = true
    )
    public void injectTick(CallbackInfo ci) {
        if (OptCarpetSettings.disabledEnderManPlaceBlockGoal) {
            ci.cancel();
        }
    }
}
