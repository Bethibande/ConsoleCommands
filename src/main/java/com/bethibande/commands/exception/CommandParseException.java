package com.bethibande.commands.exception;

public class CommandParseException extends CommandException {

    public CommandParseException(final String message) {
        super(message);
    }

    public CommandParseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CommandParseException(final Throwable cause) {
        super(cause);
    }

    public CommandParseException(final String message,
                                 final Throwable cause,
                                 final boolean enableSuppression,
                                 final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
