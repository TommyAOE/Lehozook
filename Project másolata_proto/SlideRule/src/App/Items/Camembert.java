package App.Items;

/**
 * Represents a Camembert item in the game.
 * Extends the Item class.
 */
public class Camembert extends Item {
    
    /** 
     * Applies the effect of the Camembert item.
     */
    public void ApplyEffect(){
        System.out.println("Camembert: ApplyEffect()");
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
