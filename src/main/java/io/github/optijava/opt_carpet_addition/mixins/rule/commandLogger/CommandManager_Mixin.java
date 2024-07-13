package io.github.optijava.opt_carpet_addition.mixins.rule.commandLogger;

//#if MC >= 11900
//$$ import com.mojang.brigadier.ParseResults;
//#endif

//#if MC >= 12000
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//#endif
import carpet.CarpetServer;
import carpet.utils.Messenger;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
//#if MC <= 12001
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//#endif

@Mixin(CommandManager.class)
public class CommandManager_Mixin {

    @Unique
    private static final Logger LOGGER = LogManager.getLogger("OCA Command Logger");

    @Inject(
            method = "execute",
            at = @At("HEAD")
    )
    //#if MC >= 12004
    //$$ public void injectExecute(ParseResults<ServerCommandSource> parseResults, String command, CallbackInfo ci) {
    //#elseif MC >= 12000
    //$$ public void injectExecute(ParseResults<ServerCommandSource> parseResults, String command, CallbackInfoReturnable<Integer> cir) {
    //#elseif MC >= 11900
    //$$ public void injectExecute(ParseResults<ServerCommandSource> parseResults, String command, CallbackInfoReturnable<Integer> cir) {
    //#else
    public void injectExecute(ServerCommandSource commandSource, String command, CallbackInfoReturnable<Integer> cir) {
    //#endif
        //#if MC >= 11900
        //$$ ServerCommandSource commandSource = parseResults.getContext().getSource();
        //#endif

        if (!OptCarpetSettings.commandLoggerConfigBean.logAllCommand && OptCarpetSettings.commandLogger) {
            if (OptCarpetSettings.commandLoggerConfigBean.LogCommandWhitelist.contains(command)) {
                logCommand(command, commandSource);
                return;
            }

            for (String var1 : OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixWhitelist) {
                if (command.startsWith(var1)) {
                    logCommand(command, commandSource);
                    return;
                }
            }

            if (OptCarpetSettings.commandLoggerConfigBean.LogCommandWhitelist.isEmpty() && OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixWhitelist.isEmpty()) {
                if (OptCarpetSettings.commandLoggerConfigBean.LogCommandPrefixBlacklist.isEmpty() && OptCarpetSettings.commandLoggerConfigBean.LogCommandBlacklist.isEmpty()) {
                    logCommand(command, commandSource);
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
                logCommand(command, commandSource);
            }
        } else if (OptCarpetSettings.commandLoggerConfigBean.logAllCommand && OptCarpetSettings.commandLogger) {
            logCommand(command, commandSource);
        }
    }

    @Unique
    private void logCommand(String command, ServerCommandSource commandSource){
        CommandManager_Mixin.LOGGER.info("[OCA Command Logger] %s submit command: %s".formatted(commandSource.getName(), command));

        if (OptCarpetSettings.commandLoggerBroadcastToPlayer.equals("true")) {
            Messenger.print_server_message(CarpetServer.minecraft_server, Messenger.c(
                        "gi [",
                            "li " + commandSource.getName(),
                            "gi : " + command + "]"
                    ));
        } else if (OptCarpetSettings.commandLoggerBroadcastToPlayer.equals("ops")) {
            for (ServerPlayerEntity serverPlayerEntity : CarpetServer.minecraft_server.getPlayerManager().getPlayerList()) {
                if (!CarpetServer.minecraft_server.getPlayerManager().isOperator(serverPlayerEntity.getGameProfile())) {
                    continue;
                }
                Messenger.m(serverPlayerEntity, Messenger.c(
                        "gi [",
                            "li " + commandSource.getName(),
                            "gi : " + command + "]"
                    ));
            }
        }
    }
}
