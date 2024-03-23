import java.util.LinkedHashMap;

import Items.Transistor;

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
        commands.put("Scheduler", () -> Scheduler());
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
        new Scheduler(true);
    }
    public static void Scheduler(){
        System.out.println("Scheduler");
    }
    public static void ProfEnterRoom(){
        System.out.println("ProfEnterRoom");
        new Professor().EnterRoom();
        new Room().CharacterEntered();
        if (helper.IsGassy()) {
            new Gas().Gasify();
            new Room().GetProfessors();
            new Professor().Stun();
        }else {
            new Room().GetProfessors();
            if (!helper.StudentInRoom()) {
                if (!helper.ProfInRoom()) {
                    new Room().SearchItem();
                    if (helper.ItemsInRoom()) {
                        new Room().PopItem();
                    }
                }

            }
        }

    }
    public static void StudentItem(){
        System.out.println("StudentItem");
        new Student().EnterRoom();
        new Room().CharacterEntered();
        new Room().SearchItem();
        new Room().PopItem();
        if(helper.IsTransistor()){
            if(helper.HasTwoTransistors()){
                Transistor t1 = new Transistor(1);
                Transistor t2 = new Transistor(2);

                t1.SetPair();
                t2.SetPair();
            }else{
                System.out.println("Student picked up only one transistor");
            }
        }else{
            System.out.println("Student picked up an item other than a transistor");
        }
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
