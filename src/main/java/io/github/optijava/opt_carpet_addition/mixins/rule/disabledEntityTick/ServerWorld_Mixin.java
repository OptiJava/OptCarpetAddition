package io.github.optijava.opt_carpet_addition.mixins.rule.disabledEntityTick;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorld_Mixin {
    /**
     * Mixin ServerWorld.tick(Ljava/util/function/BooleanSupplier;)V
     *
     * @author OptiJava
     * @reason rule: disabledEntityTick
     */
    @Inject(method = "tick(Ljava/util/function/BooleanSupplier;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;resetIdleTimeout()V"), cancellable = true)
    public void injectTick(CallbackInfo ci) {
        if (OptCarpetSettings.disabledEntityTick) {
            ci.cancel();
        }
    }
}