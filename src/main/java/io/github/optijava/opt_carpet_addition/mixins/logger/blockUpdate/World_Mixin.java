package io.github.optijava.opt_carpet_addition.mixins.logger.blockUpdate;

//#if MC < 11900
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
    private void injectUpdateNeighbor(BlockPos pos, Block sourceBlock, BlockPos neighborPos, CallbackInfo ci) {
        // TODO: logs
    }
}
//#endif
