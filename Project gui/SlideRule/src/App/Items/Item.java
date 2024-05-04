package App.Items;
import App.Student;

/**
 * Represents an item in the game.
 * This class is abstract and serves as a base for specific item types.
 */
public abstract class Item {
    String name;
    protected boolean glued = false; // Indicates whether the item is glued
    Student owner; // The owner of the item
    String type; // The type of the item

    /** 
     * Constructs an item with the specified name and type.
     *
     * @param name The name of the item.
     * @param type The type of the item.
     */
    public Item(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /** 
     * Constructs an item with the specified name, type, and owner.
     *
     * @param name  The name of the item.
     * @param type  The type of the item.
     * @param owner The owner of the item.
     */
    public Item(String name, String type, Student owner) {
        this.name = name;
        this.type = type;
        this.owner = owner;
    }

    /** 
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     */
    public abstract void ApplyEffect();

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public abstract String GetName();

    /**
     * Gets the type of the item.
     *
     * @return The type of the item.
     */
    public abstract String GetType();

    public void SetOwner(Student s){
        owner = s;
    }
    /**
     * Glues or unglues the item.
     * If the item is glued, it becomes unglued; if it's unglued, it becomes glued.
     */
    public void Glue() {
        glued = !glued;
    }

    /**
     * Checks whether the item is glued.
     *
     * @return true if the item is glued, false otherwise.
     */
    public boolean IsGlued() {
        return glued;
    }

    /**
     * Logs general information about the item.
     * Each subclass must implement this method to log specific information.
     */
    public abstract void InfoAll_Test();

    /**
     * Logs detailed information about the item.
     * Each subclass must implement this method to log specific information.
     */
    public abstract void Info_Test();

    /**
     * Resets the owner of the item to null.
     * This is used for testing purposes.
     */
    public void Reset_Test() {
        owner = null;
    }
}
