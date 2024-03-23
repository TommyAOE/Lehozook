public class Professor extends Character{

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
