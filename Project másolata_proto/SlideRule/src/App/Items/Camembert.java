package App.Items;

import App.Room;

/**
 * Represents a Camembert item in the game.
 * Extends the Item class.
 */
public class Camembert extends Item {

    /**
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     *
     * @param name
     */
    public Camembert(String name) {
        super(name, "Camembert");
    }

    /**
     * Applies the effect of the Camembert item.
     */
    public void ApplyEffect(){
        System.out.println("Camembert: ApplyEffect()");
        owner.GetLocation().AddGas();
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
        System.out.println("Camembert: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println(type+": "+name);
        if (owner != null)
        System.out.println("Owner: "+owner.GetName());
    }

}
