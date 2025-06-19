package io.github.optijava.opt_carpet_addition.mixins.rule.cceSuppressionCrashFix;

//#if MC < 12100
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.logger.cceSuppressionCrashLogger.CCESuppressionCrashLogger;
import io.github.optijava.opt_carpet_addition.utils.exceptions.ThrowableCCESuppression;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServer_Mixin {

    @Inject(
            method = {"tickWorlds"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/crash/CrashReport;create(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/util/crash/CrashReport;"
            )},
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    public void injectTickWorlds(BooleanSupplier shouldKeepTicking, CallbackInfo ci, @SuppressWarnings("all") Iterator var2, ServerWorld serverWorld, Throwable throwable) {
        if (OptCarpetSettings.cceSuppressionCrashFix && (throwable instanceof ThrowableCCESuppression || throwable.getCause() instanceof ThrowableCCESuppression)) {
            try {
                if (throwable instanceof ThrowableCCESuppression) {
                    CCESuppressionCrashLogger.INSTANCE.logUpdateSuppression(((ThrowableCCESuppression) throwable).pos);
                } else {
                    CCESuppressionCrashLogger.INSTANCE.logUpdateSuppression(((ThrowableCCESuppression) throwable.getCause()).pos);
                }
            } catch (Exception e){
                OptCarpetAddition.LOGGER.error("Exception when handle CCE in MinecraftServer.tickWorlds(...)", e);
            }

            ci.cancel();
        }
    }
}
//#endif
