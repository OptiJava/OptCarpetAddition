package io.github.optijava.opt_carpet_addition.mixins.disabledNetherPortalSpawn;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.block.NetherPortalBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlock_Mixin {
    /**
     * Mixin NetherPortalBlock.randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V
     *
     * @author OptiJava
     * @reason rule: disabledNetherPortalSpawn
     */
    @Inject(
            method = "randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void randomTick(CallbackInfo ci) {
        if (OptCarpetSettings.disabledNetherPortalSpawn) {
            ci.cancel();
        }
    }
}
