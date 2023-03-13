package com.bethibande.commands;

import com.bethibande.commands.Argument;
import com.bethibande.commands.Parameter;

public class ArgumentParameters {

    private final Argument parent;
    private final Object[] parameters;

    public ArgumentParameters(final Argument parent, final Object[] parameters) {
        this.parent = parent;
        this.parameters = parameters;
    }

    @SuppressWarnings("unchecked")
    public <T> T getParameter(final Parameter<T> parameter) {
        return (T) parameters[parent.getParameters().indexOf(parameter)];
    }

}
