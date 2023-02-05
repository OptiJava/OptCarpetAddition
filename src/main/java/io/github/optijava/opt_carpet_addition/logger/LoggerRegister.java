package io.github.optijava.opt_carpet_addition.logger;

import carpet.logging.LoggerRegistry;
import io.github.optijava.opt_carpet_addition.logger.disk.DiskHUDLogger;
import io.github.optijava.opt_carpet_addition.logger.updateSuppression.UpdateSuppressionLogger;

public class LoggerRegister {
    private LoggerRegister() {
    }

    public static boolean __disk = false;

    public static boolean __updateSuppression = false;

    public static void registry() {
        LoggerRegistry.registerLogger("disk", DiskHUDLogger.INSTANCE);
        LoggerRegistry.registerLogger("updateSuppression", UpdateSuppressionLogger.INSTANCE);
    }
}
