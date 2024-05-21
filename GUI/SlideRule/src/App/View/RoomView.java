package App.View;

import App.Model.Room;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.*;

public class RoomView extends JLayeredPane implements PropertyChangeListener{

    public Point point;
    private Room room;
    private GasView gas;
    private GooView goo;
    private JButton button;
    private ArrayList<JLabel> doors = new ArrayList<>();
    private JLabel floor;
    private JLabel gool;
    private JLabel darkLayer;

    public RoomView(Room r) {
        floor = new JLabel();
        floor.setIcon(new ImageIcon("resources/room/room.png"));
        floor.setBounds(0, 0, 150, 150);
        gool = new JLabel();
        gool.setIcon(new ImageIcon("resources/room/goo.png"));
        gool.setBounds(0, 0, 150, 150);
        JLabel gasl = new JLabel();
        gasl.setIcon(new ImageIcon("resources/room/gas.png"));
        gasl.setBounds(0, 0, 150, 150);

        add(floor, 2);
        add(gool, 0);
        add(gasl, 1);
        room = r;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

    private void OnGas(){}

    private void OnGoo(){}

    private void CreateDoors(){}

    private void OnNoStudents(){}
    
    private void OnChange(){}

    public Room GetRoom() {
        return room;
    }
}
