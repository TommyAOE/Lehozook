import java.util.LinkedHashMap;

import Items.Camembert;
import Items.StBeerCups;
import Items.TVSZ;
import Items.Transistor;
import Items.WetRag;

/**
 * The main class containing the skeleton of the game.
 */
public class Skeleton {
    static SkeletonHelper helper = new SkeletonHelper();
    static LinkedHashMap<String, Runnable> commands = new LinkedHashMap<>();
    /**
     * Main method to start the game and execute commands.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        FillMap();
        String key;
        while ((key = helper.ChooseSequence(commands)) != "Exit") {
            commands.get(key).run();
        }
    }

    /**
     * Fills the commands map with command strings and their corresponding actions.
     */
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
        commands.put("Exit", Skeleton::Exit);
    }

    /**
     * Exits the game.
     */
    public static void Exit(){
        System.exit(0);
    }

    /**
     * Initializes the game by creating entities and setting up the initial environment.
     */
    public static void Init(){
        new Scheduler();
        System.out.println("Scheduler created");
        new Chart();
        System.out.println("Chart created");
        new Room();
        System.out.println(1+".Room created");
        System.out.print(1+".");
        new Room().SetNeighbours();
        new Room();
        System.out.println(2+".Room created");
        System.out.print(2+".");
        new Room().SetNeighbours();
        int i = 1;
        do {
            new Student();
            System.out.println(i+".Student created");
            System.out.print(i+".");
            new Student().EnterRoom();
            System.out.print(i+".");
            new Room().CharacterEntered();
            i++;
        }while (SkeletonHelper.AddMoreStudents());
        i=1;
        do {
            new Professor();
            System.out.println(i+".Professor created");
            System.out.print(i+".");
            new Professor().EnterRoom();
            System.out.print(i+".");
            new Room().CharacterEntered();
            i++;
        }while (SkeletonHelper.AddMoreProfessors());
    }

    /**
     * Executes the scheduling logic of the game.
     */
    public static void Scheduler(){
        new Professor().Turn();
        new Student().Turn();
        if(helper.IsCombat()){
            new Student().Combat();
        }
        new Chart().IterateForRoomChanges();
        new Room().Change();

    }

    /**
     * Handles the logic when a professor enters a room.
     */
    public static void ProfEnterRoom(){
        new Professor().EnterRoom();
        new Room().CharacterEntered();
        if (helper.IsGassy()) {
            new Gas().Gasify();
            new Room().GetProfessors();
            new Professor().Stun();
            new Room().GasExpired();
        }else {
            new Room().GetStudents();
            if (!helper.StudentInRoom()) {
                new Room().GetProfessors();
                if (!helper.ProfessorsInRoom()) {
                    new Room().SearchItem();
                    if (helper.ItemsInRoom()) {
                        new Room().PopItem();
                    }
                }

            }
        }
        System.out.println("Turn ended");

    }

    /**
     * Handles the logic when a student picks up transistors.
     */
    public static void StudentItem(){
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

    /**
     * Handles the logic when a student steps into a gassed room.
     */
    public static void StudentGas(){

        Room r1 = new Room();
        r1.id = 1;
        Room r2 = new Room();
        r2.id = 2;

        helper.printRoomId(r1.id);
        r1.SearchItem();

        boolean answer = helper.IsMask();

        if(answer){
            helper.printRoomId(r1.id);
            r1.PopItem();
        }
        new Student().EnterRoom();
        helper.printRoomId(r1.id);
        r1.CharacterLeft();
        helper.printRoomId(r2.id);
        r2.CharacterEntered();
        
        new Gas().Gasify();
        helper.printRoomId(r2.id);
        r2.GetStudents();
        new Student().Stun();
        if(answer){
            System.out.println("The student was protected from the gas (has an FFP2 mask equipped)");
        }else{
            System.out.println("The student got stunned");
            helper.printRoomId(r2.id);
            r2.GasExpired();
        }

    }

    /**
     * Handles the logic when a student uses a neutral item.
     */
    public static void UseItem(){
        String useItem = helper.NeutralItemType();
        switch (useItem) {
            case "Camembert":
                new Student().Turn();
                new Student().EnterRoom();
                new Camembert().ApplyEffect();
                new Room().AddGas();
                new Room().GetStudents();
                new Student().Stun();
                break;
        
            case "Tramsistor":
                new Student().Turn();
                new Student().EnterRoom();
                Room r1 = new Room();
                r1.id = 1;
                Room r2 = new Room();
                r2.id = 2;
                Transistor t1 = new Transistor(1);
                Transistor t2 = new Transistor(2);
        
                System.out.println("Room " + r1.id);
                r1.CharacterEntered();
                System.out.println("Transistor " + t1.id);
                t1.ApplyEffect();
                System.out.println("Room " + r1.id);
                r1.AddItem();
        
                new Student().Turn();
                new Student().EnterRoom();
                System.out.println("Room " + r2.id);
                r2.CharacterEntered();
                System.out.println("Transistor " + t2.id);
                t2.ApplyEffect();
        
                new Student().Turn();
                new Student().EnterRoom();
                System.out.println("Room " + r1.id);
                r1.CharacterEntered();
                break;
        }
    }

    /**
     * Handles the result of combat between students and professors.
     */
    public static void CombatResult(){
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
                new Room().CharacterLeft();
                System.out.println("The student died.");
            }
        } else{
            System.out.println("There isn't a combat situation in this room.");
        }
    }

    /**
     * Handles the process of combat between 2 students and 2 professors.
     * A student can use a TVSZ, StBeerCups and wetrags
     */
    public static void CombatProcess(){
        // Display initial message
        System.out.println("---There are 2 students in this room");
        // Create two student objects
        Student st1 = new Student();
        Student st2 = new Student();
        // Assign IDs to students
        st1.id = 1;
        st2.id = 2;
        // Student 1 engages in combat
        helper.printStudentId(st1.id);
        st1.Combat();
        // Retrieve professors in the room
        new Room().GetProfessors();
        // Check if professors are present in the room
        if(helper.ProfessorsInRoom()){
            // Display message indicating professors are present
            System.out.println("---There are 2 professors in this room");
            // Create two professor objects
            Professor p1 = new Professor();
            Professor p2 = new Professor();
            // Assign IDs to professors
            p1.id = 1;
            p2.id = 2;
            // Check if Student 1 has an item
            helper.printStudentId(st1.id);
            if(helper.HasItem()){
                // Get the type of item the student has
                String selectedItem = helper.ItemType();
                 // Perform actions based on the selected item (Student 1)
                switch (selectedItem) {
                    // Apply TVSZ effect, protect both of the students against the professors
                    case "TVSZ":
                        new TVSZ().ApplyEffect();
                        new Room().GetStudents();
                        System.out.println("---The students are protected in this room.");
                        // Student 2 engages in combat
                        helper.printStudentId(st2.id);
                        st2.Combat();
                        // Retrieve professors in the room
                        new Room().GetProfessors();
                        System.out.println("---There are professors in the room.");
                        System.out.println("---Student2 doesn't use items, because they are already protected. (TVSZ)");
                        // Professor 1 engages in combat
                        helper.printProfessorId(p1.id);
                        p1.Combat();
                        // Retrieve students in the room and try to steel their souls
                        new Room().GetStudents();
                        helper.printStudentId(st1.id);
                        st1.Death();
                        helper.printStudentId(st2.id);
                        st2.Death();
                        System.out.println("---The students survived. (TVSZ has 2 charges left)");
                        // Professor 2 engages in combat
                        helper.printProfessorId(p2.id);
                        p2.Combat();
                        // Retrieve students in the room and try to steel their souls
                        new Room().GetStudents();
                        helper.printStudentId(st1.id);
                        st1.Death();
                        helper.printStudentId(st2.id);
                        st2.Death();
                        System.out.println("---The students survived. (TVSZ has 1 charges left)");
                        System.out.println("---Combat ended");
                        break;
                    // Apply StBeerCups effect
                    case "StBeerCups":
                        new StBeerCups().ApplyEffect();
                        System.out.println("---Student1 is protected.");
                        // Student 2 engages in combat
                        helper.printStudentId(st2.id);
                        st2.Combat();
                        // Retrieve professors in the room
                        new Room().GetProfessors();
                        System.out.println("---There are professors in the room.");
                        // Check if student 2 is protected
                        if(helper.IsProtectedStudent()){
                            System.out.println("---Student2 is protected, for example used StBeerCups");
                            // Professor 1 engages in combat
                            helper.printProfessorId(p1.id);
                            p1.Combat();
                            // Retrieve students in the room and try to steel their souls
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            System.out.println("---The students survived.");
                            // Professor 2 engages in combat
                            helper.printProfessorId(p2.id);
                            p2.Combat();
                            // Retrieve students in the room and try to steel their souls
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            System.out.println("---The students survived.");

                        } else{
                            System.out.println("---Student2 is NOT protected");
                            // Professor 1 engages in combat
                            helper.printProfessorId(p1.id);
                            p1.Combat();
                            // Retrieve students in the room and try to steel their souls
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            // Student 1 survived
                            st1.Death();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            // Student 2 is declared dead
                            new Room().CharacterLeft();
                            System.out.println("---Student1 survived, Student2 died.");
                            // Professor 2 engages in combat
                            helper.printProfessorId(p2.id);
                            p2.Combat();
                            // Retrieve students in the room and try to steel their souls
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            System.out.println("---Student1 survived, combat round ended");
                        }
                        break;
                    // Apply WetRag effect
                    case "WetRag":
                        new WetRag().ApplyEffect();
                        // Retrieve professors in the room
                        new Room().GetProfessors();
                        helper.printProfessorId(p1.id);
                        // Stun professor 1
                        p1.Stun();
                        helper.printProfessorId(p2.id);
                        // Stun professor 2
                        p2.Stun();
                        System.out.println("---Professor1 and Professor2 are stunned");
                        helper.printStudentId(st2.id);
                        // Student 2 engages in combat
                        st2.Combat();
                        // Retrieve professors in the room 
                        new Room().GetProfessors();
                        // All professors are stunned
                        System.out.println("---There isn't a combat situation in this room, all professors are stunned.");
                        break;
                }
            }else{
                System.out.println("---Student1 doesn't have items and protection.");
                helper.printStudentId(st2.id);
                // Student 2 engages in combat
                st2.Combat();
                // Retrieve professors in the room
                new Room().GetProfessors();
                // Display message indicating professors are present, this was asked in Student 1 turn
                System.out.println("---There are professors in the room.");
                // Check if student 2 has an item
                helper.printStudentId(st2.id);
                if(helper.HasItem()){
                    // Get the type of item the student has
                    String selectedItem = helper.ItemType();
                    // Perform actions based on the selected item
                    switch (selectedItem) {
                        // Apply TVSZ effect, protect both of the students against the professors
                        case "TVSZ":
                            new TVSZ().ApplyEffect();
                            new Room().GetStudents();
                            System.out.println("---The students are protected in this room.");
                            // Professor 1 engages in combat
                            helper.printProfessorId(p1.id);
                            p1.Combat();
                            // Retrieve students in the room and try to steel their souls
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            System.out.println("---The students survived. (TVSZ has 2 charges left)");
                            // Professor 2 engages in combat
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
                        // Apply StBeerCups effect
                        case "StBeerCups":
                            new StBeerCups().ApplyEffect();
                            System.out.println("---Student2 is protected.");
                            // Professor 1 engages in combat
                            helper.printProfessorId(p1.id);
                            p1.Combat();
                            // Retrieve students in the room and try to steel their souls
                            new Room().GetStudents();
                            helper.printStudentId(st1.id);
                            st1.Death();
                            // Student 1 is declared dead
                            new Room().CharacterLeft();
                            helper.printStudentId(st2.id);
                            // Student 2 survived (StBeerCups)
                            st2.Death();
                            System.out.println("---Student2 survived, Student1 died.");
                            // Professor 2 engages in combat
                            helper.printProfessorId(p2.id);
                            p2.Combat();
                            // Retrieve students in the room and try to steel their souls
                            new Room().GetStudents();
                            helper.printStudentId(st2.id);
                            st2.Death();
                            // Student 2 survived (StBeerCups)
                            System.out.println("---Student2 survived, combat round ended");
                            break;
                        // Apply WetRag effect
                        case "WetRag":
                            new WetRag().ApplyEffect();
                            // Retrieve professors in the room
                            new Room().GetProfessors();
                            // Professor 1 is stunned
                            helper.printProfessorId(p1.id);
                            p1.Stun();
                            // Professor 2 is stunned
                            helper.printProfessorId(p2.id);
                            p2.Stun();
                            System.out.println("---Professor1 and Professor2 are stunned");
                            System.out.println("---Combat round ended.");
                            break;
                    }
                }else{
                    // Display message indicating both students are unprotected, they did not have any items
                    System.out.println("---Student1 and Student2 NOT protected.");
                    // Professor 1 engages in combat
                    helper.printProfessorId(p1.id);
                    p1.Combat();
                    // Retrieve students in the room and try to steel their souls
                    new Room().GetStudents();
                    helper.printStudentId(st1.id);
                    st1.Death();
                    // Student 1 is declared dead
                    new Room().CharacterLeft();
                    helper.printStudentId(st2.id);
                    st2.Death();
                    // Student 2 is declared dead
                    new Room().CharacterLeft();
                    System.out.println("---Student1 and Student2 died.");
                    // Professor 2 engages in combat
                    helper.printProfessorId(p2.id);
                    p2.Combat();
                    // Retrieve students in the room, no students in the room, both of them died the previous round
                    new Room().GetStudents();
                    System.out.println("---There isn't a combat situation in this room.");
                }
            }
        }else{
            // No professors in the room
            System.out.println("---There isn't a combat situation in this room.");
        }
    }
    
    /**
     * Determines the result of the game.
     */
    public static void GameResult(){
        String answer =  helper.WhoWon();
        if(answer.equals("Students")){
            new Student().Turn();
            new Student().EnterRoom();
            new Room().CharacterEntered();
            new Room().SearchItem();
            new Room().PopItem();
            System.out.println("One of the students found the sliderule. Game Over students Win!4!4!");
        }else if(answer.equals("Professors")){
            System.out.println("Scheduler checks if there are students left");
            System.out.println("The last Student died, Game Over. Professors Win4!4!!");
        }
        else
        System.out.println("Acceptable answers are 'Students' or 'Professors' ");
    }

    /**
     * Handles the logic when rooms change.
     */
    public static void ChangeRooms(){
        new Chart().IterateForRoomChanges();
        if(helper.ProfessorsInRoom()) return;
        if(helper.StudentInRoom()) return;
        boolean answer = helper.WillItBeCursed();
        if(!answer){
            
            System.out.println("The changed room is not cursed! It can only split into two or merge with another room");
            new Room().Change();
        }else{
            System.out.println("The changed room is cursed! It may changes its neighbours as it vanishes its doors.");
            new Room().Change();
            
        }

    }
}
