package io.github.optijava.opt_carpet_addition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.CarpetSettings;
import com.mojang.brigadier.CommandDispatcher;
import io.github.optijava.opt_carpet_addition.command.PlayerTpCommand;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.logging.log4j.Logger;

public class OptCarpetAddition implements CarpetExtension, ModInitializer {

    public static final MinecraftServer minecraftServer = CarpetServer.minecraft_server;
    public static final Logger LOGGER = CarpetSettings.LOG;

    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(new OptCarpetAddition());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(OptCarpetSettings.class);
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        PlayerTpCommand.registerCommands(dispatcher);
    }
}
