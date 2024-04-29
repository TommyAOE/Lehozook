package App.Items;

import App.*;

/**
 * Represents a StBeerCups item in the game.
 * Extends the Item class.
 */
public class StBeerCups extends Item {

    /**
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     *
     * @param name
     */
    public StBeerCups(String name) {
        super(name, "StBeerCups");
    }
    public StBeerCups(String name, Student owner) {
        super(name, "StBeerCups", owner);
    }

    /**
     * Applies the effect of the StBeerCups item.
     */
    public void ApplyEffect(){
        owner.SetIsProtected(3);
        owner.Drunk();
        System.out.println("StBeerCups: ApplyEffect()");
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
        System.out.println("StBeerCups: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println(type+": "+name);
        if (owner != null)
        System.out.println("Owner: "+owner.GetName());
    }
}
