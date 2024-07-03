package io.github.optijava.opt_carpet_addition.utils.validator;

import carpet.settings.ParsedRule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

public class PlayerTpRateLimitTimeValidator extends Validator<Integer> {
    @Override
    public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
        return newValue >= 0 ? newValue : null;
    }

    @Override
    public String description() {
        return "Player tp rate limit time must >= 0. No limit if value is 0.";
    }
}
