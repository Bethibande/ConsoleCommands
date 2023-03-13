package com.bethibande.commands.exception;

public class UnknownCommandException extends CommandException {

    public UnknownCommandException(final String command) {
        super("Unknown command '%s'".formatted(command));
    }
}
