package io.github.optijava.opt_carpet_addition.mixins.async;

import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.utils.threading.Threading;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
}
