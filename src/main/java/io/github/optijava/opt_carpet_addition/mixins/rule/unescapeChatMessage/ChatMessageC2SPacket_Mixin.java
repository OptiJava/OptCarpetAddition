package io.github.optijava.opt_carpet_addition.mixins.rule.unescapeChatMessage;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.apache.commons.lang3.StringEscapeUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatMessageC2SPacket.class)
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
                ((ChatMessageC2SPacket) (Object) this).chatMessage = StringEscapeUtils.unescapeJava(((ChatMessageC2SPacket) (Object) this).chatMessage);
            } catch (Exception ignore) {
            }
        }
    }
}
