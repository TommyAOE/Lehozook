package App.Model.Items;

import java.util.logging.Level;

import App.Model.*;
import static App.Program.*;

/**
 * Represents a SlideRule item in the game.
 * Extends the Item class.
 */
public class SlideRule extends Item {

    /**
     * Constructs a slide rule item with the given name.
     *
     * @param name The name of the slide rule item.
     */
    public SlideRule(String name) {
        super(name, "SlideRule");
    }

    /**
     * Constructs a slide rule item with the given name and owner.
     *
     * @param name  The name of the slide rule item.
     * @param owner The owner of the slide rule item.
     */
    public SlideRule(String name, Student owner) {
        super(name, "SlideRule", owner);
    }

    /**
     * Applies the effect of the slide rule item.
     * This method prints a message indicating that the student wins the game and exits the program.
     */
    public void ApplyEffect() {
        resultLogger.log(Level.INFO,"Students have won!");
        System.exit(0);
    }

    /**
     * Gets the name of the slide rule item.
     *
     * @return The name of the slide rule item.
     */
    @Override
    public String GetName() {
        return name;
    }

    /**
     * Gets the type of the slide rule item.
     *
     * @return The type of the slide rule item.
     */
    @Override
    public String GetType() {
        return type;
    }

    /**
     * Logs information about the slide rule item.
     */
    @Override
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "SlideRule: " + name);
    }

    /**
     * Logs information about the owner of the slide rule item.
     */
    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
    }
}
