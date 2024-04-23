package Items;

/**
 * Represents a wet rag item in the game.
 * Extends the Item class.
 */
public class WetRag extends Item{
    
    /** 
     * Applies the effect of the wet rag item.
     */
    public void ApplyEffect(){
        System.out.println("WetRag: ApplyEffect()");
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
