package io.github.optijava.opt_carpet_addition.mixins.rule.noteBlockSuppressionReintroduced;

//#if MC >= 12000
//$$ import net.minecraft.block.*;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Redirect;

//$$ import io.github.optijava.opt_carpet_addition.OptCarpetSettings;

//$$ @Mixin(RedstoneWireBlock.class)
//$$ public class RedstoneWireBlock_Mixin {
//$$     @Redirect(
//$$             method = "getRenderConnectionType(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;Z)Lnet/minecraft/block/enums/WireConnection;",
//$$             at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;")
//$$     )
//$$     private Block redirectGetBlock(BlockState instance) {
//$$         if (OptCarpetSettings.noteBlockSuppressionReintroduced) {
//$$             return (RedstoneWireBlock) (Object) this;
//$$         } else {
//$$             return instance.getBlock();
//$$         }
//$$     }
//$$ }
//#endif
