package com.bethibande.commands;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Argument implements HasParameters, Parsable<Object[]> {

    private final String name;
    private final List<Parameter<?>> parameters = new ArrayList<>();

    public Argument(final @NotNull String name) {
        this.name = name;
    }

    @Override
    public ParseResult<Object[]> parse(final CommandMap map, final String[] command, int index) {
        this.checkIndex(command, index, parameters.size(), getName());

        final Object[] values = new Object[parameters.size()];
        final int startIndex = index;
        for(Parameter<?> parameter : parameters) {
            final ParseResult<?> result = parameter.parse(map, command, index);
            values[index - startIndex] = result.value();

            index = result.index();
        }

        return new ParseResult<>(values, index);
    }

    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull List<Parameter<?>> getParameters() {
        return parameters;
    }
}
