package App.Model.Items;

import App.Model.*;
import static App.Program.*;

import java.util.logging.Level;
/**
 * Represents an air freshener item.
 */
public class Airfreshener extends Item{
    /**
     * Constructs an air freshener item
     */
    public Airfreshener(){
        super("Airfreshener");
    }
    /**
     * Constructs an air freshener item with the given name.
     * @param name The name of the air freshener.
     */
    public Airfreshener(String name){
        super(name, "Airfreshener");
    }
    /**
     * Constructs an air freshener item with the given name and owner.
     * @param name  The name of the air freshener.
     * @param owner The owner of the air freshener.
     */
    public Airfreshener(String name, Student owner){
        super(name, "Airfreshener", owner);
    }
    /**
     * Gets the name of the air freshener.
     * @return The name of the air freshener.
     */
    @Override
    public String GetName() {
        return name;
    }
    /**
     * Gets the type of the air freshener.
     * @return The type of the air freshener.
     */
    @Override
    public String GetType() {
        return type;
    }
    /**
     * Applies the effect of the air freshener by indicating that gas has expired at the owner's location.
     */
    @Override
    public void ApplyEffect() {
        owner.GetLocation().GasExpired();
    }
    //------------------Functions for testing------------------
    /**
     * Logs information about the air freshener.
     */
    @Override
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "Airfreshener: "+name);
    }
    /**
     * Logs information about the owner of the air freshener.
     */
    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
    }
}
