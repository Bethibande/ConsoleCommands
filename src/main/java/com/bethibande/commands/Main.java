package com.bethibande.commands;

public class Main {

    @SafeVarargs
    public static <T> T[] arrayOf(T... values) {
        return values;
    }

    public static void main(String[] args) throws Throwable {
        final Parameter<String> text = new Parameter<>("text", s -> s);
        final Parameter<String> name = new Parameter<>("name", s -> s);
        final Argument noParamArg = new Argument("t");
        final Argument arg = new Argument("m");

        arg.addParameter(name);
        name.setAllowedValues(() -> arrayOf("Max", "Unknown"));

        final Command command = new Command("hello");
        command.addArgument(noParamArg);
        command.addArgument(arg);
        command.addParameter(text);

        final CommandMap map = new CommandMap();
        map.addCommand(command);

        System.out.println(map.getCommandStructure());
        System.out.println(map.commandToString(command));

        final String cmd = "hello --m Max world";

        final ParseResult<CommandParseResult> result = command.parse(map, cmd.split("\\s"), 1);
    }

}
