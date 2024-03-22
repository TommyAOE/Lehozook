import java.util.LinkedHashMap;
import java.util.Scanner;

public class SkeletonHelper {
    public String ChooseSequence(LinkedHashMap<String, Runnable> commands){
        for (String name : commands.keySet()) {
            System.out.println(name);
        }
        System.out.println("Please enter one of the commands above:");

        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public boolean IsTransistor(){
        System.out.println("Is the item a transistor? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes");
    }

    public boolean HasTwoTransistors(){
        System.out.println("Does the student have another transistor? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes");
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
    public void printStudentId(int id){
        System.out.println("\nSTUDENT ID = " + id);
    }
    public void printProfessorId(int id){
        System.out.println("\nPROFESSOR ID = "+ id);
    }
    public boolean HasItem(){
        System.out.println("Is the student has item? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }

    public String ItemType(){
        System.out.println("Which item would you like to use from among these?");
        System.out.println("TVSZ\nStBeerCups\nWetRag");
        System.out.println("Please enter the item name: ");

        Scanner sc = new Scanner(System.in);
        String item = sc.nextLine();

        return item;
    }

    public boolean IsCombat(){
        System.out.println("Are there any combat situations? (yes/no)");

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        return key.equals("yes") ? true : false ;
    }
    
    public String NeutralItemType(){
        System.out.println("Which item would you like to use from among these?");
        System.out.println("Camembert\nTransistor\n");
        System.out.println("Please enter the item name: ");

        Scanner sc = new Scanner(System.in);
        String item = sc.nextLine();

        return item;
    }
    //gameresult függvények kezdete
    public String WhoWon()
    {
        System.out.println("Who won? (Professors/Students): ");
        Scanner input=new Scanner(System.in);
        String who=input.nextLine();
        return who;
    }
    
    //gameresult függvények vége
}
