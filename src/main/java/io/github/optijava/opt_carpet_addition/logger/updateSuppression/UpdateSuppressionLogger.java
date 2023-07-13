package io.github.optijava.opt_carpet_addition.logger.updateSuppression;
//#if MC < 11900
import io.github.optijava.opt_carpet_addition.logger.AbstractLogger;
import io.github.optijava.opt_carpet_addition.logger.LoggerRegister;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.lang.reflect.Field;

public class UpdateSuppressionLogger extends AbstractLogger {

    public static final UpdateSuppressionLogger INSTANCE;

    static {
        try {
            INSTANCE = new UpdateSuppressionLogger(LoggerRegister.class.getField("__updateSuppression"), "updateSuppression", null, null, false);
        } catch (NoSuchFieldException e) {
            throw new Error("Failed to init UpdateSuppressionLogger", e);
        }
    }

    protected UpdateSuppressionLogger(Field acceleratorField, String logName, String def, String[] options, boolean strictOptions) {
        super(acceleratorField, logName, def, options, strictOptions);
    }

    public void logSuppression(BlockPos neighborPos, String exception, World world) {
        super.log(() -> new LiteralText[]{
                new LiteralText("Update Suppression @ %s [%s %s %s] %s".formatted(world.getRegistryKey().getValue().getPath(), neighborPos.getX(), neighborPos.getY(), neighborPos.getZ(), exception))
        });
    }
}
//#endif
