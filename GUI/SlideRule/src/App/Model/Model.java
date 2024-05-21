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
    private Chart chart;
    private ArrayList<Character> NPCs = new ArrayList<>();
    private ArrayList<Student> players = new ArrayList<>();
    private ArrayList<Student> inCombat = new ArrayList<>();
    private int playerCount;
    private int professorCount;
    public int maxRounds;
    public Student currentPlayer;

    Random random = new Random();

    public Model(int playerCount, int professorCount, int maxRounds){
        if(playerCount > 5) playerCount = 5;
        this.playerCount = playerCount;
        this.professorCount = professorCount;
        this.maxRounds = maxRounds;
        Init();
        currentPlayer = players.get(0);
    }
    public Chart GetChart(){
        return chart;
    }
    public void NextPlayer(){
        int index;
        if (inCombat.isEmpty()) {
            index = players.indexOf(currentPlayer);
            if(index == players.size() - 1){
                chart.IterateForRoomChanges();
                chart.IterateForItemSpawn(false, playerCount);
                NpcTurn();
                CombatTurn();
                if (!inCombat.isEmpty()) {
                    currentPlayer=inCombat.get(0);
                    pcs.firePropertyChange("StudentChanged", null, currentPlayer);
                    return;
                }
                currentPlayer = players.get(0);
                currentPlayer.NextTurn();
                if (maxRounds > 1){
                    pcs.firePropertyChange("NextTurn", null, --maxRounds);
                }
                else{
                    pcs.firePropertyChange("GameOver", null, null);
                    System.exit(0);
                }
            }else{
                currentPlayer = players.get(index + 1);
                currentPlayer.NextTurn();
            }
        }else{
            index = inCombat.indexOf(currentPlayer);
            if(index == inCombat.size() - 1){
                for (Character c: NPCs) {
                    if (c.GetName().startsWith("p")) {
                        Professor p = (Professor) c;
                        if (p.inCombat) p.Combat();
                    }
                }
                UpdatePlayerList();
                pcs.firePropertyChange("ListUpdate", null, null);
                currentPlayer = players.get(0);
                currentPlayer.NextTurn();
                inCombat.clear();
            }
            else{
                currentPlayer = inCombat.get(index + 1);
            }
        }
        pcs.firePropertyChange("StudentChanged", null, currentPlayer);
    }

    private void CombatTurn() {
        for (Student s : players) {
            if (s.inCombat) {
                inCombat.add(s);
            }
        }
    }

    private void NpcTurn() {
        for (Character c : NPCs) {
            c.Turn();
        }
    }


    public void Init(){

        chart = new Chart();

        chart.FindRoomByName("r11").SetCapacity(5);
        for(int i = 0; i < playerCount; i++){
            players.add(new Student("s" + i, chart.FindRoomByName("r11")));
        }
        
        /*for(int i = 0; i < professorCount; i++){
            Room r = chart.GetAllRooms().get(random.nextInt(chart.GetAllRooms().size()));
            NPCs.add(new Professor("p" + i, r));
        }
        NPCs.add(new Cleaner("c1", chart.FindRoomByName("r31")));
        NPCs.add(new Cleaner("c2", chart.FindRoomByName("r35")));*/

        chart.IterateForItemSpawn(true, playerCount);

        chart.InfoAll_Test();
    }

    public ArrayList<Character> GetNPCs() {
        return NPCs;
    }

    public ArrayList<Student> GetPlayers() {
        return players;
    }
    public void UpdatePlayerList(){
        ArrayList<Student> students = new ArrayList<>();
        for (Room room : chart.GetAllRooms()) {
            students.addAll(room.GetStudents());
        }
        if (students.isEmpty()) {
            pcs.firePropertyChange("GameOver", null, null);
        }
        players.clear();
        players.addAll(students);
        pcs.firePropertyChange("ListUpdate", null, null);
    }
}
