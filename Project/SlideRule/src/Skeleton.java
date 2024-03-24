import java.util.LinkedHashMap;

import Items.StBeerCups;
import Items.TVSZ;
import Items.Transistor;
import Items.WetRag;

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
        new Room().GetProfessors();
        if(helper.ProfessorsInRoom()){
            if(helper.IsProtectedStudent()){
                new Professor().Combat();
                new Room().GetStudents();
                new Student().Death();
                System.out.println("The student survived, this combat round ended.");
            } else{
                new Professor().Combat();
                new Room().GetStudents();
                new Student().Death();
                new Room().StudentDied();
                System.out.println("The student died.");
            }
        } else{
            System.out.println("There isn't a combat situation in this room.");
        }
    }
    public static void CombatProcess(){
        System.out.println("CombatProcess");
        System.out.println("---There are 2 students in this room");
        Student st1 = new Student();
        Student st2 = new Student();
        st1.id = 1;
        st2.id = 2;
        helper.printStudentId(st1.id);
        st1.Combat();
        new Room().GetProfessors();
        if(helper.ProfessorsInRoom()){
            System.out.println("---There are 2 professors in this room");
            Professor p1 = new Professor();
            Professor p2 = new Professor();
            p1.id = 1;
            p2.id = 2;
            helper.printStudentId(st1.id);
            if(helper.HasItem()){
                String selectedItem = helper.ItemType();
                switch (selectedItem) {
                    case "TVSZ":
                        new TVSZ().ApplyEffect();
                        new Room().GetStudents();
                        System.out.println("---The students are protected in this room.");
                        helper.printStudentId(st2.id);
                        st2.Combat();
                        new Room().GetProfessors();
                        System.out.println("---There are professors in the room.");
                        System.out.println("---Student2 doesn't use items, because they are already protected. (TVSZ)");
                        helper.printProfessorId(p1.id);
                        p1.Combat();
                        new Room().GetStudents();
                        helper.printStudentId(st1.id);
                        st1.Death();
                        helper.printStudentId(st2.id);
                        st2.Death();
                        System.out.println("---The students survived. (TVSZ has 2 charges left)");

                        helper.printProfessorId(p2.id);
                        p2.Combat();
                        new Room().GetStudents();
                        helper.printStudentId(st1.id);
                        st1.Death();
                        helper.printStudentId(st2.id);
                        st2.Death();
                        System.out.println("---The students survived. (TVSZ has 1 charges left)");
                        System.out.println("---Combat ended");
                        break;

                    case "StBeerCups":
                        new StBeerCups().ApplyEffect();
                        System.out.println("---Student1 is protected.");
                        helper.printStudentId(st2.id);
                        st2.Combat();
                        new Room().GetProfessors();
                        System.out.println("---There are professors in the room.");
                        if(helper.IsProtectedStudent()){
                            System.out.println("---Student2 is protected, for example used StBeerCups");
                            helper.printProfessorId(p1.id);
                            p1.Combat();
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            System.out.println("---The students survived.");

                            helper.printProfessorId(p2.id);
                            p2.Combat();
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            System.out.println("---The students survived.");

                        } else{
                            System.out.println("---Student2 is NOT protected");
                            helper.printProfessorId(p1.id);
                            p1.Combat();
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            new Room().StudentDied();
                            System.out.println("---Student1 survived, Student2 died.");

                            helper.printProfessorId(p2.id);
                            p2.Combat();
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            System.out.println("---Student1 survived, combat round ended");
                        }
                        break;

                    case "WetRag":
                        new WetRag().ApplyEffect();
                        new Room().GetProfessors();
                        helper.printProfessorId(p1.id);
                        p1.Stun();
                        helper.printProfessorId(p2.id);
                        p2.Stun();
                        System.out.println("---Professor1 and Professor2 are stunned");
                        helper.printStudentId(st2.id);
                        st2.Combat();
                        new Room().GetProfessors();
                        System.out.println("---There isn't a combat situation in this room, all professors are stunned.");
                        break;
                }
            }else{
                System.out.println("---Student1 doesn't have items and protection.");
                helper.printStudentId(st2.id);
                st2.Combat();
                new Room().GetProfessors();
                System.out.println("---There are professors in the room.");
                helper.printStudentId(st2.id);
                if(helper.HasItem()){
                    String selectedItem = helper.ItemType();
                    switch (selectedItem) {
                        case "TVSZ":
                            new TVSZ().ApplyEffect();
                            new Room().GetStudents();
                            System.out.println("---The students are protected in this room.");
                            helper.printProfessorId(p1.id);
                            p1.Combat();
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            System.out.println("---The students survived. (TVSZ has 2 charges left)");

                            helper.printProfessorId(p2.id);
                            p2.Combat();
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            System.out.println("---The students survived. (TVSZ has 1 charges left)");
                            System.out.println("Combat ended");
                            break;  

                        case "StBeerCups":
                            new StBeerCups().ApplyEffect();
                            System.out.println("---Student2 is protected.");
                            helper.printProfessorId(p1.id);
                            p1.Combat();
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            new Room().StudentDied();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            System.out.println("---Student2 survived, Student1 died.");

                            helper.printProfessorId(p2.id);
                            p2.Combat();
                            new Room().GetStudents();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            System.out.println("---Student2 survived, combat round ended");
                            break;

                        case "WetRag":
                            new WetRag().ApplyEffect();
                            new Room().GetProfessors();
                            helper.printProfessorId(p1.id);
                            p1.Stun();
                            helper.printProfessorId(p2.id);
                            p2.Stun();
                            System.out.println("---Professor1 and Professor2 are stunned");
                            System.out.println("---Combat round ended.");
                            break;
                    }
                }else{
                    System.out.println("---Student1 and Student2 NOT protected.");
                    helper.printProfessorId(p1.id);
                    p1.Combat();
                    new Room().GetStudents();
                    helper.printStudentId(st1.id);
                    st1.Death();
                    new Room().StudentDied();
                    helper.printStudentId(st2.id);
                    st2.Death();
                    new Room().StudentDied();
                    System.out.println("---Student1 and Student2 died.");

                    helper.printProfessorId(p2.id);
                    p2.Combat();
                    new Room().GetStudents();
                    System.out.println("---There isn't a combat situation in this room.");
                }
            }
        }else{
            System.out.println("---There isn't a combat situation in this room.");
        }
    }
    public static void GameResult(){
        System.out.println("GameResult");
    }
    public static void ChangeRooms(){
        System.out.println("ChangeRooms");
    }
}
