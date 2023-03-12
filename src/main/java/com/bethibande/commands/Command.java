package com.bethibande.commands;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Command implements HasParameters, HasArguments, Parsable<CommandParseResult> {

    private final String name;
    private final List<Parameter<?>> parameters = new ArrayList<>();
    private final List<Argument> arguments = new ArrayList<>();

    public Command(final String name) {
        this.name = name;
    }

    private Argument getArgument(final String name) {
        return arguments.stream().filter(a -> a.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public ParseResult<CommandParseResult> parse(final CommandMap map, final String[] command, int index) {
        final String delimiter = map.getArgumentDelimiter();
        final HashMap<String, Object[]> argumentValues = new HashMap<>();
        final HashMap<String, Object> parameterValues = new HashMap<>();

        while(true) {
            final String value = command[index];

            if(value.startsWith(delimiter)) {
                index++;

                final String argName = value.substring(delimiter.length());
                final Argument argument = getArgument(argName);
                if(argument == null) this.invalidValue(getName() + " unknown parameter" + argName);

                final ParseResult<Object[]> result = argument.parse(map, command, index);
                argumentValues.put(argName, result.value());

                index = result.index();

                continue;
            }

            break;
        }

        this.checkIndex(command, index, parameters.size(), getName());

        for(Parameter<?> parameter : parameters) {
            final ParseResult<?> result = parameter.parse(map, command, index);

            parameterValues.put(parameter.getName(), result.value());
            index = result.index();
        }

        return new ParseResult<>(
                new CommandParseResult(parameterValues, argumentValues),
                index
        );
    }

    public String getName() {
        return name;
    }

    @Override
    public @NotNull List<Argument> getArguments() {
        return arguments;
    }

    @Override
    public @NotNull List<Parameter<?>> getParameters() {
        return parameters;
    }
}
