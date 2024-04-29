package App.Items;

import App.*;
import static App.Proto.*;

import java.util.Random;
import java.util.logging.Level;

/**
 * Represents a fake item in the game.
 * Extends the Item class.
 */
public class FakeItem extends Item {

    /**
     * Constructs a fake item with the given name and type.
     *
     * @param name The name of the fake item.
     * @param type The type of the fake item.
     */
    public FakeItem(String name, String type) {
        super(name, type);
    }

    /**
     * Constructs a fake item with the given name, type, and owner.
     *
     * @param name  The name of the fake item.
     * @param type  The type of the fake item.
     * @param owner The owner of the fake item.
     */
    public FakeItem(String name, String type, Student owner) {
        super(name, type, owner);
    }

    /**
     * Applies the effect of the fake item.
     * This method randomly moves the owner to an adjacent room if it's not full.
     */
    @Override
    public void ApplyEffect() {
        int safe = 100;
        Room newRoom = owner.GetLocation().neighbours.get(new Random().nextInt(owner.GetLocation().neighbours.size() + 1));
        while (newRoom.IsFull() && --safe > 0)
            newRoom = owner.GetLocation().neighbours.get(new Random().nextInt(owner.GetLocation().neighbours.size() + 1));
        owner.EnterRoom(newRoom);
    }

    /**
     * Gets the name of the fake item.
     *
     * @return The name of the fake item.
     */
    @Override
    public String GetName() {
        return name;
    }

    /**
     * Gets the type of the fake item.
     *
     * @return The type of the fake item.
     */
    @Override
    public String GetType() {
        return null;
    }

    /**
     * Logs information about the fake item.
     */
    @Override
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "FakeItem: " + name);
    }

    /**
     * Logs information about the owner of the fake item.
     */
    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
    }
}

