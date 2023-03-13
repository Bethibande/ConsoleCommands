package commands;

import com.bethibande.commands.Argument;
import com.bethibande.commands.ArgumentMap;
import com.bethibande.commands.ArgumentParameters;
import com.bethibande.commands.Command;
import com.bethibande.commands.arguments.EnumParameter;
import com.bethibande.commands.arguments.IntParameter;
import com.bethibande.commands.arguments.StringParameter;

import java.util.concurrent.TimeUnit;

public class TestCommand extends Command {

    private final StringParameter text = new StringParameter("text");
    private final Argument delayed = new Argument("d");
    private final IntParameter amount = new IntParameter("amount");
    private final EnumParameter<TimeUnit> timeunit = new EnumParameter<>("time-unit", TimeUnit.class);

    public TestCommand() {
        super("print");

        delayed.addParameter(amount);
        delayed.addParameter(timeunit);
        addArgument(delayed);
        addParameter(text);

        setExecutor(this::execute);
    }

    private void print(final ArgumentMap args) {
        System.out.println("text: %s".formatted(args.getParameter(text)));
    }

    private void printAfter(final ArgumentMap args) {
        try {
            final ArgumentParameters p = args.getArgument(delayed);
            final TimeUnit tu = p.getParameter(timeunit);
            final int delay = p.getParameter(amount);

            System.out.println("Printing in %d %s.".formatted(delay, tu.name().toLowerCase()));

            Thread.sleep(tu.toMillis(delay));
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        print(args);
    }

    public void execute(final ArgumentMap args) {
        if(args.hasArgument(delayed)) {
            new Thread(() -> printAfter(args)).start();
            return;
        }

        print(args);
    }

}
