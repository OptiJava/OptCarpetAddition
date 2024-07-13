package io.github.optijava.opt_carpet_addition.mixins.rule.async.optimizePlayerConnect;
//#if MC < 11900
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.threading.Threading;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLoginNetworkHandler.class)
public abstract class ServerLoginNetworkHandler_Mixin {

    /**
     * 此优化灵感来源于模组 Very Many Player
     * <p>
     * Mixin ServerLoginNetworkHandler.addToServer(Lnet/minecraft/server/network/ServerPlayerEntity;)V
     *
     * @author OptiJava
     * @reason rule: optimizePlayerConnect
     */
    @Inject(
            method = "addToServer(Lnet/minecraft/server/network/ServerPlayerEntity;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void injectAddToServer(ServerPlayerEntity player, CallbackInfo ci) {
        if (OptCarpetSettings.optimizePlayerConnect) {
            Threading.THREAD_POOL.submit(() -> {
                OptCarpetAddition.LOGGER.info("[OptCarpetAddition] PlayerConnectThread started. Thread name: {}", Thread.currentThread().getName());
                OptCarpetAddition.LOGGER.info("[OptCarpetAddition] {} is trying to join the server...", player.getGameProfile().getName());
                try {
                    ((ServerLoginNetworkHandler) (Object) this).server.getPlayerManager().onPlayerConnect(((ServerLoginNetworkHandler) (Object) this).connection, player);
                    OptCarpetAddition.LOGGER.info("[OptCarpetAddition] {} joined successfully.", player.getGameProfile().getName());
                } catch (Throwable t) {
                    OptCarpetAddition.LOGGER.error("[OptCarpetAddition] Unexpected exception when player %s joined the game.".formatted(player.getGameProfile().getName()), t);
                }
            });
            ci.cancel();
        }
    }
}
//#endif
