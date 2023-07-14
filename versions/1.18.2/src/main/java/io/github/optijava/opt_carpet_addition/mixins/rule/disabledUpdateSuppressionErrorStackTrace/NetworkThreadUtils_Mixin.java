package io.github.optijava.opt_carpet_addition.mixins.rule.disabledUpdateSuppressionErrorStackTrace;
//#if MC >= 11900
//$$
//#elseif MC >= 11800
import carpet.helpers.ThrowableSuppression;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.network.NetworkThreadUtils;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetworkThreadUtils.class)
public class NetworkThreadUtils_Mixin {

    @Redirect(
            method = "method_11072",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V",
                    remap = false
            )
    )
    private static void redirectError(Logger instance, String s, Object packet, Object exception) {
        if (!(OptCarpetSettings.disabledUpdateSuppressionErrorStackTrace && exception instanceof Exception e && (e instanceof ThrowableSuppression || e.getCause() instanceof ThrowableSuppression))) {
            instance.error("Failed to handle packet {}, suppressing error", packet, exception);
        } else {
            OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Update Suppression.");
        }
    }
}
//#endif
