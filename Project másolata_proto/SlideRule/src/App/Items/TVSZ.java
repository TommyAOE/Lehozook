package App.Items;

import App.Room;
import App.Student;

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
        super(name,"TVSZ");
        uses = 3;
    }
    public TVSZ(String name, Student owner) {
        super(name,"TVSZ", owner);
        uses = 3;
    }

    /**
     * Applies the effect of the TVSZ item.
     */
    public void ApplyEffect(){
        System.out.println("TVSZ: ApplyEffect()");
        Room location = owner.GetLocation();
        for (Student student : location.GetStudents()) {
            student.SetIsProtected(1);
        }
        uses--;
        if(uses <= 0){
            
        }
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
