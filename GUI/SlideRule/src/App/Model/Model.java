package App.Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a scheduler in the game.
 */
public class Model {
    Chart chart;
    ArrayList<Character> NPCs = new ArrayList<>();
    ArrayList<Student> players = new ArrayList<>();
    int playerCount;
    int professorCount;
    int maxRounds;

    Random random = new Random();

    public Model(int playerCount, int professorCount, int maxRounds){
        if(playerCount > 5) playerCount = 5;
        this.playerCount = playerCount;
        this.professorCount = professorCount;
        this.maxRounds = maxRounds;

        Init();
    }
    public void Init(){

        chart = new Chart();

        chart.FindRoomByName("r11").SetCapacity(5);
        for(int i = 0; i < playerCount; i++){
            players.add(new Student("s" + i, chart.FindRoomByName("r11")));
        }
        
        for(int i = 0; i < professorCount; i++){
            Room r = chart.GetAllRooms().get(random.nextInt(chart.GetAllRooms().size()));
            NPCs.add(new Professor("p" + i, r));
        }
        NPCs.add(new Cleaner("c1", chart.FindRoomByName("r31")));
        NPCs.add(new Cleaner("c2", chart.FindRoomByName("r35")));

        chart.IterateForItemSpawn(true, playerCount);

        chart.InfoAll_Test();
    }
}
