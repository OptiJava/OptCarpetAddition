package io.github.optijava.opt_carpet_addition.utils.validator;

//#if MC >= 11900
//$$ import carpet.api.settings.Validator;
//$$ import carpet.api.settings.CarpetRule;
//#else
import carpet.settings.Validator;
import carpet.settings.ParsedRule;
//#endif
import net.minecraft.server.command.ServerCommandSource;

public class PlayerTpRateLimitTimeValidator extends Validator<Integer> {
    @Override
    //#if MC >= 11900
    //$$ public Integer validate(ServerCommandSource source, CarpetRule<Integer> currentRule, Integer newValue, String string) {
    //#else
    public Integer validate(ServerCommandSource source, ParsedRule<Integer> currentRule, Integer newValue, String string) {
    //#endif
        return newValue >= 0 ? newValue : null;
    }

    @Override
    public String description() {
        return "Player tp rate limit time must >= 0. No limit if value is 0.";
    }
}
