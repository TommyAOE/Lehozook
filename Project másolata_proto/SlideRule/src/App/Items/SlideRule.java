package App.Items;

import java.util.logging.Level;

import App.*;
import static App.Proto.*;

/**
 * Represents a SlideRule item in the game.
 * Extends the Item class.
 */
public class SlideRule extends Item {

    /**
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     *
     * @param name
     */
    public SlideRule(String name) {
        super(name, "SlideRule");
    }
    public SlideRule(String name, Student owner) {
        super(name, "SlideRule", owner);
    }

    /**
     * Applies the effect of the SlideRule item.
     */
    public void ApplyEffect(){
        System.out.println("Student wins the game!");
        System.exit(0);
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
        resultLogger.log(Level.INFO, "SlideRule: "+name);
    }

    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
    }
}
