import java.util.ArrayList;

/**
 * The Proto class containing the prototype of the game.
 */
public class Proto {
    static ProtoHelper helper = new ProtoHelper();
    static ArrayList<String[]> commands = new ArrayList<String[]>();

    /**
     * Main method to start the game and execute commands.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        FillCommands();
        System.out.println("If you want any guide with the commands, please enter: Help");

        String[] key = helper.ChooseSequence();

        while(!key[0].equals("Exit")){
            RunCommand(key);
            key = helper.ChooseSequence();
        }   
    }
    public static void RunCommand(String[] key) {
        switch(key[0]){
            case "Exit": Exit();
            case "Help": helper.Help(commands); break;
            case "Random": Random(key); break;
            case "Info": Info(key); break;
            case "Init": Init(key); break;
            case "Reset": Reset(key); break;
            case "AddRoom": AddRoom(key); break;
            case "SetNeighbour": SetNeighbour(key); break;
            case "SpawnItem": SpawnItem(key); break;
            case "AddCharacter": AddCharacter(key); break;
            case "EnterRoom": EnterRoom(key); break;
            case "AddItem": AddItem(key); break;
            case "UseItem": UseItem(key); break;
            case "PickupItem": PickupItem(key); break;
            case "DropItem": DropItem(key); break;
            case "RoomChange": RoomChange(key); break;
            case "GasRoom": GasRoom(key); break;
            case "GooRoom": GooRoom(key); break;
            case "SetPair": SetPair(key); break;
            case "ActivateTransistor": ActivateTransistor(key); break;
            case "SetRoomFull": SetRoomFull(key); break;
            case "CharacterTurn": CharacterTurn(key); break;
            case "CombatRoom": CombatRoom(key); break;
            default: System.out.println("Invalid command!");
        }
    }
    /**
     * Fills the commands list with predefined commands.
     */
    public static void FillCommands(){
        commands.add(new String[] {"Exit"});
        commands.add(new String[] {"Help"});
        commands.add(new String[] {"Random", "<on/off>"});
        commands.add(new String[] {"Info", "<Type>"});
        commands.add(new String[] {"Init", "<fileName>"});
        commands.add(new String[] {"Reset"});
        commands.add(new String[]{"AddRoom", "<Room_id>", "<Type>"});
        commands.add(new String[]{"SetNeighbour", "<Room_id1>", "<Room_id2>"});
        commands.add(new String[]{"SpawnItem", "<Room_id>", "<name>", "<Item_id>", "<real>"});
        commands.add(new String[]{"AddCharacter", "<Character_id>", "<Type>", "<Room_id>"});
        commands.add(new String[]{"EnterRoom", "<Character_id>" , "<Room_id>"});
        commands.add(new String[]{"AddItem", "<Character_id>", "<name>", "<Item_id>", "<real>"});
        commands.add(new String[]{"UseItem", "<Character_id>", "<Item_id>"});
        commands.add(new String[]{"PickupItem", "<Character_id>", "<Item_id>"});
        commands.add(new String[]{"DropItem", "<Character_id>", "<Item_id>"});
        commands.add(new String[]{"RoomChange", "<Room_id1>", "<Type>","<Room_id2>"});
        commands.add(new String[]{"GasRoom", "<Room_id>"});
        commands.add(new String[]{"GooRoom", "<Room_id>", "<glued/unglued>"});
        commands.add(new String[]{"SetPair", "<Item_id1>", "<Item_id2>"});
        commands.add(new String[]{"ActivateTransistor", "<Item_id>"});
        commands.add(new String[]{"SetRoomFull", "<Room_id>"});
        commands.add(new String[]{"CharacterTurn", "<Character_id>"});
        commands.add(new String[]{"CombatRoom", "<Room_id>"});
    }
    /**
     * Exits the game.
     */
    public static void Exit(){
        System.exit(0);
    }
    /**
     * Enables or disables the random feature.
     * @param cmd The command and its parameter.
     */
    public static void Random(String[] cmd){
        if(cmd.length < 2){
            System.out.println("Missing parameter");
            return;
        }
        if(cmd[1].equals("on")){
            System.out.println("Random enabled");
        }
        else if(cmd[1].equals("off")){
            System.out.println("Random disabled");
        }
        else{
            System.out.println("Invalid parameter for this command!");
        }
    }
    /**
     * Displays information based on the provided parameter.
     * @param cmd The command and its parameter.
     */
    public static void Info(String[] cmd){
        if(cmd.length < 2){
            System.out.println("Missing parameter");
            return;
        }
        if(cmd[1].equals("All")){
            System.out.println("List: Info All");
        }
        else {
            System.out.println("List: Info something");
        }
    }
    /**
     * Initializes the game by creating entities and setting up the initial environment from a .txt file.
     * @param cmd The command and its parameter.
     */
    public static void Init(String[] cmd){
        if(cmd.length < 2){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Init " + cmd[1] + " file loading...");
    }
    /**
     * Resets the game state.
     * @param cmd The command and its parameter.
     */
    public static void Reset(String[] cmd){
        System.out.println("Reset");
    }
    /**
     * Adds a room to the game environment.
     * @param cmd The command and its parameters.
     */
    public static void AddRoom(String[] cmd){
        if(cmd.length < 2){
            System.out.println("Missing parameter");
            return;
        }
        if(cmd.length == 3 && cmd[2].equals("Cursed")){
            System.out.println("Add Cursed Room");
        }
        else {
            System.out.println("Add Normal Room");
        }
    }
    /**
     * Sets the neighbor relationship between two rooms.
     * @param cmd The command and its parameters.
     */
    public static void SetNeighbour(String[] cmd){
        if(cmd.length < 3){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Set neighbours");
    }
    /**
     * Spawns an item in a room.
     * @param cmd The command and its parameters.
     */
    public static void SpawnItem(String[] cmd){
        if(cmd.length < 5){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Item spawned");
    }
    /**
     * Adds a character to the game environment.
     * @param cmd The command and its parameters.
     */
    public static void AddCharacter(String[] cmd){
        if(cmd.length < 4){
            System.out.println("Missing parameter");
            return;
        }
        //type check needed
        System.out.println("Character added");
    }
    /**
     * Moves a character to a specified room.
     * @param cmd The command and its parameters.
     */
    public static void EnterRoom(String[] cmd){
        if(cmd.length < 3){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Character entered room");
    }
    /**
     * Adds an item to a character's inventory.
     * @param cmd The command and its parameters.
     */
    public static void AddItem(String[] cmd){
        if(cmd.length < 5){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Item added");
    }
    /**
     * Uses an item from a character's inventory.
     * @param cmd The command and its parameters.
     */
    public static void UseItem(String[] cmd){
        if(cmd.length < 3){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Item used");
    }
    /**
     * Allows a character to pick up an item from a room.
     * @param cmd The command and its parameters.
     */
    public static void PickupItem(String[] cmd){
        if(cmd.length < 3){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Item picked up");
    }
    /**
     * Allows a character to drop an item from their inventory.
     * @param cmd The command and its parameters.
     */
    public static void DropItem(String[] cmd){
        if(cmd.length < 3){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Item dropped");
    }
    /**
     * Change rooms.
     * @param cmd The command and its parameters.
     */
    public static void RoomChange(String[] cmd){
        if(cmd.length < 3){
            System.out.println("Missing parameter");
            return;
        }
        if(cmd[2].equals("Merge") && cmd.length == 4){
            System.out.println("Merge");
        }
        System.out.println("RoomChange");
    }
    /**
     * Sets the gas state of a room.
     * @param cmd The command and its parameters.
     */
    public static void GasRoom(String[] cmd){
        if(cmd.length < 2){
            System.out.println("Missing parameter");
            return;
        }
        /*if(room is gassed)
            remove gas
        else if
            add gas
         */   
    }
    /**
     * Sets the goo state of a room.
     * @param cmd The command and its parameters.
     */
    public static void GooRoom(String[] cmd){
        if(cmd.length < 2){
            System.out.println("Missing parameter");
            return;
        }
        /*if(room contains goo)
            if(unglued)
                unglued items
            else if(glued)
                glued items
            else
                remove goo
        else if
            add goo
            if(glued)
                glued items
            else if(unglued)
                unglued items
         */   
    }
    /**
     * Sets a pair of items (usually a transistor pair).
     * @param cmd The command and its parameters.
     */
    public static void SetPair(String[] cmd){
        if(cmd.length < 3){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Transistor paired");
    }
    /**
     * Activates a transistor.
     * @param cmd The command and its parameters.
     */
    public static void ActivateTransistor(String[] cmd){
        if(cmd.length < 2){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Transistor activated");
    }
    /**
     * Sets the room's full status.
     * @param cmd The command and its parameters.
     */
    public static void SetRoomFull(String[] cmd){
        if(cmd.length < 3){
            System.out.println("Missing parameter");
            return;
        }
        /* if(!room.IsFull)
                set full
            else 
                set not full
         */
    }
    /**
     * Starts a character's turn.
     * @param cmd The command and its parameters.
     */
    public static void CharacterTurn(String[] cmd){
        if(cmd.length < 2){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Character turn starts");
    }
    /**
     * Initiates combat in a room.
     * @param cmd The command and its parameters.
     */
    public static void CombatRoom(String[] cmd){
        if(cmd.length < 2){
            System.out.println("Missing parameter");
            return;
        }
        System.out.println("Combat results");
    }
}