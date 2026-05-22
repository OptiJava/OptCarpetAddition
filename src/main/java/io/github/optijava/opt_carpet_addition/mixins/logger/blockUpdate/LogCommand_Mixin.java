package io.github.optijava.opt_carpet_addition.mixins.logger.blockUpdate;

import carpet.commands.LogCommand;
import carpet.utils.Messenger;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(LogCommand.class)
public class LogCommand_Mixin {
    @Inject(
            method = "toggleSubscription",
            at = @At("HEAD")
    )
    private static void injectToggleSubscription(CommandSourceStack source, String player_name, String logName, CallbackInfoReturnable<Integer> cir) {
        if (
                !OptCarpetSettings.allowBlockUpdateLogger && Objects.equals(logName, "blockUpdate")
        ) {
            Messenger.m(source, "Rule `allowBlockUpdateLogger` is not enabled. Logger `blockUpdate` will have no effect!");
        }
    }
}
