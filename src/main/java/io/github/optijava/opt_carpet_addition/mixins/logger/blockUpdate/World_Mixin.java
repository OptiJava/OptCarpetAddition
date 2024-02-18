package io.github.optijava.opt_carpet_addition.mixins.logger.blockUpdate;

//#if MC < 11900
import carpet.CarpetServer;
import io.github.optijava.opt_carpet_addition.logger.blockUpdate.BlockUpdateLogger;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public class World_Mixin {

    @Inject(
            method = "updateNeighbor",
            at = @At("HEAD")
    )
    private void injectUpdateNeighbor(BlockPos updatingBlockPos, Block sourceBlock, BlockPos centreBlockPos, CallbackInfo ci) {
        Block updatingBlock = CarpetServer.minecraft_server.getCommandSource().getWorld().getChunk(updatingBlockPos).getBlockState(updatingBlockPos).getBlock();

        BlockUpdateLogger.INSTANCE.logBlockUpdate(updatingBlock, updatingBlockPos, sourceBlock, centreBlockPos);
    }
}
//#endif
