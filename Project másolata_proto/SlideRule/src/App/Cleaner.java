package App;

import java.util.Random;
/**
 * Represents a cleaner character in the game.
 */
public class Cleaner extends Character
{


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
        if (r.isFull)
            return false;
        location.CharacterLeft(this);
        r.CharacterEntered(this);
        location=r;
        return true;
    }
    @Override
    public void InfoAll_Test() {
        System.out.println("Cleaner: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println("Cleaner:\n"+"Name:"+name+"\nLocation:"+location.name+"\n");
    }
}
