package io.github.optijava.opt_carpet_addition.mixins.rule.optimizeFakePlayerSpawn;

import carpet.patches.EntityPlayerMPFake;
import com.mojang.authlib.GameProfile;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
//#if MC >= 11900
//$$ import net.minecraft.util.Uuids;
//#else
import net.minecraft.entity.player.PlayerEntity;
//#endif
import net.minecraft.util.UserCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(EntityPlayerMPFake.class)
public abstract class EntityPlayerMPFake_Mixin {

    @Redirect(
            method = "createFake",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/UserCache;findByName(Ljava/lang/String;)Ljava/util/Optional;")
    )
    private static Optional<GameProfile> redirectCreateFake(UserCache instance, String playerName) {
        if (OptCarpetSettings.optimizeFakePlayerSpawn) {
            //#if MC >= 11900
            //$$ return Optional.of(new GameProfile(Uuids.getOfflinePlayerUuid(playerName), playerName));
            //#else
            return Optional.of(new GameProfile(PlayerEntity.getOfflinePlayerUuid(playerName), playerName));
            //#endif
        } else {
            return instance.findByName(playerName);
        }
    }
}
