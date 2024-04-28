
package App.Items;

import App.Room;

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
    //new
    private boolean broken;

    /**
     * Constructs a new FFP2 mask item with the specified name.
     *
     * @param name the name of the FFP2 mask item
     */
    public FFP2Mask(String name, Room location) {
        super(name, location, "FFP2Mask");
        counter = 3;
        durability = 3;
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
    //new
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
    public int GetCounter(){
        return counter;
    }

    @Override
    public void InfoAll_Test() {
        System.out.println("FFP2Mask: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println(type+": "+name);
        if (owner != null)
        System.out.println("Owner: "+owner.GetName());
        System.out.println("Counter: "+counter);
        System.out.println("Durability: "+durability);
        System.out.println("Broken: "+broken);
    }
}
