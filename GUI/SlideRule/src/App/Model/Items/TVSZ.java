package App.Model.Items;

import App.Model.*;
import static App.Program.*;

import java.util.logging.Level;

/**
 * Represents a TVSZ item in the game.
 * Extends the Item class.
 */
public class TVSZ extends Item {

    private int uses = 3;
    /**
     * Constructs a TVSZ item
     */
    public TVSZ() {
        super("TVSZ");
    }
    /**
     * Constructs a TVSZ item with the given name and initializes the number of uses.
     * @param name The name of the TVSZ item.
     */
    public TVSZ(String name) {
        super(name, "TVSZ");
    }
    /**
     * Constructs a TVSZ item with the given name and owner, and initializes the number of uses.
     * @param name  The name of the TVSZ item.
     * @param owner The owner of the TVSZ item.
     */
    public TVSZ(String name, Student owner) {
        super(name, "TVSZ", owner);
    }
    /**
     * Gets the name of the TVSZ item.
     * @return The name of the TVSZ item.
     */
    @Override
    public String GetName() {
        return name;
    }
    /**
     * Gets the type of the TVSZ item.
     * @return The type of the TVSZ item.
     */
    @Override
    public String GetType() {
        return type;
    }
    /**
     * Applies the effect of the TVSZ item.
     * Protects all students in the same room as the owner from professors' attacks.
     * Decreases the number of uses remaining.
     * If there are no uses left, removes the item from the owner's inventory.
     */
    public void ApplyEffect() {
        Room location = owner.GetLocation();
        for (Student student : location.GetStudents()) {
            student.SetIsProtected(location.GetProfessors().size());
        }
        uses--;
        if (uses <= 0) {
            owner.RemoveItem(this);
        }
    }

    //------------------Functions for testing------------------
    /**
     * Logs information about the TVSZ item, including its owner and the number of uses remaining.
     */
    @Override
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "TVSZ: " + name);
    }

    /**
     * Logs detailed information about the TVSZ item, including its owner and the number of uses remaining.
     */
    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
        resultLogger.log(Level.INFO, name + ".uses : " + uses);
    }
}
