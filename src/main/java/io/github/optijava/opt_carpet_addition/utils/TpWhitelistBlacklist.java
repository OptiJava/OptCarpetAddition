package io.github.optijava.opt_carpet_addition.utils;

import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class TpWhitelistBlacklist {
    public static void loadConfigFile() {
        Properties prop = new Properties();
        if (!ConfigUtil.exists("TpList.properties")) {
            if (!ConfigUtil.create("TpList.properties")) {
                OptCarpetAddition.LOGGER.error("[OptCarpetAddition] Failed to create config file: TpList.properties");
            }
        }

        try (InputStream input = new FileInputStream(OptCarpetSettings.configDirectory.resolve("TpList.properties").toFile())) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!prop.getProperty("TpWhitelist", "").isBlank()) {
            OptCarpetSettings.tpWhiteList.addAll(Arrays.asList(prop.getProperty("TpWhitelist").split(";")));
        } else {
            prop.put("TpWhitelist", "");
        }
    }
}
