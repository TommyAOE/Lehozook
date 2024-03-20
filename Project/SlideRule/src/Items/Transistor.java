package Items;

public class Transistor extends Item{

    public int id;

    public Transistor(int i){
        this.id = i;
    }

    public void ApplyEffect(){
        System.out.println("Transistor: ApplyEffect()");
    }
    public void SetPair(){
        System.out.println("id = " + id + ", Transistor: SetPair()");
    }
    public void Activate(){
        System.out.println("Transistor: Activate()");
    }
    public void HasPair(){
        System.out.println("Transistor: HasPair()");
    }
}
