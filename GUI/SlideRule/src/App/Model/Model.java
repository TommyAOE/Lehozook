package App.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a scheduler in the game.
 */
public class Model {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public void AddPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public void RemovePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    private Chart chart;
    private ArrayList<Character> NPCs = new ArrayList<>();
    private ArrayList<Student> players = new ArrayList<>();
    private int playerCount;
    private int professorCount;
    private int maxRounds;
    public Student currentPlayer;

    Random random = new Random();

    public Model(int playerCount, int professorCount, int maxRounds){
        if(playerCount > 5) playerCount = 5;
        this.playerCount = playerCount;
        this.professorCount = professorCount;
        this.maxRounds = maxRounds;

        Init();
        Play();

    }

    private void Play() {
        for(int i = 0; i < maxRounds; i++){
            for (Character c : NPCs) {
                c.Turn();
            }
            for(Student s : players){
                currentPlayer = s;
                pcs.firePropertyChange("StudentChanged", null, s);
                s.Turn();
            }
        }
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

    public ArrayList<Character> GetNPCs() {
        return NPCs;
    }

    public ArrayList<Student> GetPlayers() {
        return players;
    }
}
