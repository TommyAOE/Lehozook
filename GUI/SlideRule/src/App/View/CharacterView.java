package App.View;

import App.Model.Room;
import App.Model.Character;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;

public class CharacterView extends JLabel implements PropertyChangeListener{

    private Coordinates coordinates;
    private Character character;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
    private void OnDeath(){

    }

    private void OnEnterRoom(Room r){

    }
}
