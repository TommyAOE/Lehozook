public class Student extends Character{
    Student(int i){
        System.out.println(i+".Student created");
        System.out.print(i+".");
        EnterRoom();
        System.out.print(1+".");
        new Room().CharacterEntered();
    }
    Student(){
    }
    public void Construct(){

    }
    @Override
    public void Turn() {
        System.out.println("Student: Turn()");
    }
    @Override
    public void EnterRoom() {
        System.out.println("Student: EnterRoom()");
    }
    @Override
    public void Stun() {
        System.out.println("Student: Stun()");
    }
    @Override
    public void Combat() {
        System.out.println("Student: Combat()");
    }
    public void Death(){
        System.out.println("Student: Death()");
    }
}
