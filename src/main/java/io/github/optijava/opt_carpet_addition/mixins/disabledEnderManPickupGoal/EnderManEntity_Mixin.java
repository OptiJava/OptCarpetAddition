package io.github.optijava.opt_carpet_addition.mixins.disabledEnderManPickupGoal;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(EndermanEntity.PickUpBlockGoal.class)
public class EnderManEntity_Mixin {
    @Shadow
    EndermanEntity enderman;

    /**
     * Mixin EndermanEntity.PickUpBlockGoal.tick()V
     *
     * @author OptiJava
     * @reason rule: disabledEnderManPickupGoal
     */
    @Overwrite
    public void tick() {
        if (!OptCarpetSettings.disabledEnderManPickupGoal) {
            Random random = this.enderman.getRandom();
            World world = this.enderman.world;
            int i = MathHelper.floor(this.enderman.getX() - 2.0 + random.nextDouble() * 4.0);
            int j = MathHelper.floor(this.enderman.getY() + random.nextDouble() * 3.0);
            int k = MathHelper.floor(this.enderman.getZ() - 2.0 + random.nextDouble() * 4.0);
            BlockPos blockPos = new BlockPos(i, j, k);
            BlockState blockState = world.getBlockState(blockPos);
            Block block = blockState.getBlock();
            Vec3d vec3d = new Vec3d((double) MathHelper.floor(this.enderman.getX()) + 0.5, (double) j + 0.5, (double) MathHelper.floor(this.enderman.getZ()) + 0.5);
            Vec3d vec3d2 = new Vec3d((double) i + 0.5, (double) j + 0.5, (double) k + 0.5);
            BlockHitResult blockHitResult = world.raycast(new RaycastContext(vec3d, vec3d2, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, this.enderman));
            boolean bl = blockHitResult.getBlockPos().equals(blockPos);
            if (block.isIn(BlockTags.ENDERMAN_HOLDABLE) && bl) {
                world.removeBlock(blockPos, false);
                this.enderman.setCarriedBlock(blockState.getBlock().getDefaultState());
            }
        }
    }
}
