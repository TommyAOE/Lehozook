package App.Items;

import App.Room;
import App.Student;

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
    public Transistor(String name, Student owner){
        super(name, "Transistor", owner);
    }
    public Transistor(String name, Room location){
        super(name, "Transistor");
        this.location = location;
    }

    /** 
     * Applies the effect of the transistor item.
     */
    public void ApplyEffect(){
        System.out.println("Transistor: ApplyEffect()");
        if(this.pair != null){
            owner.DropItem(this);
            owner = null;
        }
    }

    /** 
     * Sets a pair for the transistor.
     */
    public void SetPair(Transistor t){
        this.pair = t;
        if(!t.HasPair())    t.SetPair(this);
    }

    /** 
     * Activates the transistor.
     */
    public void Activate(){
        System.out.println("Transistor: Activate()");
        if(pair != null){
            System.out.println("The transistor has a pair");
            Room newRoom = pair.location;
            owner.DropItem(this);
            if(owner.EnterRoom(newRoom)){
                System.out.println("Succesfully entered the new room");
            }
        }
    }

    /** 
     * Checks if the transistor has a pair.
     */
    public boolean HasPair(){
        System.out.println("Transistor: HasPair()");
        return pair != null;
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
    @Override
    public void Reset_Test(){
        super.Reset_Test();
        pair = null;
        location = null;
    }
}
