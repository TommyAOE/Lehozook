import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Helper class providing various utility methods for the Skeleton game.
 */
public class SkeletonHelper {

    /**
     * Allows the user to choose a command from a list of available commands.
     * @param commands A LinkedHashMap containing command names as keys and corresponding actions as values.
     * @return The chosen command.
     */
    public String ChooseSequence(LinkedHashMap<String, Runnable> commands){
        System.out.println();
        System.out.println("Commands:");
        int i = 1;
        for (String name : commands.keySet()) {
            System.out.print(name + "\t");
            if(i % 5 == 0) System.out.println();
            i++;
        }
        System.out.println();
        System.out.println();
        System.out.println("Please enter one of the commands above:");

        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    /**
     * Checks if the item picked up by the student is a transistor.
     * @return True if the item is a transistor, false otherwise.
     */
    public boolean IsTransistor(){
        System.out.println("Is the item a transistor? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes");
    }

    /**
     * Checks if the student has another transistor.
     * @return True if the student has another transistor, false otherwise.
     */
    public boolean HasTwoTransistors(){
        System.out.println("Does the student have another transistor? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes");
    }

    /**
     * Asks if more students should be added.
     * @return True if more students should be added, false otherwise.
     */
    public static boolean AddMoreStudents(){
        System.out.println("Do you want to add more students? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes");
    }

    /**
     * Asks if more professors should be added.
     * @return True if more professors should be added, false otherwise.
     */
    public static boolean AddMoreProfessors(){
        System.out.println("Do you want to add more professors? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes");
    }

    
    public  boolean ItemsInRoom(){
        System.out.println("Are there items in the room? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes");
    }

    /**
     * Asks if the room is cursed.
     * @return True if the room is cursed, false otherwise.
     */
    public boolean StudentInRoom(){
        System.out.println("Is there a Student in the room? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes");
    }

    /**
     * Asks if the room is gassy.
     * @return True if the room is gassy, false otherwise.
     */
    public  boolean IsGassy(){
        System.out.println("Is the room gassy? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes");
    }

    /**
     * Checks if there is at least one professor in the room.
     * @return True if there is at least one professor in the room, false otherwise.
     */
    public boolean ProfessorsInRoom(){
        System.out.println("Is there at least one professor in the room? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }

    /**
     * Asks if the student is protected against the professor.
     * @return True if the student is protected against the professor, false otherwise.
     */
    public boolean IsProtectedStudent(){
        System.out.println("Is the student protected against the professor? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }

    /**
     * Prints the ID of a student.
     * @param id The ID of the student to be printed.
     */
    public void printStudentId(int id){
        System.out.println("\nSTUDENT ID = " + id);
    }

    /**
     * Prints the ID of a room.
     * @param id The ID of the room to be printed.
     */
    public void printRoomId(int id){
        System.out.println("\nROOM ID = " + id);
    }

    /**
     * Prints the ID of a professor.
     * @param id The ID of the professor to be printed.
     */
    public void printProfessorId(int id){
        System.out.println("\nPROFESSOR ID = "+ id);
    }

    /**
     * Checks if the student has an item.
     * @return True if the student has an item, false otherwise.
     */
    public boolean HasItem(){
        System.out.println("Is the student has item? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }

    /**
     * Asks the user to choose an item type.
     * @return The chosen item type (TVSZ, StBeerCups, or WetRag).
     */
    public String ItemType(){
        System.out.println("Which item would you like to use from among these?");
        System.out.println("TVSZ\nStBeerCups\nWetRag");
        System.out.println("Please enter the item name: ");

        Scanner sc = new Scanner(System.in);
        String item = sc.nextLine();

        return item;
    }

    /**
     * Asks if there are any combat situations.
     * @return True if there are combat situations, false otherwise.
     */
    public boolean IsCombat(){
        System.out.println("Are there any combat situations? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }

    /**
     * Asks if there are any FFP2 masks available.
     * @return True if there are FFP2 masks available, false otherwise.
     */
    public boolean IsMask(){
        System.out.println("Are there any FFP2 Masks? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }

    /**
     * Asks the user to choose a neutral item for use.
     * @return The chosen neutral item (Camembert/Transistor).
     */
    public String NeutralItemType(){
        System.out.println("Which item would you like to use from among these?");
        System.out.println("Camembert\nTransistor\n");
        System.out.println("Please enter the item name: ");

        Scanner sc = new Scanner(System.in);
        String item = sc.nextLine();

        return item;
    }

    /**
     * Asks who won the game.
     * @return The winner of the game (Professors/Students).
     */
    public String WhoWon()
    {
        System.out.println("Who won? (Professors/Students): ");
        Scanner input=new Scanner(System.in);
        String who=input.nextLine();
        return who;
    }

    /**
     * Asks if the room is cursed.
     * @return True if the room is cursed, false otherwise.
     */
    public boolean WillItBeCursed(){
        System.out.println("Is the room cursed? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }
    
    //gameresult függvények vége
}
