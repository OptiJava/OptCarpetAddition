package io.github.optijava.opt_carpet_addition.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.utils.TpLimit;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class TpLimitCommand {

    private TpLimitCommand() {

    }

    public static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> argument = literal("tpmanager")
                .then(literal("tp")
                        .then(literal("whitelist")
                                .then(literal("add")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::addTpWhitelist)
                                        )
                                )
                                .then(literal("remove")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::removeTpWhitelist)
                                        )
                                )
                        )
                        .then(literal("blacklist")
                                .then(literal("add")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::addTpBlacklist)
                                        )
                                )
                                .then(literal("remove")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::removeTpBlacklist)
                                        )
                                )
                        )
                )
                .then(literal("tphere")
                        .then(literal("whitelist")
                                .then(literal("add")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::addTphereWhitelist)
                                        )
                                )
                                .then(literal("remove")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::removeTphereWhitelist)
                                        )
                                )
                        )
                        .then(literal("blacklist")
                                .then(literal("add")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::addTphereBlacklist)
                                        )
                                )
                                .then(literal("remove")
                                        .then(argument("prefix", StringArgumentType.word())
                                                .executes(TpLimitCommand::removeTphereBlacklist)
                                        )
                                )
                        )
                );
        dispatcher.register(argument);
    }

    public static int addTpWhitelist(CommandContext<ServerCommandSource> context) {
        TpLimit.add(StringArgumentType.getString(context, "prefix"), "TpWhitelist");
        OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Add TpWhitelist prefix : " + StringArgumentType.getString(context, "prefix"));
        return 1;
    }

    public static int addTpBlacklist(CommandContext<ServerCommandSource> context) {
        TpLimit.add(StringArgumentType.getString(context, "prefix"), "TpBlacklist");
        OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Add TpBlacklist prefix : " + StringArgumentType.getString(context, "prefix"));
        return 1;
    }

    public static int addTphereWhitelist(CommandContext<ServerCommandSource> context) {
        TpLimit.add(StringArgumentType.getString(context, "prefix"), "TphereWhitelist");
        OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Add TphereWhitelist prefix : " + StringArgumentType.getString(context, "prefix"));
        return 1;
    }

    public static int addTphereBlacklist(CommandContext<ServerCommandSource> context) {
        TpLimit.add(StringArgumentType.getString(context, "prefix"), "TphereBlacklist");
        OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Add TphereBlacklist prefix : " + StringArgumentType.getString(context, "prefix"));
        return 1;
    }

    public static int removeTpWhitelist(CommandContext<ServerCommandSource> context) {
        TpLimit.remove(StringArgumentType.getString(context, "prefix"), "TpWhitelist");
        OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Remove TpWhitelist prefix : " + StringArgumentType.getString(context, "prefix"));
        return 1;
    }

    public static int removeTpBlacklist(CommandContext<ServerCommandSource> context) {
        TpLimit.remove(StringArgumentType.getString(context, "prefix"), "TpBlacklist");
        OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Remove TpBlacklist prefix : " + StringArgumentType.getString(context, "prefix"));
        return 1;
    }

    public static int removeTphereWhitelist(CommandContext<ServerCommandSource> context) {
        TpLimit.remove(StringArgumentType.getString(context, "prefix"), "TphereWhitelist");
        OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Remove TphereWhitelist prefix : " + StringArgumentType.getString(context, "prefix"));
        return 1;
    }

    public static int removeTphereBlacklist(CommandContext<ServerCommandSource> context) {
        TpLimit.remove(StringArgumentType.getString(context, "prefix"), "TphereBlacklist");
        OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Remove TphereBlacklist prefix : " + StringArgumentType.getString(context, "prefix"));
        return 1;
    }
}
