public class Professor extends Character{

    Professor(int i){
        System.out.println(i+".Professor created");
        System.out.print(i+".");
        EnterRoom();
        System.out.print(1+".");
        new Room().CharacterEntered();
    }
    Professor(){
    }
    @Override
    public void Turn() {
        System.out.println("Professor: Turn()");
    }

    @Override
    public void EnterRoom() {
        System.out.println("Professor: EnterRoom()");
    }

    @Override
    public void Stun() {
        System.out.println("Professor: Stun()");
    }

    @Override
    public void Combat() {
        System.out.println("Professor: Combat()");
    }
}
