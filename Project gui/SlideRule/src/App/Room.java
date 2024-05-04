package App;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;

import App.Items.*;

import static App.Proto.resultLogger;

/**
 * Represents a room in the game.
 */
public class Room {

    Random rand = new Random();
    String name;
    int maximumcapacity;
    int characterCount;
    ArrayList<Room> neighbours;
    boolean isCursed;
    ArrayList<Item> items;
    ArrayList<Professor> professors;
    ArrayList<Student> students;
    ArrayList<Cleaner> cleaners;
    Gas gas;
    Goo goo;
    Chart chart;
    boolean isFull;

    /**
     * Constructs a room with the specified name, type, and chart.
     *
     * @param name  The name of the room.
     * @param type  The type of the room.
     * @param chart The chart containing the room.
     */
    public Room(String name, String type, Chart chart) {
        this.name = name;
        this.isCursed= Objects.equals(type, "Cursed");
        this.neighbours = new ArrayList<Room>();
        this.items = new ArrayList<Item>();
        this.professors = new ArrayList<Professor>();
        this.students = new ArrayList<Student>();
        this.cleaners = new ArrayList<Cleaner>();
        this.isFull = false;
        this.chart =  chart;
    }
    /**
     * Constructs a room with the specified name.
     *
     * @param name The name of the room.
     */
    public Room(String name) {
        this.name = name;
        this.neighbours = new ArrayList<Room>();
        this.items = new ArrayList<Item>();
        this.professors = new ArrayList<Professor>();
        this.students = new ArrayList<Student>();
        this.cleaners = new ArrayList<Cleaner>();
        this.isFull = false;
        this.isCursed = false;
    }
    /**
     * Checks if the room is full.
     *
     * @return true if the room is full, false otherwise.
     */
    public boolean IsFull() {
        return isFull;
    }
    public String GetName(){
        return name;
    }
    /**
     * Sets the given room as a neighbour of this room.
     *
     * @param r The room to set as a neighbour.
     */
    public void SetNeighbours(Room r){
        neighbours.add(r);
        resultLogger.log(Level.INFO, "Room " + r.name + " set as neighbour of Room " + this.name);
    }
    /**
     * Curses the room by modifying its neighbours.
     *
     * @param rooms The list of rooms in the game.
     */
    public void Curse(List<Room> rooms)
    {
        Room curRoom = new Room("whatever");
        Room delRoom = new Room("whatever");
        while(true) {
            curRoom = rooms.get(rand.nextInt(rooms.size() - 1));
            if(!neighbours.contains(curRoom)){
                neighbours.add(curRoom);
                curRoom.SetNeighbours(this);
                while(true){
                    delRoom = rooms.get(rand.nextInt(neighbours.size() - 1));
                    if(delRoom!=curRoom){
                        rooms.remove(delRoom);
                        try {
                            delRoom.neighbours.remove(this);
                        }
                        catch (Exception e){}
                        break;
                    }
                }
                break;
            }
        }
        resultLogger.log(Level.INFO,"Room Curse() "+curRoom.name+" added "+delRoom.name+" deleted");
    }
    /**
     * Splits the room into two rooms.
     */
    public void Split()
    {
        if(professors.size()+ students.size()+cleaners.size()>0){
            resultLogger.log(Level.INFO,"Split is impossible, characters exist in the Room");
            return;
        }
        Room newRoom = new Room("Whatever");
        newRoom.maximumcapacity=this.maximumcapacity/2+1;
        for(int i=neighbours.size()/2;i<neighbours.size();i++){
            newRoom.SetNeighbours(neighbours.get(i));
        }
        for (Room roomsOfNew:newRoom.neighbours) {
            neighbours.remove(roomsOfNew);
            try{
                roomsOfNew.neighbours.remove(this);
                roomsOfNew.SetNeighbours(newRoom);
            }
            catch (Exception e){}
        }

        for(int i=items.size()/2;i<items.size();i++){
            newRoom.AddItem(items.get(i));
        }
        for (Item itemsOfNew:newRoom.items) {
            items.remove(itemsOfNew);
        }
        newRoom.SetNeighbours(this);
        SetNeighbours(newRoom);
        chart.AddRoom(newRoom);
        resultLogger.log(Level.INFO,"Split");

    }
    /**
     * Merges this room with the given room.
     *
     * @param r The room to merge with.
     */
    public void Merge(Room r)
    {
        if(r.maximumcapacity<maximumcapacity){
            for(Item itemsOfSmaller : r.items){
                items.add(itemsOfSmaller);
            }
            for(Room neighboursOfSmaller : r.neighbours){
                neighbours.add(neighboursOfSmaller);
                neighboursOfSmaller.neighbours.remove(r);
                neighboursOfSmaller.SetNeighbours(this);
            }
            chart.removeRoom(this);
        }
        else{
            for(Item itemsOfSmaller : this.items){
                r.items.add(itemsOfSmaller);
            }
            for(Room neighboursOfSmaller : this.neighbours){
                r.neighbours.add(neighboursOfSmaller);
                neighboursOfSmaller.neighbours.remove(this);
                neighboursOfSmaller.SetNeighbours(r);
            }
            chart.removeRoom(this);
        }
        resultLogger.log(Level.INFO,"Room merged");
    }
    /** 
     * Gets the neighbors of the room.
     */
    public ArrayList<Room> GetNeighbours()
    {
        return neighbours;
    }
    /**
     * Changes the room.
     */
    public void Change(List<Room> rooms)
    {
        if (isCursed)
        {
            Curse(rooms);
        }else{
            switch (rand.nextInt(2)) {
                case 0 -> Merge(neighbours.get(rand.nextInt(neighbours.size() + 1)));
                case 1 -> Split();
                default -> {
                }
            }
        }
    }

