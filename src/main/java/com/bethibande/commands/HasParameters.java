package com.bethibande.commands;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface HasParameters {

    @NotNull List<Parameter<?>> getParameters();

    default void addParameter(final @NotNull Parameter<?> p) {
        getParameters().add(p);
    }

    default void removeParameter(final @NotNull Parameter<?> p) {
        getParameters().remove(p);
    }

}
