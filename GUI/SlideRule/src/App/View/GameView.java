package App.View;

import App.*;

import java.util.ArrayList;
import javax.swing.JFrame;

/**
 * The GameView class represents the main window of the game.
 * It extends JFrame and provides methods to manage room views, character views, and item views.
 */
public class GameView extends JFrame{
    
    protected ArrayList<RoomView> roomViews = new ArrayList<>();
    protected GameController controller;

    private ArrayList<CharacterView> studentViews = new ArrayList<>();
    private ArrayList<CharacterView> professorViews = new ArrayList<>();
    private ArrayList<CharacterView> cleanerViews = new ArrayList<>();
    private ItemListView roomItemsView;
    private ItemListView backpackView;

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
        return studentViews;
    }

    /**
     * Get the list of cleaner views.
     *
     * @return The list of cleaner views
     */
    public ArrayList<CharacterView> GetCleanerViews(){
        return studentViews;
    }
}