    /** 
     * Searches for an item in the room.
     */
    public ArrayList<Item> SearchItem()
    {
        return items;
    }

    /** 
     * Adds an item to the room.
     * @param item
     */
    public void AddItem(Item item)
    {
        items.add(item);
    }

    /** 
     * Pops an item from the room.
     */
    public Item PopItem(Item item)
    {
        Item seged = null;
        for (int i = 0; i < items.size(); i++) 
        {
            if(items.get(i).equals(item))
            {
                if(!items.get(i).IsGlued()){
                    seged = items.get(i);
                    items.remove(i);
                }
            }
        }
        return seged;
        
    }

    /** 
     * Gets the professors in the room.
     */
    public List<Professor> GetProfessors()//visszaadja a professzorokat az adott szobaban
    {
        return professors;
    }

    /** 
     * Gets the students in the room.
     */
    public List<Student> GetStudents()//visszaadja a tanulokat az adott szobaban
    {
        return students;
    }

    public List<Cleaner> GetCleaners()
    {
        return cleaners;
    }

    /** 
    * Signals that a character has entered the room.
    */
    public void CharacterEntered(Character c)
    {
        switch (c.GetName().charAt(0))
        {
            case 'p':
                professors.add((Professor)c);
                if(this.gas != null)
                    this.gas.Gasify();
                break;
            case 's':
                students.add((Student)c);
                if(this.gas != null)
                    this.gas.Gasify();
                break;
            case 'c':
                cleaners.add((Cleaner)c);
                this.Clean();
                break;
            default:
                resultLogger.log(Level.INFO,"Something went wrong with the character");
                break;
        }

    }

     /** 
     * Signals that a character has left the room.
     */
    public void CharacterLeft(Character c)
    {
        switch (c.GetName().charAt(0))
        {
            case 'p':
                professors.remove(c);
                break;
            case 's':
                students.remove(c);
                //A Grafikusnál nem itt lesz implementálva, hanem a schedulerben
                if(((Student)c).location == null){
                    int studentCounter = 0;
                    for (Room room : chart.GetAllRooms()) {
                        if(room.GetStudents().size() > 0){
                            studentCounter += room.GetStudents().size();
                        }
                    }
                    if(studentCounter <= 0){
                        resultLogger.log(Level.INFO, "Professors have won");
                        System.exit(0);
                    }
                }
                break;
            case 'c':
                cleaners.remove(c);
                break;
            default:
            resultLogger.log(Level.INFO,"Character could not be found in this Room");
                break;
        }
    }

    /** 
     * Adds gas to the room.
     */
    public void AddGas()
    {
        gas = new Gas(this);
    }

