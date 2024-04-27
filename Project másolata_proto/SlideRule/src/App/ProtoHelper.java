package App;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helper class for App.Proto game.
 */
public class ProtoHelper {
    /**
     * Allows the user to choose a command from a list of available commands.
     * @return The chosen command as a String array.
     */ 
    public String[] ChooseSequence(){
        System.out.println();
        System.out.println("Please enter one of the commands:");

        Scanner sc = new Scanner(System.in);

        return sc.nextLine().split(" ");
    }
    /**
     * Displays the list of available commands to the user.
     * @param commands The list of available commands.
     */
    public void Help(ArrayList<String[]> commands){
        System.out.println("Commands:");
        for (String[] command : commands) {
            for(int j = 0; j < command.length; j++){
                System.out.print(command[j] + " ");
            }
            System.out.println();
        }
    }
}