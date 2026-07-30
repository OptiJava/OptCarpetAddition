package io.github.optijava.opt_carpet_addition;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;
import io.github.optijava.opt_carpet_addition.utils.config_bean.CommandLoggerConfigBean;
import io.github.optijava.opt_carpet_addition.utils.config_bean.TpLimitConfigBean;
import io.github.optijava.opt_carpet_addition.utils.validator.CustomItemDiscardTimeValidator;

import io.github.optijava.opt_carpet_addition.utils.validator.PlayerTpRateLimitTimeValidator;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

@SuppressWarnings("all")
public class OptCarpetSettings {
    public static final String OCA = "OCA";
    public static final String FALSE = "false";
    public static final String TRUE = "true";
    public static Path configDirectory = FabricLoader.getInstance().getConfigDir().resolve("opt-carpet-addition");

    // Tp command rules =======================================================================

    @Rule(
            categories = {RuleCategory.COMMAND,OCA}
    )
    public static String commandTpToFakePlayer = FALSE;

    @Rule(
            categories = {RuleCategory.COMMAND,OCA},
            options = {"true", "false", "ops"}
    )
    public static String allowTpToRealPlayer = FALSE;

    @Rule(
            categories = {RuleCategory.COMMAND,OCA},
            options = {"true", "false", "ops"}
    )
    public static String commandTpHereFakePlayer = FALSE;

    @Rule(
            categories = {RuleCategory.COMMAND,OCA},
            options = {"true", "false", "ops"}
    )
    public static String allowTpHereRealPlayer = FALSE;

    // Force fake player gamemode rule =========================================================
    @Rule(
            categories = {RuleCategory.FEATURE,OCA},
            options = {"survival", "creative", "adventure", "false"}
    )
    public static String forceFakePlayerGameMode = FALSE;

    // Fix bugs ================================================================================
    @Rule(
            categories = {RuleCategory.BUGFIX,OCA}
    )
    public static boolean fixXpLevelBug = false;

    // Tp whitelist/blacklist  ====================================================================

    public static TpLimitConfigBean tpLimitConfigBean;

    public static CommandLoggerConfigBean commandLoggerConfigBean;

    @Rule(
            categories = {RuleCategory.FEATURE,OCA}
    )
    public static boolean enableTpPrefixBlacklist = false;

    @Rule(
            categories = {RuleCategory.FEATURE,OCA}
    )
    public static boolean enableTpPrefixWhitelist = false;

    @Rule(
            categories = {RuleCategory.FEATURE,OCA}
    )
    public static boolean enableTpHerePrefixBlacklist = false;

    @Rule(
            categories = {RuleCategory.FEATURE,OCA}
    )
    public static boolean enableTpHerePrefixWhitelist = false;

    @Rule(
            categories = {RuleCategory.FEATURE,OCA}
    )
    public static boolean disabledLayEggs = false;

    @Rule(
            categories = {RuleCategory.FEATURE,OCA}
    )
    public static boolean disabledEnderManPickupGoal = false;

    @Rule(
            categories = {RuleCategory.FEATURE,OCA}
    )
    public static boolean disabledEnderManPlaceBlockGoal = false;

    @Rule(
            categories = {RuleCategory.OPTIMIZATION, RuleCategory.FEATURE,OCA}
    )
    public static boolean disabledNetherPortalSpawn = false;

    @Rule(
            categories = {RuleCategory.EXPERIMENTAL,OCA}
    )
    public static boolean disabledEntityTick = false;

    @Rule(
            categories = {RuleCategory.FEATURE, RuleCategory.CLIENT,OCA}
    )
    public static boolean unescapeChatMessage = false;

    @Rule(
            categories = {RuleCategory.COMMAND, RuleCategory.FEATURE,OCA}
    )
    public static boolean commandLogger = false;
    
    //? if < 1.21.10 {
    /*@Rule(
            categories = {RuleCategory.OPTIMIZATION,OCA}
    )
    public static boolean optimizeFakePlayerSpawn = false;
    *///?}


    @Rule(
            categories = {RuleCategory.FEATURE,OCA}
    )
    public static boolean removeAllCurseOfBindingArmorWhenPlayerDeadInWall = false;

    @Rule(
            categories = {RuleCategory.FEATURE,OCA},
            options = {"-1", "6000"},
            strict = false,
            validators = CustomItemDiscardTimeValidator.class
    )
    public static int customItemDiscardTime = -1;

    @Rule(
            categories = {RuleCategory.FEATURE, RuleCategory.OPTIMIZATION,OCA}
    )
    public static boolean allowBlockUpdateLogger = false;

    @Rule(
            categories = {RuleCategory.FEATURE,OCA}
    )
    public static boolean removeBats = false;

    @Rule(
            categories = {RuleCategory.FEATURE, RuleCategory.COMMAND,OCA},
            validators = PlayerTpRateLimitTimeValidator.class,
            strict = false,
            options = {"2"}
    )
    public static int playerTpRateLimitTime = 2;

    @Rule(
            categories = {RuleCategory.COMMAND, RuleCategory.FEATURE,OCA},
            options = {"true", "false", "ops"}
    )
    public static String commandLoggerBroadcastToPlayer = FALSE;

    @Rule(
            categories = {RuleCategory.FEATURE, RuleCategory.COMMAND,OCA},
            options = {"true", "false"}
    )
    public static boolean allowSpectatorTpToAnyPlayer = false;

    @Rule(
            categories = {RuleCategory.COMMAND , OCA},
            options = {"true", "false", "1", "2", "3", "4"},
            strict = true
    )
    public static String enableLoggerCommand = TRUE;

    @Rule(
            categories = {RuleCategory.COMMAND , OCA},
            options = {"true", "false", "1", "2", "3", "4"},
            strict = true
    )
    public static String enableCrashCommand = FALSE;

    @Rule(
            categories = {RuleCategory.COMMAND , OCA}
    )
    public static boolean enableListAdvanceCommand = true;

    @Rule(
            categories = {RuleCategory.COMMAND , OCA}
    )
    public static boolean enableTpmanagerCommand = true;

    @Rule(
            categories = {RuleCategory.FEATURE, OCA}
    )
    public static boolean allowSpectatorToModifyContainer = false;

    @Rule(
            categories = {RuleCategory.COMMAND, RuleCategory.EXPERIMENTAL,OCA}
    )
    public static boolean removeDataModifyRestriction = false;
}
