public class Goo 
{
    Room location;
    int visitorcount=0;

    //increases visitors by one
    public void UpVisitorCount()
    {
        visitorcount++;
    }

    //resets visitors count
    public void ResetVisitorsCount()
    {
        visitorcount=0;
    }
}
