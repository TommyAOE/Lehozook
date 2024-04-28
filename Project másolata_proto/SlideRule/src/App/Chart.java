package App;
import java.util.List;

/**
 * Represents a chart in the game.
 */
public class Chart {
    //attributomok
    List<Room> rooms;
    //
    /** 
     * Iterates for room changes in the chart.
     */
    public void IterateForRoomChanges(){
        for (Room room : rooms) {
            room.Change();
        }
        System.out.println("Chart: IterateForRoomChanges()");
    }
    public void init()
    {
        System.out.println("Chart loaded");
    }
    public List<Room> GetAllRooms()
    {
        return rooms;
    }
}
