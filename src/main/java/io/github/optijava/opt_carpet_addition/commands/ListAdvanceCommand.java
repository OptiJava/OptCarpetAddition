package io.github.optijava.opt_carpet_addition.commands;

import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import static io.github.optijava.opt_carpet_addition.OptCarpetSettings.enableListAdvanceCommand;

import static net.minecraft.server.command.CommandManager.literal;

public class ListAdvanceCommand {

    public static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> argumentBuilder = literal("list")
                //#if MC < 12004
                //$$.requires((player) -> carpet.settings.SettingsManager.canUseCommand(source, enableListAdvanceCommand))
                //#else
                //$$.requires((player) ->  carpet.utils.CommandHelper.canUseCommand(player, enableListAdvanceCommand))
                //#endif
                .then(literal("-advance").executes(ListAdvanceCommand::listAdvance));
        dispatcher.register(argumentBuilder);
    }

    private static int listAdvance(CommandContext<ServerCommandSource> context) {
        try {
            MinecraftServer minecraftServer = context.getSource().getServer();
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (ServerPlayerEntity s : minecraftServer.getPlayerManager().getPlayerList()) {
                //#if MC >= 12004
                //$$ sb.append(s.getName().getString()).append("    ").append(s.interactionManager.getGameMode().getName()).append("    ").append(s.networkHandler.getLatency()).append("ms    ").append(s.getIp()).append("    ").append(s.getGameProfile().getId().toString()).append("\n");
                //#else
                sb.append(s.getName().getString()).append("    ").append(s.interactionManager.getGameMode().getName()).append("    ").append(s.pingMilliseconds).append("ms    ").append(s.getIp()).append("    ").append(s.getGameProfile().getId().toString()).append("\n");
                //#endif
            }
            // OptCarpetAddition.LOGGER.info(sb.toString());
            Messenger.m(context.getSource(), sb.toString());
        } catch (Exception e) {
            context.getSource().sendError(Messenger.c("Unexpected exception occurred when command list advance executed."));
            OptCarpetAddition.LOGGER.error("Unexpected exception occurred when command list advance executed.", e);
            return 0;
        }
        return 1;
    }
}