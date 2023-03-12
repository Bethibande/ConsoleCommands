package com.bethibande.commands;

import com.bethibande.commands.exception.CommandParseException;

public interface Parsable<T> {

    /**
     * @param map the map parsing the element
     * @param command the command, split by \\s (space symbol)
     * @param index the current array index to parse
     * @return the new index and value
     */
    ParseResult<T> parse(final CommandMap map, final String[] command, final int index);

    default void invalidValue(final String name) {
        throw new CommandParseException("Invalid value for parameter %s".formatted(name));
    }

    default void checkIndex(final String[] command, final int index, final int length, final String current) {
        if(index + length > command.length) invalidValue(current);
    }

}
