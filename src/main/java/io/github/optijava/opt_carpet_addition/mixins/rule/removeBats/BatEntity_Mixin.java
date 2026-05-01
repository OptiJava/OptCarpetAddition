package io.github.optijava.opt_carpet_addition.mixins.rule.removeBats;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
//? if > 1.21 {
import net.minecraft.world.entity.EntitySpawnReason;
//?} else {
/*import net.minecraft.world.entity.MobSpawnType;
*///?}
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
    //? if > 1.21 {
    private static void injectCanSpawn(EntityType<Bat> entityType, LevelAccessor levelAccessor, EntitySpawnReason entitySpawnReason, BlockPos blockPos, RandomSource randomSource, CallbackInfoReturnable<Boolean> cir) {
    //?} else {
     /*private static void injectCanSpawn(EntityType<Bat> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource, CallbackInfoReturnable<Boolean> cir) {
    *///?}
        if (OptCarpetSettings.removeBats) {
            cir.setReturnValue(false);
        }
    }
}
