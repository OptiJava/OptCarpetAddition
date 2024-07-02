package io.github.optijava.opt_carpet_addition.mixins.rule.removeBats;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BatEntity.class)
public class BatEntity_Mixin {
    @Inject(
            method = "canSpawn",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void injectCanSpawn(EntityType<BatEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (OptCarpetSettings.removeBats) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
