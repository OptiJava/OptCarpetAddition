package io.github.optijava.opt_carpet_addition.commands;

import carpet.CarpetServer;
import carpet.patches.EntityPlayerMPFake;
import carpet.utils.Messenger;
import com.google.common.util.concurrent.RateLimiter;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.McUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.github.optijava.opt_carpet_addition.utils.McUtils.MINECRAFT_SERVER;


public class PlayerTpCommand {

    /* permit 1 request per 2 seconds < == > permit 0.5 request per 1 second */
    public static Map<ServerPlayer, RateLimiter> rateLimiterMap = new HashMap<>();

    private static final String COMMAND_PREFIX = "player";

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> argumentBuilder = Commands.literal(COMMAND_PREFIX)
                .then(Commands.argument(COMMAND_PREFIX, StringArgumentType.word())
                        .then(Commands.literal("tp").executes(PlayerTpCommand::teleport))
                        .then(Commands.literal("tphere").executes(PlayerTpCommand::teleportHere))
                );
        dispatcher.register(argumentBuilder);
    }

    private static boolean ratelimit(CommandContext<CommandSourceStack> context) {
        if (OptCarpetSettings.playerTpRateLimitTime == 0) {
            return true;
        }

        boolean canTeleport = true;
        try {
            RateLimiter r = rateLimiterMap.get(context.getSource().getPlayer());
            canTeleport = r.tryAcquire();
        } catch (Exception ex) {
            OptCarpetAddition.LOGGER.error(ex);
        }
        return canTeleport;
    }

    private static int teleport(CommandContext<CommandSourceStack> context) {
        if (!ratelimit(context)) {
            Messenger.m(context.getSource(), "r Too fast!");
            return 0;
        }

        String target = StringArgumentType.getString(context, COMMAND_PREFIX);

        if (MINECRAFT_SERVER.getPlayerList().getPlayerByName(target) == null) {
            Messenger.m(context.getSource(), "r No such player");
            return 0;
        }

        if (context.getSource().equals(MINECRAFT_SERVER.createCommandSourceStack())) {
            Messenger.m(context.getSource(), "r Console can't tp to player.");
            OptCarpetAddition.LOGGER.warn("Console can't tp to player");
            return 0;
        }
        if (StringArgumentType.getString(context, COMMAND_PREFIX).isEmpty()) {
            Messenger.m(context.getSource(), "r Invalid player name.");
            return 0;
        }

        try {
            //? if >= 1.21.10 {
            final String commandSourcePlayerName = Objects.requireNonNull(context.getSource().getPlayer()).getGameProfile().name();
            //?} else {
            /*final String commandSourcePlayerName = Objects.requireNonNull(context.getSource().getPlayer()).getGameProfile().getName();
            *///?}

            if (MINECRAFT_SERVER.getPlayerList().getPlayerByName(target) instanceof EntityPlayerMPFake) {

                switch (OptCarpetSettings.commandTpToFakePlayer) {
                    case OptCarpetSettings.TRUE -> executeTp(commandSourcePlayerName, context);
                    case "ops" -> {
                        if (McUtils.isOp(context.getSource().getPlayer().getGameProfile())) {
                            executeTp(commandSourcePlayerName, context);
                        } else {
                            if (OptCarpetSettings.allowSpectatorTpToAnyPlayer && context.getSource().getPlayer().gameMode.getGameModeForPlayer().equals(GameType.SPECTATOR)){
                                executeTp(commandSourcePlayerName, context);
                            } else {
                                Messenger.m(context.getSource(), "r You have no permission to teleport to fake player. You aren't op.");
                            }
                        }
                    }
                    case OptCarpetSettings.FALSE -> {
                        if (OptCarpetSettings.allowSpectatorTpToAnyPlayer && context.getSource().getPlayer().gameMode.getGameModeForPlayer().equals(GameType.SPECTATOR)){
                            executeTp(commandSourcePlayerName, context);
                        } else {
                            Messenger.m(context.getSource(), "r Anybody can't teleport to fake player.");
                        }
                    }
                }

            } else {
                switch (OptCarpetSettings.allowTpToRealPlayer) {
                    case OptCarpetSettings.TRUE -> tp(commandSourcePlayerName, target);
                    case "ops" -> {
                        if (McUtils.isOp(context.getSource().getPlayer().getGameProfile())) {
                            tp(commandSourcePlayerName, target);
                        } else {
                            if (OptCarpetSettings.allowSpectatorTpToAnyPlayer && context.getSource().getPlayer().gameMode.getGameModeForPlayer().equals(GameType.SPECTATOR)) {
                                executeTp(commandSourcePlayerName, context);
                            } else {
                                Messenger.m(context.getSource(), "r You have no permission to teleport to real player. You aren't op.");
                            }
                        }
                    }
                    case OptCarpetSettings.FALSE -> {
                            if (OptCarpetSettings.allowSpectatorTpToAnyPlayer && context.getSource().getPlayer().gameMode.getGameModeForPlayer().equals(GameType.SPECTATOR)) {
                                executeTp(commandSourcePlayerName, context);
                            } else {
                                Messenger.m(context.getSource(), "r Anybody can't teleport to real player.");
                            }
                    }
                }
            }
        } catch (CommandSyntaxException e) {
            Messenger.m(context.getSource(), "r Maybe player name is not correct.");
            OptCarpetAddition.LOGGER.error("Unknown error occurred when execute command.", e);
            return 0;
        }
        return 1;
    }

    private static int teleportHere(CommandContext<CommandSourceStack> context) {
        if (!ratelimit(context)) {
            Messenger.m(context.getSource(), "r Too fast!");
            return 0;
        }

        String target = StringArgumentType.getString(context, COMMAND_PREFIX);

        if (MINECRAFT_SERVER.getPlayerList().getPlayerByName(target) == null) {
            Messenger.m(context.getSource(), "r No such player");
            return 0;
        }

        if (context.getSource().equals(MINECRAFT_SERVER.createCommandSourceStack())) {
            Messenger.m(context.getSource(), "r Console can't tp here player.");
            OptCarpetAddition.LOGGER.warn("Console can't tp here player");
            return 0;
        }

        if (StringArgumentType.getString(context, COMMAND_PREFIX).isEmpty()) {
            Messenger.m(context.getSource(), "r Invalid player name.");
            return 0;
        }

        try {
            //? if >= 1.21.10 {
            final String commandSourcePlayerName = Objects.requireNonNull(context.getSource().getPlayer()).getGameProfile().name();
            //?} else {
            /*final String commandSourcePlayerName = Objects.requireNonNull(context.getSource().getPlayer()).getGameProfile().getName();
            *///?}

            if (MINECRAFT_SERVER.getPlayerList().getPlayerByName(target) instanceof EntityPlayerMPFake) {

                switch (OptCarpetSettings.commandTpHereFakePlayer) {
                    case OptCarpetSettings.TRUE -> executeTpHere(commandSourcePlayerName, context);
                    case "ops" -> {
                        if (McUtils.isOp(context.getSource().getPlayer().getGameProfile())) {
                            executeTpHere(commandSourcePlayerName, context);
                        } else {
                            Messenger.m(context.getSource(), "r You have no permission to teleport here fake player. You aren't op.");
                        }
                    }
                    case OptCarpetSettings.FALSE -> Messenger.m(context.getSource(), "r Anybody can't teleport here fake player.");
                }

            } else {

                switch (OptCarpetSettings.allowTpHereRealPlayer) {
                    case OptCarpetSettings.TRUE -> tp(target, commandSourcePlayerName);
                    case "ops" -> {
                        if (McUtils.isOp(context.getSource().getPlayer().getGameProfile())) {
                            tp(target, commandSourcePlayerName);
                        } else {
                            Messenger.m(context.getSource(), "r You have no permission to teleport here real player. You aren't op.");
                        }
                    }
                    case OptCarpetSettings.FALSE -> Messenger.m(context.getSource(), "r Anybody can't teleport here real player.");
                }

            }

        } catch (CommandSyntaxException e) {
            Messenger.m(context.getSource(), "r Unknown error occurred when execute command : com.mojang.brigadier.exceptions.CommandSyntaxException");
            OptCarpetAddition.LOGGER.error("Unknown error occurred when execute command.", e);
            return 0;
        }
        return 1;
    }

    private static void executeTp(String commandSourcePlayerName, CommandContext<CommandSourceStack> context) {
        String target = StringArgumentType.getString(context, COMMAND_PREFIX);

        try {
            if (OptCarpetSettings.enableTpPrefixWhitelist && checkTpWhitelist(StringArgumentType.getString(context, COMMAND_PREFIX))) {
                tp(commandSourcePlayerName, target);
            } else if (OptCarpetSettings.enableTpPrefixBlacklist && checkTpBlacklist(StringArgumentType.getString(context, COMMAND_PREFIX))) {
                tp(commandSourcePlayerName, target);
            } else if (!OptCarpetSettings.enableTpPrefixBlacklist && !OptCarpetSettings.enableTpPrefixWhitelist) {
                tp(commandSourcePlayerName, target);
            } else {
                Messenger.m(context.getSource(), "r You can't tp to this player because of tp limit.");
            }
        } catch (CommandSyntaxException e) {
            Messenger.m(context.getSource(), "r Unknown error occurred when execute command : com.mojang.brigadier.exceptions.CommandSyntaxException");
            OptCarpetAddition.LOGGER.error("Unknown error occurred when execute command.", e);
        }
    }

    private static void executeTpHere(String commandSourcePlayerName, CommandContext<CommandSourceStack> context) {
        String target = StringArgumentType.getString(context, COMMAND_PREFIX);

        try {
            if (OptCarpetSettings.enableTpHerePrefixWhitelist && checkTpHereWhitelist(StringArgumentType.getString(context, COMMAND_PREFIX))) {
                tp(target, commandSourcePlayerName);
            } else if (OptCarpetSettings.enableTpHerePrefixBlacklist && checkTpHereBlacklist(StringArgumentType.getString(context, COMMAND_PREFIX))) {
                tp(target, commandSourcePlayerName);
            } else if (!OptCarpetSettings.enableTpHerePrefixBlacklist && !OptCarpetSettings.enableTpHerePrefixWhitelist) {
                tp(target, commandSourcePlayerName);
            } else {
                Messenger.m(context.getSource(), "r You can't tp here this player because of tp limit.");
            }
        } catch (CommandSyntaxException e) {
            Messenger.m(context.getSource(), "r Unknown error occurred when execute command : com.mojang.brigadier.exceptions.CommandSyntaxException");
            OptCarpetAddition.LOGGER.error("Unknown error occurred when execute command.", e);
        }
    }

    private static boolean checkTpBlacklist(String name) {
        return OptCarpetSettings.tpLimitConfigBean.TpBlacklist.stream().noneMatch(name::startsWith);
    }

    private static boolean checkTpWhitelist(String name) {
        return OptCarpetSettings.tpLimitConfigBean.TpWhitelist.stream().anyMatch(name::startsWith);
    }

    private static boolean checkTpHereBlacklist(String name) {
        return OptCarpetSettings.tpLimitConfigBean.TphereBlacklist.stream().noneMatch(name::startsWith);
    }

    private static boolean checkTpHereWhitelist(String name) {
        return OptCarpetSettings.tpLimitConfigBean.TphereWhitelist.stream().anyMatch(name::startsWith);
    }

    private static void tp(String target, String commandSourcePlayerName) throws CommandSyntaxException {
        Commands commands = MINECRAFT_SERVER.getCommands();
        commands.getDispatcher().execute(commands.getDispatcher().parse("tp " + target + " " + commandSourcePlayerName, MINECRAFT_SERVER.createCommandSourceStack()));
    }
}