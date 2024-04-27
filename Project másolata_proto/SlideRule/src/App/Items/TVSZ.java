package App.Items;

/**
 * Represents a TVSZ item in the game.
 * Extends the Item class.
 */
public class TVSZ extends Item{
    int uses ;
    /**
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     *
     * @param name
     */
    public TVSZ(String name) {
        super(name, "TVSZ");
        uses = 3;
    }

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
    public String GetType() {
        return type;
    }

    @Override
    public void Glue() {
       return;
    }

    @Override
    public boolean IsGlued() {
       return false;
    }

    @Override
    public void InfoAll_Test() {
        System.out.println("TVSZ: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println(type+": "+name);
        if (owner != null)
        System.out.println("Owner: "+owner.GetName());
        System.out.println("Uses: "+uses);
    }
}
