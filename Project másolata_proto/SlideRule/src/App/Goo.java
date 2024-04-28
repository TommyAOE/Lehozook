package App;
import App.Items.Item;

public class Goo 
{
    Room location;
    int visitorcount=0;

    public Goo(Room location)
    {
        this.location=location;
    }

    //increases visitors by one
    public void UpVisitorCount()//növeli a látogatók számát, ha eléri az ötöt meghivja az itemek ragasztasat
    {
        visitorcount++;
        if(visitorcount>=5)
        {
            for(Item item:location.SearchItem())
            {
                if(!item.IsGlued())
                    item.Glue();
            }
        }
    }

    //resets visitors count
    public void ResetVisitorsCount()//nullázza a látogatók számát, ha 5 volt (vagy annál több) feloldja előtte az itemragasztást
    {
        for(Item item : location.SearchItem())
        {
            if(item.IsGlued())
                item.Glue();
        }
        visitorcount=0;
    }

    public void Activate_Test() {
        visitorcount = 5;
        UpVisitorCount();
    }

}
