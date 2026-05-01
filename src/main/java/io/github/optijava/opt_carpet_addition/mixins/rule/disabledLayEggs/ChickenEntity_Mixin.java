package io.github.optijava.opt_carpet_addition.mixins.rule.disabledLayEggs;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
//? if >= 1.21.11 {
import net.minecraft.world.entity.animal.chicken.Chicken;
//?} else {
/*import net.minecraft.world.entity.animal.Chicken;
*///?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chicken.class)
public class ChickenEntity_Mixin {

    /**
     * Mixin ChickenEntity.tickMovement()V
     *
     * @author OptiJava
     * @reason rule: disabledLayEggs
     */
    @Inject(
            at = @At("HEAD"),
            method = "aiStep"
    )
    public void injectTickMovement(CallbackInfo callbackInfo) {
        if (OptCarpetSettings.disabledLayEggs) {
            ((Chicken) (Object) this).eggTime = 50;
        }
    }
}
