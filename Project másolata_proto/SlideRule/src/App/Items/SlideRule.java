package App.Items;

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
        glued= !glued;
    }

    @Override
    public boolean IsGlued() {
        return glued;
    }
}
package App.Items;

/**
 * Represents a SlideRule item in the game.
 * Extends the Item class.
 */
public class SlideRule extends Item {

    /**
     * Applies the effect of the item.
     * Each subclass must implement this method to define its specific effect.
     *
     * @param name
     */
    public SlideRule(String name) {
        super(name, "SlideRule");
    }

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
        System.out.println("SlideRule: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println(type+": "+name);
        if (owner != null)
        System.out.println("Owner: "+owner.GetName());
    }
}
