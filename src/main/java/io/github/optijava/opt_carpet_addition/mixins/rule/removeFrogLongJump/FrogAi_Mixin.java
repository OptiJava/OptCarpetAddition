package io.github.optijava.opt_carpet_addition.mixins.rule.removeFrogLongJump;

//?if >= 26.1 {

import com.google.common.collect.ImmutableList;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.world.entity.ai.ActivityData;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.FrogAi;
import net.minecraft.world.entity.schedule.Activity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(FrogAi.class)
public class FrogAi_Mixin {
    @Inject(
            method = "updateActivity",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void injectUpdateActivity(Frog body, CallbackInfo ci) {
        if (OptCarpetSettings.removeFrogLongJump) {
            body.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.TONGUE, Activity.LAY_SPAWN, Activity.SWIM, Activity.IDLE));
            ci.cancel();
        }
    }

    @Inject(
            method = "getActivities",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void injectGetActivities(CallbackInfoReturnable<List<ActivityData<Frog>>> cir) {
        if (OptCarpetSettings.removeFrogLongJump) {
            cir.setReturnValue(List.copyOf(cir.getReturnValue().subList(0, cir.getReturnValue().size() - 1)));
        }
    }
}
//? }
