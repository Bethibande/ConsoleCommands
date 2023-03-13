package com.bethibande.commands.arguments;

import com.bethibande.commands.Parameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class LongParameter extends Parameter<Long> {

    public LongParameter(final @NotNull String name) {
        super(name, Long::parseLong);
        setValueValidator(null);
    }

    private boolean validate(final String s) {
        return s.matches("-?\\d+(-?e\\d+)?");
    }

    @Override
    public void setValueValidator(final @Nullable Function<String, Boolean> valueValidator) {
        super.setValueValidator(s -> {
            if(s == null) return false;
            if(!this.validate(s)) return false;
            if(valueValidator == null) return true;
            return valueValidator.apply(s);
        });
    }

}
