package com.bethibande.commands.exception;

public class CommandExecutionException extends CommandException {

    public CommandExecutionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CommandExecutionException(final Throwable cause) {
        super(cause);
    }

    public CommandExecutionException(final String message,
                                     final Throwable cause,
                                     final boolean enableSuppression,
                                     final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
