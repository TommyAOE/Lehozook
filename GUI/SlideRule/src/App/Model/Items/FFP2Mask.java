
package App.Model.Items;

import java.util.logging.Level;

import App.Model.*;
import static App.Program.*;

/**
 * Represents an FFP2 mask item in the game.
 * Extends the Item class.
 */
public class FFP2Mask extends Item {

    /** The number of times the mask can be used. */
    public int counter;

    /** The durability of the mask. */
    private int durability;

    /** Indicates whether the mask is broken. */
    private boolean broken;

    /**
     * Constructs a new FFP2 mask item with the specified name.
     *
     * @param name the name of the FFP2 mask item
     */
    public FFP2Mask(String name) {
        super(name, "FFP2Mask");
        counter = 3;
        durability = 3;
        broken = false;
    }

    public FFP2Mask() {
        super("FFP2Mask");
        counter = 3;
        durability = 3;
        broken = false;
    }

    /**
     * Constructs a new FFP2 mask item with the specified name and owner.
     *
     * @param name  the name of the FFP2 mask item
     * @param owner the owner of the FFP2 mask item
     */
    public FFP2Mask(String name, Student owner) {
        super(name, "FFP2Mask", owner);
        counter = 3;
        durability = 3;
        broken = false;
    }

    /**
     * Constructs a new FFP2 mask item with the specified name, usage counter, and durability.
     *
     * @param name       the name of the FFP2 mask item
     * @param counter    the usage counter of the FFP2 mask item
     * @param durability the durability of the FFP2 mask item
     */
    public FFP2Mask(String name, int counter, int durability) {
        super(name, "FFP2Mask");
        this.counter = counter;
        this.durability = durability;
        broken = false;
    }

    /**
     * Applies the effect of the FFP2 mask item.
     * Decrements the usage counter and updates the durability and broken status accordingly.
     */
    public void ApplyEffect() {
        --counter;
        if (counter == 0) {
            if (--durability == 0) {
                broken = true;
            }
        } else if (counter == -1) {
            counter = durability;
        }
    }

    /**
     * Checks whether the FFP2 mask is broken.
     *
     * @return true if the mask is broken, false otherwise
     */
    public boolean IsBroken() {
        return broken;
    }

    /**
     * Gets the name of the FFP2 mask item.
     *
     * @return the name of the mask item
     */
    @Override
    public String GetName() {
        return name;
    }

    /**
     * Gets the type of the FFP2 mask item.
     *
     * @return the type of the mask item
     */
    @Override
    public String GetType() {
        return type;
    }

    /**
     * Gets the counter of the FFP2 mask item.
     *
     * @return the counter of the mask item
     */
    public int GetCounter() {
        return counter;
    }

    /**
     * Logs information about the FFP2 mask item.
     */
    @Override
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "FFP2Mask" + name);
    }

    /**
     * Logs detailed information about the FFP2 mask item, including its owner, counter, durability, and broken status.
     */
    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
        resultLogger.log(Level.INFO, name + ".counter : " + counter);
        resultLogger.log(Level.INFO, name + ".durability : " + durability);
        resultLogger.log(Level.INFO, name + ".broken : " + broken);
    }
}

