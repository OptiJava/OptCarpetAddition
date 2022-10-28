package io.github.optijava.opt_carpet_addition;

import carpet.settings.Rule;
import carpet.settings.RuleCategory;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static carpet.settings.RuleCategory.COMMAND;

public class OptCarpetSettings {
    private static final String False = "false";
    public static Path configDirectory = Path.of(FabricLoader.getInstance().getConfigDir().toString() + File.separator + "opt-carpet-addition");

    // Tp command rules =======================================================================

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to fake player",
            category = COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String commandTpToFakePlayer = False;

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to real player",
            category = COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String allowTpToRealPlayer = False;

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to fake player",
            category = COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String commandTpHereFakePlayer = False;

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to real player",
            category = COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String allowTpHereRealPlayer = False;

    // Force fake player gamemode rule =========================================================
    @Rule(
            desc = "Force fake player gamemode",
            category = RuleCategory.FEATURE,
            options = {"survival", "creative", "adventure", "false"}
    )
    public static String forceFakePlayerGameMode = False;

    // Fix bugs ================================================================================
    @Rule(
            desc = "Refresh xp level when player change world",
            category = RuleCategory.BUGFIX
    )
    public static boolean fixXpLevelBug = false;

    // Tp whitelist/blacklist  ====================================================================
    public static final List<String> tpBlackList = new ArrayList<>();
    public static final List<String> tpWhiteList = new ArrayList<>();
    public static final List<String> tphereBlackList = new ArrayList<>();
    public static final List<String> tphereWhiteList = new ArrayList<>();

    @Rule(
            desc = "Enable tp prefix blacklist",
            category = RuleCategory.FEATURE
    )
    public static boolean enableTpPrefixBlacklist = false;

    @Rule(
            desc = "Enable tp prefix whitelist",
            category = RuleCategory.FEATURE
    )
    public static boolean enableTpPrefixWhitelist = false;

    @Rule(
            desc = "Enable tp here prefix blacklist",
            category = RuleCategory.FEATURE
    )
    public static boolean enableTpHerePrefixBlacklist = false;

    @Rule(
            desc = "Enable tp here prefix whitelist",
            category = RuleCategory.FEATURE
    )
    public static boolean enableTpHerePrefixWhitelist = false;

}
