package io.github.optijava.opt_carpet_addition.mixins.rule.removeDataModifyRestriction;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.commands.data.EntityDataAccessor;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.Entity;
//?if > 1.21.5 {
import net.minecraft.world.level.storage.TagValueInput;
//?}
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.UUID;

@Mixin(EntityDataAccessor.class)
public class EntityDataAccessor_Mixin {

    @Final
    @Shadow
    private Entity entity;

    @Final
    @Shadow
    private static Logger LOGGER;

    @Inject(
            method = "setData",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/brigadier/exceptions/SimpleCommandExceptionType;create()Lcom/mojang/brigadier/exceptions/CommandSyntaxException;"),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            cancellable = true
    )
    public void setData(CompoundTag tag, CallbackInfo ci) {
        if (OptCarpetSettings.removeDataModifyRestriction) {
            UUID uuid = this.entity.getUUID();

            //?if > 1.21.5 {
            try (ProblemReporter.ScopedCollector reporter = new ProblemReporter.ScopedCollector(this.entity.problemPath(), LOGGER)) {
                this.entity.load(TagValueInput.create(reporter, this.entity.registryAccess(), tag));
                this.entity.setUUID(uuid);
            }
            ci.cancel();
            //?}

            //?if <= 1.21.5 {
            //this.entity.load(tag);
            //this.entity.setUUID(uuid);
            //ci.cancel();
            //?}
        }
    }
}
