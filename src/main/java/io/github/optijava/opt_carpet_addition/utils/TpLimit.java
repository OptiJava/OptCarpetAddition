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
                OptCarpetAddition.LOGGER.error("Failed to create config file: TpLimit.json");
                return;
            }
            if (!ConfigUtil.write("TpLimit.json", "{\n" +
                    "                      \"TpWhitelist\": [],\n" +
                    "                      \"TpBlacklist\": [],\n" +
                    "                      \"TphereWhitelist\": [],\n" +
                    "                      \"TphereBlacklist\": []\n" +
                    "                    }")) {
                OptCarpetAddition.LOGGER.error("Failed to init config file: TpLimit.json");
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
            case "TpBlacklist":
                OptCarpetSettings.bean.tpBlacklist.add(prefix);
                break;
            case "TpWhitelist":
                OptCarpetSettings.bean.tpWhitelist.add(prefix);
                break;
            case "TphereWhitelist":
                OptCarpetSettings.bean.tphereWhitelist.add(prefix);
                break;
            case "TphereBlacklist":
                OptCarpetSettings.bean.tphereBlacklist.add(prefix);
                break;
            default:
                throw new IllegalArgumentException("Invalid list name.");
        }
    }

    public static void remove(String prefix, String whichList) {
        switch (whichList) {
            case "TpBlacklist":
                OptCarpetSettings.bean.tpBlacklist.remove(prefix);
                break;
            case "TpWhitelist":
                OptCarpetSettings.bean.tpWhitelist.remove(prefix);
                break;
            case "TphereWhitelist":
                OptCarpetSettings.bean.tphereWhitelist.remove(prefix);
                break;
            case "TphereBlacklist":
                OptCarpetSettings.bean.tphereBlacklist.remove(prefix);
                break;
            default:
                throw new IllegalArgumentException("Invalid list name.");
        }
    }

    public static void flush() {
        ConfigUtil.write("TpLimit.json", gson.toJson(OptCarpetSettings.bean));
    }
}

