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
package App.Items;
public class Airfreshener extends Item{

    public Airfreshener(String name){
        super(name,"Airfreshener");
    }

    @Override
    public void ApplyEffect() {
        System.out.println("Airfreshener: ApplyEffect()");
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
    public void Glue() {
        return;
    }

    @Override
    public boolean IsGlued() {
        return false;
    }

    @Override
    public void InfoAll_Test() {
        System.out.println("Airfreshener: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println(type+": "+name);
        if (owner != null)
        System.out.println("Owner: "+owner.GetName());
    }

}
