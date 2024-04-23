package Items;

/**
 * Represents a SlideRule item in the game.
 * Extends the Item class.
 */
public class SlideRule extends Item {
    
    /** 
     * Applies the effect of the SlideRule item.
     */
    public void ApplyEffect(){
        System.out.println("SlideRule: ApplyEffect()");
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
