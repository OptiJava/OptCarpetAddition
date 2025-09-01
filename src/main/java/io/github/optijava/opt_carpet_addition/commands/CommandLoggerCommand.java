package io.github.optijava.opt_carpet_addition.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.optijava.opt_carpet_addition.utils.CommandLogger;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import static io.github.optijava.opt_carpet_addition.OptCarpetSettings.commandLogger;

public class CommandLoggerCommand {
    public static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> argumentBuilder = CommandManager.literal("commandlogger")
                //#if MC < 12004
                //$$.requires((player) -> carpet.settings.SettingsManager.canUseCommand(source, commandLogger))
                //#else
                //$$.requires((player) ->  carpet.utils.CommandHelper.canUseCommand(player, commandLogger))
                //#endif
                .then(
                        CommandManager.literal("reload")
                                .executes(CommandLoggerCommand::reload)
                );

        dispatcher.register(argumentBuilder);
    }

    public static int reload(CommandContext<ServerCommandSource> context) {
        return CommandLogger.reload();
    }
}