package App.Model;
import App.Model.Items.Item;

/**
 * Represents a Goo object in the game.
 */
public class Goo {
    Room location;
    int visitorcount = 0;

    /**
     * Constructs a Goo object associated with the specified room.
     *
     * @param location The room where the Goo object is located.
     */
    public Goo(Room location) {
        this.location = location;
    }

    /**
     * Increases the visitor count by one. If the visitor count reaches five, it triggers item gluing.
     */
    public void UpVisitorCount() {
        visitorcount++;
        if (visitorcount >= 5) {
            for (Item item : location.SearchItem()) {
                if (!item.IsGlued())
                    item.Glue();
            }
        }
    }

    /**
     * Resets the visitor count. If the count was five or more, it releases item gluing before resetting.
     */
    public void ResetVisitorsCount() {
        for (Item item : location.SearchItem()) {
            if (item.IsGlued())
                item.Glue();
        }
        visitorcount = 0;
    }

    /**
     * A testing method to simulate the activation of Goo by setting visitor count to five and invoking UpVisitorCount.
     */
    public void Activate_Test() {
        visitorcount = 5;
        UpVisitorCount();
    }
}
