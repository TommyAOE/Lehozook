package App.Items;
public class FakeItem extends Item{

    @Override
    public void ApplyEffect() {
        System.out.println("FakeItem: ApplyEffect()");
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
