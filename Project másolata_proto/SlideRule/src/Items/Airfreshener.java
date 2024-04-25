package Items;
public class Airfreshener extends Item{

    @Override
    public void ApplyEffect() {
        System.out.println("Airfreshener: ApplyEffect()");
    }

    @Override
    public String GetName() {
        return name;
    }

    @Override
    public void Glue() {
        return;
    }

    @Override
    public boolean IsGlued() {
        return false;
    }

}
