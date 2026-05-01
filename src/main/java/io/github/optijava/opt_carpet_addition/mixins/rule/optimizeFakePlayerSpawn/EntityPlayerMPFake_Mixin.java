//? if < 1.21.10 {
package io.github.optijava.opt_carpet_addition.mixins.rule.optimizeFakePlayerSpawn;

import carpet.patches.EntityPlayerMPFake;
import com.mojang.authlib.GameProfile;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.core.UUIDUtil;
import net.minecraft.server.players.GameProfileCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(EntityPlayerMPFake.class)
public abstract class EntityPlayerMPFake_Mixin {

    @Redirect(
            method = "createFake",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/players/GameProfileCache;get(Ljava/lang/String;)Ljava/util/Optional;")
    )
    private static Optional<GameProfile> redirectCreateFake(GameProfileCache instance, String playerName) {
        if (OptCarpetSettings.optimizeFakePlayerSpawn) {
            return Optional.of(new GameProfile(UUIDUtil.createOfflinePlayerUUID(playerName), playerName));
        } else {
            return instance.get(playerName);
        }
    }

}
//?}