    /** 
     * Signals that the gas in the room has expired.
     */
    public void GasExpired()//
    {
        this.gas=null;
    }
    /**
     * Checks if the room has gas.
     *
     * @return true if the room has gas, false otherwise.
     */
    public boolean HasGas()
    {
        return gas != null;
    }
    /**
     * Cleans the room by resetting the visitor count.
     */
    public void Clean()
    {
        int studentCounter = 0;
        while(students.size() > studentCounter) {
            if(students.get(studentCounter).IsStunned() == 0){
                Room newRoom = this.neighbours.get(new Random().nextInt(this.neighbours.size()));
                while(!(students.get(studentCounter).EnterRoom(newRoom)))
                    newRoom = this.neighbours.get(new Random().nextInt(this.neighbours.size()));
            }
            else {
                studentCounter++;
            }
        }
        int professorCounter = 0;
        while(professors.size() > professorCounter) {
            if(professors.get(professorCounter).IsStunned() == 0){
                Room newRoom = this.neighbours.get(new Random().nextInt(this.neighbours.size()));
                while(!(professors.get(professorCounter).EnterRoom(newRoom)))
                    newRoom = this.neighbours.get(new Random().nextInt(this.neighbours.size()));
            }
            else {
                professorCounter++;
            }
        }
        if(goo == null){
            goo = new Goo(this);
        }
        else{
            goo.ResetVisitorsCount();
        }

        if(gas != null){
            gas = null;
        }
    }
    /**
     * Adds an item to the room for testing purposes.
     *
     * @param type The type of the item.
     * @param name The name of the item.
     * @param real Indicates whether the item is real.
     */
    public void AddItem_Test(String type, String name,boolean real)
    {
        switch (type) {
            case "FFP2Mask" -> items.add(real ? new FFP2Mask(name) : new FakeItem(name, "FFP2Mask"));
            case "Airfreshener" -> items.add(real ? new Airfreshener(name) : new FakeItem(name, "Airfreshener"));
            case "Camembert" -> items.add(real ? new Camembert(name) : new FakeItem(name, "Camambert"));
            case "SlideRule" -> items.add(real ? new SlideRule(name) : new FakeItem(name, "SlideRule"));
            case "StBeerCups" -> items.add(real ? new StBeerCups(name) : new FakeItem(name, "StBeerCups"));
            case "Transistor" -> items.add(real ? new Transistor(name, this) : new FakeItem(name,"Transistor"));
            case "TVSZ" -> items.add(real ? new TVSZ(name) : new FakeItem(name, "TVSZ"));
            case "WetRag" -> items.add(real ? new WetRag(name) : new FakeItem(name, "WetRag"));
            default -> {
                return;
            }
        }
        String msg = (real ? "Normal" : "Fake") + " Item " + type +" "+ name + " has been added to Room " + this.name;
        resultLogger.log(Level.INFO, msg);
    }
    /**
     * Changes the room based on the given type for testing purposes.
     *
     * @param type The type of change to apply.
     */
    public void Change_Test(String type) {
        switch (type) {
            case "Curse" -> Curse(chart.GetAllRooms());
            case "Split" -> Split();
            default -> {
            }
        }
    }
    /**
     * Initiates combat for all professors in the room for testing purposes.
     */
    public void CombatRoom_Test(){
        for (Professor prof : this.professors) {
            prof.Combat();
        }
    }
    /**
     * Initiates the goo glue effect for testing purposes.
     */
    public void GooGlue_Test() {
        goo.Activate_Test();
        resultLogger.log(Level.INFO, "Goo activated in Room " + this.name + ", items glued");
    }
    /**
     * Resets the goo visitor count for testing purposes.
     */
    public void GooUnglue_Test() {
        goo.ResetVisitorsCount();
        resultLogger.log(Level.INFO, "Goo deactivated in Room " + this.name + ", items unglued");
    }
    /**
     * Logs information about the room and its contents for testing purposes.
     */
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "Room "+name);
        for (Item item : items) {
            item.InfoAll_Test();
        }
        for (Professor professor : professors) {
            professor.InfoAll_Test();
        }
        for (Student student : students) {
            student.InfoAll_Test();
        }
        for (Cleaner cleaner : cleaners) {
            cleaner.InfoAll_Test();
        }

    }
    /**
     * Logs basic information about the room for testing purposes.
     */
    public void Info_Test() {
        resultLogger.log(Level.INFO, name+".neighbours "+neighbours.size());
        for (Room room : neighbours) {
            resultLogger.log(Level.INFO, "Room "+room.name);
        }
        resultLogger.log(Level.INFO, name+".items "+items.size());
        for (Item item : items) {
            resultLogger.log(Level.INFO, "Item "+item.GetName());
        }
        resultLogger.log(Level.INFO, name+".professors "+professors.size());
        for (Professor professor : professors) {
            resultLogger.log(Level.INFO, "Professor "+professor.GetName());
        }
        resultLogger.log(Level.INFO, name+".students "+students.size());
        for (Student student : students) {
            resultLogger.log(Level.INFO, "Student "+student.GetName());
        }
        resultLogger.log(Level.INFO, name+".cleaners "+cleaners.size());
        for (Cleaner cleaner : cleaners) {
            resultLogger.log(Level.INFO, "Cleaner "+cleaner.GetName());
        }
        resultLogger.log(Level.INFO, name+".gas : "+ ((gas == null) ? "NULL" : "notnull"));
    }
    /**
     * Resets the room and its contents for testing purposes.
     */
    public void Reset_Test() {
        for (Item item : items) {
            item.Reset_Test();
        }
        for (Professor professor : professors) {
            professor.Reset_Test();
        }
        for (Student student : students) {
            student.Reset_Test();
        }
        for (Cleaner cleaner : cleaners) {
            cleaner.Reset_Test();
        }
        resultLogger.log(Level.INFO, "The game has been reset");
    }
}