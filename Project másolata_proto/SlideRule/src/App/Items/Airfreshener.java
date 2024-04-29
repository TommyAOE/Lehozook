package App.Items;

import App.Student;

public class Airfreshener extends Item{

    public Airfreshener(String name){
        super(name, "Airfreshener");
    }
    public Airfreshener(String name, Student owner){
        super(name, "Airfreshener", owner);
    }

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
    public String GetType() {
        return type;
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
