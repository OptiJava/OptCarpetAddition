package io.github.optijava.opt_carpet_addition;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.COMMAND;

public class OptCarpetSettings {
    @Rule(desc = "Enables [/player xxx tp] to teleport to fake player", category = COMMAND)
    public static String commandTpToFakePlayer = "false";

    @Rule(desc = "Enables [/player xxx tp] to teleport to real player", category = COMMAND)
    public static String allowTpToRealPlayer = "false";

    @Rule(desc = "Enables [/player xxx tp] to teleport to fake player", category = COMMAND)
    public static String commandTpHereFakePlayer = "false";

    @Rule(desc = "Enables [/player xxx tp] to teleport to real player", category = COMMAND)
    public static String allowTpHereRealPlayer = "false";

}
