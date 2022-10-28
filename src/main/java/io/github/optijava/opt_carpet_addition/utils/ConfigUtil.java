package io.github.optijava.opt_carpet_addition.utils;

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
            e.printStackTrace();
            return "Failed";
        }
    }

    public static boolean create(String fileName) {
        try {
            Files.createFile(OptCarpetSettings.configDirectory.resolve(fileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean init() {
        File file = OptCarpetSettings.configDirectory.toFile();
        if (!file.exists()) {
            if (!file.mkdirs()) {
                return false;
            }
        }
        return true;
    }

    public static boolean exists(String fileName) {
        return Files.exists(OptCarpetSettings.configDirectory.resolve(fileName));
    }
}
