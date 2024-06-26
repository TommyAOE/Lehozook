package App.Model.Items;

import java.util.logging.Level;

import App.Model.*;
import static App.Program.*;

/**
 * Represents a StBeerCups item in the game.
 * Extends the Item class.
 */
public class StBeerCups extends Item {
    /**
     * Constructs a StBeerCups object with the default name "StBeerCups".
     */
    public StBeerCups() {
        super("StBeerCups");
    }
    /**
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     * @param name
     */
    public StBeerCups(String name) {
        super(name, "StBeerCups");
    }
    /**
     * Constructs a StBeerCups object with the specified name and owner.
     * @param name  The name of the StBeerCups object.
     * @param owner The owner (Student) of the StBeerCups object.
     */
    public StBeerCups(String name, Student owner) {
        super(name, "StBeerCups", owner);
    }
    /**
     * Gets the name of the StBeerCups item.
     * @return The name of the StBeerCups item.
     */
    @Override
    public String GetName() {
        return name;
    }
    /**
     * Gets the type of the StBeerCups item.
     * @return The type of the StBeerCups item.
     */
    @Override
    public String GetType() {
        return type;
    }
    /**
     * Applies the effect of the StBeerCups item.
     */
    public void ApplyEffect(){
        owner.SetIsProtected(3);
        owner.RemoveItem(this);
        owner.Drunk();
        owner = null;
    }

    //------------------Functions for testing------------------
    /**
     * Logs information about all instances of the StBeerCups item.
     */
    @Override
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "StBeerCups: " + name);
    }

    /**
     * Logs information about the owner of the StBeerCups item.
     * If the owner is null, logs that the owner is NULL.
     */
    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
    }

}
