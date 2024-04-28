package App.Items;
import App.Student;
import App.Room;
/**
 * Represents an item in the game.
 * This class is abstract and serves as a base for specific item types.
 */
public abstract class Item {
    String name;
    protected boolean glued=false;//leragasztott_e 
    Student owner;
    String type;

    /** 
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     */
    public Item(String name, String type){
        this.name = name;
        this.type = type;
    }
    public abstract void ApplyEffect();

    public abstract String GetName();
//new
    public abstract String GetType();

    public void Glue(){
        glued = !glued;
    }

    public boolean IsGlued(){
        return glued;
    }

    public abstract void InfoAll_Test();

    public abstract void Info_Test();
}
