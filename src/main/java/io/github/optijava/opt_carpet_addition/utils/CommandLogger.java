package io.github.optijava.opt_carpet_addition.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.OptCarpetSettings;
import io.github.optijava.opt_carpet_addition.utils.config_bean.CommandLoggerConfigBean;

import java.util.Objects;

public class CommandLogger {
    private static final Gson gson = new Gson();

    public static void loadConfigFile() {
        final var FILE_NAME = "CommandLogger.json";

        if (!ConfigUtil.exists(FILE_NAME)) {
            if (!ConfigUtil.create(FILE_NAME)) {
                OptCarpetAddition.LOGGER.error("Failed to create config file: " + FILE_NAME);
                return;
            }

            if (!ConfigUtil.write(FILE_NAME, "{\n" +
                    "  \"logAllCommand\": false,\n" +
                    "  \"LogCommandWhitelist\": [],\n" +
                    "  \"LogCommandBlacklist\": [],\n" +
                    "  \"LogCommandPrefixWhitelist\": [],\n" +
                    "  \"LogCommandPrefixBlacklist\": []\n" +
                    "}")) {
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
            OptCarpetSettings.commandLoggerConfigBean = gson.fromJson(content, CommandLoggerConfigBean.class);
        } catch (JsonSyntaxException e) {
            new RuntimeException("Exception when parsed json config file.", e).printStackTrace();
        } finally {
            if (OptCarpetSettings.commandLoggerConfigBean == null) {
                OptCarpetSettings.commandLoggerConfigBean = new CommandLoggerConfigBean();
            }
        }
    }

    public static int reload() {
        loadConfigFile();
        return 1;
    }
}
