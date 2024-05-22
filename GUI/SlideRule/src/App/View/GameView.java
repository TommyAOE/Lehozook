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
    public static JTextArea info = new JTextArea();
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

        button = new JButton("END TURN");
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBounds(825, 600, 300, 50);
        button.setBackground(Color.WHITE);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.NextTurn();
                UpdateRoomButtonsEnabled(true);
            }
        });
        JLabel roomItemsText = new JLabel("Items in room");
        roomItemsText.setFont(new Font("Arial", Font.BOLD, 25));
        roomItemsText.setForeground(Color.WHITE);
        roomItemsText.setBounds(775, 300, 300, 50);
        roomItemsView = new ItemListView(new Point(775, 350), controller.GetModel().currentPlayer, "Room", controller.GetModel());
        JLabel inventoryText = new JLabel("Backpack");
        inventoryText.setFont(new Font("Arial", Font.BOLD, 25));
        inventoryText.setForeground(Color.WHITE);
        inventoryText.setBounds(775, 150, 300, 50);
        backpackView = new ItemListView(new Point(775, 200), controller.GetModel().currentPlayer, "Player", controller.GetModel());
        info = new JTextArea();
        info.setText("Welcome to the game!" + "\n");
        info.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        info.setEditable(false);
        scroll = new JScrollPane(info);
        scroll.setBounds(10, 750, 700, 200);
        currentPlayer = new JLabel("Normal turn: Alice");
        currentPlayer.setFont(new Font("Arial", Font.BOLD, 25));
        currentPlayer.setForeground(Color.WHITE);
        currentPlayer.setBounds(775, 10, 300, 50);
        counter = new JLabel("Turns letf: "+controller.GetModel().maxRounds);
        counter.setFont(new Font("Arial", Font.BOLD, 15));
        counter.setForeground(Color.WHITE);
        counter.setBounds(775, 50, 150, 50);
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,750,750);
        add(roomItemsText);
        add(inventoryText);
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
        this.setSize(1200, 1000);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
        Subscribe();

    }

    private void Subscribe() {
        for (Student s : controller.GetModel().GetPlayers()) {
            s.AddPropertyChangeListener(this);
        }
        for (Room r : controller.GetModel().GetChart().GetAllRooms()) {
            r.AddPropertyChangeListener(this);
        }
    }

    private void SetUpRooms(){
        ArrayList<Room> rooms = (ArrayList<Room>) controller.GetModel().GetChart().GetAllRooms();
        for (Room r : rooms){
            RoomView rv = new RoomView(r, this);
            roomViews.add(rv);
            layeredPane.add(rv,1);
            r.AddPropertyChangeListener(this);
        }
        int i = 0;
        for(int y = 0; y < 5 * 150; y += 150) {
            for(int x = 0; x < 5 * 150; x += 150) {
                roomViews.get(i).setBounds(x, y, 150, 150);
                roomViews.get(i).point = new Point(x, y);
                i++;
                
            }
        }
        for (RoomView roomView : roomViews) {
            roomView.CreateDoors();
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
            roomItemsView.setVisible(true);
            backpackView.setVisible(true);

            switch (controller.GetModel().currentPlayer.GetName()) {

                    case "s0" -> {
                        if (controller.GetModel().currentPlayer.inCombat) {
                            currentPlayer.setText("Combat turn: Alice");
                        }else {
                            currentPlayer.setText("Normal turn: Alice");
                        }
                    }
                    case "s1" -> {
                        if (controller.GetModel().currentPlayer.inCombat) {
                            currentPlayer.setText("Combat turn: Claus");
                        }else {
                            currentPlayer.setText("Normal turn: Claus");
                        }
                    }
                    case "s2" -> {
                        if (controller.GetModel().currentPlayer.inCombat) {
                            currentPlayer.setText("Combat turn: Felicity");
                        }else {
                            currentPlayer.setText("Normal turn: Felicity");
                        }
                    }
                    case "s3" -> {
                        if (controller.GetModel().currentPlayer.inCombat) {
                            currentPlayer.setText("Combat turn: Jeremy");
                        }else {
                            currentPlayer.setText("Normal turn: Jeremy");
                        }
                    }
                    case "s4" -> {
                        if (controller.GetModel().currentPlayer.inCombat) {
                            currentPlayer.setText("Combat turn: Lee");
                        }else {
                            currentPlayer.setText("Normal turn: Lee");
                        }
                    }
                    default -> currentPlayer.setText("Combat turn: Error");
                }
            if (((Student) evt.getNewValue()).isStunned()>0){
                info.append("\n You are Stunned! You can't move for "+((Student) evt.getNewValue()).isStunned()+" turns.");
                backpackView.setVisible(false);
                roomItemsView.setVisible(false);
                UpdatePlayers();
            }
            if (((Student) evt.getNewValue()).isStunned() == 0){
                //info.append("\n You are Stunned! You can't move for "+((Student) evt.getNewValue()).isStunned()+" turns.");
                backpackView.setVisible(true);
                roomItemsView.setVisible(true);
                UpdatePlayers();
            }
        }


        if (evt.getPropertyName().equals("NextTurn")){
            counter.setText("Turns: "+evt.getNewValue());
        }
        if (evt.getPropertyName().equals("ListUpdate")){
            UpdatePlayers();
        }
        if (evt.getPropertyName().equals("CharacterMoved")){
            RoomUpdate((Room) evt.getNewValue());
        }
    }
    private void RoomUpdate(Room r){
        for (RoomView rv:roomViews){
            if (rv.GetRoom().GetName().equals(r.GetName())){
                int i = 0;
                for (CharacterView cv:studentViews){
                    if (rv.GetRoom().GetStudents().contains(cv.character)){
                        if (i<4){
                            cv.setBounds(rv.point.x+i*30,rv.point.y,50,50);
                        }
                        else{
                            cv.setBounds(rv.point.x+(i-4)*30,rv.point.y+30,50,50);
                        }
                        i++;
                    }
                }
                for (CharacterView cv:professorViews){
                    if (rv.GetRoom().GetProfessors().contains(cv.character)){
                        if (i<4){
                            cv.setBounds(rv.point.x+i*30,rv.point.y,50,50);
                        }
                        else{
                            cv.setBounds(rv.point.x+(i-4)*30,rv.point.y+30,50,50);
                        }
                        i++;
                    }
                }
                for (CharacterView cv:cleanerViews){
                    if (rv.GetRoom().GetCleaners().contains(cv.character)){
                        if (i<4){
                            cv.setBounds(rv.point.x+i*30,rv.point.y,50,50);
                        }
                        else{
                            cv.setBounds(rv.point.x+(i-4)*30,rv.point.y+30,50,50);
                        }
                        i++;
                    }
                }
            }
        }
    }
    private void UpdatePlayers() {
        for (CharacterView cv : studentViews) {
            layeredPane.remove(cv);
        }
        studentViews.clear();
        for (RoomView r: roomViews){
            for (Student s:r.GetRoom().GetStudents()){
                    CharacterView cv = new CharacterView(s,controller.GetModel().GetPlayers().indexOf(s),r.point);
                    cv.setBounds(cv.point.x,cv.point.y,50,50);
                    studentViews.add(cv);
                    layeredPane.add(cv,1);

            }
            RoomUpdate(r.GetRoom());
        }
    }

    public void UpdateRoomButtonsEnabled(boolean enabled){
        for(RoomView current : roomViews){
            current.GetRoomButton().setEnabled(enabled);
        }
    }
}
