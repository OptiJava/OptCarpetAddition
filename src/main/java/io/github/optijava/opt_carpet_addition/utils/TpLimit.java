package io.github.optijava.opt_carpet_addition.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.config_bean.TpLimitConfigBean;

public class TpLimit {

    static Gson gson = new Gson();

    public static void loadConfigFile() {
        if (!ConfigUtil.exists("TpLimit.json")) {
            if (!ConfigUtil.create("TpLimit.json")) {
                OptCarpetAddition.LOGGER.error("[OptCarpetAddition] Failed to create config file: TpLimit.json");
                return;
            }
            if (!ConfigUtil.write("TpLimit.json", """
                    {
                      "TpWhitelist": [],
                      "TpBlacklist": [],
                      "TphereWhitelist": [],
                      "TphereBlacklist": []
                    }""")) {
                OptCarpetAddition.LOGGER.error("[OptCarpetAddition] Failed to init config file: TpLimit.json");
                return;
            }
        }
        try {
            OptCarpetSettings.bean = gson.fromJson(ConfigUtil.load("TpLimit.json"), TpLimitConfigBean.class);
        } catch (JsonSyntaxException e) {
            new RuntimeException("Exception when parsed json config file.", e).printStackTrace();
        } finally {
            if (OptCarpetSettings.bean == null) {
                OptCarpetSettings.bean = new TpLimitConfigBean();
            }
        }
    }

    public static void add(String prefix, String whichList) {
        switch (whichList) {
            case "TpBlacklist" -> OptCarpetSettings.bean.TpBlacklist.add(prefix);

            case "TpWhitelist" -> OptCarpetSettings.bean.TpWhitelist.add(prefix);

            case "TphereWhitelist" -> OptCarpetSettings.bean.TphereWhitelist.add(prefix);

            case "TphereBlacklist" -> OptCarpetSettings.bean.TphereBlacklist.add(prefix);

            default -> throw new IllegalArgumentException("Invalid list name.");
        }
    }

    public static void remove(String prefix, String whichList) {
        switch (whichList) {
            case "TpBlacklist" -> OptCarpetSettings.bean.TpBlacklist.remove(prefix);

            case "TpWhitelist" -> OptCarpetSettings.bean.TpWhitelist.remove(prefix);

            case "TphereWhitelist" -> OptCarpetSettings.bean.TphereWhitelist.remove(prefix);

            case "TphereBlacklist" -> OptCarpetSettings.bean.TphereBlacklist.remove(prefix);

            default -> throw new IllegalArgumentException("Invalid list name.");
        }
    }

    public static void flush() {
        ConfigUtil.write("TpLimit.json", gson.toJson(OptCarpetSettings.bean));
    }
}

