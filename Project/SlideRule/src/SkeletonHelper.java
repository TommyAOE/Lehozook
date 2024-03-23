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
    public static boolean AddMoreStudents(){
        System.out.println("Do you want to add more students? (y/n)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("y");
    }
    public static boolean AddMoreProfessors(){
        System.out.println("Do you want to add more professors? (y/n)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("y");
    }
    public  boolean ItemsInRoom(){
        System.out.println("Are there items in the room? (y/n)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("y");
    }
    public  boolean ProfInRoom(){
        System.out.println("Is there a professor in the room? (y/n)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("y");
    }
    public boolean StudentInRoom(){
        System.out.println("Is there a Student in the room? (y/n)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("y");
    }
    public  boolean IsGassy(){
        System.out.println("Is the room gassy? (y/n)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("y");
    }
}
