package io.github.optijava.opt_carpet_addition.commands;

import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import static io.github.optijava.opt_carpet_addition.OptCarpetSettings.enableListAdvanceCommand;

public class ListAdvanceCommand {

    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> argumentBuilder = Commands.literal("list")
                .requires((player) ->  carpet.utils.CommandHelper.canUseCommand(player, enableListAdvanceCommand))
                .then(Commands.literal("-advance").executes(ListAdvanceCommand::listAdvance));
        dispatcher.register(argumentBuilder);
    }

    private static int listAdvance(CommandContext<CommandSourceStack> context) {
        try {
            MinecraftServer minecraftServer = context.getSource().getServer();
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            minecraftServer.getPlayerList().getPlayers().forEach(s -> {
                //? if >= 1.21.10 {
                sb.append(s.getName().getString()).append("    ").append(s.gameMode().getName()).append("    ").append(s.connection.latency()).append("ms    ").append(s.getIpAddress()).append("    ").append(s.getGameProfile().id().toString()).append("\n");
                //?} else if >= 1.21.5 {
                /*sb.append(s.getName().getString()).append("    ").append(s.gameMode).append("    ").append(s.connection.latency()).append("ms    ").append(s.getIpAddress()).append("    ").append(s.getGameProfile().getId().toString()).append("\n");
                *///?}
            });
            Messenger.m(context.getSource(), sb.toString());
        } catch (Exception e) {
            context.getSource().sendFailure(Messenger.c("Unexpected exception occurred when command list advance executed."));
            OptCarpetAddition.LOGGER.error("Unexpected exception occurred when command list advance executed.", e);
            return 0;
        }
        return 1;
    }
}