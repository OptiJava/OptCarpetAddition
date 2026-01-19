package io.github.optijava.opt_carpet_addition.utils;

import carpet.CarpetServer;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
//#if MC >= 12110
//$$ import net.minecraft.server.PlayerConfigEntry;
//#endif


public class McUtils {
    private static final MinecraftServer server = CarpetServer.minecraft_server;

    public static boolean isOp(GameProfile gameProfile) {
        //#if MC < 12110
        return server.getPlayerManager().isOperator(gameProfile);
        //#else
        //$$ return server.getPlayerManager().isOperator(server.getPlayerManager().getPlayer(gameProfile.id()).getPlayerConfigEntry());
        //#endif
    }
}
