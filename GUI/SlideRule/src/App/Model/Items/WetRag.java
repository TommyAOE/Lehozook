package App.Model.Items;

import java.util.Random;
import java.util.logging.Level;

import App.Model.*;
import static App.Program.*;

/**
 * Represents a wet rag item in the game.
 * Extends the Item class.
 */
public class WetRag extends Item{
    private int moistureLevel; // The moisture level of the wet rag

    /**
     * Constructs a wet rag item with the given name and randomly generates a moisture level.
     *
     * @param name The name of the wet rag item.
     */
    public WetRag(String name) {
        super(name, "WetRag");
        Random random = new Random();
        moistureLevel = random.nextInt(3) + 1;
    }

    public WetRag() {
        super("WetRag");
        Random random = new Random();
        moistureLevel = random.nextInt(3) + 1;
    }

    /**
     * Constructs a wet rag item with the given name and owner, and randomly generates a moisture level.
     *
     * @param name  The name of the wet rag item.
     * @param owner The owner of the wet rag item.
     */
    public WetRag(String name, Student owner) {
        super(name, "WetRag", owner);
        Random random = new Random();
        moistureLevel = random.nextInt(3) + 1;
    }

    /**
     * Applies the effect of the wet rag item.
     * Stuns professors in the same room as the owner based on the moisture level of the rag.
     */
    public void ApplyEffect() {
        for (Professor prof : owner.GetLocation().GetProfessors()) {
            prof.Stun(moistureLevel);
        }
    }

    /**
     * Gets the name of the wet rag item.
     *
     * @return The name of the wet rag item.
     */
    @Override
    public String GetName() {
        return name;
    }

    /**
     * Gets the type of the wet rag item.
     *
     * @return The type of the wet rag item.
     */
    @Override
    public String GetType() {
        return type;
    }

    /**
     * Logs information about the wet rag item.
     */
    @Override
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "WetRag: " + name);
    }

    /**
     * Logs detailed information about the wet rag item, including its owner and moisture level.
     */
    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
        resultLogger.log(Level.INFO, name + ".moistureLevel : " + moistureLevel);
    }

    /**
     * Gets the moisture level of the wet rag item.
     *
     * @return The moisture level of the wet rag item.
     */
    public int GetMoistureLevel() {
        return moistureLevel;
    }
}
