package App.Items;

import java.util.Random;
import java.util.logging.Level;

import App.*;
import static App.Proto.*;

/**
 * Represents a wet rag item in the game.
 * Extends the Item class.
 */
public class WetRag extends Item{
    private int moistureLevel;
    /**
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     *
     * @param name
     */
    public WetRag(String name) {
        super(name, "WetRag");
        Random random = new Random();
        moistureLevel = random.nextInt(3) + 1;
    }
    public WetRag(String name, Student owner) {
        super(name, "WetRag", owner);
        Random random = new Random();
        moistureLevel = random.nextInt(3) + 1;
    }

    /**
     * Applies the effect of the wet rag item.
     */
    public void ApplyEffect(){
        System.out.println("WetRag: ApplyEffect()");
        for(Professor prof : owner.GetLocation().GetProfessors()){
            prof.Stun(moistureLevel);
        }
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
        resultLogger.log(Level.INFO, name + ".moistureLevel : " + moistureLevel);
    }
    //new
    public int GetMoistureLevel(){
        return moistureLevel;
    }
}
