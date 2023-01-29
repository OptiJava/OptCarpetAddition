package io.github.optijava.opt_carpet_addition.logger;

import carpet.logging.HUDLogger;
import net.minecraft.server.MinecraftServer;

import java.lang.reflect.Field;

public abstract class AbstractHUDLogger extends HUDLogger {

    protected AbstractHUDLogger(Field acceleratorField, String logName, String def, String[] options, boolean strictOptions) {
        super(acceleratorField, logName, def, options, strictOptions);
    }

    public abstract void updateHUD(MinecraftServer server);
}
