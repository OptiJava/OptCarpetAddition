package io.github.optijava.opt_carpet_addition.mixins.rule.unescapeChatMessage;
//#if MC < 11900
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import net.minecraft.client.gui.screen.Screen;
import org.apache.commons.lang3.StringEscapeUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Screen.class)
public class Screen_Mixin {

    /**
     * Mixin Screen.sendMessage(Ljava/lang/String;)V
     *
     * @author OptiJava
     * @reason rule: unescapeChatMessage
     */
    @ModifyArg(method = "sendMessage(Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;sendMessage(Ljava/lang/String;Z)V"), index = 0)
    @SuppressWarnings("deprecation")
    public String injectSendMessage(String message) {
        if (OptCarpetSettings.unescapeChatMessage) {
            try {
                return StringEscapeUtils.unescapeJava(message);
            } catch (Exception e) {
                return message;
            }
        } else {
            return message;
        }
    }
}
//#endif
