package io.github.optijava.opt_carpet_addition.commands;

import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.utils.TpLimit;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

import static io.github.optijava.opt_carpet_addition.OptCarpetSettings.enableTpmanagerCommand;
import static net.minecraft.commands.Commands.argument;

public class TpLimitCommand {

    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {

        LiteralArgumentBuilder<CommandSourceStack> argument = Commands.literal("tpmanager")
                .requires((player) ->  carpet.utils.CommandHelper.canUseCommand(player, enableTpmanagerCommand))
                .then(
                        Commands.literal("reload")
                                .executes(TpLimit::reload)
                )
                .then(
                        Commands.literal("tp")
                                .then(Commands.literal("whitelist")
                                        .then(Commands.literal("add")
                                                .then(argument("prefix", StringArgumentType.word())
                                                        .executes(TpLimitCommand::addTpWhitelist)
                                                )
                                        )
                                        .then(Commands.literal("remove")
                                                .then(argument("prefix", StringArgumentType.word())
                                                        .executes(TpLimitCommand::removeTpWhitelist)
                                                )
                                        )
                                )
                                .then(Commands.literal("blacklist")
                                        .then(Commands.literal("add")
                                                .then(argument("prefix", StringArgumentType.word())
                                                        .executes(TpLimitCommand::addTpBlacklist)
                                                )
                                        )
                                        .then(Commands.literal("remove")
                                                .then(argument("prefix", StringArgumentType.word())
                                                        .executes(TpLimitCommand::removeTpBlacklist)
                                                )
                                        )
                                )
                )
                .then(Commands.literal("tphere")
                        .then(Commands.literal("whitelist")
                                .then(Commands.literal("add")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::addTphereWhitelist)
                                        )
                                )
                                .then(Commands.literal("remove")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::removeTphereWhitelist)
                                        )
                                )
                        )
                        .then(Commands.literal("blacklist")
                                .then(Commands.literal("add")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::addTphereBlacklist)
                                        )
                                )
                                .then(Commands.literal("remove")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::removeTphereBlacklist)
                                        )
                                )
                        )
                );
        dispatcher.register(argument);
    }

    public static int addTpWhitelist(CommandContext<CommandSourceStack> context) {
        TpLimit.add(StringArgumentType.getString(context, "prefix"), "TpWhitelist");
        OptCarpetAddition.LOGGER.info("Add TpWhitelist prefix : {}", StringArgumentType.getString(context, "prefix"));
        Messenger.m(context.getSource(), "Add TpWhitelist prefix : " + StringArgumentType.getString(context, "prefix"));
        TpLimit.flush();
        return 1;
    }

    public static int addTpBlacklist(CommandContext<CommandSourceStack> context) {
        TpLimit.add(StringArgumentType.getString(context, "prefix"), "TpBlacklist");
        OptCarpetAddition.LOGGER.info("Add TpBlacklist prefix : {}", StringArgumentType.getString(context, "prefix"));
        Messenger.m(context.getSource(), "Add TpBlacklist prefix : " + StringArgumentType.getString(context, "prefix"));
        TpLimit.flush();
        return 1;
    }

    public static int addTphereWhitelist(CommandContext<CommandSourceStack> context) {
        TpLimit.add(StringArgumentType.getString(context, "prefix"), "TphereWhitelist");
        OptCarpetAddition.LOGGER.info("Add TphereWhitelist prefix : {}", StringArgumentType.getString(context, "prefix"));
        Messenger.m(context.getSource(), "Add TphereWhitelist prefix : " + StringArgumentType.getString(context, "prefix"));
        TpLimit.flush();
        return 1;
    }

    public static int addTphereBlacklist(CommandContext<CommandSourceStack> context) {
        TpLimit.add(StringArgumentType.getString(context, "prefix"), "TphereBlacklist");
        OptCarpetAddition.LOGGER.info(" Add TphereBlacklist prefix : {}", StringArgumentType.getString(context, "prefix"));
        Messenger.m(context.getSource(), "Add TphereBlacklist prefix : " + StringArgumentType.getString(context, "prefix"));
        TpLimit.flush();
        return 1;
    }

    public static int removeTpWhitelist(CommandContext<CommandSourceStack> context) {
        TpLimit.remove(StringArgumentType.getString(context, "prefix"), "TpWhitelist");
        OptCarpetAddition.LOGGER.info("Remove TpWhitelist prefix : {}", StringArgumentType.getString(context, "prefix"));
        Messenger.m(context.getSource(), "Remove TpWhitelist prefix : " + StringArgumentType.getString(context, "prefix"));
        TpLimit.flush();
        return 1;
    }

    public static int removeTpBlacklist(CommandContext<CommandSourceStack> context) {
        TpLimit.remove(StringArgumentType.getString(context, "prefix"), "TpBlacklist");
        OptCarpetAddition.LOGGER.info("Remove TpBlacklist prefix : {}", StringArgumentType.getString(context, "prefix"));
        Messenger.m(context.getSource(), "Remove TpBlacklist prefix : " + StringArgumentType.getString(context, "prefix"));
        TpLimit.flush();
        return 1;
    }

    public static int removeTphereWhitelist(CommandContext<CommandSourceStack> context) {
        TpLimit.remove(StringArgumentType.getString(context, "prefix"), "TphereWhitelist");
        OptCarpetAddition.LOGGER.info("Remove TphereWhitelist prefix : {}", StringArgumentType.getString(context, "prefix"));
        Messenger.m(context.getSource(), "Remove TphereWhitelist prefix : " + StringArgumentType.getString(context, "prefix"));
        TpLimit.flush();
        return 1;
    }

    public static int removeTphereBlacklist(CommandContext<CommandSourceStack> context) {
        TpLimit.remove(StringArgumentType.getString(context, "prefix"), "TphereBlacklist");
        OptCarpetAddition.LOGGER.info("Remove TphereBlacklist prefix : {}", StringArgumentType.getString(context, "prefix"));
        Messenger.m(context.getSource(), "Remove TphereBlacklist prefix : " + StringArgumentType.getString(context, "prefix"));
        TpLimit.flush();
        return 1;
    }
}