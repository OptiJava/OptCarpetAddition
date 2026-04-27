package io.github.optijava.opt_carpet_addition.mixins.rule.unescapeChatMessage;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import org.apache.commons.lang3.StringEscapeUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerboundChatPacket.class)
public class ChatMessageC2SPacket_Mixin {

    /**
     * Mixin ChatMessageC2SPacket.<init>
     *
     * @author OptiJava
     * @reason rule: unescapeChatMessage
     */
    @Inject(method = "<init>*", at = @At("TAIL"))
    @SuppressWarnings("all")
    public void injectInit(CallbackInfo ci) {
        if (OptCarpetSettings.unescapeChatMessage) {
            try {
                ((ServerboundChatPacket) (Object) this).message = StringEscapeUtils.unescapeJava(((ServerboundChatPacket) (Object) this).message);
            } catch (Exception ignore) {
            }
        }
    }
}
