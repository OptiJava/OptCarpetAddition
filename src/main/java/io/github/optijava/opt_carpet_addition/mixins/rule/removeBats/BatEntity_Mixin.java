package io.github.optijava.opt_carpet_addition.mixins.rule.removeBats;

import carpet.script.api.WorldAccess;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Bat.class)
public class BatEntity_Mixin {
    @Inject(
            method = "checkBatSpawnRules",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void injectCanSpawn(EntityType<Bat> entityType, LevelAccessor levelAccessor, EntitySpawnReason entitySpawnReason, BlockPos blockPos, RandomSource randomSource, CallbackInfoReturnable<Boolean> cir) {
        if (OptCarpetSettings.removeBats) {
            cir.setReturnValue(false);
        }
    }
}
