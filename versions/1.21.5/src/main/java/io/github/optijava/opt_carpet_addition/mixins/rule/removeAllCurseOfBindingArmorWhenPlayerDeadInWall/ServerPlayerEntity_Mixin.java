package io.github.optijava.opt_carpet_addition.mixins.rule.removeAllCurseOfBindingArmorWhenPlayerDeadInWall;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
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
        if (OptCarpetSettings.removeAllCurseOfBindingArmorWhenPlayerDeadInWall && ((ServerPlayerEntity) (Object) this).getServerWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY) && Objects.equals(source.getName(), "inWall")) {
            RegistryEntry<Enchantment> enc = ((ServerPlayerEntity)(Object)this).server.getRegistryManager().getEntryOrThrow(Enchantments.BINDING_CURSE);

            if (EnchantmentHelper.getLevel(enc, ((ServerPlayerEntity) (Object)this).getEquippedStack(EquipmentSlot.HEAD)) > 0) {
                ((ServerPlayerEntity) (Object)this).getEquippedStack(EquipmentSlot.HEAD).setCount(0);
            }
            if (EnchantmentHelper.getLevel(enc, ((ServerPlayerEntity) (Object)this).getEquippedStack(EquipmentSlot.CHEST)) > 0) {
                ((ServerPlayerEntity) (Object)this).getEquippedStack(EquipmentSlot.CHEST).setCount(0);
            }
            if (EnchantmentHelper.getLevel(enc, ((ServerPlayerEntity) (Object)this).getEquippedStack(EquipmentSlot.LEGS)) > 0) {
                ((ServerPlayerEntity) (Object)this).getEquippedStack(EquipmentSlot.LEGS).setCount(0);
            }
            if (EnchantmentHelper.getLevel(enc, ((ServerPlayerEntity) (Object)this).getEquippedStack(EquipmentSlot.FEET)) > 0) {
                ((ServerPlayerEntity) (Object)this).getEquippedStack(EquipmentSlot.FEET).setCount(0);
            }
        }
    }
}

