package io.github.optijava.opt_carpet_addition.mixins.logger.blockUpdate;

import carpet.CarpetServer;
import carpet.logging.LoggerRegistry;
import carpet.utils.Messenger;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(LoggerRegistry.class)
public class LoggerRegistry_Mixin {

    @Inject(
            method = "subscribePlayer",
            at = @At("HEAD"),
            remap = false
    )
    private static void injectSubscribePlayer(String playerName, String logName, String option, CallbackInfo ci) {
        if (Objects.equals(logName, "blockUpdate") && !OptCarpetSettings.allowBlockUpdateLogger) {
            try {
                ServerCommandSource commandSource = Objects.requireNonNull(CarpetServer.minecraft_server.getPlayerManager().getPlayer(playerName)).getCommandSource();
                Messenger.m(commandSource, "g Rule allowBlockUpdateLogger was disabled. You can't receive any block update message. Please contact operators.");
            } catch (Exception ignored) {
            }
        }
    }
}
