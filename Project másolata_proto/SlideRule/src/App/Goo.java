package App;
import App.Items.Item;

public class Goo 
{
    Room location;
    int visitorcount=0;

    //increases visitors by one
    public void UpVisitorCount()//növeli a látogatók számát, ha eléri az ötöt meghivja az itemek ragasztasat
    {
        visitorcount++;
        if(visitorcount>=5)
        {
            for(Item itemek:location.GetItems())
            {
                itemek.Glue();
            }
        }
    }

    //resets visitors count
    public void ResetVisitorsCount()//nullázza a látogatók számát, ha 5 volt (vagy annál több) feloldja előtte az itemragasztást
    {
        if(visitorcount>=5)
        {
            for(Item itemek:location.GetItems())
            {
                itemek.Glue();
            }
        }
        visitorcount=0;
    }
}
