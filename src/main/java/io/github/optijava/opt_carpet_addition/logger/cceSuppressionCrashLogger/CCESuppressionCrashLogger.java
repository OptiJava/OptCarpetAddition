package io.github.optijava.opt_carpet_addition.logger.cceSuppressionCrashLogger;

import carpet.utils.Messenger;
import io.github.optijava.opt_carpet_addition.logger.AbstractLogger;
import io.github.optijava.opt_carpet_addition.logger.LoggerRegister;
//#if MC >= 11900
//$$ import net.minecraft.text.Text;
//#else
import net.minecraft.text.BaseText;
//#endif
import net.minecraft.util.math.BlockPos;

import java.lang.reflect.Field;

public class CCESuppressionCrashLogger extends AbstractLogger {

    public static final CCESuppressionCrashLogger INSTANCE;

    static {
        try {
            INSTANCE = new CCESuppressionCrashLogger(LoggerRegister.class.getField("__cceSuppressionCrash"), "cceSuppressionCrash", null, null, false);
        } catch (NoSuchFieldException e) {
            throw new Error("Failed to init UpdateSuppressionLogger", e);
        }
    }

    protected CCESuppressionCrashLogger(Field acceleratorField, String logName, String def, String[] options, boolean strictOptions) {
        super(acceleratorField, logName, def, options, strictOptions);
    }

    public void logUpdateSuppression(BlockPos pos) {
        if (LoggerRegister.__cceSuppressionCrash) {
            //#if MC >= 11900
            //$$ super.log(() -> new Text[]{Messenger.c("w CCE suppression crash prevented in: ", "m world tick ", "w - at: ", "g [ " + pos.toShortString() + " ]")});
            //#else
            super.log(() -> new BaseText[]{
                    Messenger.c("w CCE suppression crash prevented in: ", "m world tick ", "w - at: ", "g [ " + pos.toShortString() + " ]")
            });
            //#endif
        }
    }
}