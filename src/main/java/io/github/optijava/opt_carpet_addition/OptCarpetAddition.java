package io.github.optijava.opt_carpet_addition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.CarpetSettings;
import carpet.patches.EntityPlayerMPFake;
import com.mojang.brigadier.CommandDispatcher;
import io.github.optijava.opt_carpet_addition.command.PlayerTpCommand;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class OptCarpetAddition implements CarpetExtension, ModInitializer {
    public static final Logger LOGGER = CarpetSettings.LOG;

    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(new OptCarpetAddition());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(OptCarpetSettings.class);
        CarpetServer.settingsManager.addRuleObserver(((serverCommandSource, rule, s) -> {
            if (Objects.equals(rule.name, "forceFakePlayerGameMode")) {
                for (ServerPlayerEntity player : serverCommandSource.getMinecraftServer().getPlayerManager().getPlayerList()) {
                    if (player instanceof EntityPlayerMPFake) {
                        player.setGameMode(GameMode.valueOf(OptCarpetSettings.forceFakePlayerGameMode));
                    }
                }
            }
        }));
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        PlayerTpCommand.registerCommands(dispatcher);
    }

    @Override
    public void onPlayerLoggedIn(ServerPlayerEntity player) {
        if (player instanceof EntityPlayerMPFake && !(Objects.equals(OptCarpetSettings.forceFakePlayerGameMode, "false"))) {
            player.setGameMode(GameMode.valueOf(OptCarpetSettings.forceFakePlayerGameMode));
        }
    }
}
