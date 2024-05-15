package App.Model.Items;

import java.util.logging.Level;

import App.Model.*;
import static App.Program.*;

/**
 * Represents a Camembert item in the game.
 * Extends the Item class.
 */
public class Camembert extends Item {

    
    /**
     * Constructs a Camembert item with the given name.
     *
     * @param name The name of the Camembert.
     */
    public Camembert(String name) {
        super(name, "Camembert");
    }

    public Camembert() {
        super("Camembert");
    }

    /**
     * Constructs a Camembert item with the given name and owner.
     *
     * @param name  The name of the Camembert.
     * @param owner The owner of the Camembert.
     */
    public Camembert(String name, Student owner) {
        super(name, "Camembert", owner);
    }

    /**
     * Applies the effect of the Camembert item.
     * This method adds gas to the location of the Camembert's owner.
     */
    public void ApplyEffect() {
        owner.GetLocation().AddGas();
    }

    /**
     * Gets the name of the Camembert.
     *
     * @return The name of the Camembert.
     */
    @Override
    public String GetName() {
        return name;
    }

    /**
     * Gets the type of the Camembert.
     *
     * @return The type of the Camembert.
     */
    @Override
    public String GetType() {
        return type;
    }

    /**
     * Logs information about the Camembert.
     */
    @Override
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "Camembert: " + name);
    }

    /**
     * Logs information about the owner of the Camembert.
     */
    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
    }

}
