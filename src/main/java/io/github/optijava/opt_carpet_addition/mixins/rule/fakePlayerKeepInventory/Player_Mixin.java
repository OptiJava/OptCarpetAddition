package io.github.optijava.opt_carpet_addition.mixins.rule.fakePlayerKeepInventory;

import carpet.patches.EntityPlayerMPFake;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class Player_Mixin {
    @Inject(
            method = "dropEquipment",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getGameRules()Lnet/minecraft/world/level/gamerules/GameRules;"),
            cancellable = true
    )
    public void injectDropEquipment(ServerLevel level, CallbackInfo ci) {
        if (OptCarpetSettings.fakePlayerKeepInventory && ((Player)(Object) this) instanceof EntityPlayerMPFake) {
            ci.cancel();
        }
    }
}
