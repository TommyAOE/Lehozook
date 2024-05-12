/**
 * Represents a student character in the game.
 */
public class Student extends Character{

    /** The ID of the student. */
    public int id;

    /** Overrides the Turn method of the Character class. */
    @Override
    public void Turn() {
        System.out.println("Student: Turn()");
    }

    /** Overrides the EnterRoom method of the Character class. */
    @Override
    public void EnterRoom() {
        System.out.println("Student: EnterRoom()");
    }

    /** Overrides the Stun method of the Character class. */
    @Override
    public void Stun() {
        System.out.println("Student: Stun()");
    }

    /** Overrides the Combat method of the Character class. */
    @Override
    public void Combat() {
        System.out.println("Student: Combat()");
    }

    /** Custom method to handle student's death. */
    public void Death(){
        System.out.println("Student: Death()");
    }
}
