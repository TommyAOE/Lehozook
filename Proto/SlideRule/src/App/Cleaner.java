package App;

import java.util.Random;
import java.util.logging.Level;

import static App.Proto.resultLogger;
/**
 * Represents a cleaner character in the game.
 */
public class Cleaner extends Character
{

    /**
     * Constructs a Cleaner object with the given name and assigns it to the specified room.
     *
     * @param name The name of the Cleaner.
     * @param r    The room where the Cleaner will be assigned.
     */
    public Cleaner(String name, Room r) {
        super(name,r);
    }

    /**
     * Overrides the Turn method of the Character class.
     * The cleaner randomly moves to an adjacent room, and then attempts to move each student and professor
     * in the current room to random adjacent rooms if they are not stunned.
     * After the movement, the cleaner cleans the current room.
     */
    @Override
    public void Turn() {
        int safe=100;
        Room newRoom = location.neighbours.get(new Random().nextInt(location.neighbours.size()+1));
        while (newRoom.isFull&&--safe>0)
            newRoom = location.neighbours.get(new Random().nextInt(location.neighbours.size()+1));
        EnterRoom(newRoom);

        for (Student s :location.GetStudents()) {
            if (s.isStunned()==0){
                safe=100;
                while (newRoom.isFull&&--safe>0)
                    newRoom = location.neighbours.get(new Random().nextInt(location.neighbours.size()+1));
                s.EnterRoom(newRoom);
            }

        }
        for (Professor p :location.GetProfessors()) {
            if(p.IsStunned()==0) {
                safe=100;
                while (newRoom.isFull&&--safe>0)
                    newRoom = location.neighbours.get(new Random().nextInt(location.neighbours.size()+1));
                p.EnterRoom(newRoom);
            }
        }
        location.Clean();
    }

    /**
     * Overrides the EnterRoom method of the Character class.
     * Moves the cleaner to the specified room.
     *
     * @param r the room to enter
     */
    @Override
    public boolean EnterRoom(Room r) {
        if (r.isFull){
            String msg = "Room " + r.name + " is full";
            resultLogger.log(Level.INFO, msg);
            return false;
        }
        if(!this.location.GetNeighbours().contains(r)){
            resultLogger.log(Level.INFO, "Room "+r.name+" is not neighbour of the character's current room");
            return false;
        }
        location.CharacterLeft(this);
        r.CharacterEntered(this);
        location=r;
        String msg = "Character "+ name + " has entered Room " + r.name;
        resultLogger.log(Level.INFO, msg);
        return true;
    }
    /**
     * Logs information about the Cleaner, including their name.
     * This method is used for testing purposes to log information about all Cleaners.
     */
    @Override
    public void InfoAll_Test() {
        String msg = "Cleaner "+ name;
        resultLogger.log(Level.INFO, msg);
        
    }
    
    /**
     * Logs information about the Cleaner's location, including the room's name.
     * This method is used for testing purposes to log specific information about a Cleaner.
     */
    @Override
    public void Info_Test() {
        String msg = name + ".location " + location.name;
        resultLogger.log(Level.INFO, msg);        
    }
}
