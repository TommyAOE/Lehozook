package App.Items;
import App.Room;
public class Airfreshener extends Item{

    @Override
    public void ApplyEffect() {
        System.out.println("Airfreshener: ApplyEffect()");
        owner.GetLocation().GasExpired();//a tulajdonosa szobajanak a gasexpired hivja
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
