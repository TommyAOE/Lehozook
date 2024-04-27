package App;

import App.Items.Item;

import java.util.Random;

/**
 * Represents a professor character in the game.
 */
public class Professor extends Character implements IFighter {

    /** The stun duration of the professor. */
    int isStunned;

    /** Indicates whether the professor is in combat. */
    //new
    boolean inCombat;
    /**
     * Constructs a new professor character with the specified name and initial location.
     *
     * @param name     the name of the professor
     * @param location the initial location of the professor
     */
    public Professor(String name, Room location)
    {
        super(name,location);
        isStunned=0;
        inCombat=false;
    }
    /**
     * Overrides the Turn method of the Character class.
     * If the professor is stunned, the stun duration is decremented and the method returns.
     *
     * Otherwise, the professor randomly moves to an adjacent room if possible.
     * If not in combat, the professor removes all items from the current room.
     */
    @Override
    public void Turn() {
        if (isStunned>0){
            isStunned--;
            return;
        }
            int safe=100;
            Room newRoom = location.neighbours.get(new Random().nextInt(location.neighbours.size()+1));
            while (newRoom.isFull&&--safe>0)
                newRoom = location.neighbours.get(new Random().nextInt(location.neighbours.size()+1));
            EnterRoom(newRoom);
            if (!inCombat)
            {
                for (Item i:location.SearchItem()) {
                      {
                    location.PopItem(i);
                    }
                }
            }
        System.out.println("App.Professor: Turn()");
    }

    /**
     * Overrides the EnterRoom method of the Character class.
     * Moves the professor to the specified room.
     *
     * @param r the room to enter
     * @return
     */
    @Override
    public boolean EnterRoom(Room r) {
        if (r.isFull)
            return false;
        location.CharacterLeft(this);
        r.CharacterEntered(this);
        location=r;
        System.out.println("App.Professor: EnterRoom()");
        return false;
    }

    @Override
    public void InfoAll_Test() {
        System.out.println("Professor: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println("Professor: "+name);
        System.out.println("Location: "+location.name);
        System.out.println("Stunned: "+isStunned);
        System.out.println("In Combat: "+inCombat);

    }


    /**
     * Overrides the Stun method of the Character class.
     * Increases the stun duration of the professor.
     *
     * @param stunDuration the duration of the stun effect
     * @return true indicating successful stun
     */
    @Override
    public boolean Stun(int stunDuration) {
        isStunned+=stunDuration;
        return true;
    }

    /**
     * Overrides the Combat method of the Character class.
     * Initiates combat for the professor by causing all students in the same room to die.
     * If the professor is stunned, the method returns without initiating combat.
     */
    @Override
    public void Combat() {
        if (isStunned>0)
            return;
        for (Student s :location.GetStudents()) {
            s.Death();
        }
        System.out.println("App.Professor: Combat()");
    }

    @Override
    public int IsStunned() {
        return isStunned;
    }
}
