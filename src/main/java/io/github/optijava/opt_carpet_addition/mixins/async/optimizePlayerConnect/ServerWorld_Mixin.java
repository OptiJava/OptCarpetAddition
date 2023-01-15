package io.github.optijava.opt_carpet_addition.mixins.async.optimizePlayerConnect;

import com.google.common.collect.Lists;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.threading.Threading;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Mixin({ServerWorld.class})
public class ServerWorld_Mixin {
    @Final
    @Shadow
    List<ServerPlayerEntity> players;

    @Inject(
            method = {"getPlayers()Ljava/util/List;"},
            at = {@At("HEAD")},
            cancellable = true
    )
    public void injectGetPlayer(CallbackInfoReturnable<List<ServerPlayerEntity>> cir) {
        if (OptCarpetSettings.optimizePlayerConnect) {
            synchronized (Threading.LOCK) {
                cir.setReturnValue(this.players);
            }
        }
    }

    @Inject(
            method = {"getPlayers(Ljava/util/function/Predicate;)Ljava/util/List;"},
            at = {@At("HEAD")},
            cancellable = true
    )
    @SuppressWarnings("unchecked")
    public void injectGetPlayer(@SuppressWarnings("all") Predicate predicate, CallbackInfoReturnable<List<ServerPlayerEntity>> cir) {
        if (OptCarpetSettings.optimizePlayerConnect) {
            ArrayList<ServerPlayerEntity> list = Lists.newArrayList();

            synchronized (Threading.LOCK) {
                for (ServerPlayerEntity serverPlayerEntity : this.players) {
                    if (predicate.test(serverPlayerEntity)) {
                        list.add(serverPlayerEntity);
                    }
                }
                cir.setReturnValue(list);
            }
        }

    }
}