package Items;

/**
 * Represents a transistor item in the game.
 * Extends the Item class.
 */
public class Transistor extends Item{

    
    Transistor pair;
    //Room location;
    
    /** The ID of the transistor. */
    public int id;

    /**
     * Constructs a transistor with the given ID.
     * @param i The ID of the transistor.
     */
    public Transistor(int i){
        this.id = i;
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
        System.out.println("id = " + id + ", Transistor: SetPair()");
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
    public void Glue() {
       return;
    }

    @Override
    public boolean IsGlued() {
        return false;
    }
}
