package Items;

/**
 * Represents an item in the game.
 * This class is abstract and serves as a base for specific item types.
 */
public abstract class Item {
    
    /** 
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     */
    public abstract void ApplyEffect();
}
