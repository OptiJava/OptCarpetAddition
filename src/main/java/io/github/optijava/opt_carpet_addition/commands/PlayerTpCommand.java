package io.github.optijava.opt_carpet_addition.commands;

import carpet.CarpetSettings;
import carpet.patches.EntityPlayerMPFake;
import carpet.settings.SettingsManager;
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

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class PlayerTpCommand {

    private PlayerTpCommand() {

    }

    private static final String COMMAND_PREFIX = "player";

    public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> argumentBuilder = literal(COMMAND_PREFIX)
                .requires((player) -> SettingsManager.canUseCommand(player, CarpetSettings.commandPlayer))
                .then(argument(COMMAND_PREFIX, StringArgumentType.word())
                        .then(literal("tp").executes(PlayerTpCommand::teleport))
                        .then(literal("tphere").executes(PlayerTpCommand::teleportHere)));
        dispatcher.register(argumentBuilder);
    }

    private static int teleport(CommandContext<ServerCommandSource> context) {
        final MinecraftServer server = context.getSource().getMinecraftServer();

        if (context.getSource().equals(server.getCommandSource())) {
            Messenger.m(context.getSource(), "r Console can't tp to player.");
            return 0;
        }
        if (StringArgumentType.getString(context, COMMAND_PREFIX).equals("") || StringArgumentType.getString(context, COMMAND_PREFIX) == null) {
            Messenger.m(context.getSource(), "r Invalid player name.");
            return 0;
        }

        try {
            final String commandSourcePlayerName = context.getSource().getPlayer().getGameProfile().getName();

            OptCarpetAddition.LOGGER.info(commandSourcePlayerName + " submit command: /player " + StringArgumentType.getString(context, COMMAND_PREFIX) + " tp");

            if (server.getPlayerManager().getPlayer(StringArgumentType.getString(context, COMMAND_PREFIX)) instanceof EntityPlayerMPFake) {
                if (OptCarpetSettings.commandTpToFakePlayer.equals("true")) {
                    server.getCommandManager().execute(server.getCommandSource(), "tp " + commandSourcePlayerName + " " + StringArgumentType.getString(context, "player"));
                } else if (OptCarpetSettings.commandTpToFakePlayer.equals("ops")) {
                    if ((server.getPlayerManager().isOperator(context.getSource().getPlayer().getGameProfile()))) {
                        server.getCommandManager().execute(server.getCommandSource(), "tp " + commandSourcePlayerName + " " + StringArgumentType.getString(context, "player"));
                    } else {
                        Messenger.m(context.getSource(), "r You have no permission to teleport to fake player.You aren't op.");
                    }
                } else if (OptCarpetSettings.commandTpToFakePlayer.equals("false")) {
                    Messenger.m(context.getSource(), "r Anybody can't teleport to fake player.");
                }
            } else {
                if (OptCarpetSettings.allowTpToRealPlayer.equals("true")) {
                    server.getCommandManager().execute(server.getCommandSource(), "tp " + commandSourcePlayerName + " " + StringArgumentType.getString(context, "player"));
                } else if (OptCarpetSettings.allowTpToRealPlayer.equals("ops")) {
                    if ((server.getPlayerManager().isOperator(context.getSource().getPlayer().getGameProfile()))) {
                        server.getCommandManager().execute(server.getCommandSource(), "tp " + commandSourcePlayerName + " " + StringArgumentType.getString(context, "player"));
                    } else {
                        Messenger.m(context.getSource(), "r You have no permission to teleport to real player.You aren't op.");
                    }
                } else if (OptCarpetSettings.commandTpToFakePlayer.equals("false")) {
                    Messenger.m(context.getSource(), "r Anybody can't teleport to real player.");
                }
            }


        } catch (CommandSyntaxException e) {
            Messenger.m(context.getSource(), "r Maybe player name is not correct.");
            e.printStackTrace();
        }
        return 1;
    }

    private static int teleportHere(CommandContext<ServerCommandSource> context) {
        final MinecraftServer server = context.getSource().getMinecraftServer();

        if (context.getSource().equals(server.getCommandSource())) {
            Messenger.m(context.getSource(), "r Console can't tp here player.");
            return 0;
        }
        if (StringArgumentType.getString(context, COMMAND_PREFIX).equals("") || StringArgumentType.getString(context, "player") == null) {
            Messenger.m(context.getSource(), "r Invalid player name.");
            return 0;
        }

        try {
            final String commandSourcePlayerName = context.getSource().getPlayer().getGameProfile().getName();

            OptCarpetAddition.LOGGER.info(commandSourcePlayerName + " submit command: /player " + StringArgumentType.getString(context, COMMAND_PREFIX) + " tphere");

            if (server.getPlayerManager().getPlayer(StringArgumentType.getString(context, COMMAND_PREFIX)) instanceof EntityPlayerMPFake) {
                if (OptCarpetSettings.commandTpHereFakePlayer.equals("true")) {
                    server.getCommandManager().execute(server.getCommandSource(), "tp " + StringArgumentType.getString(context, COMMAND_PREFIX) + " " + commandSourcePlayerName);
                } else if (OptCarpetSettings.commandTpHereFakePlayer.equals("ops")) {
                    if ((server.getPlayerManager().isOperator(context.getSource().getPlayer().getGameProfile()))) {
                        server.getCommandManager().execute(server.getCommandSource(), "tp " + StringArgumentType.getString(context, COMMAND_PREFIX) + " " + commandSourcePlayerName);
                    } else {
                        Messenger.m(context.getSource(), "r You have no permission to teleport here fake player.You aren't op.");
                    }
                } else if (OptCarpetSettings.commandTpHereFakePlayer.equals("false")) {
                    Messenger.m(context.getSource(), "r Anybody can't teleport here fake player.");
                }
            } else {
                if (OptCarpetSettings.allowTpHereRealPlayer.equals("true")) {
                    server.getCommandManager().execute(server.getCommandSource(), "tp " + StringArgumentType.getString(context, COMMAND_PREFIX) + " " + commandSourcePlayerName);
                } else if (OptCarpetSettings.allowTpHereRealPlayer.equals("ops")) {
                    if ((server.getPlayerManager().isOperator(context.getSource().getPlayer().getGameProfile()))) {
                        server.getCommandManager().execute(server.getCommandSource(), "tp " + StringArgumentType.getString(context, COMMAND_PREFIX) + " " + commandSourcePlayerName);
                    } else {
                        Messenger.m(context.getSource(), "r You have no permission to teleport here real player.You aren't op.");
                    }
                } else if (OptCarpetSettings.commandTpHereFakePlayer.equals("false")) {
                    Messenger.m(context.getSource(), "r Anybody can't teleport here real player.");
                }
            }


        } catch (CommandSyntaxException e) {
            Messenger.m(context.getSource(), "r Unknown error occurred when execute command : com.mojang.brigadier.exceptions.CommandSyntaxException");
            OptCarpetAddition.LOGGER.error("Unknown error occurred when execute command.", e);
        }
        return 1;
    }
}
