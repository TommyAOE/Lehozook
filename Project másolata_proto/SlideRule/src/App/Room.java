package App;
import java.util.LinkedList;
import java.util.List;

import App.Items.Item;

/**
 * Represents a room in the game.
 */
public class Room {

    //attributomok
    int maximumcapacity;
    int characterCount;
    List<Room> neighbours;
    boolean isCursed;
    List<Item> items;
    List<Professor> professors;
    List<Student> students;
    List<Cleaner> cleaners;
    Gas gas;
    Goo goo;
    boolean isFull;
    //////////////
    
    /** The ID of the room. */
    public int id;
//szobaval kapcsolatos metodusok
    public void Curse()
    {
        System.out.println("Szoba:Curse()");
    }
    public void split()
    {
        System.out.println("Szoba:Split");
    }
    public void Merge(Room r)
    {
        System.out.println("Szoba:"+r+"osszeolvadt");
    }
    /** 
     * Gets the neighbors of the room.
     */
    public void GetNeighbours()
    {
        
        System.out.println("Room: GetNeighbours()");
        
    }
    /** 
     * Sets the neighbors of the room.
     */
    public void SetNeighbours()
    {
        System.out.println("Room: SetNeighbours()");
    }
    /** 
     * Changes the room.
     */
    public void Change()
    {
        System.out.println("Room: Change()");
    }
    //////////////////////////////////


    //Itemekkel kapcsolatos fuggvenyek

    public List<Item> GetItems()//szuksegem volt ra a gooban, visszaadja az adott szobaban levo itemeket
    {
        return items;
    }
    /** 
     * Searches for an item in the room.
     */
    public void SearchItem()
    {
        System.out.println("Room: SearchItem()");
    }

    /** 
     * Adds an item to the room.
     */
    public void AddItem()
    {
        System.out.println("Room: AddItem()");
    }

    /** 
     * Pops an item from the room.
     */
    public void PopItem()
    {
        System.out.println("Room: PopItem()");
       /*  Item seged;
        for (Item item : items) 
        {
            if(item.equals(i))
            {
                seged=item;
                items.remove(item);
            }
        }
            return seged;*/
        
    }
    //////////////////////////////////

    //karakterekkel kapcsolatos

    /** 
     * Gets the professors in the room.
     */
    public List<Professor> GetProfessors()//visszaadja a professzorokat az adott szobaban
    {
        System.out.println("Room: GetProfessors()");
        return professors;
    }

    /** 
     * Gets the students in the room.
     */
    public List<Student> GetStudents()//visszaadja a tanulokat az adott szobaban
    {
        System.out.println("Room: GetStudents()");
        return students;
    }

    public void GetCleaners()
    {
        System.out.println("Room: GetCleaners()");
    }

    /** 
    * Signals that a character has entered the room.
    */
    public void CharacterEntered()
    {
        System.out.println("Room: CharacterEntered()");
    }

     /** 
     * Signals that a character has left the room.
     */
    public void CharacterLeft()
    {
        System.out.println("Room: CharacterLeft()");
    }

    ///////////////////////////

    //egyeb

    /** 
     * Adds gas to the room.
     */
    public void AddGas()
    {
        System.out.println("Room: AddGas()");
    }

    /** 
     * Signals that the gas in the room has expired.
     */
    public void GasExpired()//
    {
        System.out.println("Room: GasExpired()");
        this.gas=null;
    }

    public boolean HasGas()//boolean lesz
    {
        return (gas==null)? false:true;
    }

    public void Clean()
    {
        System.out.println("Room:Clean()");
    }
    ///////    
}
