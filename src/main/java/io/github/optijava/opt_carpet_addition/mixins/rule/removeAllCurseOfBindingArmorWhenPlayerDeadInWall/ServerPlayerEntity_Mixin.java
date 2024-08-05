package io.github.optijava.opt_carpet_addition.mixins.rule.removeAllCurseOfBindingArmorWhenPlayerDeadInWall;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntity_Mixin {

    @Inject(
            method = "onDeath",
            at = @At("HEAD")
    )
    public void injectOnDeath(DamageSource source, CallbackInfo ci) {
        //#if MC >= 12000
        //$$ if (OptCarpetSettings.removeAllCurseOfBindingArmorWhenPlayerDeadInWall && ((ServerPlayerEntity) (Object)this).getWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY) && Objects.equals(source.getName(), "inWall")) {
        //#else
        if (OptCarpetSettings.removeAllCurseOfBindingArmorWhenPlayerDeadInWall && ((ServerPlayerEntity) (Object)this).world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY) && Objects.equals(source.getName(), "inWall")) {
        //#endif
            for (ItemStack armor : ((ServerPlayerEntity) (Object)this).getArmorItems()) {
                //#if MC >= 12100
                //$$ if (EnchantmentHelper.hasAnyEnchantmentsWith(armor, net.minecraft.component.EnchantmentEffectComponentTypes.PREVENT_EQUIPMENT_DROP)) {
                //$$     armor.setCount(0);
                //$$ }
                //#else
                if (EnchantmentHelper.getLevel(Enchantments.BINDING_CURSE, armor) > 0) {
                    armor.setCount(0);
                }
                //#endif
            }
        }
    }
}
