package io.github.optijava.opt_carpet_addition.mixins.rule.commandLogger;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CommandManager.class)
public class CommandManager_Mixin {

    private static final Logger LOGGER = LogManager.getLogger("OCA Command Logger");

    @Inject(
            method = "execute",
            at = @At("HEAD")
    )
    public void injectExecute(ServerCommandSource commandSource, String command, CallbackInfoReturnable<Integer> cir) {
        if (!OptCarpetSettings.commandLoggerConfigBean.logAllCommand && OptCarpetSettings.commandLogger) {
            if (OptCarpetSettings.commandLoggerConfigBean.LogCommandWhitelist.contains(command)) {
                CommandManager_Mixin.LOGGER.info("[OCA Command Logger] %s submit command: %s".formatted(commandSource.getName(), command));
                return;
            }

            for (String var1 : OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixWhitelist) {
                if (command.startsWith(var1)) {
                    CommandManager_Mixin.LOGGER.info("[OCA Command Logger] %s submit command: %s".formatted(commandSource.getName(), command));
                    return;
                }
            }

            if (OptCarpetSettings.commandLoggerConfigBean.LogCommandWhitelist.isEmpty() && OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixWhitelist.isEmpty()) {
                if (OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixBlacklist.isEmpty() && OptCarpetSettings.commandLoggerConfigBean.LogCommandBlacklist.isEmpty()) {
                    CommandManager_Mixin.LOGGER.info("[OCA Command Logger] %s submit command: %s".formatted(commandSource.getName(), command));
                    return;
                }

                if (OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixBlacklist.isEmpty() && OptCarpetSettings.commandLoggerConfigBean.LogCommandBlacklist.contains(command)) {
                    return;
                }

                for (String var2 : OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixBlacklist) {
                    if (command.startsWith(var2)) {
                        return;
                    }
                }
                CommandManager_Mixin.LOGGER.info("[OCA Command Logger] %s submit command: %s".formatted(commandSource.getName(), command));
            }
        } else if (OptCarpetSettings.commandLoggerConfigBean.logAllCommand && OptCarpetSettings.commandLogger) {
            CommandManager_Mixin.LOGGER.info("[OCA Command Logger] %s submit command: %s".formatted(commandSource.getName(), command));
        }
    }
}