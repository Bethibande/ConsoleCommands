package com.bethibande.commands.arguments;

import com.bethibande.commands.Parameter;
import org.jetbrains.annotations.NotNull;

public class BooleanParameter extends Parameter<Boolean> {

    public BooleanParameter(final @NotNull String name) {
        super(name, (s) -> Boolean.valueOf(s));
        setAllowedValues(() -> new String[]{"true", "false"});
        setValueValidator((s) -> s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false"));
    }

}
