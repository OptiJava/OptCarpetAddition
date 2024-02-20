package io.github.optijava.opt_carpet_addition.logger;

import carpet.logging.LoggerRegistry;
import io.github.optijava.opt_carpet_addition.logger.blockUpdate.BlockUpdateLogger;
import io.github.optijava.opt_carpet_addition.logger.cceSuppressionCrashLogger.CCESuppressionCrashLogger;
import io.github.optijava.opt_carpet_addition.logger.disk.DiskHUDLogger;
//#if MC < 11900
import io.github.optijava.opt_carpet_addition.logger.updateSuppression.UpdateSuppressionLogger;
//#endif

public class LoggerRegister {
    private LoggerRegister() {
    }

    public static boolean __disk = false;

    //#if MC < 11900
    public static boolean __updateSuppression = false;
    //#endif

    public static boolean __cceSuppressionCrash = false;

    public static boolean __blockUpdate = false;

    public static void registry() {
        LoggerRegistry.registerLogger("disk", DiskHUDLogger.INSTANCE);
        //#if MC < 11900
        LoggerRegistry.registerLogger("updateSuppression", UpdateSuppressionLogger.INSTANCE);
        //#endif
        LoggerRegistry.registerLogger("cceSuppressionCrash", CCESuppressionCrashLogger.INSTANCE);
        LoggerRegistry.registerLogger("blockUpdate", BlockUpdateLogger.INSTANCE);
    }
}
