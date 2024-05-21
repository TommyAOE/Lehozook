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

    private Model model;
    private GameView view;

    public GameController(Model m){
        model = m;
        model.AddPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("RoomChanged")){
        }
        if (evt.getPropertyName().equals("GameOver")){
            System.exit(0);
        }
    }
    /**
     * Get the model of the game.
     * @return The model of the game.
     */
    public Model GetModel(){
        return model;
    }

    /**
     * Get the current player.
     * @return The current player.
     */
    public Student GetCurrentPlayer(){
        return model.currentPlayer;
    }
    /**
     * Enter a room in the game.
     * @param r The room to enter.
     */
    public boolean EnterRoom(Room r){
        return model.currentPlayer.EnterRoom(r);
    }

    public void NextTurn() {
        model.NextPlayer();
    }
}
