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
    protected static GameController controller;
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
    private JLayeredPane layeredPane;

    public GameView(Model model) {
        model.AddPropertyChangeListener(this);
        controller = new GameController(model);

        button = new JButton("Next Turn");
        button.setBounds(1200, 600, 100, 50);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.NextTurn();
                UpdateRoomButtonsEnabled(true);
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
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,750,750);
        add(roomItemsView);
        add(backpackView);
        add(button);
        add(currentPlayer);
        add(scroll);
        add(counter);
        add(layeredPane);
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
            RoomView rv = new RoomView(r, this);
            roomViews.add(rv);
            layeredPane.add(rv,1);
            r.AddPropertyChangeListener(this);
            //add(rv);
        }
        int i = 0;
        for(int y = 0; y < 5 * 150; y += 150) {
            for(int x = 0; x < 5 * 150; x += 150) {
                roomViews.get(i).setBounds(x, y, 150, 150);
                roomViews.get(i).point = new Point(x, y);
                i++;
                
            }
        }
    }
    private void SetUpPlayers(){
        for (RoomView r: roomViews){
            int i = 0;
            int j = 0;
            for (Student s:r.GetRoom().GetStudents()){
                if (i<4){
                CharacterView cv = new CharacterView(s,controller.GetModel().GetPlayers().indexOf(s),r.point);
                cv.setBounds(cv.point.x+i*30,cv.point.y+j*30,50,50);
                studentViews.add(cv);
                layeredPane.add(cv,0);
                i++;}
                else{
                    i = 0;
                    j++;
                    CharacterView cv = new CharacterView(s,controller.GetModel().GetPlayers().indexOf(s),r.point);
                    cv.setBounds(cv.point.x,cv.point.y+j*30,50,50);
                    studentViews.add(cv);
                    layeredPane.add(cv,0);
                    i++;
                }
            }
            for (Professor p:r.GetRoom().GetProfessors()){
                if (i<4){
                    CharacterView cv = new CharacterView(p,0,r.point);
                    cv.setBounds(cv.point.x+i*30,cv.point.y+j*30,50,50);
                    //cv.setVisible(!cv.character.GetLocation().GetStudents().isEmpty());
                    professorViews.add(cv);
                    layeredPane.add(cv,0);
                    i++;}
                else{
                    i = 0;
                    j++;
                    CharacterView cv = new CharacterView(p,0,r.point);
                    cv.setBounds(cv.point.x,cv.point.y+j*30,50,50);
                    //cv.setVisible(!cv.character.GetLocation().GetStudents().isEmpty());
                    professorViews.add(cv);
                    layeredPane.add(cv,0);
                    i++;
                }
            }
            for (Cleaner c:r.GetRoom().GetCleaners()){
                if (i<4){
                    CharacterView cv = new CharacterView(c,0,r.point);
                    cv.setBounds(cv.point.x+i*30,cv.point.y+j*30,50,50);
                    //cv.setVisible(!cv.character.GetLocation().GetStudents().isEmpty());
                    cleanerViews.add(cv);
                    layeredPane.add(cv,0);
                    i++;}
                else{
                    i = 0;
                    j++;
                    CharacterView cv = new CharacterView(c,0,r.point);
                    cv.setBounds(cv.point.x,cv.point.y+j*25,50,50);
                    //cv.setVisible(!cv.character.GetLocation().GetStudents().isEmpty());
                    cleanerViews.add(cv);
                    layeredPane.add(cv,0);
                    i++;
                }
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
            UpdatePlyers();
        }
        if (evt.getPropertyName().equals("CharacterMoved")){
            MoveCharacter((Character) evt.getNewValue());
        }
    }
    private Point SearchLocation(Character c){
        for (RoomView rv:roomViews){
            if(c.GetName().startsWith("s")){
                if (rv.GetRoom().GetStudents().contains(c)){
                    Point p;
                    if (rv.GetRoom().characterCount>4){
                        p= new Point(rv.point.x+(rv.GetRoom().characterCount-5)*30,rv.point.y+30);
                    }else {
                        p = new Point(rv.point.x + (rv.GetRoom().characterCount-1) * 30, rv.point.y);
                    }
                    return p;
                }
            }else if (c.GetName().startsWith("p")){
                if (rv.GetRoom().GetProfessors().contains( c)){
                    Point p;
                    if (rv.GetRoom().characterCount>4){
                        p= new Point(rv.point.x+(rv.GetRoom().characterCount-5)*30,rv.point.y+30);
                    }else {
                        p = new Point(rv.point.x + (rv.GetRoom().characterCount-1) * 30, rv.point.y);
                    }
                    return p;
                }
            }else if (c.GetName().startsWith("c")){
                if (rv.GetRoom().GetCleaners().contains(c)){
                    Point p;
                    if (rv.GetRoom().characterCount>4){
                        p= new Point(rv.point.x+(rv.GetRoom().characterCount-5)*30,rv.point.y+30);
                    }else {
                        p = new Point(rv.point.x + (rv.GetRoom().characterCount-1) * 30, rv.point.y);
                    }
                    return p;
                }
            }
        }
        return null;
    }
    private void MoveCharacter(Character c){
        if (c.GetName().startsWith("s")){
            for (CharacterView cv:studentViews){
                if (cv.character.GetName().equals(c.GetName())){
                    Point p = SearchLocation(c);
                    if (p!=null){
                        cv.setBounds(p.x,p.y,50,50);
                    }
                }
            }
        }else if (c.GetName().startsWith("p")){
            for (CharacterView cv:professorViews){
                if (cv.character.GetName().equals(c.GetName())){
                    Point p = SearchLocation(c);
                    if (p!=null){
                        //cv.setVisible(!cv.character.GetLocation().GetStudents().isEmpty());
                        cv.setBounds(p.x,p.y,50,50);
                    }
                }
            }
        }else if (c.GetName().startsWith("c")){
            for (CharacterView cv:cleanerViews){
                if (cv.character.GetName().equals(c.GetName())){
                    Point p = SearchLocation(c);
                    if (p!=null){
                        //cv.setVisible(!cv.character.GetLocation().GetStudents().isEmpty());
                        cv.setBounds(p.x,p.y,50,50);
                    }
                }
            }
        }
    }
    private void UpdatePlyers() {
        for (CharacterView cv : studentViews) {
            layeredPane.remove(cv);
        }
        for (RoomView r: roomViews){
            int i = 0;
            int j = 0;
            for (Student s:r.GetRoom().GetStudents()){
                if (i<4){
                    CharacterView cv = new CharacterView(s,controller.GetModel().GetPlayers().indexOf(s),r.point);
                    cv.setBounds(cv.point.x+i*30,cv.point.y+j*30,50,50);
                    studentViews.add(cv);
                    layeredPane.add(cv,1);
                    i++;}
                else{
                    i = 0;
                    j++;
                    CharacterView cv = new CharacterView(s,controller.GetModel().GetPlayers().indexOf(s),r.point);
                    cv.setBounds(cv.point.x,cv.point.y+j*30,50,50);
                    studentViews.add(cv);
                    layeredPane.add(cv,1);
                    i++;
                }
            }
        }
    }

    public void UpdateRoomButtonsEnabled(boolean enabled){
        for(RoomView current : roomViews){
            current.GetRoomButton().setEnabled(enabled);
        }
    }
}
