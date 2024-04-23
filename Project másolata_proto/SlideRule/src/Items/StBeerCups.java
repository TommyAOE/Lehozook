package Items;

/**
 * Represents a StBeerCups item in the game.
 * Extends the Item class.
 */
public class StBeerCups extends Item {
    
    /** 
     * Applies the effect of the StBeerCups item.
     */
    public void ApplyEffect(){
        System.out.println("StBeerCups: ApplyEffect()");
    }

    @Override
    public String GetName() {
        return name;
    }

    @Override
    public void Glue() {
        System.out.println("StBeer:glue");
    }

    @Override
    public boolean IsGlued() {
        return false;
    }
}
