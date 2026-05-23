package io.github.optijava.opt_carpet_addition.utils;

import carpet.CarpetServer;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class McUtils {
    public static final MinecraftServer MINECRAFT_SERVER = CarpetServer.minecraft_server;

    public static boolean isOp(GameProfile profile) {
        //? if < 1.21.10 {
        /*return MINECRAFT_SERVER.getPlayerList().isOp(profile);
        *///?} else {
        ServerPlayer player = MINECRAFT_SERVER.getPlayerList().getPlayer(profile.id());
        if (player == null) {
            return false;
        }
        return MINECRAFT_SERVER.getPlayerList().isOp(player.nameAndId());
        //?}
    }
}
