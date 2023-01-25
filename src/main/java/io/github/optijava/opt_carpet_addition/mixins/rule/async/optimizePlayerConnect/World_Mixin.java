package io.github.optijava.opt_carpet_addition.mixins.rule.async.optimizePlayerConnect;

import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ConcurrentModificationException;
import java.util.function.Consumer;

@Mixin(World.class)
public class World_Mixin {
    @Inject(
            method = "tickEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/crash/CrashReport;create(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/util/crash/CrashReport;"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    public void injectTickWorlds(@SuppressWarnings("all") Consumer tickConsumer, Entity entity, CallbackInfo ci, Throwable throwable) {
        if (OptCarpetSettings.optimizePlayerConnect && (throwable instanceof ConcurrentModificationException)) {
            OptCarpetAddition.LOGGER.fatal("[OptCarpetAddition] Unexpected exception when ticking entity: %s (class %s) in BlockPos %s (in chunk %s %s %s)".formatted(entity.toString(), entity.getClass().toString(), entity.getBlockPos().toString(), entity.getBlockX() / 16, entity.getBlockY() / 16, entity.getBlockZ() / 16), throwable);
            OptCarpetAddition.LOGGER.fatal("[OptCarpetAddition] If you see this message, you may need to restart the server. This exception may caused by OptCarpetAddition, if you think so, please send the reports to GitHub Issues.");
            OptCarpetAddition.LOGGER.fatal("[OptCarpetAddition] I'm sorry to hear that.");
            ci.cancel();
        }
    }
}
