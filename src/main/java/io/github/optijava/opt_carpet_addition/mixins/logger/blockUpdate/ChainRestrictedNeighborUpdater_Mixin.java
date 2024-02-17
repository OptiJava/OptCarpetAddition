package io.github.optijava.opt_carpet_addition.mixins.logger.blockUpdate;

//#if MC >= 11900
//$$ import net.minecraft.util.math.BlockPos;
//$$ import net.minecraft.world.block.ChainRestrictedNeighborUpdater;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//$$ @Mixin(ChainRestrictedNeighborUpdater.class)
//$$ public class ChainRestrictedNeighborUpdater_Mixin {

//$$     @Inject(
//$$             method = "enqueue",
//$$             at = @At("HEAD")
//$$     )
//$$     private void injectEnqueue(BlockPos pos, ChainRestrictedNeighborUpdater.Entry entry, CallbackInfo ci) {
//$$         // TODO: log
//$$     }
//$$ }
//#endif
