package io.github.optijava.opt_carpet_addition.mixins.async.optimizePlayerConnect;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.threading.Threading;
import net.minecraft.server.PlayerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.Map;

@Mixin(PlayerManager.class)
public class PlayerManager_Mixin {

    @Redirect(
            method = "onPlayerConnect",
            at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z")
    )
    @SuppressWarnings("unchecked")
    public boolean redirectAdd(@SuppressWarnings("all") List instance, Object entity) {
        if (OptCarpetSettings.optimizePlayerConnect) {
            synchronized (Threading.LOCK) {
                instance.add(entity);
            }
        } else {
            instance.add(entity);
        }

        return true;
    }

    @Redirect(
            method = "onPlayerConnect",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;")
    )
    @SuppressWarnings("unchecked")
    public Object redirectPut(@SuppressWarnings("all") Map instance, Object k, Object v) {
        if (OptCarpetSettings.optimizePlayerConnect) {
            synchronized (Threading.LOCK) {
                instance.put(k, v);
            }
        } else {
            instance.put(k, v);
        }
        return v;
    }
}
