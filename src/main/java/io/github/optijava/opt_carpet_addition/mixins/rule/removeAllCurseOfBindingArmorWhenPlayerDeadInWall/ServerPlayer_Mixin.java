//package io.github.optijava.opt_carpet_addition.mixins.rule.removeAllCurseOfBindingArmorWhenPlayerDeadInWall;
//
//import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//import net.minecraft.world.level.GameRules;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.Objects;
//
//@Mixin(ServerPlayer.class)
//public class ServerPlayer_Mixin {
//    @Inject(
//            method = "die",
//            at = @At("HEAD")
//    )
//    public void injectOnDeath(DamageSource source, CallbackInfo ci) {
//        ServerPlayer player = (ServerPlayer) (Object) this;
//        if (OptCarpetSettings.removeAllCurseOfBindingArmorWhenPlayerDeadInWall && player.serverLevel().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) && Objects.equals(source.getMsgId(), "inWall")) {
//            for (ItemStack armor : player.getInventory().equipment) {
//                if (EnchantmentHelper.has(armor, EnchantmentEffectComponents.PREVENT_EQUIPMENT_DROP)) {
//                     armor.setCount(0);
//                }
//            }
//        }
//    }
//}
