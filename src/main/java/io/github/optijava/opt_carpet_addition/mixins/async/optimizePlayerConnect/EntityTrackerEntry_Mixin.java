package io.github.optijava.opt_carpet_addition.mixins.async.optimizePlayerConnect;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.threading.Threading;
import net.minecraft.server.network.EntityTrackerEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;

@Mixin(EntityTrackerEntry.class)
public class EntityTrackerEntry_Mixin {

    @Redirect(
            method = "tick",
            at = @At(value = "INVOKE", target = "Ljava/util/Iterator;next()Ljava/lang/Object;")
    )
    public Object redirectNext(Iterator instance) {
        if (OptCarpetSettings.optimizePlayerConnect) {
            synchronized (Threading.LOCK) {
                return instance.next();
            }
        } else {
            return instance.next();
        }
    }
}
