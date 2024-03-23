import java.util.LinkedHashMap;
import java.util.Scanner;

public class SkeletonHelper {
    public String ChooseSequence(LinkedHashMap<String, Runnable> commands){
        for (String name : commands.keySet()) {
            System.out.println(name);
        }
        System.out.println("Please enter one of the commands above:");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key;
    }

    public boolean IsTransistor(){
        System.out.println("Is the item a transistor? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }

    public boolean HasTwoTransistors(){
        System.out.println("Does the student have another transistor? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }

    public boolean ProfessorsInRoom(){
        System.out.println("Is there at least one professor in the room? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }

    public boolean IsProtectedStudent(){
        System.out.println("Is the student protected against the professor? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }
}
