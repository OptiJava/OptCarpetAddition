package io.github.optijava.opt_carpet_addition.mixins.rule.disallowRedstoneWireConnectsToOpenTrapdoor;

 import net.minecraft.world.level.block.Block;
 import net.minecraft.world.level.block.RedStoneWireBlock;
 import net.minecraft.world.level.block.state.BlockState;
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.injection.At;
 import org.spongepowered.asm.mixin.injection.Redirect;

 import io.github.optijava.opt_carpet_addition.OptCarpetSettings;

 @Mixin(value = RedStoneWireBlock.class,priority = 2000)
 public class RedstoneWireBlock_Mixin {
     @Redirect(
             method = "getConnectingSide(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;Z)Lnet/minecraft/world/level/block/state/properties/RedstoneSide;",
             at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;")
     )
     private Block redirectGetBlock(BlockState instance) {
         if (OptCarpetSettings.disallowRedstoneWireConnectsToOpenTrapdoor) {
             return (RedStoneWireBlock) (Object) this;
         } else {
             return instance.getBlock();
         }
     }
 }
