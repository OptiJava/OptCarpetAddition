package io.github.optijava.opt_carpet_addition.mixins.rule.removeAllCurseOfBindingArmorWhenPlayerDeadInWall;

//? if >= 26.1 {
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(ServerPlayer.class)
public class ServerPlayer_Mixin {
    @Inject(
            method = "die",
            at = @At("HEAD")
    )
    public void injectDie(DamageSource source, CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer) (Object) this;
        if (OptCarpetSettings.removeAllCurseOfBindingArmorWhenPlayerDeadInWall && player.level().getGameRules().get(GameRules.KEEP_INVENTORY) && Objects.equals(source.getMsgId(), "inWall")) {
            final List<ItemStack> list = List.of(player.getInventory().equipment.get(EquipmentSlot.CHEST), player.getInventory().equipment.get(EquipmentSlot.FEET), player.getInventory().equipment.get(EquipmentSlot.LEGS), player.getInventory().equipment.get(EquipmentSlot.HEAD));
            for (ItemStack armor : list) {
                if (EnchantmentHelper.has(armor, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) {
                     armor.setCount(0);
                }
            }
        }
    }
}
//?} else if >= 1.21.11 {
/*import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(ServerPlayer.class)
public class ServerPlayer_Mixin {

    @Inject(
            method = "die",
            at = @At("HEAD")
    )
    public void injectOnDeath(DamageSource source, CallbackInfo ci) {
        if (OptCarpetSettings.removeAllCurseOfBindingArmorWhenPlayerDeadInWall && ((ServerPlayer) (Object)this).level().getGameRules().get(GameRules.KEEP_INVENTORY) && Objects.equals(source.getMsgId(), "inWall")) {
            final List<ItemStack> list = List.of(((ServerPlayer)(Object)this).getInventory().equipment.get(EquipmentSlot.CHEST), ((ServerPlayer) (Object)this).getInventory().equipment.get(EquipmentSlot.FEET), ((ServerPlayer) (Object)this).getInventory().equipment.get(EquipmentSlot.LEGS), ((ServerPlayer) (Object)this).getInventory().equipment.get(EquipmentSlot.HEAD));
            for (ItemStack armor : list) {
                if (EnchantmentHelper.has(armor, net.minecraft.world.item.enchantment.EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) {
                    armor.setCount(0);
                }
            }
        }
    }
}
*///?} else if >= 1.21.5 {
/*import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(ServerPlayer.class)
public class ServerPlayer_Mixin {

    @Inject(
            method = "die",
            at = @At("HEAD")
    )
    public void injectOnDeath(DamageSource source, CallbackInfo ci) {
        if (OptCarpetSettings.removeAllCurseOfBindingArmorWhenPlayerDeadInWall && Objects.equals(source.getMsgId(), "inWall")) {
            final List<ItemStack> list = List.of(((ServerPlayer) (Object)this).getInventory().equipment.get(EquipmentSlot.CHEST), ((ServerPlayer) (Object)this).getInventory().equipment.get(EquipmentSlot.FEET), ((ServerPlayer) (Object)this).getInventory().equipment.get(EquipmentSlot.LEGS), ((ServerPlayer) (Object)this).getInventory().equipment.get(EquipmentSlot.HEAD));
            for (ItemStack armor : list) {
                if (EnchantmentHelper.has(armor, EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) {
                    armor.setCount(0);
                }
            }
        }
    }
}
*///?} else {

/*import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(ServerPlayer.class)
public class ServerPlayer_Mixin {

    @Inject(
            method = "die",
            at = @At("HEAD")
    )
    public void injectOnDeath(DamageSource source, CallbackInfo ci) {
        if (OptCarpetSettings.removeAllCurseOfBindingArmorWhenPlayerDeadInWall && Objects.equals(source.getMsgId(), "inWall")) {
            for (ItemStack armor : ((ServerPlayer) (Object) this).getArmorSlots()) {
                if (EnchantmentHelper.has(armor, net.minecraft.world.item.enchantment.EnchantmentEffectComponents.PREVENT_ARMOR_CHANGE)) {
                    armor.setCount(0);
                }
            }
        }
    }
}
*///?}
