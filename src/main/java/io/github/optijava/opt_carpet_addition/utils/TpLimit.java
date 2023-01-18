package io.github.optijava.opt_carpet_addition.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.config_bean.TpLimitConfigBean;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Objects;

public class TpLimit {

    private static final Gson gson = new Gson();

    public static void loadConfigFile() {
        final var FILE_NAME = "TpLimit.json";

        if (!ConfigUtil.exists(FILE_NAME)) {
            if (!ConfigUtil.create(FILE_NAME)) {
                OptCarpetAddition.LOGGER.error("Failed to create config file: " + FILE_NAME);
                return;
            }
            if (!ConfigUtil.write(FILE_NAME, """
                    {
                      "TpWhitelist": [],
                      "TpBlacklist": [],
                      "TphereWhitelist": [],
                      "TphereBlacklist": []
                    }""")) {
                OptCarpetAddition.LOGGER.error("Failed to init config file: " + FILE_NAME);
                return;
            }
        }
        try {
            String content = ConfigUtil.load(FILE_NAME);
            Objects.requireNonNull(content);
            if (content.equals("Failed")) {
                OptCarpetAddition.LOGGER.error("Failed to read config file " + FILE_NAME);
                return;
            }
            OptCarpetSettings.tpLimitConfigBean = gson.fromJson(content, TpLimitConfigBean.class);
        } catch (JsonSyntaxException e) {
            new RuntimeException("Exception when parsed json config file.", e).printStackTrace();
        } finally {
            if (OptCarpetSettings.tpLimitConfigBean == null) {
                OptCarpetSettings.tpLimitConfigBean = new TpLimitConfigBean();
            }
        }
    }

    public static void add(String prefix, String whichList) {
        switch (whichList) {
            case "TpBlacklist" -> OptCarpetSettings.tpLimitConfigBean.TpBlacklist.add(prefix);

            case "TpWhitelist" -> OptCarpetSettings.tpLimitConfigBean.TpWhitelist.add(prefix);

            case "TphereWhitelist" -> OptCarpetSettings.tpLimitConfigBean.TphereWhitelist.add(prefix);

            case "TphereBlacklist" -> OptCarpetSettings.tpLimitConfigBean.TphereBlacklist.add(prefix);

            default -> throw new IllegalArgumentException("Invalid list name.");
        }
    }

    public static void remove(String prefix, String whichList) {
        switch (whichList) {
            case "TpBlacklist" -> OptCarpetSettings.tpLimitConfigBean.TpBlacklist.remove(prefix);

            case "TpWhitelist" -> OptCarpetSettings.tpLimitConfigBean.TpWhitelist.remove(prefix);

            case "TphereWhitelist" -> OptCarpetSettings.tpLimitConfigBean.TphereWhitelist.remove(prefix);

            case "TphereBlacklist" -> OptCarpetSettings.tpLimitConfigBean.TphereBlacklist.remove(prefix);

            default -> throw new IllegalArgumentException("Invalid list name.");
        }
    }

    public static void flush() {
        ConfigUtil.write("TpLimit.json", gson.toJson(OptCarpetSettings.tpLimitConfigBean));
    }

    public static int reload(CommandContext<ServerCommandSource> context) {
        loadConfigFile();
        return 1;
    }
}

