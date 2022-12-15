package io.github.optijava.opt_carpet_addition.mixins.disabledUpdateSuppressionErrorStackTrace;

import carpet.CarpetSettings;
import carpet.helpers.ThrowableSuppression;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.util.thread.ThreadExecutor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ThreadExecutor.class)
public class ThreadExecutor_Mixin {

    /**
     * Mixin ThreadExecutor.executeTask(Ljava/lang/Runnable;)V
     *
     * @author OptiJava
     * @reason rule: disabledUpdateSuppressionErrorStackTrace
     */
    @Redirect(method = "executeTask(Ljava/lang/Runnable;)V", at = @At(value = "INVOKE", target = "Ljava/lang/Runnable;run()V"))
    public void redirectRun(Runnable task) {
        if (OptCarpetSettings.disabledUpdateSuppressionErrorStackTrace) {
            try {
                task.run();
            } catch (ThrowableSuppression t) {
                CarpetSettings.LOG.info("[OptCarpetAddition] Update Suppression.");
            }
        } else {
            task.run();
        }
    }
}

