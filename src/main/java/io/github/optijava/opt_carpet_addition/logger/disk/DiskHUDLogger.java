package io.github.optijava.opt_carpet_addition.logger.disk;

import carpet.logging.LoggerRegistry;
import carpet.utils.Messenger;
import io.github.optijava.opt_carpet_addition.logger.AbstractHUDLogger;
import io.github.optijava.opt_carpet_addition.logger.LoggerRegister;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DiskHUDLogger extends AbstractHUDLogger {

    public static final DiskHUDLogger INSTANCE;
    private static final long TO_GB = 1024L*1024L*1024L;

    static {
        try {
            INSTANCE = new DiskHUDLogger(LoggerRegister.class.getField("__disk"), "disk", null, null, false);
        } catch (NoSuchFieldException e) {
            throw new Error("Failed to init DiskLogger", e);
        }
    }

    protected DiskHUDLogger(Field acceleratorField, String logName, String def, String[] options, boolean strictOptions) {
        super(acceleratorField, logName, def, options, strictOptions);
    }

    @Override
    public void updateHUD(MinecraftServer server) {
        if (LoggerRegister.__disk) {
            List<Text> list = new ArrayList<>();

            for (File f : File.listRoots()) {
                if (f.getPath().equals("/")) {
                    list.add(Text.of(Messenger.c("g %s %sGB/%sGB".formatted("RootFileSystem", (f.getTotalSpace() - f.getFreeSpace()) / TO_GB, f.getTotalSpace() / TO_GB))));
                } else {
                    list.add(Text.of(Messenger.c("g %s %sGB/%sGB".formatted(f.getPath(), (f.getTotalSpace() - f.getFreeSpace()) / TO_GB, f.getTotalSpace() / TO_GB))));
                }
            }
            LoggerRegistry.getLogger("disk").log(() -> list.toArray(new Text[0]));
        }
    }
}