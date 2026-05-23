package io.github.optijava.opt_carpet_addition.mixins.logger.blockUpdate;

 import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
 import io.github.optijava.opt_carpet_addition.logger.blockUpdate.BlockUpdateLogger;
 import net.minecraft.core.BlockPos;
 import net.minecraft.world.level.Level;
 import net.minecraft.world.level.block.Block;
 import net.minecraft.world.level.block.state.BlockState;
 import net.minecraft.world.level.redstone.NeighborUpdater;
 //?if > 1.21 {
  import net.minecraft.world.level.redstone.Orientation;
 //? }
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.injection.At;
 import org.spongepowered.asm.mixin.injection.Inject;
 import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

 @Mixin(NeighborUpdater.class)
 public interface NeighborUpdater_Mixin {

     @Inject(
             method = "executeUpdate",
             at = @At("HEAD")
     )
     //?if > 1.21 {
      private static void injectExecuteUpdate(Level level, BlockState state, BlockPos pos, Block changedBlock, Orientation orientation, boolean movedByPiston, CallbackInfo ci) {
     //? } else {
     /*private static void injectExecuteUpdate(Level level, BlockState state, BlockPos pos, Block changedBlock, BlockPos blockPos2, boolean movedByPiston, CallbackInfo ci) {
     *///? }
         if (OptCarpetSettings.allowBlockUpdateLogger) {
             /*
             * 以下是我的研究
             * 对于26.1顺序是：
             *  源方块或其他逻辑调用neighborUpdater.neighborChanged()  neighborUpdater可能是collecting/instant
             *  neighborchanged()调用collectingNeighborUpdater.addAndRun()
             *  addAndRun()只将需要被更新的方块以neighborUpdates实例的形式储存入人工栈
             *  在被更新时，遍历人工栈，调用NeighborUpdater.executeUpdate()等
             *  executeUpdate()调用
             *  本方法的参数：state和pos均为被更新的方块的信息，changedBlock是中心方块更新前的方块类型，orientation为中心方块的朝向
             * */
             BlockUpdateLogger.INSTANCE.logBlockUpdate(pos, state.getBlock(), changedBlock);
             // OptCarpetAddition.LOGGER.info(level.dimension().identifier().toShortString() + " centre block after change: " + level.getBlockState(pos) + " state.getBlock():" + state.getBlock() + " pos:" + pos.toShortString() + " changedblock:" + changedBlock.getName() + " movedbypiston:" + movedByPiston);
         }
     }
 }
