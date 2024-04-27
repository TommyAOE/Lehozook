package App.Items;

/**
 * Represents an FFP2 mask item in the game.
 * Extends the Item class.
 */
public class FFP2Mask extends Item {
    
    /** 
     * Applies the effect of the FFP2 mask item.
     */
    public void ApplyEffect(){
        System.out.println("FFP2Mask: ApplyEffect()");
    }

    @Override
    public String GetName() {
        return name;
    }

    @Override
    public void Glue() {
        glued= !glued;
    }

    @Override
    public boolean IsGlued() {
        return glued;
    }
}
