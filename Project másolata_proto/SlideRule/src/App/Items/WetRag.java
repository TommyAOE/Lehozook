package App.Items;

import App.Room;

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
    public WetRag(String name, Room location) {
        super(name, location, "WetRag");
        moistureLevel = 3;
    }

    /**
     * Applies the effect of the wet rag item.
     */
    public void ApplyEffect(){
        System.out.println("WetRag: ApplyEffect()");
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
        System.out.println("WetRag: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println(type+": "+name);
        if (owner != null)
        System.out.println("Owner: "+owner.GetName());
        System.out.println("Moisture Level: "+moistureLevel);
    }
    //new
    public int GetMoistureLevel(){
        return moistureLevel;
    }
}
