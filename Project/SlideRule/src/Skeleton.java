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
        commands.put("Init", Skeleton::Init);
        commands.put("Scheduler", Skeleton::Scheduler);
        commands.put("ProfEnterRoom", Skeleton::ProfEnterRoom);
        commands.put("StudentItem", Skeleton::StudentItem);
        commands.put("StudentGas", Skeleton::StudentGas);
        commands.put("UseItem", Skeleton::UseItem);
        commands.put("CombatResult", Skeleton::CombatResult);
        commands.put("CombatProcess", Skeleton::CombatProcess);
        commands.put("GameResult", Skeleton::GameResult);
        commands.put("ChangeRooms", Skeleton::ChangeRooms);
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
            new Room().GetStudents();
            if (!helper.StudentInRoom()) {
                new Room().GetProfessors();
                if (!helper.ProfInRoom()) {
                    new Room().SearchItem();
                    if (helper.ItemsInRoom()) {
                        new Room().PopItem();
                    }
                }

            }
        }
        System.out.println("Turn ended");

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
