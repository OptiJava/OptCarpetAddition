package io.github.optijava.opt_carpet_addition.mixins.logger.blockUpdate;

//#if MC >= 11900
//$$ import carpet.CarpetServer;
//$$ import io.github.optijava.opt_carpet_addition.logger.blockUpdate.BlockUpdateLogger;
//$$ import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
//$$ import net.minecraft.block.Block;
//$$ import net.minecraft.util.math.BlockPos;
//$$ import net.minecraft.world.block.ChainRestrictedNeighborUpdater;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//$$
//$$ @Mixin(ChainRestrictedNeighborUpdater.class)
//$$ public class ChainRestrictedNeighborUpdater_Mixin {
//$$
//$$     @Inject(
//$$             method = "enqueue",
//$$             at = @At("HEAD")
//$$     )
//$$     private void injectEnqueue(BlockPos updatingBlockPos, ChainRestrictedNeighborUpdater.Entry entry, CallbackInfo ci) {
//$$         if (OptCarpetSettings.allowBlockUpdateLogger) {
//$$            Block updatingBlock = CarpetServer.minecraft_server.getCommandSource().getWorld().getChunk(updatingBlockPos).getBlockState(updatingBlockPos).getBlock();
//$$
//$$            Block sourceBlock = null;
//$$            BlockPos centreBlockPos = null;
//$$
//$$            if (entry instanceof ChainRestrictedNeighborUpdater.SimpleEntry simpleEntry) {
//$$                sourceBlock = simpleEntry.sourceBlock();
//$$                //#if MC < 12105
//$$                //$$ centreBlockPos = simpleEntry.sourcePos();
//$$                //#endif
//$$            } else if (entry instanceof ChainRestrictedNeighborUpdater.StatefulEntry statefulEntry) {
//$$                sourceBlock = statefulEntry.sourceBlock();
//$$                //#if MC < 12105
//$$                //$$ centreBlockPos = statefulEntry.sourcePos();
//$$                //#endif
//$$            } else if (entry instanceof ChainRestrictedNeighborUpdater.SixWayEntry sixWayEntry) {
//$$                sourceBlock = sixWayEntry.sourceBlock;
//$$                //#if MC < 12105
//$$                //$$ centreBlockPos = sixWayEntry.pos;
//$$                //#endif
//$$            } else if (entry instanceof ChainRestrictedNeighborUpdater.StateReplacementEntry stateReplacementEntry) {
//$$                sourceBlock = stateReplacementEntry.neighborState().getBlock();
//$$                //#if MC < 12105
//$$                //$$ centreBlockPos = stateReplacementEntry.neighborPos();
//$$                //#endif
//$$            }
//$$            //#if MC >= 12105
//$$            //$$ BlockUpdateLogger.INSTANCE.logBlockUpdate(updatingBlock, updatingBlockPos, sourceBlock);
//$$            //#else
//$$            BlockUpdateLogger.INSTANCE.logBlockUpdate(updatingBlock, updatingBlockPos, sourceBlock, centreBlockPos);
//$$            //#endif
//$$         }
//$$     }
//$$ }
//#endif
