package App.Items;

import App.Room;
public class FakeItem extends Item{

    public FakeItem(String name, String type) {
        super(name, type);
    }
    @Override
    public void ApplyEffect() {
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
