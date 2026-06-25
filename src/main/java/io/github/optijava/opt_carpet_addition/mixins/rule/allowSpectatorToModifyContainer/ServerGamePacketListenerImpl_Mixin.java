package io.github.optijava.opt_carpet_addition.mixins.rule.allowSpectatorToModifyContainer;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImpl_Mixin {
    @Redirect(
            method = "handleContainerClick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;isSpectator()Z")
    )
    public boolean redirectIsSpectator(ServerPlayer instance) {
        if (OptCarpetSettings.allowSpectatorToModifyContainer) {
            return false;
        } else {
            return instance.isSpectator();
        }
    }

    @Redirect(
            method = "handleContainerSlotStateChanged",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;isSpectator()Z")
    )
    public boolean redirectIsSpectator1(ServerPlayer instance) {
        if (OptCarpetSettings.allowSpectatorToModifyContainer) {
            return false;
        } else {
            return instance.isSpectator();
        }
    }
}
