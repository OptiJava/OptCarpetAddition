package io.github.optijava.opt_carpet_addition.mixins.logger.updateSuppression;
//#if MC < 11900
import io.github.optijava.opt_carpet_addition.logger.updateSuppression.UpdateSuppressionLogger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = World.class, priority = 500)
public class World_Mixin {

    @Inject(
            method = "updateNeighbor",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/crash/CrashReport;create(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/util/crash/CrashReport;"),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public void injectUpdateNeighbors(BlockPos pos, Block sourceBlock, BlockPos neighborPos, CallbackInfo ci, BlockState blockState, Throwable throwable) {
        if (throwable instanceof StackOverflowError) {
            UpdateSuppressionLogger.INSTANCE.logSuppression(neighborPos, throwable.getClass().getSimpleName(), ((World) (Object) this));
        }
    }
}
//#endif
