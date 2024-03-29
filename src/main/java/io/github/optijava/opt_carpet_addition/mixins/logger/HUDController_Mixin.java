package io.github.optijava.opt_carpet_addition.mixins.logger;

import carpet.logging.HUDController;
import io.github.optijava.opt_carpet_addition.logger.disk.DiskHUDLogger;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(HUDController.class)
public abstract class HUDController_Mixin {
    @Inject(
            method = "update_hud",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/Map;keySet()Ljava/util/Set;"),
            remap = false
    )
    private static void injectUpdateHUD(MinecraftServer server, List<ServerPlayerEntity> force, CallbackInfo ci) {
        DiskHUDLogger.INSTANCE.updateHUD(server);
    }
}
