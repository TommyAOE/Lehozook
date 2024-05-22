package App.View;

import App.Model.Room;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import App.View.*;
import javax.swing.*;
import javax.swing.border.Border;

public class RoomView extends JLayeredPane implements PropertyChangeListener{

    public Point point;
    private GameView gameView;
    private Room room;
    private GasView gas;
    private GooView goo;
    private JButton button;
    private ArrayList<JLabel> doors = new ArrayList<>();
    private JLabel floor;
    private JLabel darkLayer;
    private JPanel glass;

    public RoomView(Room r, GameView gView) {
        room = r;
        gameView = gView;
        room.AddPropertyChangeListener(this);

        floor = new JLabel();
        floor.setIcon(new ImageIcon("SlideRule/resources/items/room.png"));
        floor.setBounds(0, 0, 150, 150);
        
        gas = new GasView();
        goo = new GooView();
        darkLayer = new JLabel(new ImageIcon("SlideRule/resources/room/darkroom.png"));
        darkLayer.setBounds(0, 0, 150, 150);
        button = new JButton();

        

        button = new JButton();
        SetUpButton(); 

        //CreateDoors();
        for (JLabel d : doors) {
            add(d);
        }
        add(button);
        add(floor);
        add(gas);
        add(goo);
        add(darkLayer);
        
        
        UpdateRoomView();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
            UpdateRoomView();
    }

    public JButton GetRoomButton(){
        return button;
    }
    public void UpdateRoomView() {
        //System.out.println(room.GetStudents().size());
        darkLayer.setVisible(room.GetStudents().isEmpty());

        gas.setVisible(room.HasGas());
        goo.setVisible(room.HasGoo());

    }

    private void SetUpButton(){
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                boolean enabled = GameView.controller.EnterRoom(room);
                gameView.UpdateRoomButtonsEnabled(!enabled);
            } 
        });
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        button.setBounds(0, 0, 150, 150);
    }

    private void CreateDoors() {
        ArrayList<Room> neighbours = room.GetNeighbours();
        ArrayList<RoomView> neighboursView = gameView.roomViews;

        for(Room currentNeighbour : neighbours){
            for(RoomView currentView : neighboursView){
                if(currentNeighbour.GetName().equals(currentView.room.GetName())){
                    int coordinateDifference = Integer.parseInt(currentNeighbour.GetName().substring(1)) - Integer.parseInt(room.GetName().substring(1));
                    int halfwidth = 75;
                    int doorSize = 10;
                    Point middle = new Point(halfwidth, halfwidth);
                    Point doorplace = new Point();
                    if(coordinateDifference > 1){
                        coordinateDifference /= 10;
                        doorplace.setLocation(middle.x, middle.y + halfwidth);
                        JLabel door = new JLabel();
                        door.setBackground(Color.BLACK);
                        door.setBounds(10, 10, 50, doorSize);
                        door.setOpaque(true);
                        doors.add(door);
                    }
                    
                }
            }
        }
    }
    
    private void OnChange(){}

    public Room GetRoom() {
        return room;
    }
}
