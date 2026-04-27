package io.github.optijava.opt_carpet_addition.utils;

import carpet.CarpetServer;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
//? > 1.21.10
//import net.minecraft.server.PlayerConfigEntry;



public class McUtils {
    private static final MinecraftServer server = CarpetServer.minecraft_server;

    public static boolean isOp(GameProfile gameProfile) {
        //? if < 1.21.10 {
        return server.getPlayerList().isOp(gameProfile);
        //?} else {
        //return server.getPlayerManager().isOperator(server.getPlayerManager().getPlayer(gameProfile.id()).getPlayerConfigEntry());
        //?}
    }
}
