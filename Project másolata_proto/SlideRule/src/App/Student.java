package App;
import java.util.List;

import App.Items.Item;

/**
 * Represents a student character in the game.
 */
public class Student extends Character{

    List<Item> items;
    boolean inCombat;
    int isProtected;
    int isStunned;

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
    //@Override
    public boolean Stun() {
        System.out.println("Student: Stun()");
        return false;
    }

    /** Overrides the Combat method of the Character class. */
    //@Override
    public void Combat() {
        System.out.println("Student: Combat()");
    }

    /** Custom method to handle student's death. */
    public void Death(){
        System.out.println("Student: Death()");
    }

    //returns the position of the student
    public void GetLocation()//Room lesz a tipus
    {
        return;
    }
    public void Drunk()
    {
        return;
    }
    public int isStunned()
    {
        return isStunned;
    }
}
