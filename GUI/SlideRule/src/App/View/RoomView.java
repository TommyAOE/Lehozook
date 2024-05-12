package App.View;

import App.Model.Room;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoomView extends JPanel implements PropertyChangeListener{

    public Coordinates coordinates;
    private Room room;
    private GasView gas;
    private GooView goo;

    private JButton button;
    private ArrayList<JLabel> doors = new ArrayList<>();
    private JLabel floor;
    private JLabel darkLayer;

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
