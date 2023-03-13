package com.bethibande.commands;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;

public class Parameter<T> implements Parsable<T> {

    private final String name;

    private Supplier<String[]> allowedValues = null;
    private Function<String, Boolean> valueValidator = null;

    private final Function<String, T> fromString;

    public Parameter(final @NotNull String name, final Function<String, T> fromString) {
        this.name = name;
        this.fromString = fromString;
    }

    @Override
    public ParseResult<T> parse(final CommandMap map, final String[] command, int index) {
        final StringBuilder sb = new StringBuilder();
        boolean hasLength = false;

        while(true) {
            checkIndex(command, index, 1, getName());

            if(command[index].startsWith("\"")) {
                if(hasLength) {
                    this.invalidValue(getName());
                }
                hasLength = true;
            }

            if(!hasLength) {
                sb.append(command[index]);
                index++;
                break;
            }

            if(hasLength) {
                if(sb.length() != 0) sb.append(" ");
                sb.append(command[index]);

                if(command[index].matches(".*[^\\\\]?\"")) {
                    index++;
                    break;
                }
            }

            index++;
        }

        if(hasLength) {
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length()-1);
        }

        boolean validation = true;

        if(allowedValues != null) {
            boolean found = false;
            for(String allowed : allowedValues.get()) {
                if(allowed.equals(sb.toString())) {
                    found = true;
                    break;
                }
            }
            if(!found) validation = false;
        }

        if(valueValidator != null) {
            validation = valueValidator.apply(sb.toString());
        }

        if(!validation) this.invalidValue(getName());

        T value = null;
        try {
            value = fromString.apply(sb.toString());
        } catch(Throwable th) {
            this.invalidValue(getName());
        }

        return new ParseResult<>(
                value,
                index
        );
    }

    public void setAllowedValues(final @Nullable Supplier<String[]> allowedValues) {
        this.allowedValues = allowedValues;
    }

    public void setValueValidator(final @Nullable Function<String, Boolean> valueValidator) {
        this.valueValidator = valueValidator;
    }

    public @NotNull String getName() {
        return name;
    }

    public @Nullable Supplier<String[]> getAllowedValues() {
        return allowedValues;
    }

    public @Nullable Function<String, Boolean> getValueValidator() {
        return valueValidator;
    }
}
