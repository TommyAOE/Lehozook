package App;

/**
 * Represents a character in the game.
 * This class serves as a base for all types of characters.
 */
public abstract class Character {


    /**
     * The name of the character.
     */
    protected String name;

    /**
     * The current location of the character.
     */
    protected Room location;
    public Character(String name, Room location) {
        this.name = name;
        this.location = location;
    }
    /**
     * Performs the character's turn action.
     */
    public abstract void Turn();

    /**
     * Moves the character to a room.
     *
     * @param r the room to enter
     */
    public abstract boolean EnterRoom(Room r);

    /**
     * Gets the name of the character.
     *
     * @return the name of the character
     */
    //new
    public String GetName() {
        return name;
    }
    public abstract void InfoAll_Test();
    public abstract void Info_Test();

}
