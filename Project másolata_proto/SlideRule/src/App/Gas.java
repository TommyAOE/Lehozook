package App;
/**
 * Represents gas in the game.
 */

public class Gas {

    Room location;

    public Gas(Room location)
    {
        this.location=location;
        this.Gasify();
    }
    
    /** 
     * Gasifies the environment.
     */
    public void Gasify(){
        System.out.println("Gas: Gasify()");
        boolean expire=false;

        for(Student student: location.GetStudents())
        {
            if(student.Stun())expire=true;
        }
        for(Professor prof: location.GetProfessors())
        {
            if(prof.Stun())expire=true;
        }
        if(expire) location.GasExpired();
    }
}
