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
        sc.close();

        return key;
}
}
