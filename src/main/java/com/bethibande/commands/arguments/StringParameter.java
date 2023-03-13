package com.bethibande.commands.arguments;

import com.bethibande.commands.Parameter;
import org.jetbrains.annotations.NotNull;

public class StringParameter extends Parameter<String> {

    public StringParameter(final @NotNull String name) {
        super(name, (s) -> s);
    }
}
