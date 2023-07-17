package io.github.optijava.opt_carpet_addition.logger.cceSuppressionCrashLogger;

import net.minecraft.util.math.BlockPos;

public class ThrowableCCESuppression extends RuntimeException{
    public final BlockPos pos;

    public ThrowableCCESuppression(String message, BlockPos pos, Throwable cause) {
        super(message, cause);
        this.pos = pos;
    }
}
