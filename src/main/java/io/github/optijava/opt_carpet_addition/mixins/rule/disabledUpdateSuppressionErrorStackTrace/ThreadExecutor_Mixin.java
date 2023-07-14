package io.github.optijava.opt_carpet_addition.mixins.rule.disabledUpdateSuppressionErrorStackTrace;
//#if MC < 11900
import carpet.helpers.ThrowableSuppression;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.util.thread.ThreadExecutor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ThreadExecutor.class)
public class ThreadExecutor_Mixin {

    /**
     * Mixin ThreadExecutor.executeTask(Ljava/lang/Runnable;)V
     *
     * @author OptiJava
     * @reason rule: disabledUpdateSuppressionErrorStackTrace
     */
    @Inject(
            method = "executeTask",
            at = @At(
                    value = "INVOKE",
                    //#if MC >= 11800
                    //$$ target = "Lorg/slf4j/Logger;error(Lorg/slf4j/Marker;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V",
                    //#else
                    target = "Lorg/apache/logging/log4j/Logger;fatal(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V",
                    //#endif
                    remap = false
            ),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public void injectExecuteTask(Runnable task, CallbackInfo ci, Exception exception) {
        if (OptCarpetSettings.disabledUpdateSuppressionErrorStackTrace && (exception instanceof ThrowableSuppression || exception.getCause() instanceof ThrowableSuppression)) {
            OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Update Suppression.");
            ci.cancel();
        }
    }
}
//#endif
