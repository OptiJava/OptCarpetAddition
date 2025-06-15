package io.github.optijava.opt_carpet_addition.mixins.logger.blockUpdate;

//#if MC >= 11900
//$$ import io.github.optijava.opt_carpet_addition.logger.blockUpdate.BlockUpdateLogger;
//$$ import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
//$$ import net.minecraft.block.Block;
//$$ import net.minecraft.block.BlockState;
//$$ import net.minecraft.util.math.BlockPos;
//$$ import net.minecraft.world.block.SimpleNeighborUpdater;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//$$ //#if MC >= 12105
//$$ //$$ import net.minecraft.world.block.WireOrientation;
//$$ //#endif
//$$
//$$ @Mixin(SimpleNeighborUpdater.class)
//$$ public class SimpleNeighborUpdater_Mixin {
//$$
//$$     //#if MC >= 12105
//$$     //$$ @Inject(
//$$     //$$        method = "updateNeighbor(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;Lnet/minecraft/world/block/WireOrientation;Z)V",
//$$     //$$        at = @At("HEAD")
//$$    //$$ )
//$$    //$$ private void injectUpdateNeighbor(BlockState state, BlockPos pos, Block sourceBlock, WireOrientation orientation, boolean notify, CallbackInfo ci) {
//$$    //$$     if (OptCarpetSettings.allowBlockUpdateLogger) {
//$$    //$$        BlockUpdateLogger.INSTANCE.logBlockUpdate(state.getBlock(), pos, sourceBlock);
//$$    //$$     }
//$$    //$$ }
//$$    //#else
//$$     @Inject(
//$$             method = "updateNeighbor(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;Lnet/minecraft/util/math/BlockPos;Z)V",
//$$             at = @At("HEAD")
//$$     )
//$$     private void injectUpdateNeighbor(BlockState updatingBlockState, BlockPos updatingBlockPos, Block sourceBlock, BlockPos centreBlockPos, boolean notify, CallbackInfo ci) {
//$$         if (OptCarpetSettings.allowBlockUpdateLogger) {
//$$            BlockUpdateLogger.INSTANCE.logBlockUpdate(updatingBlockState.getBlock(), updatingBlockPos, sourceBlock, centreBlockPos);
//$$         }
//$$     }
//$$     //#endif
//$$ }
//#endif