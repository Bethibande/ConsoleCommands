package com.bethibande.commands;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;

public class CommandMap {

    private final HashMap<String, Command> commands = new HashMap<>();
    private String argumentDelimiter = "--";

    public String getCommandStructure() {
        return "COMMAND (" + argumentDelimiter + "argument [argument parameters]) [command parameters]";
    }

    public String parameterToString(final @NotNull Parameter<?> parameter) {
        if(parameter.getAllowedValues() == null || parameter.getAllowedValues().get().length > 5) {
            return "[%s]".formatted(parameter.getName());
        }

        final String[] values = parameter.getAllowedValues().get();
        return "%s".formatted(Arrays.toString(values));
    }

    public String argumentToString(final @NotNull Argument argument) {
        final StringBuilder sb = new StringBuilder(this.argumentDelimiter + argument.getName());

        for(final Parameter<?> parameter : argument.getParameters()) {
            sb.append(" %s".formatted(parameterToString(parameter)));
        }

        return sb.toString();
    }

    public String commandToString(final @NotNull Command command) {
        final StringBuilder sb = new StringBuilder(command.getName());

        for(Parameter<?> parameter : command.getParameters()) {
            sb.append(" %s".formatted(parameterToString(parameter)));
        }

        if(!command.getArguments().isEmpty()) sb.append(" | Arguments: ");

        for(Argument argument : command.getArguments()) {
            sb.append(" %s,".formatted(argumentToString(argument)));
        }

        return sb.toString();
    }

    public void addCommand(final @NotNull Command command) {
        commands.put(command.getName(), command);
    }

    public @Nullable Command removeCommand(final @NotNull String command) {
        return commands.remove(command);
    }

    public void removeCommand(final @NotNull Command command) {
        removeCommand(command.getName());
    }

    public @NotNull HashMap<String, Command> getCommands() {
        return commands;
    }

    public String getArgumentDelimiter() {
        return argumentDelimiter;
    }

    public void setArgumentDelimiter(final String argumentDelimiter) {
        this.argumentDelimiter = argumentDelimiter;
    }
}
