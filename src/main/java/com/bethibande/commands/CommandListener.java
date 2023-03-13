package com.bethibande.commands;

import com.bethibande.commands.exception.CommandException;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class CommandListener extends Thread {

    private final CommandDispatcher dispatcher;
    private final Consumer<CommandException> exceptionLogger;

    public CommandListener(final @NotNull CommandDispatcher dispatcher,
                           final @NotNull Consumer<CommandException> exceptionLogger) {
        super("CommandListener");
        super.setDaemon(true);

        this.dispatcher = dispatcher;
        this.exceptionLogger = exceptionLogger;
    }

    @Override
    public void run() {
        final InputStream in = System.in;
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while(true) {
            try {
                final String str = reader.readLine();

                try {
                    dispatcher.dispatchCommand(str);
                } catch(CommandException ex) {
                    exceptionLogger.accept(ex);
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
