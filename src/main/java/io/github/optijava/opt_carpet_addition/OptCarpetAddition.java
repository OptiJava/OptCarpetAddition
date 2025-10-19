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
//#if MC >= 11900
//$$ import net.minecraft.command.CommandRegistryAccess;
//#endif
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
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
        CarpetServer.manageExtension(new OptCarpetAddition());

        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register(new FixExperienceBug());
    }

    @Override
    public void onGameStarted() {
        // add setting class to carpet extension manager
        CarpetServer.settingsManager.parseSettingsClass(OptCarpetSettings.class);

        // add rule observer
        //#if MC >= 11900
        //$$ CarpetServer.settingsManager.registerRuleObserver(((serverCommandSource, rule, s) -> {
        //#else
        CarpetServer.settingsManager.addRuleObserver(((serverCommandSource, rule, s) -> {
        //#endif
            //#if MC >= 11900
            //$$ if (Objects.equals(rule.name(), "forceFakePlayerGameMode") && !Objects.equals(OptCarpetSettings.forceFakePlayerGameMode, "false")) {
            //#else
            if (Objects.equals(rule.name, "forceFakePlayerGameMode") && !Objects.equals(OptCarpetSettings.forceFakePlayerGameMode, "false")) {
            //#endif
                GameMode gameMode = GameMode.SURVIVAL;

                if (OptCarpetSettings.forceFakePlayerGameMode.equals("creative")) {
                    gameMode = GameMode.CREATIVE;
                } else if (OptCarpetSettings.forceFakePlayerGameMode.equals("adventure")) {
                    gameMode = GameMode.ADVENTURE;
                }

                for (ServerPlayerEntity player : serverCommandSource.getServer().getPlayerManager().getPlayerList()) {
                    if (player instanceof EntityPlayerMPFake) {
                        player.changeGameMode(gameMode);
                    }
                }
            }

            //#if MC >= 11900
            //$$ if (rule.name().equals("enableTpPrefixBlacklist") && OptCarpetSettings.enableTpPrefixBlacklist && OptCarpetSettings.enableTpPrefixWhitelist) {
            //#else
            if (rule.name.equals("enableTpPrefixBlacklist") && OptCarpetSettings.enableTpPrefixBlacklist && OptCarpetSettings.enableTpPrefixWhitelist) {
            //#endif
                OptCarpetSettings.enableTpPrefixBlacklist = false;
                Messenger.m(serverCommandSource, "r You can't enable TpPrefixBlacklist because you have enabled TpPrefixWhitelist");
            }
            //#if MC >= 11900
            //$$ if (rule.name().equals("enableTpPrefixWhitelist") && OptCarpetSettings.enableTpPrefixWhitelist && OptCarpetSettings.enableTpPrefixBlacklist) {
            //#else
            if (rule.name.equals("enableTpPrefixWhitelist") && OptCarpetSettings.enableTpPrefixWhitelist && OptCarpetSettings.enableTpPrefixBlacklist) {
            //#endif
                OptCarpetSettings.enableTpPrefixWhitelist = false;
                Messenger.m(serverCommandSource, "r You can't enable TpPrefixWhitelist because you have enabled TpPrefixBlacklist");
            }
            //#if MC >= 11900
            //$$ if (rule.name().equals("enableTpherePrefixWhitelist") && OptCarpetSettings.enableTpHerePrefixWhitelist && OptCarpetSettings.enableTpHerePrefixBlacklist) {
            //#else
            if (rule.name.equals("enableTpherePrefixWhitelist") && OptCarpetSettings.enableTpHerePrefixWhitelist && OptCarpetSettings.enableTpHerePrefixBlacklist) {
            //#endif
                OptCarpetSettings.enableTpHerePrefixWhitelist = false;
                Messenger.m(serverCommandSource, "r You can't enable TpherePrefixWhitelist because you have enabled TpherePrefixBlacklist");
            }
            //#if MC >= 11900
            //$$ if (rule.name().equals("enableTpherePrefixBlacklist") && OptCarpetSettings.enableTpHerePrefixBlacklist && OptCarpetSettings.enableTpHerePrefixWhitelist) {
            //#else
            if (rule.name.equals("enableTpherePrefixBlacklist") && OptCarpetSettings.enableTpHerePrefixBlacklist && OptCarpetSettings.enableTpHerePrefixWhitelist) {
            //#endif
                OptCarpetSettings.enableTpHerePrefixBlacklist = false;
                Messenger.m(serverCommandSource, "r You can't enable TpherePrefixBlacklist because you have enabled TpherePrefixWhitelist");
            }

            //#if MC >= 11900
            //$$ if (rule.name().equals("playerTpRateLimitTime")) {
            //#else
            if (rule.name.equals("playerTpRateLimitTime")) {
            //#endif
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
    //#if MC >= 11900
    //$$ public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandBuildContext) {
    //#else
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
    //#endif
        PlayerTpCommand.registerCommands(dispatcher);
        ListAdvanceCommand.registerCommand(dispatcher);
        TpLimitCommand.registerCommand(dispatcher);
        CommandLoggerCommand.registerCommand(dispatcher);
        CrashCommand.registerCommand(dispatcher);
    }

    @Override
    public void onPlayerLoggedIn(ServerPlayerEntity player) {
        if (player instanceof EntityPlayerMPFake && !(Objects.equals(OptCarpetSettings.forceFakePlayerGameMode, "false"))) {
            GameMode gameMode = GameMode.SURVIVAL;

            if (OptCarpetSettings.forceFakePlayerGameMode.equals("creative")) {
                gameMode = GameMode.CREATIVE;
            } else if (OptCarpetSettings.forceFakePlayerGameMode.equals("adventure")) {
                gameMode = GameMode.ADVENTURE;
            }

            player.changeGameMode(gameMode);
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
    public void onPlayerLoggedOut(ServerPlayerEntity player) {
        PlayerTpCommand.rateLimiterMap.remove(player);
    }

    @Override
    public void registerLoggers() {
        LoggerRegister.registry();
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return RuleTranslator.getTranslationFromResourcePath(lang);
    }
}
