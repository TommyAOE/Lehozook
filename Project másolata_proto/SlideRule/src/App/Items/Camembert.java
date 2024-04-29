package App.Items;

import java.util.logging.Level;

import App.*;
import static App.Proto.*;

/**
 * Represents a Camembert item in the game.
 * Extends the Item class.
 */
public class Camembert extends Item {

    /**
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     *
     * @param name
     */
    public Camembert(String name) {
        super(name, "Camembert");
    }

    public Camembert(String name, Student owner) {
        super(name, "Camembert", owner);
    }

    /**
     * Applies the effect of the Camembert item.
     */
    public void ApplyEffect(){
        owner.GetLocation().AddGas();
    }

    @Override
    public String GetName() {
        return name;
    }

    @Override
    public String GetType() {
        return type;
    }

    @Override
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "Camembert: " + name);
    }

    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
    }

}
