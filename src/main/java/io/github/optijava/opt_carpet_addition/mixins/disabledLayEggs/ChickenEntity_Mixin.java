package io.github.optijava.opt_carpet_addition.mixins.disabledLayEggs;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.entity.passive.ChickenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(ChickenEntity.class)
public class ChickenEntity_Mixin {

    /**
     * Mixin ChickenEntity.tickMovement()V
     *
     * @author OptiJava
     * @reason rule: disabledLayEggs
     */
    @Inject(at = @At("HEAD"), method = "tickMovement()V")
    public void injectTickMovement() {
        if (OptCarpetSettings.disabledLayEggs) {
            ((ChickenEntity) (Object) this).eggLayTime = 50;
        }
    }
}
