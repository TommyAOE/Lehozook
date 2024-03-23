public class Room {
    public Room(int i) {
        System.out.println(i+".Room created");
    }
    public Room() {
    }
    public void GetNeighbours(){
        System.out.println("Room: GetNeighbours()");
    }
    public void SetNeighbours(){
        System.out.println("Room: SetNeighbours()");
    }
    public void Change(){
        System.out.println("Room: Change()");
    }
    public void SearchItem(){
        System.out.println("Room: SearchItem()");
    }
    public void AddItem(){
        System.out.println("Room: AddItem()");
    }
    public void PopItem(){
        System.out.println("Room: PopItem()");
    }
    public void GetProfessors(){
        System.out.println("Room: GetProfessors()");
    }
    public void GetStudents(){
        System.out.println("Room: GetStudents()");
    }
    public void CharacterEntered(){
        System.out.println("Room: CharacterEntered()");
    }
    public void StudentDied(){
        System.out.println("Room: StudentDied()");
    }
    public void AddGas(){
        System.out.println("Room: AddGas()");
    }
}
