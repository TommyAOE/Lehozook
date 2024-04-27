package App.Items;

import App.Room;

/**
 * Represents a transistor item in the game.
 * Extends the Item class.
 */
public class Transistor extends Item{

    
    Transistor pair;
    public Room location;

    /**
     * Constructs a transistor with the given ID.
     * @param name The ID of the transistor.
     */
    public Transistor(String name){
        super(name,"Transistor");
    }

    /** 
     * Applies the effect of the transistor item.
     */
    public void ApplyEffect(){
        System.out.println("Transistor: ApplyEffect()");
    }

    /** 
     * Sets a pair for the transistor.
     */
    public void SetPair(){
        //System.out.println("id = " + id + ", Transistor: SetPair()");
    }

    /** 
     * Activates the transistor.
     */
    public void Activate(){
        System.out.println("Transistor: Activate()");
    }

    /** 
     * Checks if the transistor has a pair.
     */
    public void HasPair(){
        System.out.println("Transistor: HasPair()");
    }

    @Override
    public String GetName() {
        return name;
    }

    @Override
    public String GetType() {
        return null;
    }

    @Override
    public void Glue() {
        glued= !glued;
    }

    @Override
    public boolean IsGlued() {
        return false;
    }

    @Override
    public void InfoAll_Test() {
        System.out.println("Transistor: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println(type+": "+name);
        if (owner != null)
        System.out.println("Owner: "+owner);
        System.out.println("Pair: "+pair);
        if (location != null)
        System.out.println("Location: "+location);

    }
}
