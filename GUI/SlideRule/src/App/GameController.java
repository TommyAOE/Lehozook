package App;

import App.Model.*;
import App.Model.Items.*;
import App.View.GameView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameController implements PropertyChangeListener{

    private Scheduler model;
    private GameView view;
    private Student currentPlayer;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
    
    public void EnterRoom(Room r){}

    public void PickUpItem(Room r, Item i){}

    public void DropItem(Room r, Item i){}

    public void UseItem(Item i){}

    public void ActivateTransistor(Transistor t){}
}
