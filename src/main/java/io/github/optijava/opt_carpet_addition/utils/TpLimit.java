package io.github.optijava.opt_carpet_addition.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.config_bean.TpLimitConfigBean;

import java.util.Objects;

public class TpLimit {

    private static final Gson gson = new Gson();

    public static void loadConfigFile() {
        if (!ConfigUtil.exists("TpLimit.json")) {
            if (!ConfigUtil.create("TpLimit.json")) {
                OptCarpetAddition.LOGGER.error("Failed to create config file: TpLimit.json");
                return;
            }
            if (!ConfigUtil.write("TpLimit.json", """
                    {
                      "TpWhitelist": [],
                      "TpBlacklist": [],
                      "TphereWhitelist": [],
                      "TphereBlacklist": []
                    }""")) {
                OptCarpetAddition.LOGGER.error("Failed to init config file: TpLimit.json");
                return;
            }
        }
        try {
            String content = ConfigUtil.load("TpLimit.json");
            Objects.requireNonNull(content);
            if (content.equals("Failed")) {
                OptCarpetAddition.LOGGER.error("Failed to read config file TpLimit.json!");
                return;
            }
            OptCarpetSettings.bean = gson.fromJson(content, TpLimitConfigBean.class);
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
            case "TpBlacklist" -> OptCarpetSettings.bean.tpBlacklist.add(prefix);

            case "TpWhitelist" -> OptCarpetSettings.bean.tpWhitelist.add(prefix);

            case "TphereWhitelist" -> OptCarpetSettings.bean.tphereWhitelist.add(prefix);

            case "TphereBlacklist" -> OptCarpetSettings.bean.tphereBlacklist.add(prefix);

            default -> throw new IllegalArgumentException("Invalid list name.");
        }
    }

    public static void remove(String prefix, String whichList) {
        switch (whichList) {
            case "TpBlacklist" -> OptCarpetSettings.bean.tpBlacklist.remove(prefix);

            case "TpWhitelist" -> OptCarpetSettings.bean.tpWhitelist.remove(prefix);

            case "TphereWhitelist" -> OptCarpetSettings.bean.tphereWhitelist.remove(prefix);

            case "TphereBlacklist" -> OptCarpetSettings.bean.tphereBlacklist.remove(prefix);

            default -> throw new IllegalArgumentException("Invalid list name.");
        }
    }

    public static void flush() {
        ConfigUtil.write("TpLimit.json", gson.toJson(OptCarpetSettings.bean));
    }
}

