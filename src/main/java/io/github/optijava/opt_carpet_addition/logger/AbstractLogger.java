package io.github.optijava.opt_carpet_addition.logger;

import carpet.logging.Logger;

import java.lang.reflect.Field;

public abstract class AbstractLogger extends Logger {

    protected AbstractLogger(Field acceleratorField, String logName, String def, String[] options, boolean strictOptions) {
        super(acceleratorField, logName, def, options, strictOptions);
    }
}
