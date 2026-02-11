package io.github.optijava.opt_carpet_addition.commands;

import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import net.minecraft.server.command.ServerCommandSource;
import static io.github.optijava.opt_carpet_addition.OptCarpetSettings.enableCrashCommand;

import static net.minecraft.server.command.CommandManager.literal;

public class CrashCommand {
    private CrashCommand() {
    }

    private static boolean isPreparing = false;

    public static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> argumentBuilder = literal("crash")
                //#if MC < 12004
                //$$ .requires((serverCommandSource -> serverCommandSource.hasPermissionLevel(4)))
                //$$.requires((player) -> carpet.settings.SettingsManager.canUseCommand(source, enableCrashCommand))
                //#else
                //$$.requires((serverCommandSource -> serverCommandSource.hasPermissionLevel(4)))
                //$$.requires((player) ->  carpet.utils.CommandHelper.canUseCommand(player, enableCrashCommand))
                //#endif
                .executes(CrashCommand::prepare)
                .then(
                        literal("confirm")
                                .executes(CrashCommand::confirm)
                )
                .then(
                        literal("abort")
                                .executes(CrashCommand::abort)
                );
        dispatcher.register(argumentBuilder);
    }

    public static int prepare(CommandContext<ServerCommandSource> context) {
        Messenger.m(context.getSource(), "r Prepare to crash the server!");
        Messenger.m(context.getSource(), "r Type '/crash confirm' to confirm");
        Messenger.m(context.getSource(), "r Type '/crash abort' to abort");
        isPreparing = true;
        return 1;
    }

    public static int confirm(CommandContext<ServerCommandSource> context) {
        if (!isPreparing) {
            Messenger.m(context.getSource(), "r Nothing to confirm.");
            return 0;
        }
        OptCarpetAddition.LOGGER.fatal("[OCA Crash Command] Confirm Crash!");
//        Runtime.getRuntime().halt(1);
        throw new Error("[OCA Crash Command] Confirm Crash!");
    }

    public static int abort(CommandContext<ServerCommandSource> context) {
        if (!isPreparing) {
            Messenger.m(context.getSource(), "r Nothing to abort.");
            return 0;
        }
        Messenger.m(context.getSource(), "r Abort!");
        isPreparing = false;
        return 1;
    }
}