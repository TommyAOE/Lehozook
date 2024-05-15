package App.View;

import App.Model.Room;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoomView extends JPanel implements PropertyChangeListener{

    public Coordinates coordinates = new Coordinates();
    private int width = 150;
    private int height = 150;
    private Room room;
    private GasView gas;
    private GooView goo;

    private JButton button;
    private ArrayList<JLabel> doors = new ArrayList<>();
    private JLabel floor;
    private JLabel darkLayer;

    public RoomView(Room room){
        this.room = room;
        this.setLayout(new BorderLayout());
        floor = new JLabel(new ImageIcon("./SlideRule/resources/room/floor.png"));
        gas = new GasView();

        this.add(floor);
        if(this.room.HasGas()){
            this.add(gas);
        }

        this.setSize(width, height);
        this.setBackground(Color.BLACK);
    }
    

    public void DrawRoom(){
        this.setLocation(coordinates.GetX(), coordinates.GetY());
        //this.setBounds(coordinates.GetX(), coordinates.GetY(), width, height);
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

}
