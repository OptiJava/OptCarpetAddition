package io.github.optijava.opt_carpet_addition.mixins.rule.fixPlayerPositionNaN;

import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class Entity_Mixin {

    @Inject(
            method = "setPosRaw",
            at = @At("HEAD"),
            cancellable = true
    )
    public void injectTick(final double x, final double y, final double z, CallbackInfo ci) {
        if (OptCarpetSettings.fixPlayerPositionNaN) {
            Entity ths = (Entity)(Object)this;

            if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
                OptCarpetAddition.LOGGER.error("Detect NaN position in Entity " + ths.getStringUUID(), new NumberFormatException());
                ci.cancel();
            }
        }
    }
}
