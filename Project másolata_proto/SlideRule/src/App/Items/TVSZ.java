package App.Items;

import App.*;
import static App.Proto.*;

import java.util.logging.Level;

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
        Room location = owner.GetLocation();
        for (Student student : location.GetStudents()) {
            student.SetIsProtected(location.GetProfessors().size());
        }
        uses--;
        if(uses <= 0){
            owner.RemoveItem(this);
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
        resultLogger.log(Level.INFO, "StBeerCups: "+name);
    }

    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
        resultLogger.log(Level.INFO, name + ".uses : " + uses);
    }
}
