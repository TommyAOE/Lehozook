package App.Model.Items;

import java.util.logging.Level;

import App.Model.*;
import static App.Program.*;

/**
 * Represents a transistor item in the game.
 * Extends the Item class.
 */
public class Transistor extends Item{
    
    Transistor pair; // The paired transistor
    public Room location; // The location of the transistor

    /**
     * Constructs a transistor with the given ID and owner.
     *
     * @param name  The ID of the transistor.
     * @param owner The owner of the transistor.
     */
    public Transistor(String name, Student owner) {
        super(name, "Transistor", owner);
    }

    /**
     * Constructs a transistor with the given ID and location.
     *
     * @param name     The ID of the transistor.
     * @param location The location of the transistor.
     */
    public Transistor(String name, Room location) {
        super(name, "Transistor");
        this.location = location;
    }

    /**
     * Applies the effect of the transistor item.
     * If the transistor has a pair, it drops itself from the owner.
     */
    public void ApplyEffect() {
        if (this.pair != null) {
            owner.DropItem(this);
            owner = null;
        }
    }

    /**
     * Sets a pair for the transistor.
     *
     * @param t The transistor to pair with.
     */
    public void SetPair(Transistor t) {
        this.pair = t;
        if (!t.HasPair()) t.SetPair(this);
    }

    /**
     * Activates the transistor.
     * If the transistor has a pair, it moves the owner to the pair's location.
     */
    public void Activate() {
        resultLogger.log(Level.INFO, "Transistor activated");
        if (pair != null) {
            resultLogger.log(Level.INFO, "The transistor has a pair");
            Room newRoom = pair.location;
            owner.DropItem(this);
            if (owner.TravelWithTransistor(newRoom)) {
                resultLogger.log(Level.INFO, "Successfully entered the new room");
            }
        }
    }

    /**
     * Checks if the transistor has a pair.
     *
     * @return true if the transistor has a pair, false otherwise.
     */
    public boolean HasPair() {
        return pair != null;
    }

    /**
     * Gets the name of the transistor item.
     *
     * @return The name of the transistor item.
     */
    @Override
    public String GetName() {
        return name;
    }

    /**
     * Gets the type of the transistor item.
     *
     * @return The type of the transistor item.
     */
    @Override
    public String GetType() {
        return type;
    }

    /**
     * Logs information about the transistor item.
     */
    @Override
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "Transistor: " + name);
    }

    /**
     * Logs detailed information about the transistor item, including its owner, pair, and location.
     */
    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
        if (pair != null)
            resultLogger.log(Level.INFO, name + ".pair: " + pair.GetName());
        else
            resultLogger.log(Level.INFO, name + ".pair : NULL");
        if (location != null)
            resultLogger.log(Level.INFO, name + ".location: " + location.GetName());
        else
            resultLogger.log(Level.INFO, name + ".location : NULL");
    }

    /**
     * Resets the owner, pair, and location of the transistor item to null.
     */
    @Override
    public void Reset_Test() {
        super.Reset_Test();
        pair = null;
        location = null;
    }
}
