package io.github.optijava.opt_carpet_addition.commands;

import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import static io.github.optijava.opt_carpet_addition.OptCarpetSettings.enableCrashCommand;

public class CrashCommand {
    private CrashCommand() {
    }

    private static boolean isPreparing = false;

    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> argumentBuilder = Commands.literal("crash")
                .requires((player) ->  carpet.utils.CommandHelper.canUseCommand(player, enableCrashCommand))
                .executes(CrashCommand::prepare)
                .then(
                        Commands.literal("confirm")
                                .executes(CrashCommand::confirm)
                )
                .then(
                        Commands.literal("abort")
                                .executes(CrashCommand::abort)
                );
        dispatcher.register(argumentBuilder);
    }

    public static int prepare(CommandContext<CommandSourceStack> context) {
        Messenger.m(context.getSource(), "r Prepare to crash the server!");
        Messenger.m(context.getSource(), "r Type '/crash confirm' to confirm");
        Messenger.m(context.getSource(), "r Type '/crash abort' to abort");
        isPreparing = true;
        return 1;
    }

    public static int confirm(CommandContext<CommandSourceStack> context) {
        if (!isPreparing) {
            Messenger.m(context.getSource(), "r Nothing to confirm.");
            return 0;
        }
        OptCarpetAddition.LOGGER.fatal("[OCA Crash Command] Confirm Crash!");
        throw new Error("[OCA Crash Command] Confirm Crash!");
    }

    public static int abort(CommandContext<CommandSourceStack> context) {
        if (!isPreparing) {
            Messenger.m(context.getSource(), "r Nothing to abort.");
            return 0;
        }
        Messenger.m(context.getSource(), "r Abort!");
        isPreparing = false;
        return 1;
    }
}