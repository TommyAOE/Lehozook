package App.Model.Items;

import App.Model.*;
import static App.Program.*;

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
        if(owner.GetLocation().GetNeighbours().size() == 0){
            resultLogger.log(Level.WARNING, "The room has no neighbours");
            return;
        }
        Room newRoom = owner.GetLocation().GetNeighbours().get(new Random().nextInt(owner.GetLocation().GetNeighbours().size()));
        while(!owner.EnterRoom(newRoom))
            newRoom = owner.GetLocation().GetNeighbours().get(new Random().nextInt(owner.GetLocation().GetNeighbours().size()));
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
        return "FakeItem";
    }
    public String GetFakeType(){
        return type;
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

