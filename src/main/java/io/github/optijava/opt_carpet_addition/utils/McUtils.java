package io.github.optijava.opt_carpet_addition.utils;

import carpet.CarpetServer;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;

public class McUtils {
    private static final MinecraftServer server = CarpetServer.minecraft_server;

    public static boolean isOp(GameProfile gameProfile) {
        //#if MC < 12110
        return server.getPlayerManager().isOperator(gameProfile);
        //#else
        //$$ return server.getPlayerManager().isOperator(new PlayerConfigEntry(context.getSource().getPlayerOrThrow().getGameProfile()));
        //#endif
    }
}
