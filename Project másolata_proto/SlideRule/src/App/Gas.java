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
        boolean expire=false;

        for(Student student: location.GetStudents())
        {
            if(student.Stun(2)) expire=true;
        }
        for(Professor prof: location.GetProfessors())
        {
            if(prof.Stun(2))    expire=true;
        }
        if(expire) location.GasExpired();
    }
}
