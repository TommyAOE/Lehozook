package App.View;

import App.*;
import App.Model.*;
import App.Model.Character;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The GameView class represents the main window of the game.
 * It extends JFrame and provides methods to manage room views, character views, and item views.
 */
public class GameView extends JFrame implements PropertyChangeListener {
    
    protected ArrayList<RoomView> roomViews = new ArrayList<>();
    protected GameController controller;
    JButton button;
    JTextArea info;
    JScrollPane scroll;
    JLabel currentPlayer;
    JLabel counter;
    private ArrayList<CharacterView> studentViews = new ArrayList<>();
    private ArrayList<CharacterView> professorViews = new ArrayList<>();
    private ArrayList<CharacterView> cleanerViews = new ArrayList<>();
    private ItemListView roomItemsView;
    private ItemListView backpackView;
    public GameView(Model model){
        model.AddPropertyChangeListener(this);
        controller = new GameController(model);


        button = new JButton("Next Turn");
        button.setBounds(1200, 600, 100, 50);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.NextTurn();
            }
        });
        roomItemsView = new ItemListView(new Point(1200, 400), controller.GetModel().currentPlayer, "Room", controller.GetModel());
        backpackView = new ItemListView(new Point(1200, 100), controller.GetModel().currentPlayer, "Player", controller.GetModel());
        info = new JTextArea();
        info.setText("Welcome to the game!");
        info.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        info.setEditable(false);
        scroll = new JScrollPane(info);
        scroll.setBounds(10, 750, 700, 200);
        currentPlayer = new JLabel("Current Player: "+controller.GetModel().currentPlayer.GetName());
        currentPlayer.setBounds(1300, 10, 200, 50);
        counter = new JLabel("Turns: "+controller.GetModel().maxRounds);
        counter.setBounds(1300, 50, 200, 50);
        add(roomItemsView);
        add(backpackView);
        add(button);
        add(currentPlayer);
        add(scroll);
        add(counter);
        SetUpRooms();
        SetUpPlayers();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600, 1000);
        this.setLayout(null);
        this.setVisible(true);

    }
    private void SetUpRooms(){
        ArrayList<Room> rooms = (ArrayList<Room>) controller.GetModel().GetChart().GetAllRooms();
        for (Room r : rooms){
            RoomView rv = new RoomView(r);
            roomViews.add(rv);
            rv.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(rv);
        }
        int i = 0;
        for(int x=0;x<5*150;x+=150){
            for(int y=0;y<5*150;y+=150){
                for (y = 0; y < 5 * 150; y += 150) {
                    roomViews.get(i).setBounds(x, y, 150, 150);
                    roomViews.get(i).point = new Point(x, y);
                    i++;
                }
            }
        }
    }
    private Point SearchRoom(Character c){
        Point p = new Point();
        for (RoomView rv : roomViews){
            if (rv.GetRoom()==c.GetLocation()){
                p=rv.point;
            }
        }
        return p;
    }
    private void SetUpPlayers(){
        for (Student s : controller.GetModel().GetPlayers()){
            CharacterView cv = new CharacterView(s,controller.GetModel().GetPlayers().indexOf(s),SearchRoom(s));
            studentViews.add(cv);
            cv.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(cv);
        }
        for (Character c : controller.GetModel().GetNPCs()){
                CharacterView cv = new CharacterView(c,0,SearchRoom(c));
                cleanerViews.add(cv);
                cv.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(cv);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("StudentChanged")){
            currentPlayer.setText("Current Player: "+controller.GetModel().currentPlayer.GetName());
        }
        if (evt.getPropertyName().equals("NextTurn")){
            counter.setText("Turns: "+evt.getNewValue());
        }
        if (evt.getPropertyName().equals("ListUpdate")){
            //UpdatePlyers();
        }
    }
}
