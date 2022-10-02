package io.github.optijava.opt_carpet_addition;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.COMMAND;

public class OptCarpetSettings {
    @Rule(desc = "Enables [/player xxx tp] to teleport to fake player", category = COMMAND)
    public static String commandTpToFakePlayer = "true";

    @Rule(desc = "Enables [/player xxx tp] to teleport to real player", category = COMMAND)
    public static String allowTpToRealPlayer = "ops";

    @Rule(desc = "Enables [/player xxx tp] to teleport to fake player", category = COMMAND)
    public static String commandTpHereFakePlayer = "true";

    @Rule(desc = "Enables [/player xxx tp] to teleport to real player", category = COMMAND)
    public static String allowTpHereRealPlayer = "ops";
}
