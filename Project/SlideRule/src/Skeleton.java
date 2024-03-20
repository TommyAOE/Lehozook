import java.util.LinkedHashMap;

public class Skeleton {
    static SkeletonHelper helper = new SkeletonHelper();
    static LinkedHashMap<String, Runnable> commands = new LinkedHashMap<>();
    public static void main(String[] args) {
        FillMap();
        String key = helper.ChooseSequence(commands);
        commands.get(key).run();
    }

    public static void FillMap(){
        commands.put("Init", () -> Init());
        commands.put("Schedular", () -> Schedular());
        commands.put("ProfEnterRoom", () -> ProfEnterRoom());
        commands.put("StudentItem", () -> StudentItem());
        commands.put("StudentGas", () -> StudentGas());
        commands.put("UseItem", () -> UseItem());
        commands.put("CombatResult", () -> CombatResult());
        commands.put("CombatProcess", () -> CombatProcess());
        commands.put("GameResult", () -> GameResult());
        commands.put("ChangeRooms", () -> ChangeRooms());
    }

    public static void Init(){
        System.out.println("Init");
    }
    public static void Schedular(){
        System.out.println("Schedular");
    }
    public static void ProfEnterRoom(){
        System.out.println("ProfEnterRoom");
    }
    public static void StudentItem(){
        System.out.println("StudentItem");
    }
    public static void StudentGas(){
        System.out.println("StudentGas");
    }
    public static void UseItem(){
        System.out.println("UseItem");
    }
    public static void CombatResult(){
        System.out.println("CombatResult");
    }
    public static void CombatProcess(){
        System.out.println("CombatProcess");
    }
    public static void GameResult(){
        System.out.println("GameResult");
    }
    public static void ChangeRooms(){
        System.out.println("ChangeRooms");
    }
}
