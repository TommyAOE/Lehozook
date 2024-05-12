package App;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import App.Items.Transistor;

/**
 * The App.Proto class containing the prototype of the game.
 */
public class Proto {
    static ProtoHelper helper = new ProtoHelper();
    public static Logger resultLogger = Logger.getLogger("resultLogger");
    static ArrayList<String[]> commands = new ArrayList<String[]>();
    static boolean rnd = false;
    static Chart chart = new Chart();
    static FileHandler fileHandler;
    /**
     * Main method to start the game and execute commands.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        FillCommands();
        if(args.length == 0){
            System.out.println("If you want any guide with the commands, please enter: Help");
            String[] key = helper.ChooseSequence();
    
            while(!key[0].equals("Exit")){
                RunCommand(key);
                key = helper.ChooseSequence();
            }  
        }
        else if(args.length == 2){
            ArrayList<String[]> commands = helper.ReadCommands(new File(args[0]));
            InitLogger(args[1]);
            for(String[] key : commands){
                RunCommand(key);
            }
        }
        else{
            System.out.println("Something is wrong with the args...");
        }
 
    }
    /**
    * Executes the command specified by the given key.
    *
    * @param key the command key and its arguments
    */
    public static void RunCommand(String[] key) {
        switch(key[0]){
            case "Exit": Exit();
            case "Help": helper.Help(commands); break;
            case "Random": Random(key); break;
            case "Info": Info(key); break;
            case "Init": Init(key); break;
            case "Reset": Reset(key); break;
            case "AddRoom": AddRoom(key); break;
            case "SetNeighbours": SetNeighbours(key); break;
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
            case "ActivateTransistor": ActivateTransistor(key); break;
            case "SetRoomFull": SetRoomFull(key); break;
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
        commands.add(new String[]{"ActivateTransistor", "<Item_id>"});
        commands.add(new String[]{"SetRoomFull", "<Room_id>"});
        commands.add(new String[]{"CombatRoom", "<Room_id>"});
    }
    /**
     * Initializes the logger with the specified output file.
     *
     * @param output the path to the output file
     */
    public static void InitLogger(String output){

        fileHandler = null;
        try {
            fileHandler = new FileHandler(output);
            SimpleFormatter formatter = new SimpleFormatter(){
                private static final String format = "[%1$tT] %2$-7s %3$s %n";
                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format, lr.getMillis(), lr.getLevel().getLocalizedName(), lr.getMessage());
                }
            };
            fileHandler.setFormatter(formatter);
            fileHandler.setLevel(Level.INFO);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(formatter);
            consoleHandler.setLevel(Level.INFO);
            resultLogger.setUseParentHandlers(false);
            resultLogger.addHandler(consoleHandler);
            resultLogger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    /**
     * Exits the game.
     */
    public static void Exit(){
        resultLogger.log(Level.INFO, "Exit");
        if (resultLogger != null) {
            for (Handler handler : resultLogger.getHandlers()) {
                handler.close();
            }
        }
        System.exit(0);
    }
    /**
     * Enables or disables the random feature.
     * @param cmd The command and its parameter.
     */
    public static void Random(String[] cmd){
        if(cmd.length < 2){
            resultLogger.log(Level.INFO, "Missing parameter");
            return;
        }
        if(cmd[1].equals("on")){
            resultLogger.log(Level.INFO,"Random enabled");
            rnd = true;
        }
        else if(cmd[1].equals("off")){
            resultLogger.log(Level.INFO,"Random disabled");
            rnd = false;
        }
        else{
            resultLogger.log(Level.INFO,"Invalid parameter for this command!");
        }
    }
    /**
     * Displays information based on the provided parameter.
     * @param cmd The command and its parameter.
     */
    public static void Info(String[] cmd){
        if(cmd.length < 2){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        if(cmd[1].equals("all")){
            chart.InfoAll_Test();
        }
        else if (cmd.length == 3){
            chart.Info_Test(cmd[1], cmd[2]);
        }
    }
    /**
     * Initializes the game by creating entities and setting up the initial environment from a .txt file.
     * @param cmd The command and its parameter.
     */
    public static void Init(String[] cmd){
        if(cmd.length < 2){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        ArrayList<String[]> commands = helper.ReadCommands(new File(cmd[1]));
        for(String[] key:commands){
            RunCommand(key);
        }
        resultLogger.log(Level.INFO,cmd[1] + " Chart loaded");
    }
    /**
     * Resets the game state.
     * @param cmd The command and its parameter.
     */
    public static void Reset(String[] cmd){
        chart.Reset_Test();
    }
    /**
     * Adds a room to the game environment.
     * @param cmd The command and its parameters.
     */
    public static void AddRoom(String[] cmd){
        if(cmd.length < 2){
            resultLogger.log(Level.INFO, "Missing parameter");
            return;
        }
        if(cmd.length == 3 && cmd[2].equals("Cursed")){
            chart.AddRoom_Test(cmd[1], cmd[2]);
        }
        else {
            chart.AddRoom_Test(cmd[1], "Normal");
        }
    }
    /**
     * Sets the neighbor relationship between two rooms.
     * @param cmd The command and its parameters.
     */
    public static void SetNeighbours(String[] cmd){
        if(cmd.length < 3){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        chart.SetNeighbours_Test(cmd[1], cmd[2]);
    }
    /**
     * Spawns an item in a room.
     * @param cmd The command and its parameters.
     */
    public static void SpawnItem(String[] cmd){
        if(cmd.length < 5){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        chart.findRoomByName_Test(cmd[1]).AddItem_Test(cmd[2], cmd[3], Objects.equals(cmd[4], "true"));
    }
    /**
     * Adds a character to the game environment.
     * @param cmd The command and its parameters.
     */
    public static void AddCharacter(String[] cmd){
        if(cmd.length < 4){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        //type check needed
        chart.AddCharacter_Test(cmd[1], cmd[2], cmd[3]);
    }
    /**
     * Moves a character to a specified room.
     * @param cmd The command and its parameters.
     */
    public static void EnterRoom(String[] cmd){
        if(cmd.length < 3){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        chart.findCharacterByName_Test(cmd[1]).EnterRoom(chart.findRoomByName_Test(cmd[2]));
    }
    /**
     * Adds an item to a character's inventory.
     * @param cmd The command and its parameters.
     */
    public static void AddItem(String[] cmd){
        if(cmd.length < 5){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        ((Student)chart.findCharacterByName_Test(cmd[1])).AddItem_Test(cmd[2], cmd[3], Objects.equals(cmd[4], "true"));
    }
    /**
     * Uses an item from a character's inventory.
     * @param cmd The command and its parameters.
     */
    public static void UseItem(String[] cmd){
        if(cmd.length < 3){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        ((Student)chart.findCharacterByName_Test(cmd[1])).UseItem_Test(cmd[2]);
    }
    /**
     * Allows a character to pick up an item from a room.
     * @param cmd The command and its parameters.
     */
    public static void PickupItem(String[] cmd){
        if(cmd.length < 3){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        ((Student)chart.findCharacterByName_Test(cmd[1])).PickupItem_Test(cmd[2]);
    }
    /**
     * Allows a character to drop an item from their inventory.
     * @param cmd The command and its parameters.
     */
    public static void DropItem(String[] cmd){
        if(cmd.length < 3){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        ((Student)chart.findCharacterByName_Test(cmd[1])).DropItem_Test(cmd[2]);
    }
    /**
     * Change rooms.
     * @param cmd The command and its parameters.
     */
    public static void RoomChange(String[] cmd){
        if(cmd.length < 3){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        if(cmd[2].equals("Merge") && cmd.length == 4){
            chart.findRoomByName_Test(cmd[1]).Merge(chart.findRoomByName_Test(cmd[3]));
            return;
        }

        if(cmd[2].equals("Split") && cmd.length == 3){
            chart.findRoomByName_Test(cmd[1]).Split();
            return;
        }
    }
    /**
     * Sets the gas state of a room.
     * @param cmd The command and its parameters.
     */
    public static void GasRoom(String[] cmd){
        if(cmd.length < 2){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        if (chart.findRoomByName_Test(cmd[1]).gas!=null){
            chart.findRoomByName_Test(cmd[1]).GasExpired();
            resultLogger.log(Level.INFO,"Gas expired");
        }
        else {
            chart.findRoomByName_Test(cmd[1]).AddGas();
            resultLogger.log(Level.INFO,"Gas added");
        }
    }
    /**
     * Sets the goo state of a room.
     * @param cmd The command and its parameters.
     */
    public static void GooRoom(String[] cmd){
        if(cmd.length < 3){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        if (chart.findRoomByName_Test(cmd[1]).goo!=null){
            if (Objects.equals(cmd[2], "glued"))
                chart.findRoomByName_Test(cmd[1]).GooGlue_Test();
            else if (Objects.equals(cmd[2], "unglued"))
                chart.findRoomByName_Test(cmd[1]).GooUnglue_Test();
            else
                chart.findRoomByName_Test(cmd[1]).goo=null;
        }
        else {
            Room roomByID = chart.findRoomByName_Test(cmd[1]);
            roomByID.goo=new Goo(roomByID);
            if (Objects.equals(cmd[2], "glued"))
                chart.findRoomByName_Test(cmd[1]).GooGlue_Test();
            else if (Objects.equals(cmd[2], "unglued"))
                chart.findRoomByName_Test(cmd[1]).GooUnglue_Test();
        } 
    }
    /**
     * Activates a transistor.
     * @param cmd The command and its parameters.
     */
    public static void ActivateTransistor(String[] cmd){
        if(cmd.length < 2){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        ((Transistor)chart.findItemByName_Test(cmd[1])).Activate();
    }
    /**
     * Sets the room's full status.
     * @param cmd The command and its parameters.
     */
    public static void SetRoomFull(String[] cmd){
        if(cmd.length < 2){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        chart.findRoomByName_Test(cmd[1]).isFull=!chart.findRoomByName_Test(cmd[1]).isFull;
    }
    /*
     * Initiates combat in a room.
     * @param cmd The command and its parameters.
     */
    public static void CombatRoom(String[] cmd){
        if(cmd.length < 2){
            resultLogger.log(Level.INFO,"Missing parameter");
            return;
        }
        chart.findRoomByName_Test(cmd[1]).CombatRoom_Test();
    }
}