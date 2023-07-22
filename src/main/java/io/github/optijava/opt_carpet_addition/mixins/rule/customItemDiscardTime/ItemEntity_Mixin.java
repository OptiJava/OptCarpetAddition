package io.github.optijava.opt_carpet_addition.mixins.rule.customItemDiscardTime;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.entity.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemEntity.class)
public class ItemEntity_Mixin {

    @ModifyConstant(
            method = "tick",
            constant = @Constant(intValue = 6000)
    )
    public int modifyConstantItemAge(int discardAgeTicks) {
        return OptCarpetSettings.customItemDiscardTime >= 0 ? OptCarpetSettings.customItemDiscardTime : discardAgeTicks;
    }
}
