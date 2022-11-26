package io.github.optijava.opt_carpet_addition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.CarpetSettings;
import carpet.patches.EntityPlayerMPFake;
import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import io.github.optijava.opt_carpet_addition.commands.ListAdvanceCommand;
import io.github.optijava.opt_carpet_addition.commands.PlayerTpCommand;
import io.github.optijava.opt_carpet_addition.commands.TpLimitCommand;
import io.github.optijava.opt_carpet_addition.events.FixExperienceBug;
import io.github.optijava.opt_carpet_addition.utils.ConfigUtil;
import io.github.optijava.opt_carpet_addition.utils.TpLimit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

import java.io.File;
import java.util.Objects;

public class OptCarpetAddition implements CarpetExtension, ModInitializer {

    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(new OptCarpetAddition());

        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register(new FixExperienceBug());
    }

    @Override
    public void onGameStarted() {
        // add setting class to carpet extension manager
        CarpetServer.settingsManager.parseSettingsClass(OptCarpetSettings.class);

        // add rule observer
        CarpetServer.settingsManager.addRuleObserver(((serverCommandSource, rule, s) -> {
            if (Objects.equals(rule.name, "forceFakePlayerGameMode") && !Objects.equals(OptCarpetSettings.forceFakePlayerGameMode, "false")) {
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

            if (rule.name.equals("enableTpPrefixBlacklist") && OptCarpetSettings.enableTpPrefixBlacklist && OptCarpetSettings.enableTpPrefixWhitelist) {
                OptCarpetSettings.enableTpPrefixBlacklist = false;
                Messenger.m(serverCommandSource, "r You can't enable TpPrefixBlacklist because you have enabled TpPrefixWhitelist");
            }

            if (rule.name.equals("enableTpPrefixWhitelist") && OptCarpetSettings.enableTpPrefixWhitelist && OptCarpetSettings.enableTpPrefixBlacklist) {
                OptCarpetSettings.enableTpPrefixWhitelist = false;
                Messenger.m(serverCommandSource, "r You can't enable TpPrefixWhitelist because you have enabled TpPrefixBlacklist");
            }

            if (rule.name.equals("enableTpherePrefixWhitelist") && OptCarpetSettings.enableTpHerePrefixWhitelist && OptCarpetSettings.enableTpHerePrefixBlacklist) {
                OptCarpetSettings.enableTpHerePrefixWhitelist = false;
                Messenger.m(serverCommandSource, "r You can't enable TpherePrefixWhitelist because you have enabled TpherePrefixBlacklist");
            }

            if (rule.name.equals("enableTpherePrefixBlacklist") && OptCarpetSettings.enableTpHerePrefixBlacklist && OptCarpetSettings.enableTpHerePrefixWhitelist) {
                OptCarpetSettings.enableTpHerePrefixBlacklist = false;
                Messenger.m(serverCommandSource, "r You can't enable TpherePrefixBlacklist because you have enabled TpherePrefixWhitelist");
            }

            if (rule.name.equals("enableFlightCheck")) {
                CarpetServer.minecraft_server.setFlightEnabled(OptCarpetSettings.enableFlightCheck);
            }
        }));

        // config
        if (!ConfigUtil.init()) {
            CarpetSettings.LOG.error("[OptCarpetAddition] Failed to create config folder:" + OptCarpetSettings.configDirectory.toString() + File.separator + "opt-carpet-addition");
            return;
        }
        TpLimit.loadConfigFile();
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        PlayerTpCommand.registerCommands(dispatcher);
        ListAdvanceCommand.registerCommand(dispatcher);
        TpLimitCommand.registerCommand(dispatcher);
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
    }
}
