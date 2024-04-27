package App;
/**
 * Represents a professor character in the game.
 */
public class Professor extends Character{

    int isStunned;
    /** The ID of the professor. */
    public int id;

    /** Overrides the Turn method of the Character class. */
    //@Override
    public void Turn() {
        System.out.println("Professor: Turn()");
    }

    /** Overrides the EnterRoom method of the Character class. */
    //@Override
    public void EnterRoom() {
        System.out.println("Professor: EnterRoom()");
    }

    /** Overrides the Stun method of the Character class. */
    //@Override
    public boolean Stun() //boolean lesz majd
    {
        System.out.println("Professor: Stun()");
        return false;
    }

    /** Overrides the Combat method of the Character class. */
    //@Override
    public void Combat() {
        System.out.println("Professor: Combat()");
    }

    public int isStunned()//studentben int professorban voidnak van irva ugyanez a fv forras:reszletes tervek
    {
        return isStunned;
    }
}
