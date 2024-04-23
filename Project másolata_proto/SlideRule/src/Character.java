/**
 * Represents a character in the game.
 * This class serves as a base for all types of characters.
 */
public abstract class Character {
    
    String name;
    Room location;
    /** 
     * Performs the character's turn action.
     */
    public abstract void Turn();
    
    /* 
     * Performs the character's action when entering a room.
     */
    public abstract void EnterRoom();
   /*  
    /** 
     * Performs the character's stun action.
     */
    //public abstract void Stun();
    
    /** 
     * Performs the character's combat action.
     */
    //public abstract void Combat();

}
