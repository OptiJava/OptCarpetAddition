package io.github.optijava.opt_carpet_addition.mixins.rule.dropperCrashFix;
//#if MC < 11900
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DispenserBlock.class)
public class DispenserBlock_Mixin {
    @Inject(
            method = "dispense",
            at = @At("HEAD"),
            cancellable = true
    )
    public void injectDispense(ServerWorld world, BlockPos pos, CallbackInfo ci) {
        if (OptCarpetSettings.dropperCrashFix && !(world.getBlockEntity(pos) instanceof DispenserBlockEntity)) {
            OptCarpetAddition.LOGGER.fatal("[OptCarpetAddition] Detected dispenser crash at %s [%s %s %s]".formatted(world.getRegistryKey().getValue().getPath(), pos.getX(), pos.getY(), pos.getZ()));
            ci.cancel();
        }
    }
}
//#endif
