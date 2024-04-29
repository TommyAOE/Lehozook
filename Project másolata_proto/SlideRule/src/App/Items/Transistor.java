package App.Items;

import java.util.logging.Level;

import App.*;
import static App.Proto.*;

/**
 * Represents a transistor item in the game.
 * Extends the Item class.
 */
public class Transistor extends Item{

    
    Transistor pair;
    public Room location;

    /**
     * Constructs a transistor with the given ID.
     * @param name The ID of the transistor.
     */
    public Transistor(String name, Student owner){
        super(name, "Transistor", owner);
    }
    public Transistor(String name, Room location){
        super(name, "Transistor");
        this.location = location;
    }

    /** 
     * Applies the effect of the transistor item.
     */
    public void ApplyEffect(){
        System.out.println("Transistor: ApplyEffect()");
        if(this.pair != null){
            owner.DropItem(this);
            owner = null;
        }
    }

    /** 
     * Sets a pair for the transistor.
     */
    public void SetPair(Transistor t){
        this.pair = t;
        if(!t.HasPair())    t.SetPair(this);
    }

    /** 
     * Activates the transistor.
     */
    public void Activate(){
        resultLogger.log(Level.INFO, "Transistor activated");
        if(pair != null){
            resultLogger.log(Level.INFO, "The transistor has a pair");
            Room newRoom = pair.location;
            owner.DropItem(this);
            if(owner.EnterRoom(newRoom)){
                resultLogger.log(Level.INFO, "Succesfully entered the new room");
            }
        }
    }

    /** 
     * Checks if the transistor has a pair.
     */
    public boolean HasPair(){
        return pair != null;
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
        resultLogger.log(Level.INFO, "Transistor: "+name);
    }

    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
        if (pair != null)
            resultLogger.log(Level.INFO, name + ".pair: " + pair.GetName());
        else
            resultLogger.log(Level.INFO, name + ".pair : NULL");
        if (location != null)
            resultLogger.log(Level.INFO, name + ".location: " + name);
        else
            resultLogger.log(Level.INFO, name + ".location : NULL");
    }
    @Override
    public void Reset_Test(){
        super.Reset_Test();
        pair = null;
        location = null;
    }
}
