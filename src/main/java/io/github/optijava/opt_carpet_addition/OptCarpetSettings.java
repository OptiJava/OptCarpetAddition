package io.github.optijava.opt_carpet_addition;

import carpet.settings.Rule;
import carpet.settings.RuleCategory;

import static carpet.settings.RuleCategory.COMMAND;

public class OptCarpetSettings {
    // Tp command rules =========================================================
    @Rule(
            desc = "Enables [/player xxx tp] to teleport to fake player",
            category = COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String commandTpToFakePlayer = "false";

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to real player",
            category = COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String allowTpToRealPlayer = "false";

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to fake player",
            category = COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String commandTpHereFakePlayer = "false";

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to real player",
            category = COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String allowTpHereRealPlayer = "false";

    // Force fake player gamemode rule =========================================================
    @Rule(
            desc = "Force fake player gamemode",
            category = RuleCategory.FEATURE,
            options = {"survival", "creative", "adventure", "spectator", "false"}
    )
    public static String forceFakePlayerGameMode = "false";

    // Fix bugs ================================================================================
    @Rule(
            desc = "Refresh xp level when player change world",
            category = RuleCategory.BUGFIX
    )
    public static boolean fixXpLevelBug = false;
}
