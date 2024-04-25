package Items;

/**
 * Represents a TVSZ item in the game.
 * Extends the Item class.
 */
public class TVSZ extends Item{
    
    /** 
     * Applies the effect of the TVSZ item.
     */
    public void ApplyEffect(){
        System.out.println("TVSZ: ApplyEffect()");
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
