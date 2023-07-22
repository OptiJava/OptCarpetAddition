package io.github.optijava.opt_carpet_addition.mixins.rule.cceSuppressionCrashFix;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.exceptions.ThrowableCCESuppression;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBoxBlock.class)
public class ShulkerBoxBlock_Mixin {

    @Inject(
            method = "getComparatorOutput",
            at = @At("HEAD"),
            cancellable = true
    )
    public void injectGetComparatorOutput(BlockState state, World world, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (OptCarpetSettings.cceSuppressionCrashFix) {
            int var1;
            try {
                var1 = ScreenHandler.calculateComparatorOutput((Inventory)((Object)world.getBlockEntity(pos)));
            } catch (ClassCastException e) {
                throw new ThrowableCCESuppression(e.getMessage(), pos, e);
            }
            cir.setReturnValue(var1);
        } else {
            cir.setReturnValue(ScreenHandler.calculateComparatorOutput((Inventory)((Object)world.getBlockEntity(pos))));
        }
    }
}
