package io.github.optijava.opt_carpet_addition.mixins.rule.async;
//#if MC < 11900
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.threading.Threading;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServer_Mixin {

    @Inject(
            method = "shutdown()V",
            at = @At("HEAD")
    )
    public void injectShutdown(CallbackInfo ci) {
        OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Closing thread pool...");
        Threading.THREAD_POOL.shutdown();
        OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Thread pool closed successfully!");
    }

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
        if (OptCarpetSettings.optimizePlayerConnect && (throwable instanceof ConcurrentModificationException)) {
            OptCarpetAddition.LOGGER.fatal("[OptCarpetAddition] Unexpected exception when ticking world: {}", serverWorld.toString(), throwable);
            OptCarpetAddition.LOGGER.fatal("[OptCarpetAddition] If you see this message, you may need to restart the server. This exception may cause by OptCarpetAddition, if you think so, please send the reports to GitHub Issues.");
            OptCarpetAddition.LOGGER.fatal("[OptCarpetAddition] I'm sorry to hear that.");
            ci.cancel();
        }
    }
}
//#endif
