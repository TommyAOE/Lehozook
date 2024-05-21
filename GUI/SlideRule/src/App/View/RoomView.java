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

    public RoomView(Room r, GameView gView) {
        room = r;
        gameView = gView;
        room.AddPropertyChangeListener(this);

        floor = new JLabel();
        floor.setIcon(new ImageIcon("resources/room/room.png"));
        floor.setBounds(0, 0, 150, 150);
        
        gas = new GasView();
        goo = new GooView();
        darkLayer = new JLabel(new ImageIcon("resources/room/darkroom.png"));
        darkLayer.setBounds(0, 0, 150, 150);
        button = new JButton();

        button = new JButton();
        SetUpButton();
        
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

    public void CreateDoors() {
        for(Room currentNeighbour : room.GetNeighbours()){
            for(RoomView currentView : gameView.roomViews){
                if(currentNeighbour.GetName().equals(currentView.room.GetName())){

                    int coordinateDifference = Integer.parseInt(currentNeighbour.GetName().substring(1)) - Integer.parseInt(room.GetName().substring(1));
                    int doorSize = 20;

                    JLabel door = new JLabel();
                    Point doorPoint = new Point(75 - doorSize / 2, 75 - doorSize / 2);

                    if(Math.abs(coordinateDifference) > 1){
                        coordinateDifference /= 10;
                        doorPoint.translate(0, coordinateDifference * 75);
                    }else{
                        doorPoint.translate(coordinateDifference * 75, 0);
                    }
                    if(room.IsCursed()) door.setBackground(new Color(128,0,128));
                    else                door.setBackground(Color.BLACK);
                    door.setOpaque(true);
                    door.setBounds(doorPoint.x, doorPoint.y, doorSize, doorSize);
                    doors.add(door);
                    
                }
            }
        }
        removeAll();
        add(button);
        add(darkLayer);
        for (JLabel d : doors) {
            add(d);
        }
        add(goo);
        add(gas);
        add(floor);
    }
    
    private void OnChange(){}

    public Room GetRoom() {
        return room;
    }
}
