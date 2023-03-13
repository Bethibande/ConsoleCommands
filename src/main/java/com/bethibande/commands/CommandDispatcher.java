package com.bethibande.commands;

import com.bethibande.commands.exception.CommandExecutionException;
import com.bethibande.commands.exception.UnknownCommandException;
import org.jetbrains.annotations.Nullable;

public class CommandDispatcher {

    private final CommandMap map;

    public CommandDispatcher(final CommandMap map) {
        this.map = map;
    }

    public void dispatchCommand(final @Nullable String command) {
        final ArgumentMap args = parseCommand(command);
        if(map == null) return;

        final Command cmd = args.command();
        if(cmd.getExecutor() != null) {
            try {
                cmd.getExecutor().accept(args);
            } catch(Throwable th) {
                throw new CommandExecutionException("An error occurred whilst executing a commands: " + th.getMessage(), th);
            }
        }
    }

    public ArgumentMap parseCommand(final @Nullable String command) {
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
                        cmd,
                        result.value().parameterValues(),
                        result.value().argumentValues()
                );

                return args;
            }
        }

        throw new UnknownCommandException(command);
    }

}
