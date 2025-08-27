package io.github.optijava.opt_carpet_addition;

//#if MC >= 11900
//$$ import carpet.api.settings.RuleCategory;
//#else

import carpet.settings.RuleCategory;
//#endif
import carpet.settings.Rule;

import io.github.optijava.opt_carpet_addition.utils.config_bean.CommandLoggerConfigBean;
import io.github.optijava.opt_carpet_addition.utils.config_bean.TpLimitConfigBean;
import io.github.optijava.opt_carpet_addition.utils.validator.CustomItemDiscardTimeValidator;

import io.github.optijava.opt_carpet_addition.utils.validator.PlayerTpRateLimitTimeValidator;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

//#if MC >= 11900
//$$ @SuppressWarnings("all")
//#endif
public class OptCarpetSettings {
    public static final String OCA = "OCA";
    public static final String FALSE = "false";
    public static Path configDirectory = FabricLoader.getInstance().getConfigDir().resolve("opt-carpet-addition");

    // Tp command rules =======================================================================

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to fake player",
            category = {RuleCategory.COMMAND,OCA},
            options = {"true", "false", "ops"}
    )
    public static String commandTpToFakePlayer = FALSE;

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to real player",
            category = {RuleCategory.COMMAND,OCA},
            options = {"true", "false", "ops"}
    )
    public static String allowTpToRealPlayer = FALSE;

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to fake player",
            category = {RuleCategory.COMMAND,OCA},
            options = {"true", "false", "ops"}
    )
    public static String commandTpHereFakePlayer = FALSE;

    @Rule(
            desc = "Enables [/player xxx tp] to teleport to real player",
            category = {RuleCategory.COMMAND,OCA},
            options = {"true", "false", "ops"}
    )
    public static String allowTpHereRealPlayer = FALSE;

    // Force fake player gamemode rule =========================================================
    @Rule(
            desc = "Force fake player gamemode",
            category = {RuleCategory.FEATURE,OCA},
            options = {"survival", "creative", "adventure", "false"}
    )
    public static String forceFakePlayerGameMode = FALSE;

    // Fix bugs ================================================================================
    @Rule(
            desc = "Refresh xp level when player change world",
            category = {RuleCategory.BUGFIX,OCA}
    )
    public static boolean fixXpLevelBug = false;

    // Tp whitelist/blacklist  ====================================================================

    public static TpLimitConfigBean tpLimitConfigBean;

    public static CommandLoggerConfigBean commandLoggerConfigBean;

    @Rule(
            desc = "Enable tp prefix blacklist",
            category = {RuleCategory.FEATURE,OCA}
    )
    public static boolean enableTpPrefixBlacklist = false;

    @Rule(
            desc = "Enable tp prefix whitelist",
            category = {RuleCategory.FEATURE,OCA}
    )
    public static boolean enableTpPrefixWhitelist = false;

    @Rule(
            desc = "Enable tp here prefix blacklist",
            category = {RuleCategory.FEATURE,OCA}
    )
    public static boolean enableTpHerePrefixBlacklist = false;

    @Rule(
            desc = "Enable tp here prefix whitelist",
            category = {RuleCategory.FEATURE,OCA}
    )
    public static boolean enableTpHerePrefixWhitelist = false;

    @Rule(
            desc = "Disable chicken lays eggs",
            category = {RuleCategory.FEATURE,OCA}
    )
    public static boolean disabledLayEggs = false;

    @Rule(
            desc = "Disable enderman pickup block goal",
            category = {RuleCategory.FEATURE,OCA}
    )
    public static boolean disabledEnderManPickupGoal = false;

    @Rule(
            desc = "Disable enderman place block goal",
            category = {RuleCategory.FEATURE,OCA}
    )
    public static boolean disabledEnderManPlaceBlockGoal = false;

    @Rule(
            desc = "Disable zombified piglin spawn in nether portal",
            category = {RuleCategory.OPTIMIZATION, RuleCategory.FEATURE,OCA}
    )
    public static boolean disabledNetherPortalSpawn = false;

    @Rule(
            desc = "Disable entity tick(contains all entity and BlockEntity)",
            category = {RuleCategory.EXPERIMENTAL,OCA}
    )
    public static boolean disabledEntityTick = false;

    //#if MC < 11900
    @Rule(
            desc = "Disable error stack when update suppression.",
            category = {RuleCategory.BUGFIX,OCA}
    )
    public static boolean disabledUpdateSuppressionErrorStackTrace = false;
    //#endif

    @Rule(
            desc = "Unescape chat message. \\u4e2d -> 中",
            category = {RuleCategory.FEATURE, RuleCategory.CLIENT,OCA}
    )
    public static boolean unescapeChatMessage = false;

    //#if MC < 11900
    @Rule(
            desc = "Handle player connect in a new thread to reduce the lag.",
            category = {RuleCategory.OPTIMIZATION, RuleCategory.EXPERIMENTAL,OCA}
    )
    public static boolean optimizePlayerConnect = false;
    //#endif

    //#if MC < 11900
    @Rule(
            desc = "Handle player teleport in a new thread to reduce the lag.",
            category = {RuleCategory.OPTIMIZATION, RuleCategory.EXPERIMENTAL,OCA}
    )
    public static boolean optimizeTeleport = false;
    //#endif

    @Rule(
            desc = "Print info log when players submit command",
            category = {RuleCategory.COMMAND, RuleCategory.FEATURE,OCA}
    )
    public static boolean commandLogger = false;

    //#if MC < 11900
    @Rule(
            desc = "Dropper crash fix",
            category = {RuleCategory.BUGFIX,OCA}
    )
    public static boolean dropperCrashFix = false;
    //#endif

    @Rule(
            desc = "Always spawn offline fake player to reduce lagging",
            category = {RuleCategory.OPTIMIZATION,OCA}
    )
    public static boolean optimizeFakePlayerSpawn = false;

    //#if MC < 12100
    @Rule(
            desc = "Fix CCE Suppression crashes",
            category = {RuleCategory.BUGFIX, RuleCategory.EXPERIMENTAL,OCA}
    )
    public static boolean cceSuppressionCrashFix = false;
    //#endif

    @Rule(
            desc = "Remove every armour which has `curse of binding` enchantment when player dead in wall (keepInventory must be true).",
            category = {RuleCategory.FEATURE,OCA}
    )
    public static boolean removeAllCurseOfBindingArmorWhenPlayerDeadInWall = false;

    @Rule(
            desc = "Modify item disappeared time, default value -1 (unit: tick)",
            category = {RuleCategory.FEATURE,OCA},
            options = {"-1", "6000"},
            strict = false,
            validate = CustomItemDiscardTimeValidator.class
    )
    public static int customItemDiscardTime = -1;

    //#if MC >= 12000
    //$$ @Rule(
    //$$         desc = "Disallow redstone wire connects to open trapdoor(in version >= 1.20)",
    //$$         category = {RuleCategory.FEATURE,OCA}
    //$$ )
    //$$ public static boolean disallowRedstoneWireConnectsToOpenTrapdoor = false;
    //#endif

    @Rule(
            desc = "Don't enable this rule unless you need blockUpdate logger! Enabling this rule may cause performance issues, so this rule was false by default. Before you set this rule to true, everyone can not use blockUpdate logger.",
            category = {RuleCategory.FEATURE, RuleCategory.OPTIMIZATION,OCA}
    )
    public static boolean allowBlockUpdateLogger = false;

    @Rule(
            desc = "Don't spawn bats!",
            category = {RuleCategory.FEATURE,OCA}
    )
    public static boolean removeBats = false;

    @Rule(
            desc = "The rate limit of `/player xxx tp` command. Default: permit 1 request per 2 seconds per player.",
            category = {RuleCategory.FEATURE, RuleCategory.COMMAND,OCA},
            validate = PlayerTpRateLimitTimeValidator.class,
            strict = false,
            options = {"2"}
    )
    public static int playerTpRateLimitTime = 2;

    @Rule(
            desc = "If enabled, command logger will broadcast commands to those players.",
            category = {RuleCategory.COMMAND, RuleCategory.FEATURE,OCA},
            options = {"true", "false", "ops"}
    )
    public static String commandLoggerBroadcastToPlayer = FALSE;

    @Rule(
            desc = "allow spectator tp to any player!",
            category = {RuleCategory.FEATURE, RuleCategory.COMMAND,OCA},
            options = {"true", "false"}
    )
    public static boolean allowSpectatorTpToAnyPlayer = false;
}
