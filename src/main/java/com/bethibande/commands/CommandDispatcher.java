package com.bethibande.commands;

import com.bethibande.commands.exception.CommandExecutionException;
import com.bethibande.commands.exception.UnknownCommandException;

public class CommandDispatcher {

    private final CommandMap map;

    public CommandDispatcher(final CommandMap map) {
        this.map = map;
    }

    public ArgumentMap dispatchCommand(final String command) {
        if(command == null) return null;

        for(Command cmd : map.getCommands().values()) {
            if(!command.startsWith(cmd.getName())) continue;
            final String other = command.substring(cmd.getName().length());

            if(other.split("\\s").length >= cmd.getParameters().size()) {
                final ParseResult<CommandParseResult> result = cmd.parse(
                        map,
                        command.split("\\s"),
                        cmd.getName().split("\\s").length
                );

                final ArgumentMap args = new ArgumentMap(
                        result.value().parameterValues(),
                        result.value().argumentValues()
                );

                if(cmd.getExecutor() != null) {
                    try {
                        cmd.getExecutor().accept(args);
                    } catch(Throwable th) {
                        throw new CommandExecutionException("An error occurred whilst executing a commands: " + th.getMessage(), th);
                    }
                }

                return args;
            }
        }

        throw new UnknownCommandException(command);
    }

}
