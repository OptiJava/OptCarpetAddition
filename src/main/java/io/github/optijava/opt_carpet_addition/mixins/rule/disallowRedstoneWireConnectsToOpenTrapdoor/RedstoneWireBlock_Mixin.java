package io.github.optijava.opt_carpet_addition.mixins.rule.disallowRedstoneWireConnectsToOpenTrapdoor;

//#if MC >= 12000
//$$ import net.minecraft.block.*;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Redirect;
//$$
//$$ import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
//$$
//$$ @Mixin(value = RedstoneWireBlock.class, priority = 2000)
//$$ public class RedstoneWireBlock_Mixin {
//$$     @Redirect(
//$$             method = "getRenderConnectionType(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;Z)Lnet/minecraft/block/enums/WireConnection;",
//$$             at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;")
//$$     )
//$$     private Block redirectGetBlock(BlockState instance) {
//$$         if (OptCarpetSettings.disallowRedstoneWireConnectsToOpenTrapdoor) {
//$$             return (RedstoneWireBlock) (Object) this;
//$$         } else {
//$$             return instance.getBlock();
//$$         }
//$$     }
//$$ }
//#endif
