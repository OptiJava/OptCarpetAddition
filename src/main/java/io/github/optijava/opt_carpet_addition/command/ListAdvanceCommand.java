package io.github.optijava.opt_carpet_addition.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

import static net.minecraft.server.command.CommandManager.literal;

public class ListAdvanceCommand {
    public static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> argumentBuilder = literal("list")
                .then(literal("advance").executes(ListAdvanceCommand::listAdvance));
        dispatcher.register(argumentBuilder);
    }

    public static int listAdvance(CommandContext<ServerCommandSource> context) {
        try {
            MinecraftServer minecraftServer = context.getSource().getMinecraftServer();
            StringBuilder sb = new StringBuilder();
            for (ServerPlayerEntity s : minecraftServer.getPlayerManager().getPlayerList()) {
                sb.append(s.getGameProfile().getName()).append("    ").append(s.interactionManager.getGameMode().getName()).append("    ").append(s.pingMilliseconds).append("ms    ").append(s.getGameProfile().getId().toString()).append("\n");
            }
            context.getSource().sendFeedback(new LiteralText(sb.toString()), true);
        } catch (Exception e) {
            context.getSource().sendError(new LiteralText("Unexpected exception occurred when command list advance executed."));
            return 0;
        }
        return 1;
    }
}
