package App.View;

import App.*;
import App.Model.*;

import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * The GameView class represents the main window of the game.
 * It extends JFrame and provides methods to manage room views, character views, and item views.
 */
public class GameView extends JFrame{
    
    protected ArrayList<RoomView> roomViews = new ArrayList<>();
    protected GameController controller;

    private Model model;
    private ArrayList<CharacterView> studentViews = new ArrayList<>();
    private ArrayList<CharacterView> professorViews = new ArrayList<>();
    private ArrayList<CharacterView> cleanerViews = new ArrayList<>();
    private ItemListView roomItemsView;
    private ItemListView backpackView;

    public GameView(Model model){
        this.model = model;
        this.setTitle("SlideRule");

        this.setSize(1600, 1000);
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);

        Init();
        

        this.setVisible(true);
    }

    private void Init(){
        Chart chart = model.GetChart();
        for (Room r : chart.GetAllRooms()) {
            RoomView rv = new RoomView(r);
            roomViews.add(rv);
        }

        SetUpRooms();
    }

    private void SetUpRooms(){
        int i = 0;
        for(int x = 50; x < 5 * 150; x += 150){
            for(int y = 50; y < 5 * 150; y += 150){
                roomViews.get(i).coordinates.Set(x, y);
                roomViews.get(i).DrawRoom();
                this.add(roomViews.get(i));
                i++;
            }
        }
    }

    /**
     * Get the list of student views.
     *
     * @return The list of student views
     */
    public ArrayList<CharacterView> GetStudentViews(){
        return studentViews;
    }

    /**
     * Get the list of professor views.
     *
     * @return The list of professor views
     */
    public ArrayList<CharacterView> GetProfessorViews(){
        return professorViews;
    }

    /**
     * Get the list of cleaner views.
     *
     * @return The list of cleaner views
     */
    public ArrayList<CharacterView> GetCleanerViews(){
        return cleanerViews;
    }
}
