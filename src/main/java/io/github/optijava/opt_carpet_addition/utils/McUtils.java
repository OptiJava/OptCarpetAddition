package io.github.optijava.opt_carpet_addition.utils;

import carpet.CarpetServer;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;

public class McUtils {
    private static final MinecraftServer server = CarpetServer.minecraft_server;

    public static boolean isOp(GameProfile profile) {
        //? if < 1.21.10 {
        return server.getPlayerList().isOp(profile);
        //?} else {
        /*return server.getPlayerList().isOp(server.getPlayerList().getPlayer(profile.id()).nameAndId());
        *///?}
    }
}
