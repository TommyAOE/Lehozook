package App.Items;

import App.*;
import static App.Proto.*;

import java.util.logging.Level;

public class Airfreshener extends Item{

    public Airfreshener(String name){
        super(name, "Airfreshener");
    }
    public Airfreshener(String name, Student owner){
        super(name, "Airfreshener", owner);
    }

    @Override
    public void ApplyEffect() {
        owner.GetLocation().GasExpired();
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
        resultLogger.log(Level.INFO, "Airfreshener: "+name);
    }

    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
    }
}
