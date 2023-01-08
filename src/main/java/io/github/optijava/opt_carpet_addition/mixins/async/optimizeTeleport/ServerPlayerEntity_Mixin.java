package io.github.optijava.opt_carpet_addition.mixins.async.optimizeTeleport;

import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.threading.Threading;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.DifficultyS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRespawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WorldProperties;
import net.minecraft.world.biome.source.BiomeAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
        value = ServerPlayerEntity.class,
        priority = 900
)
public abstract class ServerPlayerEntity_Mixin {

    @Inject(
            method = "teleport",
            at = @At("HEAD"),
            cancellable = true
    )
    public void injectTeleport(ServerWorld targetWorld, double x, double y, double z, float yaw, float pitch, CallbackInfo ci) {
        if (OptCarpetSettings.optimizeTeleport) {
            OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Submit teleport task.");

            Threading.THREAD_POOL.submit(() -> {
                ServerPlayerEntity thisInstance = ((ServerPlayerEntity) (Object) this);

                thisInstance.setCameraEntity(thisInstance);
                thisInstance.stopRiding();
                if (targetWorld == thisInstance.world) {
                    thisInstance.networkHandler.requestTeleport(x, y, z, yaw, pitch);
                } else {
                    ServerWorld serverWorld = thisInstance.getServerWorld();
                    WorldProperties worldProperties = targetWorld.getLevelProperties();
                    thisInstance.networkHandler.sendPacket(new PlayerRespawnS2CPacket(targetWorld.getDimension(), targetWorld.getRegistryKey(), BiomeAccess.hashSeed(targetWorld.getSeed()), thisInstance.interactionManager.getGameMode(), thisInstance.interactionManager.getPreviousGameMode(), targetWorld.isDebugWorld(), targetWorld.isFlat(), true));
                    thisInstance.networkHandler.sendPacket(new DifficultyS2CPacket(worldProperties.getDifficulty(), worldProperties.isDifficultyLocked()));
                    thisInstance.server.getPlayerManager().sendCommandTree(thisInstance);
                    serverWorld.removePlayer(thisInstance, Entity.RemovalReason.CHANGED_DIMENSION);
                    thisInstance.unsetRemoved();
                    thisInstance.refreshPositionAndAngles(x, y, z, yaw, pitch);
                    thisInstance.setWorld(targetWorld);
                    targetWorld.onPlayerTeleport(thisInstance);
                    thisInstance.worldChanged(serverWorld);
                    thisInstance.networkHandler.requestTeleport(x, y, z, yaw, pitch);
                    thisInstance.server.getPlayerManager().sendWorldInfo(thisInstance, targetWorld);
                    thisInstance.server.getPlayerManager().sendPlayerStatus(thisInstance);
                }
            });
            ci.cancel();
        }
    }
}
