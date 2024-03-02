package io.github.optijava.opt_carpet_addition.utils;

import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ConfigUtil {
    public static String load(String fileName) {
        try {
            return Files.readString(OptCarpetSettings.configDirectory.resolve(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            OptCarpetAddition.LOGGER.error(e);
            return "Failed";
        }
    }

    public static boolean create(String fileName) {
        try {
            Files.createFile(OptCarpetSettings.configDirectory.resolve(fileName));
            return true;
        } catch (IOException e) {
            OptCarpetAddition.LOGGER.error(e);
            return false;
        }
    }

    public static boolean init() {
        File file = OptCarpetSettings.configDirectory.toFile();
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    public static boolean exists(String fileName) {
        return Files.exists(OptCarpetSettings.configDirectory.resolve(fileName));
    }

    public static boolean write(String fileName, String content) {
        try {
            Files.write(OptCarpetSettings.configDirectory.resolve(fileName), content.getBytes());
        } catch (IOException e) {
            OptCarpetAddition.LOGGER.error(e);
            return false;
        }
        return true;
    }
}
