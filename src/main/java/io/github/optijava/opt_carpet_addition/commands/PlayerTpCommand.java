package io.github.optijava.opt_carpet_addition.commands;

import carpet.CarpetServer;
import carpet.patches.EntityPlayerMPFake;
import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Objects;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class PlayerTpCommand {

    private PlayerTpCommand() {

    }

    private static final String COMMAND_PREFIX = "player";

    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> argumentBuilder = literal(COMMAND_PREFIX)
                .then(argument(COMMAND_PREFIX, StringArgumentType.word())
                        .then(literal("tp").executes(PlayerTpCommand::teleport))
                        .then(literal("tphere").executes(PlayerTpCommand::teleportHere))
                );
        dispatcher.register(argumentBuilder);
    }

    private static int teleport(CommandContext<ServerCommandSource> context) {
        String target = StringArgumentType.getString(context, COMMAND_PREFIX);

        if (CarpetServer.minecraft_server.getPlayerManager().getPlayer(target) == null) {
            Messenger.m(context.getSource(), "r No such player");
            return 0;
        }

        final MinecraftServer server = context.getSource().getServer();

        if (context.getSource().equals(server.getCommandSource())) {
            Messenger.m(context.getSource(), "r Console can't tp to player.");
            OptCarpetAddition.LOGGER.warn("Console can't tp to player");
            return 0;
        }
        if (StringArgumentType.getString(context, COMMAND_PREFIX).isEmpty() || target == null) {
            Messenger.m(context.getSource(), "r Invalid player name.");
            return 0;
        }

        try {
            final String commandSourcePlayerName = Objects.requireNonNull(context.getSource().getPlayer()).getGameProfile().getName();

            if (server.getPlayerManager().getPlayer(target) instanceof EntityPlayerMPFake) {

                switch (OptCarpetSettings.commandTpToFakePlayer) {
                    case "true" -> executeTp(commandSourcePlayerName, context, server);
                    case "ops" -> {
                        if ((server.getPlayerManager().isOperator(context.getSource().getPlayer().getGameProfile()))) {
                            executeTp(commandSourcePlayerName, context, server);
                        } else {
                            Messenger.m(context.getSource(), "r You have no permission to teleport to fake player.You aren't op.");
                        }
                    }
                    case OptCarpetSettings.FALSE ->
                            Messenger.m(context.getSource(), "r Anybody can't teleport to fake player.");
                }

            } else {

                switch (OptCarpetSettings.allowTpToRealPlayer) {
                    case "true" ->
                            server.getCommandManager().getDispatcher().execute(server.getCommandManager().getDispatcher().parse("tp " + commandSourcePlayerName + " " + target, server.getCommandSource()));
                    case "ops" -> {
                        if ((server.getPlayerManager().isOperator(context.getSource().getPlayer().getGameProfile()))) {
                            server.getCommandManager().getDispatcher().execute(server.getCommandManager().getDispatcher().parse("tp " + commandSourcePlayerName + " " + target, server.getCommandSource()));
                        } else {
                            Messenger.m(context.getSource(), "r You have no permission to teleport to real player.You aren't op.");
                        }
                    }
                    case OptCarpetSettings.FALSE ->
                            Messenger.m(context.getSource(), "r Anybody can't teleport to real player.");
                }

            }


        } catch (CommandSyntaxException e) {
            Messenger.m(context.getSource(), "r Maybe player name is not correct.");
            OptCarpetAddition.LOGGER.error("Unknown error occurred when execute command.", e);
            return 0;
        }
        return 1;
    }

    private static int teleportHere(CommandContext<ServerCommandSource> context) {
        String target = StringArgumentType.getString(context, COMMAND_PREFIX);

        if (CarpetServer.minecraft_server.getPlayerManager().getPlayer(target) == null) {
            Messenger.m(context.getSource(), "r No such player");
            return 0;
        }

        final MinecraftServer server = context.getSource().getServer();

        if (context.getSource().equals(server.getCommandSource())) {
            Messenger.m(context.getSource(), "r Console can't tp here player.");
            OptCarpetAddition.LOGGER.warn("Console can't tp here player");
            return 0;
        }
        if (StringArgumentType.getString(context, COMMAND_PREFIX).isEmpty() || target == null) {
            Messenger.m(context.getSource(), "r Invalid player name.");
            return 0;
        }

        try {
            final String commandSourcePlayerName = Objects.requireNonNull(context.getSource().getPlayer()).getGameProfile().getName();

            if (server.getPlayerManager().getPlayer(target) instanceof EntityPlayerMPFake) {

                switch (OptCarpetSettings.commandTpHereFakePlayer) {
                    case "true" -> executeTpHere(commandSourcePlayerName, context, server);
                    case "ops" -> {
                        if ((server.getPlayerManager().isOperator(context.getSource().getPlayer().getGameProfile()))) {
                            executeTpHere(commandSourcePlayerName, context, server);
                        } else {
                            Messenger.m(context.getSource(), "r You have no permission to teleport here fake player.You aren't op.");
                        }
                    }
                    case "false" -> Messenger.m(context.getSource(), "r Anybody can't teleport here fake player.");
                }

            } else {

                switch (OptCarpetSettings.allowTpHereRealPlayer) {
                    case "true" ->
                            server.getCommandManager().getDispatcher().execute(server.getCommandManager().getDispatcher().parse("tp " + target + " " + commandSourcePlayerName, server.getCommandSource()));
                    case "ops" -> {
                        if ((server.getPlayerManager().isOperator(context.getSource().getPlayer().getGameProfile()))) {
                            server.getCommandManager().getDispatcher().execute(server.getCommandManager().getDispatcher().parse("tp " + target + " " + commandSourcePlayerName, server.getCommandSource()));
                        } else {
                            Messenger.m(context.getSource(), "r You have no permission to teleport here real player.You aren't op.");
                        }
                    }
                    case "false" -> Messenger.m(context.getSource(), "r Anybody can't teleport here real player.");
                }

            }

        } catch (CommandSyntaxException e) {
            Messenger.m(context.getSource(), "r Unknown error occurred when execute command : com.mojang.brigadier.exceptions.CommandSyntaxException");
            OptCarpetAddition.LOGGER.error("Unknown error occurred when execute command.", e);
            return 0;
        }
        return 1;
    }

    private static void executeTp(String commandSourcePlayerName, CommandContext<ServerCommandSource> context, MinecraftServer server) {
        String target = StringArgumentType.getString(context, COMMAND_PREFIX);

        try {
            if (OptCarpetSettings.enableTpPrefixWhitelist && checkTpWhitelist(StringArgumentType.getString(context, COMMAND_PREFIX))) {
                server.getCommandManager().getDispatcher().execute(server.getCommandManager().getDispatcher().parse("tp " + commandSourcePlayerName + " " + target, server.getCommandSource()));
            } else if (OptCarpetSettings.enableTpPrefixBlacklist && checkTpBlacklist(StringArgumentType.getString(context, COMMAND_PREFIX))) {
                server.getCommandManager().getDispatcher().execute(server.getCommandManager().getDispatcher().parse("tp " + commandSourcePlayerName + " " + target, server.getCommandSource()));
            } else if (!OptCarpetSettings.enableTpPrefixBlacklist && !OptCarpetSettings.enableTpPrefixWhitelist) {
                server.getCommandManager().getDispatcher().execute(server.getCommandManager().getDispatcher().parse("tp " + commandSourcePlayerName + " " + target, server.getCommandSource()));
            } else {
                Messenger.m(context.getSource(), "r You can't tp to this player because of tp limit.");
            }
        } catch (CommandSyntaxException e) {
            Messenger.m(context.getSource(), "r Unknown error occurred when execute command : com.mojang.brigadier.exceptions.CommandSyntaxException");
            OptCarpetAddition.LOGGER.error("Unknown error occurred when execute command.", e);
        }
    }

    private static void executeTpHere(String commandSourcePlayerName, CommandContext<ServerCommandSource> context, MinecraftServer server) {
        String target = StringArgumentType.getString(context, COMMAND_PREFIX);

        try {
            if (OptCarpetSettings.enableTpHerePrefixWhitelist && checkTpHereWhitelist(StringArgumentType.getString(context, COMMAND_PREFIX))) {
                server.getCommandManager().getDispatcher().execute(server.getCommandManager().getDispatcher().parse("tp " + target + " " + commandSourcePlayerName, server.getCommandSource()));
            } else if (OptCarpetSettings.enableTpHerePrefixBlacklist && checkTpHereBlacklist(StringArgumentType.getString(context, COMMAND_PREFIX))) {
                server.getCommandManager().getDispatcher().execute(server.getCommandManager().getDispatcher().parse("tp " + target + " " + commandSourcePlayerName, server.getCommandSource()));
            } else if (!OptCarpetSettings.enableTpHerePrefixBlacklist && !OptCarpetSettings.enableTpHerePrefixWhitelist) {
                server.getCommandManager().getDispatcher().execute(server.getCommandManager().getDispatcher().parse("tp " + target + " " + commandSourcePlayerName, server.getCommandSource()));
            } else {
                Messenger.m(context.getSource(), "r You can't tp here this player because of tp limit.");
            }
        } catch (CommandSyntaxException e) {
            Messenger.m(context.getSource(), "r Unknown error occurred when execute command : com.mojang.brigadier.exceptions.CommandSyntaxException");
            OptCarpetAddition.LOGGER.error("Unknown error occurred when execute command.", e);
        }
    }

    private static boolean checkTpBlacklist(String name) {
        for (String s : OptCarpetSettings.tpLimitConfigBean.TpBlacklist) {
            if (name.startsWith(s)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkTpWhitelist(String name) {
        for (String s : OptCarpetSettings.tpLimitConfigBean.TpWhitelist) {
            if (name.startsWith(s)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkTpHereBlacklist(String name) {
        for (String s : OptCarpetSettings.tpLimitConfigBean.TphereBlacklist) {
            if (name.startsWith(s)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkTpHereWhitelist(String name) {
        for (String s : OptCarpetSettings.tpLimitConfigBean.TphereWhitelist) {
            if (name.startsWith(s)) {
                return true;
            }
        }
        return false;
    }
}