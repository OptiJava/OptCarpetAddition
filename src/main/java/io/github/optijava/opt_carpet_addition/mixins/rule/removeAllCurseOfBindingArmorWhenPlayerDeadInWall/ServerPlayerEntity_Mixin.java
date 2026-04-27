package io.github.optijava.opt_carpet_addition.mixins.rule.removeAllCurseOfBindingArmorWhenPlayerDeadInWall;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerEntity_Mixin {
    //TODO support 26.1
//    @Inject(
//            method = "die",
//            at = @At("HEAD")
//    )
//    public void injectOnDeath(DamageSource source, CallbackInfo ci) {
//        if (OptCarpetSettings.removeAllCurseOfBindingArmorWhenPlayerDeadInWall && ((ServerPlayerEntity) (Object)this).getWorld().().getBoolean(GameRules.KEEP_INVENTORY) && Objects.equals(source.getName(), "inWall")) {
//            for (ItemStack armor : ((ServerPlayer) (Object)this).getArmorItems()) {
//                if (EnchantmentHelper.has(armor, DataComponentType)) {
//                     armor.setCount(0);
//                }
//
//                if (EnchantmentHelper.getItemEnchantmentLevel(, armor) > 0) {
//                    armor.setCount(0);
//                }
//            }
//        }
//    }
}
