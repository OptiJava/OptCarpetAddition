package io.github.optijava.opt_carpet_addition.utils.validator;

import carpet.api.settings.Validator;
import carpet.api.settings.CarpetRule;
import net.minecraft.commands.CommandSourceStack;


public class PlayerTpRateLimitTimeValidator extends Validator<Integer> {
    @Override
    public Integer validate(CommandSourceStack source, CarpetRule<Integer> currentRule, Integer newValue, String string) {
        return newValue >= 0 ? newValue : null;
    }

    @Override
    public String description() {
        return "Player tp rate limit time must >= 0. No limit if value is 0.";
    }
}
