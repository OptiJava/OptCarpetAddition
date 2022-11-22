package io.github.optijava.opt_carpet_addition.mixins.disabledEnderManPlaceBlockGoal;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(EndermanEntity.PlaceBlockGoal.class)
public class EndermanEntity_Mixin {
    private boolean canPlaceOn(World world, BlockPos posAbove, BlockState carriedState, BlockState stateAbove, BlockState state, BlockPos pos) {
        return stateAbove.isAir() && !state.isAir() && !state.isOf(Blocks.BEDROCK) && state.isFullCube(world, pos) && carriedState.canPlaceAt(world, posAbove) && world.getOtherEntities(this.enderman, Box.from(Vec3d.of(posAbove))).isEmpty();
    }

    @Shadow
    EndermanEntity enderman;

    /**
     * Mixin EndermanEntity.PlaceBlockGoal.tick()V
     *
     * @author OptiJava
     * @reason rule: disabledEnderManPlaceBlockGoal
     */
    @Overwrite
    public void tick() {
        if (!OptCarpetSettings.disabledEnderManPlaceBlockGoal) {
            Random random = this.enderman.getRandom();
            World world = this.enderman.world;
            int i = MathHelper.floor(this.enderman.getX() - 1.0 + random.nextDouble() * 2.0);
            int j = MathHelper.floor(this.enderman.getY() + random.nextDouble() * 2.0);
            int k = MathHelper.floor(this.enderman.getZ() - 1.0 + random.nextDouble() * 2.0);
            BlockPos blockPos = new BlockPos(i, j, k);
            BlockState blockState = world.getBlockState(blockPos);
            BlockPos blockPos2 = blockPos.down();
            BlockState blockState2 = world.getBlockState(blockPos2);
            BlockState blockState3 = this.enderman.getCarriedBlock();
            if (blockState3 != null) {
                blockState3 = Block.postProcessState(blockState3, this.enderman.world, blockPos);
                if (this.canPlaceOn(world, blockPos, blockState3, blockState, blockState2, blockPos2)) {
                    world.setBlockState(blockPos, blockState3, 3);
                    world.emitGameEvent(this.enderman, GameEvent.BLOCK_PLACE, blockPos);
                    this.enderman.setCarriedBlock((BlockState) null);
                }
            }
        }
    }
}
