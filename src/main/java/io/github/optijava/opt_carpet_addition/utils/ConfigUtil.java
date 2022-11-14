package io.github.optijava.opt_carpet_addition.utils;

import io.github.optijava.opt_carpet_addition.OptCarpetSettings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class ConfigUtil {
    public static String load(String fileName) {
        try {
            return Arrays.toString(Files.readAllBytes(OptCarpetSettings.configDirectory.resolve(fileName)));
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
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
