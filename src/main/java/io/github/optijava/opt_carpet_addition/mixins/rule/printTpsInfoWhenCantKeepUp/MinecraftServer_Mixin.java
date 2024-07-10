package io.github.optijava.opt_carpet_addition.mixins.rule.printTpsInfoWhenCantKeepUp;

import carpet.CarpetServer;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.server.MinecraftServer;
//#if MC >= 12004
//$$ import net.minecraft.server.ServerTickManager;
//$$ import net.minecraft.util.TimeHelper;
//#else
import carpet.helpers.TickSpeed;
//#endif
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServer_Mixin {

    @Inject(
            method = "runServer",
            at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", shift = At.Shift.AFTER),
            remap = false
    )
    private void injectRunServer(CallbackInfo ci) {
        // reference: carpet logger `tps`
        // carpet.logging.HUDController#126 `send_tps_display(MinecraftServer server)`

        if (OptCarpetSettings.printTpsInfoWhenCantKeepUp) {
            //#if MC >= 12004
            //$$ double mspt = (double)CarpetServer.minecraft_server.getAverageNanosPerTick() / (double) TimeHelper.MILLI_IN_NANOS;
            //$$ ServerTickManager trm = CarpetServer.minecraft_server.getTickManager();
            //$$ double tps = 1000.0 / Math.max(trm.isSprinting() ? 0.0 : (double)trm.getMillisPerTick(), mspt);
            //$$ if (trm.isFrozen()) {
            //$$     tps = 0.0;
            //$$ }
            //#else
            double mspt = MathHelper.average(CarpetServer.minecraft_server.lastTickLengths) * 1.0E-6;
            double tps = 1000.0 / Math.max(TickSpeed.time_warp_start_time != 0L ? 0.0 : (double) TickSpeed.mspt, mspt);
            if (TickSpeed.isPaused()) {
                tps = 0.0;
            }
            //#endif

            OptCarpetAddition.LOGGER.warn("Server TPS: %s, MSPT: %s".formatted(tps, mspt));
        }
    }
}
