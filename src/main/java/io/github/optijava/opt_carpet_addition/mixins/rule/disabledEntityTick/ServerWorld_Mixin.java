package io.github.optijava.opt_carpet_addition.mixins.rule.disabledEntityTick;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class ServerWorld_Mixin {
    /**
     * Mixin ServerWorld.tick(Ljava/util/function/BooleanSupplier;)V
     *
     * @author OptiJava
     * @reason rule: disabledEntityTick
     */
    @Inject(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;resetEmptyTime()V"),
            cancellable = true
    )
    public void injectTick(CallbackInfo ci) {
        if (OptCarpetSettings.disabledEntityTick) {
            ci.cancel();
        }
    }
}