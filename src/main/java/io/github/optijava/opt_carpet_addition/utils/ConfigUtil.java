package io.github.optijava.opt_carpet_addition.utils;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigUtil {
    public static String load(String fileName) {
        try {
            return Files.readString(Path.of(OptCarpetSettings.configDir + fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    public static boolean create(String fileName) {
        try {
            Files.createFile(Path.of(OptCarpetSettings.configDir + fileName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void init() {
        File file = Path.of(OptCarpetSettings.configDir).toFile();
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static boolean exists(String fileName) {
        return Files.exists(Path.of(OptCarpetSettings.configDir + fileName));
    }
}
