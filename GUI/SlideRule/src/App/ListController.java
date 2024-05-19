package App;

import App.Model.Items.Item;
import App.Model.Items.Transistor;
import App.Model.Model;

public class ListController {
    Model model;
    public ListController(Model model){
        this.model = model;
    }
    public void DropItem(Item item){
        model.currentPlayer.DropItem(item);

    }
    public void PickUpItem(Item item){
        model.currentPlayer.PickUpItem(item);
    }
    public void UseItem(Item item){
        model.currentPlayer.UseItem(item);
    }
    public void ActivateItem(Transistor item){
        model.currentPlayer.ActivateTransistor(item);
    }
}
