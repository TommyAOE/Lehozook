/**
 * Represents a room in the game.
 */
public class Room {

    /** The ID of the room. */
    public int id;

    /** 
     * Gets the neighbors of the room.
     */
    public void GetNeighbours(){
        System.out.println("Room: GetNeighbours()");
    }

    /** 
     * Sets the neighbors of the room.
     */
    public void SetNeighbours(){
        System.out.println("Room: SetNeighbours()");
    }

    /** 
     * Changes the room.
     */
    public void Change(){
        System.out.println("Room: Change()");
    }

    /** 
     * Searches for an item in the room.
     */
    public void SearchItem(){
        System.out.println("Room: SearchItem()");
    }

    /** 
     * Adds an item to the room.
     */
    public void AddItem(){
        System.out.println("Room: AddItem()");
    }

    /** 
     * Pops an item from the room.
     */
    public void PopItem(){
        System.out.println("Room: PopItem()");
    }

    /** 
     * Gets the professors in the room.
     */
    public void GetProfessors(){
        System.out.println("Room: GetProfessors()");
    }

    /** 
     * Gets the students in the room.
     */
    public void GetStudents(){
        System.out.println("Room: GetStudents()");
    }

    /** 
     * Signals that a character has entered the room.
     */
    public void CharacterEntered(){
        System.out.println("Room: CharacterEntered()");
    }

    /** 
     * Signals that a character has left the room.
     */
    public void CharacterLeft(){
        System.out.println("Room: CharacterLeft()");
    }

    /** 
     * Adds gas to the room.
     */
    public void AddGas(){
        System.out.println("Room: AddGas()");
    }

    /** 
     * Signals that the gas in the room has expired.
     */
    public void GasExpired(){
        System.out.println("Room: GasExpired()");
    }
}
