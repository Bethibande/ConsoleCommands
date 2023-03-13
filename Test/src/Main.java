import com.bethibande.commands.CommandDispatcher;
import com.bethibande.commands.CommandListener;
import com.bethibande.commands.CommandMap;
import commands.TestCommand;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final CommandMap map = new CommandMap();
        final CommandDispatcher dispatcher = new CommandDispatcher(map);
        final CommandListener listener = new CommandListener(dispatcher, (ex) -> {
           System.err.println(ex.getMessage());
        });

        map.addCommand(new TestCommand());
        listener.start();

        System.out.println(map.commandToString(map.getCommands().get("print")));

        // blocking main thread to preven jvm from shutting down
        while(true) {
            Thread.sleep(1000);
        }
    }
}