package io.github.optijava.opt_carpet_addition.logger.disk;

import carpet.logging.LoggerRegistry;
import io.github.optijava.opt_carpet_addition.logger.AbstractHUDLogger;
import io.github.optijava.opt_carpet_addition.logger.LoggerRegister;
import net.minecraft.server.MinecraftServer;
//#if MC >= 11900
//$$ import net.minecraft.text.LiteralTextContent;
//$$ import net.minecraft.text.MutableText;
//$$ import net.minecraft.text.Text;
//#else
import net.minecraft.text.BaseText;
import net.minecraft.text.LiteralText;
//#endif

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DiskHUDLogger extends AbstractHUDLogger {

    public static final DiskHUDLogger INSTANCE;

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
            //#if MC >= 11900
            //$$ List<Text> list = new ArrayList<>();
            //#else
            List<BaseText> list = new ArrayList<>();
            //#endif

            //#if MC >= 11900
            //$$ for (File f : File.listRoots()) {
            //$$    if (f.getPath().equals("/")) {
            //$$        list.add(MutableText.of(new LiteralTextContent("%s %sGB/%sGB %n".formatted("RootFileSystem", (f.getTotalSpace() - f.getFreeSpace()) / 1024 / 1024 / 1024, f.getTotalSpace() / 1024 / 1024 / 1024))));
            //$$    } else {
            //$$        list.add(MutableText.of(new LiteralTextContent("%s %sGB/%sGB %n".formatted(f.getPath(), (f.getTotalSpace() - f.getFreeSpace()) / 1024 / 1024 / 1024, f.getTotalSpace() / 1024 / 1024 / 1024))));
            //$$    }
            //$$ }
            //$$ LoggerRegistry.getLogger("disk").log(() -> list.toArray(new Text[0]));
            //#else
            for (File f : File.listRoots()) {
                if (f.getPath().equals("/")) {
                    list.add(new LiteralText("%s %sGB/%sGB %n".formatted("RootFileSystem", (f.getTotalSpace() - f.getFreeSpace()) / 1024 / 1024 / 1024, f.getTotalSpace() / 1024 / 1024 / 1024)));
                } else {
                    list.add(new LiteralText("%s %sGB/%sGB %n".formatted(f.getPath(), (f.getTotalSpace() - f.getFreeSpace()) / 1024 / 1024 / 1024, f.getTotalSpace() / 1024 / 1024 / 1024)));
                }
            }
            LoggerRegistry.getLogger("disk").log(() -> list.toArray(new BaseText[0]));
            //#endif
        }
    }
}