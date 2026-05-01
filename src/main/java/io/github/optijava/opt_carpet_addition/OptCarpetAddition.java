package io.github.optijava.opt_carpet_addition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.patches.EntityPlayerMPFake;
import carpet.utils.Messenger;
import com.google.common.util.concurrent.RateLimiter;
import com.mojang.brigadier.CommandDispatcher;
import io.github.optijava.opt_carpet_addition.commands.*;
import io.github.optijava.opt_carpet_addition.events.fixExperienceBug.FixExperienceBug;
import io.github.optijava.opt_carpet_addition.logger.LoggerRegister;
import io.github.optijava.opt_carpet_addition.utils.CommandLogger;
import io.github.optijava.opt_carpet_addition.utils.ConfigUtil;
import io.github.optijava.opt_carpet_addition.utils.TpLimit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Map;
import java.util.Objects;

public class OptCarpetAddition implements CarpetExtension, ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("OptCarpetAddition");

    @Override
    public void onInitialize() {
        LOGGER.info("OptCarpetAddition is loading...");
        CarpetServer.manageExtension(this);

        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register(new FixExperienceBug());
    }

    @Override
    public void onGameStarted() {
        // add setting class to carpet extension manager
        CarpetServer.settingsManager.parseSettingsClass(OptCarpetSettings.class);

        // add rule observer
        CarpetServer.settingsManager.registerRuleObserver(((CommandSourceStack, rule, s) -> {
            if (Objects.equals(rule.name(), "forceFakePlayerGameMode") && !Objects.equals(OptCarpetSettings.forceFakePlayerGameMode, "false")) {
                GameType gameMode;

                if (OptCarpetSettings.forceFakePlayerGameMode.equals("creative")) {
                    gameMode = GameType.CREATIVE;
                } else if (OptCarpetSettings.forceFakePlayerGameMode.equals("adventure")) {
                    gameMode = GameType.ADVENTURE;
                } else {
                    gameMode = GameType.SURVIVAL;
                }

                CommandSourceStack.getServer().getPlayerList().getPlayers().forEach(player -> {
                    if (player instanceof EntityPlayerMPFake) {
                        player.setGameMode(gameMode);
                    }
                });
            }

            if (rule.name().equals("enableTpPrefixBlacklist") && OptCarpetSettings.enableTpPrefixBlacklist && OptCarpetSettings.enableTpPrefixWhitelist) {
                OptCarpetSettings.enableTpPrefixBlacklist = false;
                Messenger.m(CommandSourceStack, "r You can't enable TpPrefixBlacklist because you have enabled TpPrefixWhitelist");
            }
            if (rule.name().equals("enableTpPrefixWhitelist") && OptCarpetSettings.enableTpPrefixWhitelist && OptCarpetSettings.enableTpPrefixBlacklist) {
                OptCarpetSettings.enableTpPrefixWhitelist = false;
                Messenger.m(CommandSourceStack, "r You can't enable TpPrefixWhitelist because you have enabled TpPrefixBlacklist");
            }
            if (rule.name().equals("enableTpherePrefixWhitelist") && OptCarpetSettings.enableTpHerePrefixWhitelist && OptCarpetSettings.enableTpHerePrefixBlacklist) {
                OptCarpetSettings.enableTpHerePrefixWhitelist = false;
                Messenger.m(CommandSourceStack, "r You can't enable TpherePrefixWhitelist because you have enabled TpherePrefixBlacklist");
            }
            if (rule.name().equals("enableTpherePrefixBlacklist") && OptCarpetSettings.enableTpHerePrefixBlacklist && OptCarpetSettings.enableTpHerePrefixWhitelist) {
                OptCarpetSettings.enableTpHerePrefixBlacklist = false;
                Messenger.m(CommandSourceStack, "r You can't enable TpherePrefixBlacklist because you have enabled TpherePrefixWhitelist");
            }


            if (rule.name().equals("playerTpRateLimitTime")) {
                double time;
                if (OptCarpetSettings.playerTpRateLimitTime == 0) {
                    time = 0;
                } else {
                    time = (double) 1 / OptCarpetSettings.playerTpRateLimitTime;
                }
                for (RateLimiter rateLimiter : PlayerTpCommand.rateLimiterMap.values()) {
                    rateLimiter.setRate(time);
                }
            }
        }));

        // config
        if (!ConfigUtil.init()) {
            OptCarpetAddition.LOGGER.error("Failed to create config folder: {}{}opt-carpet-addition", OptCarpetSettings.configDirectory.toString(), File.separator);
            return;
        }

        TpLimit.loadConfigFile();
        CommandLogger.loadConfigFile();
    }

    @Override
    public void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext) {
        PlayerTpCommand.registerCommands(dispatcher);
        ListAdvanceCommand.registerCommand(dispatcher);
        TpLimitCommand.registerCommand(dispatcher);
        CommandLoggerCommand.registerCommand(dispatcher);
        CrashCommand.registerCommand(dispatcher);
    }

    @Override
    public void onPlayerLoggedIn(ServerPlayer player) {
        if (!(Objects.equals(OptCarpetSettings.forceFakePlayerGameMode, "false")) && player instanceof EntityPlayerMPFake) {
            GameType gameMode = GameType.SURVIVAL;

            if (OptCarpetSettings.forceFakePlayerGameMode.equals("creative")) {
                gameMode = GameType.CREATIVE;
            } else if (OptCarpetSettings.forceFakePlayerGameMode.equals("adventure")) {
                gameMode = GameType.ADVENTURE;
            }

            player.setGameMode(gameMode);
        }

        double time;
        if (OptCarpetSettings.playerTpRateLimitTime == 0) {
            time = 0;
        } else {
            time = 1.0d / OptCarpetSettings.playerTpRateLimitTime;
        }
        PlayerTpCommand.rateLimiterMap.put(player, RateLimiter.create(time));
    }

    @Override
    public void onPlayerLoggedOut(ServerPlayer player) {
        PlayerTpCommand.rateLimiterMap.remove(player);
    }

    @Override
    public void registerLoggers() {
        LoggerRegister.registry();
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        //add rule translator
        return RuleTranslator.getTranslationFromResourcePath(lang);
    }
}
