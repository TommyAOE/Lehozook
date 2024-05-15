package App;

import App.Model.*;
import App.Model.Items.*;
import App.View.GameView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
/**
 * The controller class for the game.
 */
public class GameController implements PropertyChangeListener{

    private Scheduler model;
    private GameView view;
    private Student currentPlayer;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
    /**
     * Get the model of the game.
     * @return The model of the game.
     */
    public Scheduler GetModel(){
        return model;
    }

    /**
     * Get the current player.
     * @return The current player.
     */
    public Student GetCurrentPlayer(){
        return currentPlayer;
    }
    /**
     * Enter a room in the game.
     * @param r The room to enter.
     */
    public void EnterRoom(Room r){}
    /**
     * Pick up an item in the room.
     * @param r The room where the item is located.
     * @param i The item to pick up.
     */
    public void PickUpItem(Room r, Item i){}
    /**
     * Drop an item in the room.
     * @param r The room where the item will be dropped.
     * @param i The item to drop.
     */
    public void DropItem(Room r, Item i){}
    /**
     * Use an item.
     * @param i The item to use.
     */
    public void UseItem(Item i){}
    /**
     * Activate a transistor.
     * @param t The transistor to activate.
     */
    public void ActivateTransistor(Transistor t){}
}
