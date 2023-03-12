package com.bethibande.commands;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface HasArguments {

    @NotNull List<Argument> getArguments();

    default void addArgument(final @NotNull Argument argument) {
        getArguments().add(argument);
    }

    default void removeArgument(final @NotNull Argument argument) {
        getArguments().remove(argument);
    }

}
