package io.github.optijava.opt_carpet_addition;

//#if MC >= 11900
//$$ import carpet.api.settings.RuleCategory;
//#endif
import carpet.settings.Rule;
//#if MC < 11900
import carpet.settings.RuleCategory;
//#endif
import io.github.optijava.opt_carpet_addition.utils.config_bean.CommandLoggerConfigBean;
import io.github.optijava.opt_carpet_addition.utils.config_bean.TpLimitConfigBean;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

//#if MC >= 11900
//$$ @SuppressWarnings("all")
//#endif
public class OptCarpetSettings {
    public static final String FALSE = "false";
    public static Path configDirectory = FabricLoader.getInstance().getConfigDir().resolve("opt-carpet-addition");

    // Tp command rules =======================================================================

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to fake player",
            category = RuleCategory.COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String commandTpToFakePlayer = FALSE;

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to real player",
            category = RuleCategory.COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String allowTpToRealPlayer = FALSE;

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to fake player",
            category = RuleCategory.COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String commandTpHereFakePlayer = FALSE;

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to real player",
            category = RuleCategory.COMMAND,
            options = {"true", "false", "ops"}
    )
    public static String allowTpHereRealPlayer = FALSE;

    // Force fake player gamemode rule =========================================================
    @Rule(
            desc = "Force fake player gamemode",
            category = RuleCategory.FEATURE,
            options = {"survival", "creative", "adventure", "false"}
    )
    public static String forceFakePlayerGameMode = FALSE;

    // Fix bugs ================================================================================
    @Rule(
            desc = "Refresh xp level when player change world",
            category = RuleCategory.BUGFIX
    )
    public static boolean fixXpLevelBug = false;

    // Tp whitelist/blacklist  ====================================================================

    public static TpLimitConfigBean tpLimitConfigBean;

    public static CommandLoggerConfigBean commandLoggerConfigBean;

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

    @Rule(
            desc = "Disable chicken lays eggs",
            category = RuleCategory.FEATURE
    )
    public static boolean disabledLayEggs = false;

    @Rule(
            desc = "Disable enderman pickup block goal",
            category = RuleCategory.FEATURE
    )
    public static boolean disabledEnderManPickupGoal = false;

    @Rule(
            desc = "Disable enderman place block goal",
            category = RuleCategory.FEATURE
    )
    public static boolean disabledEnderManPlaceBlockGoal = false;

    @Rule(
            desc = "Disable zombified piglin spawn in nether portal",
            category = {RuleCategory.OPTIMIZATION, RuleCategory.FEATURE}
    )
    public static boolean disabledNetherPortalSpawn = false;

    @Rule(
            desc = "Disable entity tick(contains all entity and BlockEntity)",
            category = {RuleCategory.EXPERIMENTAL}
    )
    public static boolean disabledEntityTick = false;

    //#if MC < 11900
    //$$
    @Rule(
            desc = "Disable error stack when update suppression.",
            category = {RuleCategory.BUGFIX}
    )
    public static boolean disabledUpdateSuppressionErrorStackTrace = false;
    //#endif

    @Rule(
            desc = "Unescape chat message. \\u4e2d -> 中",
            category = {RuleCategory.FEATURE, RuleCategory.CLIENT}
    )
    public static boolean unescapeChatMessage = false;

    //#if MC < 11900
    @Rule(
            desc = "Handle player connect in a new thread to reduce the lag.",
            category = {RuleCategory.OPTIMIZATION, RuleCategory.EXPERIMENTAL}
    )
    public static boolean optimizePlayerConnect = false;
    //#endif

    @Rule(
            desc = "Handle player teleport in a new thread to reduce the lag.",
            category = {RuleCategory.OPTIMIZATION, RuleCategory.EXPERIMENTAL}
    )
    public static boolean optimizeTeleport = false;

    @Rule(
            desc = "Print info log when players submit command",
            category = {RuleCategory.COMMAND, RuleCategory.FEATURE}
    )
    public static boolean commandLogger = false;

    //#if MC < 11900
    @Rule(
            desc = "Dropper crash fix",
            category = RuleCategory.BUGFIX
    )
    public static boolean dropperCrashFix = false;
    //#endif

    @Rule(
            desc = "Always spawn offline fake player to reduce lagging",
            category = RuleCategory.OPTIMIZATION
    )
    public static boolean optimizeFakePlayerSpawn = false;

    @Rule(
            desc = "Fix CCE Suppression crashes",
            category = {RuleCategory.BUGFIX, RuleCategory.EXPERIMENTAL}
    )
    public static boolean cceSuppressionCrashFix = false;

    @Rule(
            desc = "Remove every armour which has `curse of binding` enchantment when player dead in wall (keepInventory must be true).",
            category = RuleCategory.FEATURE
    )
    public  static boolean removeAllCurseOfBindingArmorWhenPlayerDeadInWall = false;
}
