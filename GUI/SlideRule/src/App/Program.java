package App;

import App.Model.Items.*;
import App.Model.Model;
import App.View.GameView;

import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Program {

    public static Logger resultLogger = Logger.getLogger("resultLogger");

    /**
     * Main method to start the game and execute commands.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SimpleFormatter formatter = new SimpleFormatter(){
            private static final String format = "[%1$tT] %2$-7s %3$s %n";
            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format, lr.getMillis(), lr.getLevel().getLocalizedName(), lr.getMessage());
            }
        };
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(formatter);
        resultLogger.setUseParentHandlers(false);
        resultLogger.addHandler(consoleHandler);
        Model model = new Model(5, 5, 5);

        GameView view = new GameView(model);

    }
}