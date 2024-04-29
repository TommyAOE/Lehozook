package App.Items;

import App.*;
import static App.Proto.*;

import java.util.Random;
import java.util.logging.Level;

public class FakeItem extends Item{

    public FakeItem(String name, String type) {
        super(name, type);
    }
    public FakeItem(String name, String type, Student owner) {
        super(name, type, owner);
    }
    @Override
    public void ApplyEffect() {
        int safe=100;
        Room newRoom = owner.GetLocation().neighbours.get(new Random().nextInt(owner.GetLocation().neighbours.size()+1));
        while (newRoom.IsFull()&&--safe>0)
            newRoom = owner.GetLocation().neighbours.get(new Random().nextInt(owner.GetLocation().neighbours.size()+1));
        owner.EnterRoom(newRoom);
    }

    @Override
    public String GetName() {
        return name;
    }

    @Override
    public String GetType() {
        return null;
    }

    @Override
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "FakeItem: "+name);
    }

    @Override
    public void Info_Test() {
        if (owner != null)
            resultLogger.log(Level.INFO, name + ".owner : " + owner.GetName());
        else
            resultLogger.log(Level.INFO, name + ".owner : NULL");
    }

}
