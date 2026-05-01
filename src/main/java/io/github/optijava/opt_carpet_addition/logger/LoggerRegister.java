package io.github.optijava.opt_carpet_addition.logger;

import carpet.logging.LoggerRegistry;
import io.github.optijava.opt_carpet_addition.logger.disk.DiskHUDLogger;

public class LoggerRegister {
    private LoggerRegister() {
    }

    public static boolean __disk = false;

    // TODO 26.1 Remove CCE Supression Crash

    public static boolean __blockUpdate = false;

    public static void registry() {
        LoggerRegistry.registerLogger("disk", DiskHUDLogger.INSTANCE);
        //TODO incompatible with 26.1
        //LoggerRegistry.registerLogger("blockUpdate", BlockUpdateLogger.INSTANCE);
    }
}
