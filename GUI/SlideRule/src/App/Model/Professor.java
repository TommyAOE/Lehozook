package App.Model;
import App.Model.Items.Item;

import java.util.Random;
import java.util.logging.Level;
import static App.Program.resultLogger;

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
    public Professor(String name, Room location) {
        super(name);
        EnterRoom(location);
        isStunned = 0;
        inCombat = false;
    }

    /**
     * Overrides the Turn method of the Character class.
     * If the professor is stunned, the stun duration is decremented and the method returns.
     * Otherwise, the professor randomly moves to an adjacent room if possible.
     * If not in combat, the professor removes all items from the current room.
     */
    @Override
    public void Turn() {
        if (isStunned > 0) {
            isStunned--;
            return;
        }
        int safe = 100;
        Room newRoom = location.GetNeighbours().get(new Random().nextInt(location.GetNeighbours().size()));
        while (newRoom.IsFull() && --safe > 0)
            newRoom = location.GetNeighbours().get(new Random().nextInt(location.GetNeighbours().size()));
        EnterRoom(newRoom);
        if (!inCombat) {
            for (Item i : location.SearchItem()) {
                location.PopItem(i);
            }
        }
        System.out.println("App.Professor: Turn()");
    }

    /**
     * Overrides the EnterRoom method of the Character class.
     * Moves the professor to the specified room.
     *
     * @param r the room to enter
     * @return true if entering the room is successful, false otherwise
     */
    @Override
    public boolean EnterRoom(Room r) {
        if (r.IsFull()) {
            String msg = "Room " + r.GetName() + " is full";
            resultLogger.log(Level.INFO, msg);
            return false;
        }
        if(location != null && !this.location.GetNeighbours().contains(r)){
            resultLogger.log(Level.INFO, "Room "+r.GetName()+" is not neighbour of the character's current room");
            return false;
        }
        if(location != null){
            location.CharacterLeft(this);
        }
        r.CharacterEntered(this);
        location = r;
        while(location.SearchItem().size() != 0){
            if(!location.SearchItem().get(0).IsGlued())
                location.SearchItem().remove(0);
            else
                break;
        }
        String msg = "Character " + name + " has entered Room " + r.GetName();
        resultLogger.log(Level.INFO, msg);
        return true;
    }

    /**
     * Overrides the InfoAll_Test method of the Character class.
     * Logs information about the professor.
     */
    @Override
    public void InfoAll_Test() {
        String msg = "Professor " + name;
        resultLogger.log(Level.INFO, msg);
    }

    /**
     * Overrides the Info_Test method of the Character class.
     * Logs detailed information about the professor, including its location, stun status, and combat status.
     */
    @Override
    public void Info_Test() {
        String room = name + ".location : " + location.GetName();
        resultLogger.log(Level.INFO, room);
        String stunned = name + ".isStunned : " + ((isStunned > 0) ? "positive" : "0");
        resultLogger.log(Level.INFO, stunned);
        String combat = name + ".inCombat : " + inCombat;
        resultLogger.log(Level.INFO, combat);
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
        isStunned += stunDuration;
        return true;
    }

    /**
     * Overrides the Combat method of the Character class.
     * Initiates combat for the professor by causing all students in the same room to die.
     * If the professor is stunned, the method returns without initiating combat.
     */
    @Override
    public void Combat() {
        if (isStunned > 0) {
            String msg = name + ".isStunned : positive";
            resultLogger.log(Level.INFO, msg);
            return;
        }
        for (int i = 0; i < location.GetStudents().size(); i++) {
            String msg = "Attack Student : " + location.GetStudents().get(i).name;
            resultLogger.log(Level.INFO, msg);
            boolean suc=location.GetStudents().get(i).Death();
            if(suc) i--;
        }
    }

    /**
     * Retrieves the stun duration of the professor.
     *
     * @return the stun duration
     */
    @Override
    public int IsStunned() {
        return isStunned;
    }
}
