package App.Items;

import App.Room;

import java.util.Random;

public class FakeItem extends Item{

    public FakeItem(String name, String type) {
        super(name, type);
    }
    @Override
    public void ApplyEffect() {
        int safe=100;
        Room newRoom = owner.GetLocation().neighbours.get(new Random().nextInt(owner.GetLocation().neighbours.size()+1));
        while (newRoom.IsFull()&&--safe>0)
            newRoom = owner.GetLocation().neighbours.get(new Random().nextInt(owner.GetLocation().neighbours.size()+1));
        owner.EnterRoom(newRoom);
        System.out.println("FakeItem: ApplyEffect()");
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
        System.out.println("FakeItem: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println(type+": "+name);
        if (owner != null)
        System.out.println("Owner: "+owner.GetName());
    }

}
