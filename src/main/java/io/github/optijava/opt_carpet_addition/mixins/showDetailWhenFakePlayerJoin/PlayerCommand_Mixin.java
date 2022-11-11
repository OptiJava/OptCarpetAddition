package io.github.optijava.opt_carpet_addition.mixins.showDetailWhenFakePlayerJoin;

import carpet.CarpetServer;
import carpet.commands.PlayerCommand;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerCommand.class)
public class PlayerCommand_Mixin {
    @Inject(at = @At("TAIL"), method = "spawn(Lcom/mojang/brigadier/context/CommandContext;)I", cancellable = true)
    public void injectSpawn(CommandContext<ServerCommandSource> context, CallbackInfo info) {
        if (context.getSource().getEntity() == null) {
            CarpetServer.minecraft_server.getCommandManager().execute(CarpetServer.minecraft_server.getCommandSource(), "tell @a Unexpected exception occurred when show fake player details: Can't show details because 'context.getSource().getEntity()' is null.");
            OptCarpetAddition.LOGGER.warn("Unexpected exception occurred when show fake player details: Can't show details because 'context.getSource().getEntity()' is null.");
            info.cancel();
        }
        CarpetServer.minecraft_server.getCommandManager().execute(CarpetServer.minecraft_server.getCommandSource(), "tell @a Fake player " + StringArgumentType.getString(context, "player") + " was summoned by " + context.getSource().getName() + " in " + context.getSource().getWorld().toString() + "@" + context.getSource().getEntity().getX() + "/" + context.getSource().getEntity().getY() + "/" + context.getSource().getEntity().getZ());
        OptCarpetAddition.LOGGER.info("Fake player " + StringArgumentType.getString(context, "player") + " was summoned by " + context.getSource().getName() + " in " + context.getSource().getWorld().toString() + "@" + context.getSource().getEntity().getX() + "/" + context.getSource().getEntity().getY() + "/" + context.getSource().getEntity().getZ());
    }
}
