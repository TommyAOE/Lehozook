package App.Model.Items;
import App.Model.Student;

/**
 * Represents an item in the game.
 * This class is abstract and serves as a base for specific item types.
 */
public abstract class Item {
    
    protected String name;
    protected Student owner;
    protected String type;
    protected boolean glued = false;

    /** 
     * Constructs an item with the specified type.
     * @param type The type of the item.
     */
    public Item(String type){
        this.type = type;
        name = "empty";
    }
    /** 
     * Constructs an item with the specified name and type.
     * @param name The name of the item.
     * @param type The type of the item.
     */
    public Item(String name, String type) {
        this.name = name;
        this.type = type;
    }
    /** 
     * Constructs an item with the specified name, type, and owner.
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
     * Gets the name of the item.
     * @return The name of the item.
     */
    public abstract String GetName();

    /**
     * Gets the type of the item.
     * @return The type of the item.
     */
    public abstract String GetType();
    
    /**
     * Sets the owner of the room to the specified student.
     * @param s The student to set as the owner of the room.
     */
    public void SetOwner(Student s){
        owner = s;
    }
    /**
     * Checks whether the item is glued.
     * @return true if the item is glued, false otherwise.
     */
    public boolean IsGlued() {
        return glued;
    }
    /**
     * Glues or unglues the item.
     * If the item is glued, it becomes unglued; if it's unglued, it becomes glued.
     */
    public void Glue() {
        glued = !glued;
    }

    /** 
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     */
    public abstract void ApplyEffect();

    //------------------Functions for testing------------------
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
