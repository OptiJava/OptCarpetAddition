package io.github.optijava.opt_carpet_addition.mixins.rule.async.optimizeTeleport;
//#if MC < 11900
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.threading.Threading;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(
        value = ServerPlayNetworkHandler.class,
        priority = 900
)
public abstract class ServerPlayNetworkHandler_Mixin {

    @Shadow
    private Vec3d requestedTeleportPos;
    @Shadow
    private int requestedTeleportId;
    @Shadow
    private int teleportRequestTick;
    @Shadow
    public ServerPlayerEntity player;
    @Shadow
    private int ticks;

    @Inject(
            method = "requestTeleport(DDDFFLjava/util/Set;Z)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void injectRequestTeleport(double x, double y, double z, float yaw, float pitch, Set<PlayerPositionLookS2CPacket.Flag> flags, boolean shouldDismount, CallbackInfo ci) {
        if (OptCarpetSettings.optimizeTeleport) {
            double d = flags.contains(PlayerPositionLookS2CPacket.Flag.X) ? this.player.getX() : 0.0;
            double e = flags.contains(PlayerPositionLookS2CPacket.Flag.Y) ? this.player.getY() : 0.0;
            double f = flags.contains(PlayerPositionLookS2CPacket.Flag.Z) ? this.player.getZ() : 0.0;
            float g = flags.contains(PlayerPositionLookS2CPacket.Flag.Y_ROT) ? this.player.getYaw() : 0.0f;
            float h = flags.contains(PlayerPositionLookS2CPacket.Flag.X_ROT) ? this.player.getPitch() : 0.0f;
            this.requestedTeleportPos = new Vec3d(x, y, z);
            if (++this.requestedTeleportId == Integer.MAX_VALUE) {
                this.requestedTeleportId = 0;
            }
            this.teleportRequestTick = this.ticks;
            this.player.updatePositionAndAngles(x, y, z, yaw, pitch);

            OptCarpetAddition.LOGGER.info("[OptCarpetAddition] Submit teleport task.");
            Threading.THREAD_POOL.submit(() -> this.player.networkHandler.sendPacket(new PlayerPositionLookS2CPacket(x - d, y - e, z - f, yaw - g, pitch - h, flags, this.requestedTeleportId, shouldDismount)));
            ci.cancel();
        }
    }
}
//#endif
