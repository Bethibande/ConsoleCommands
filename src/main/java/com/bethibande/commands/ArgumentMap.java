package com.bethibande.commands;

import java.util.HashMap;

public record ArgumentMap(Command command,
                          HashMap<String, Object> parameterValues,
                          HashMap<String, Object[]> argumentValues) {

    public boolean hasArgument(final Argument argument) {
        return hasArgument(argument.getName());
    }

    public boolean hasArgument(final String argument) {
        return argumentValues.containsKey(argument);
    }

    public ArgumentParameters getArgument(final Argument argument) {
        if (!hasArgument(argument)) return null;
        return new ArgumentParameters(argument, argumentValues.get(argument.getName()));
    }

    public <T> T getParameter(final Parameter<T> parameter) {
        return this.getParameter(parameter.getName());
    }

    @SuppressWarnings("unchecked")
    public <T> T getParameter(final String name) {
        return (T) parameterValues.get(name);
    }
}
