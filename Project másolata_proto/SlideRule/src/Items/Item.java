package Items;
/**
 * Represents an item in the game.
 * This class is abstract and serves as a base for specific item types.
 */
public abstract class Item {
    String name;
    //Student owner;

    /** 
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     */
    public abstract void ApplyEffect();

    public abstract String GetName();

    public abstract void Glue();

    public abstract boolean IsGlued();
}
