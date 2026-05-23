package io.github.optijava.opt_carpet_addition.utils.validator;


import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
import net.minecraft.commands.CommandSourceStack;


public class CustomItemDiscardTimeValidator extends Validator<Integer> {
    public Integer validate(CommandSourceStack source, CarpetRule<Integer> currentRule, Integer newValue, String string) {
            return newValue >= -1 ? newValue : null;
    }

    @Override
    public String description() {
        return "Item discard time must >= -1, -1 is the default value.";
    }
}
