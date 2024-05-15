package App;

import App.Model.Items.*;
import java.util.logging.Logger;

public class Program {

    public static Logger resultLogger = Logger.getLogger("resultLogger");

    /**
     * Main method to start the game and execute commands.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        GameController gc = new GameController();
    }

}