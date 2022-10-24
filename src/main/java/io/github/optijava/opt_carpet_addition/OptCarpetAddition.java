package io.github.optijava.opt_carpet_addition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.CarpetSettings;
import carpet.patches.EntityPlayerMPFake;
import com.mojang.brigadier.CommandDispatcher;
import io.github.optijava.opt_carpet_addition.commands.ListAdvanceCommand;
import io.github.optijava.opt_carpet_addition.commands.PlayerTpCommand;
import io.github.optijava.opt_carpet_addition.events.FixExperienceBug;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class OptCarpetAddition implements CarpetExtension, ModInitializer {
    public static final Logger LOGGER = CarpetSettings.LOG;

    public static final OptCarpetAddition additionInstance = new OptCarpetAddition();

    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(additionInstance);

        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register(new FixExperienceBug());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(OptCarpetSettings.class);
        CarpetServer.settingsManager.addRuleObserver(((serverCommandSource, rule, s) -> {
            if (Objects.equals(rule.name, "forceFakePlayerGameMode")) {
                for (ServerPlayerEntity player : serverCommandSource.getServer().getPlayerManager().getPlayerList()) {
                    if (player instanceof EntityPlayerMPFake) {
                        player.changeGameMode(GameMode.valueOf(OptCarpetSettings.forceFakePlayerGameMode));
                    }
                }
            }
        }));
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        PlayerTpCommand.registerCommands(dispatcher);
        ListAdvanceCommand.registerCommand(dispatcher);
    }

    @Override
    public void onPlayerLoggedIn(ServerPlayerEntity player) {
        if (player instanceof EntityPlayerMPFake && !(Objects.equals(OptCarpetSettings.forceFakePlayerGameMode, "false"))) {
            player.changeGameMode(GameMode.valueOf(OptCarpetSettings.forceFakePlayerGameMode));
        }
    }
}
