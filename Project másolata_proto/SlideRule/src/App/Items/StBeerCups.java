package App.Items;

import java.util.logging.Level;

import App.*;
import static App.Proto.*;

/**
 * Represents a StBeerCups item in the game.
 * Extends the Item class.
 */
public class StBeerCups extends Item {

    /**
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     *
     * @param name
     */
    public StBeerCups(String name) {
        super(name, "StBeerCups");
    }
    public StBeerCups(String name, Student owner) {
        super(name, "StBeerCups", owner);
    }

    /**
     * Applies the effect of the StBeerCups item.
     */
    public void ApplyEffect(){
        owner.SetIsProtected(3);
        owner.Drunk();
        System.out.println("StBeerCups: ApplyEffect()");
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
        resultLogger.log(Level.INFO, "StBeerCups: "+name);
    }

    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
    }
}
