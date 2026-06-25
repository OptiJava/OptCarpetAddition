package io.github.optijava.opt_carpet_addition.mixins.rule.commandLogger;

import carpet.CarpetServer;
import carpet.utils.Messenger;
import com.mojang.brigadier.ParseResults;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.McUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Commands.class)
public class CommandManager_Mixin {

    @Unique
    private static final Logger LOGGER = LogManager.getLogger("OCA Command Logger");

    @Inject(
            method = "performCommand",
            at = @At("HEAD")
    )
    public void injectExecute(ParseResults<CommandSourceStack> parseResults, String command, CallbackInfo ci) {
        CommandSourceStack commandSource = parseResults.getContext().getSource();

        if (!OptCarpetSettings.commandLoggerConfigBean.logAllCommand && OptCarpetSettings.commandLogger) {
            if (OptCarpetSettings.commandLoggerConfigBean.LogCommandWhitelist.contains(command)) {
                logCommand(command, commandSource);
                return;
            }

            if (OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixWhitelist.stream().anyMatch(command::startsWith)) {
                logCommand(command, commandSource);
                return;
            }

            if (OptCarpetSettings.commandLoggerConfigBean.LogCommandWhitelist.isEmpty() && OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixWhitelist.isEmpty()) {
                if (OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixBlacklist.isEmpty() && OptCarpetSettings.commandLoggerConfigBean.LogCommandBlacklist.isEmpty()) {
                    logCommand(command, commandSource);
                    return;
                }

                if (OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixBlacklist.isEmpty() && OptCarpetSettings.commandLoggerConfigBean.LogCommandBlacklist.contains(command)) return;
                if (OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixBlacklist.stream().anyMatch(command::startsWith)) return;
                logCommand(command, commandSource);
            }
        } else if (OptCarpetSettings.commandLoggerConfigBean.logAllCommand && OptCarpetSettings.commandLogger) {
            logCommand(command, commandSource);
        }
    }

    @Unique
    private void logCommand(String command, CommandSourceStack commandSource){
        CommandManager_Mixin.LOGGER.info("[OCA Command Logger] {} submit command: {}", commandSource.getTextName(), command);

        if (OptCarpetSettings.commandLoggerBroadcastToPlayer.equals("true")) {
            Messenger.print_server_message(CarpetServer.minecraft_server, Messenger.c(
                    "gi [",
                    "li " + commandSource.getTextName(),
                    "gi : " + command + "]"
            ));
        } else if (OptCarpetSettings.commandLoggerBroadcastToPlayer.equals("ops")) {
            CarpetServer.minecraft_server.getPlayerList().getPlayers().forEach(serverPlayerEntity -> {
                if (McUtils.isOp(serverPlayerEntity.getGameProfile())) {
                    Messenger.m(serverPlayerEntity, Messenger.c(
                            "gi [",
                            "li " + commandSource.getTextName(),
                            "gi : " + command + "]"
                    ));
                }
            });
        }
    }
}
