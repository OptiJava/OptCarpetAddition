package io.github.optijava.opt_carpet_addition.logger;

import carpet.logging.LoggerRegistry;
import io.github.optijava.opt_carpet_addition.logger.blockUpdate.BlockUpdateLogger;
import io.github.optijava.opt_carpet_addition.logger.disk.DiskHUDLogger;

public class LoggerRegister {
    public static boolean __disk = false;
    public static boolean __blockUpdate = false;

    public static void registry() {
        LoggerRegistry.registerLogger("disk", DiskHUDLogger.INSTANCE);
        LoggerRegistry.registerLogger("blockUpdate", BlockUpdateLogger.INSTANCE);
    }
}
