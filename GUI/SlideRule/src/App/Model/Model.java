package App.Model;

import java.util.ArrayList;

/**
 * Represents a scheduler in the game.
 */
public class Model {
    Chart chart;
    ArrayList<Character> NPCs;
    ArrayList<Student> players;
    int playerCount;
    int professorCount;
    int maxRounds;

    public Model(int playerCount, int professorCount, int maxRounds){
        if(playerCount > 5) playerCount = 5;
        this.playerCount = playerCount;
        this.professorCount = professorCount;
        this.maxRounds = maxRounds;

        Init();
    }
    public void Init(){

        chart = new Chart();
    }
}
