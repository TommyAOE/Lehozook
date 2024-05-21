package App.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;

import App.Model.Items.*;

import static App.Program.resultLogger;

/**
 * Represents a room in the game.
 */
public class Room {

    private Random rand = new Random();
    private String name;
    private int maximumcapacity;
    public int characterCount;
    private ArrayList<Room> neighbours;
    private ArrayList<Room> cursedNeighbours = new ArrayList<>();
    private boolean isCursed;
    private ArrayList<Item> items;
    private ArrayList<Professor> professors;
    private ArrayList<Student> students;
    private ArrayList<Cleaner> cleaners;
    private Gas gas;
    private Goo goo;
    private Chart chart;
    private boolean isFull;
    
    private String[] itemNames = {
        "FFP2Mask",
        "Airfreshener",
        "Camembert",
        "StBeerCups",
        "Transistor",
        "TVSZ",
        "WetRag"
    };
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public void AddPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public void RemovePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Constructs a room with the specified name, type, and chart.
     *
     * @param name  The name of the room.
     * @param type  The type of the room.
     * @param chart The chart containing the room.
     */
    public Room(String name, String type, Chart chart) {
        this.name = name;
        this.isCursed= Objects.equals(type, "cursed");
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
        this.maximumcapacity = rand.nextInt(7) + 1;
        this.isFull = false;
        this.isCursed = false;
    }
    /**
     * Return the name of the room.
     * @return name.
     */
    public String GetName() {
        return name;
    }
    /** 
     * Gets the professors in the room.
     */
    public List<Professor> GetProfessors() {
        return professors;
    }
    /** 
     * Gets the students in the room.
     */
    public List<Student> GetStudents() {
        return students;
    }
    /** 
     * Gets the cleaners in the room.
     */
    public List<Cleaner> GetCleaners() {
        return cleaners;
    }
    /**
     * Checks if the room is full.
     * @return true if the room is full, false otherwise.
     */
    public boolean IsFull() {
        return isFull;
    }
    /**
     * Checks if the room is cursed.
     * @return true if the room is cursed, false otherwise.
     */
    public boolean IsCursed() {
        return isCursed;
    }
    /**
     * Sets the maximum capacity of the room.
     *@param maximum The maximum capacity to set for the room.
     */
    public void SetCapacity(int maximum) {
        maximumcapacity = maximum;
    }
    /** 
     * Gets the neighbors of the room.
     */
    public ArrayList<Room> GetNeighbours() {
        return neighbours;
    }
    /**
     * Checks if the given room is a neighbor of this room.
     * @param r The room to check for neighbor relationship.
     * @return true if the given room is a neighbor, false otherwise.
     */
    private boolean isNeighbour(Room r) {
        boolean isNeighbour = false;
        for(Room current: neighbours){
            if(current == r)
                isNeighbour = true;
        }
        return isNeighbour;
    }
    /**
     * Sets the given room as a neighbour of this room.
     *
     * @param rooms The room to set as a neighbour.
     */
    public void SetNeighboursOneWay(ArrayList<Room> rooms) {
        for (Room r : rooms) {
            if(!isNeighbour(r)){
                neighbours.add(r);
                //resultLogger.log(Level.INFO,"new neighbours: " + name + " " + r.name);
            }
            else{
                //resultLogger.log(Level.INFO,"already neighbours: " + name + " " + r.name);
            }
            //resultLogger.log(Level.INFO, "Room " + r.name + " set as neighbour of Room " + this.name);
        }
    }
    public void SetNeighboursTwoWay(ArrayList<Room> rooms) {
        for (Room r : rooms) {
            if(!isNeighbour(r)){
                neighbours.add(r);
                r.SetNeighboursOneWay(new ArrayList<Room>(Collections.singletonList(this)));
                //resultLogger.log(Level.INFO,"new neighbours: " + name + " " + r.name);
            }
            else{
                //resultLogger.log(Level.INFO,"already neighbours: " + name + " " + r.name);
            }
            //resultLogger.log(Level.INFO, "Room " + r.name + " set as neighbour of Room " + this.name);
        }
    }
    /** 
     * Adds gas to the room.
     */
    public void AddGas() {
        gas = new Gas(this);
    }
    /** 
     * Signals that the gas in the room has expired.
     */
    public void GasExpired() {
        this.gas=null;
        pcs.firePropertyChange("GasExpired", null, null);
        
    }
    /**
     * Checks if the room has gas.
     * @return true if the room has gas, false otherwise.
     */
    public boolean HasGas() {
        return gas != null;
    }

    /**
     * Checks if the room has goo.
     * @return true if the room has goo, false otherwise.
     */
    public boolean HasGoo() {
        return goo != null;
    }
    /** 
    * Signals that a character has entered the room.
    */
    public void CharacterEntered(Character c) {
        switch (c.GetName().charAt(0)) {
            case 'p' -> {
                professors.add((Professor) c);
                if (!students.isEmpty()) {
                    ((Professor) c).inCombat = true;
                    for (Student s : students) {
                        s.inCombat = true;
                        s.canMove = false;
                    }
                }
                if (this.gas != null)
                    this.gas.Gasify();
            }
            case 's' -> {
                students.add((Student) c);
                pcs.firePropertyChange("StudentEntered", null, c);
                if (this.gas != null)
                    this.gas.Gasify();
                if (!professors.isEmpty()) ((Student) c).inCombat = true;
            }
            case 'c' -> {
                cleaners.add((Cleaner) c);
                this.Clean();
            }
            default -> resultLogger.log(Level.INFO, "Something went wrong with the character");
        }
        characterCount++;
        pcs.firePropertyChange("CharacterMoved", null, this);
    }
     /** 
     * Signals that a character has left the room.
     */
    public void CharacterLeft(Character c) {
        switch (c.GetName().charAt(0)){
            case 'p':
                professors.remove(c);
                break;
            case 's':
                students.remove(c);
                pcs.firePropertyChange("StudentLeft", null, c);
                if(((Student)c).location == null) {
                    int studentCounter = 0;
                    for (Room room : chart.GetAllRooms()) {
                        if(room.GetStudents().size() > 0){
                            studentCounter += room.GetStudents().size();
                        }
                    }
                    if(studentCounter <= 0) {
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
        characterCount--;
        pcs.firePropertyChange("CharacterMoved", null, this);
    }
    /**
     * Cleans the room by resetting the visitor count.
     */
    public void Clean() {
        int studentCounter = 0;
        while(students.size() > studentCounter) {
            if(students.get(studentCounter).IsStunned() == 0) {
                Room newRoom = this.neighbours.get(rand.nextInt(this.neighbours.size()));
                while(!(students.get(studentCounter).EnterRoom(newRoom)))
                    newRoom = this.neighbours.get(rand.nextInt(this.neighbours.size()));
            } else {
                studentCounter++;
            }
        }
        int professorCounter = 0;
        while(professors.size() > professorCounter) {
            if(professors.get(professorCounter).IsStunned() == 0) {
                Room newRoom = this.neighbours.get(rand.nextInt(this.neighbours.size()));
                while(!(professors.get(professorCounter).EnterRoom(newRoom)))
                    newRoom = this.neighbours.get(rand.nextInt(this.neighbours.size()));
            } else {
                professorCounter++;
            }
        }
        if(goo == null) {
            goo = new Goo(this);
        } else {
            goo.ResetVisitorsCount();
        }
        if(gas != null) {
            gas = null;
        }
    }
    /** 
     * Searches for an item in the room.
     */
    public ArrayList<Item> SearchItem() {
        return items;
    }
    /** 
     * Adds an item to the room.
     * @param item
     */
    public void AddItem(Item item) {
        items.add(item);
    }
    /** 
     * Spawns an item to the room randomly.
     */
    public void SpawnItem() {
        String type = itemNames[rand.nextInt(itemNames.length)];
        boolean real = rand.nextInt(100) < 80;
        switch (type) {
            case "FFP2Mask" -> items.add(real ? new FFP2Mask() : new FakeItem("FFP2Mask"));
            case "Airfreshener" -> items.add(real ? new Airfreshener() : new FakeItem("Airfreshener"));
            case "Camembert" -> items.add(real ? new Camembert() : new FakeItem("Camembert"));
            case "SlideRule" -> items.add(real ? new SlideRule() : new FakeItem("SlideRule"));
            case "StBeerCups" -> items.add(real ? new StBeerCups() : new FakeItem("StBeerCups"));
            case "Transistor" -> items.add(real ? new Transistor(this) : new FakeItem("Transistor"));
            case "TVSZ" -> items.add(real ? new TVSZ() : new FakeItem("TVSZ"));
            case "WetRag" -> items.add(real ? new WetRag() : new FakeItem("WetRag"));
        }
    }
    /** 
     * Pops an item from the room.
     */
    public Item PopItem(Item item) {
        Item tempItem = null;
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).equals(item)) {
                if(!items.get(i).IsGlued()) {
                    tempItem = items.get(i);
                    index = i;
                }
            }
        }
        if(index != -1) {
            items.remove(index);
        }
        return tempItem;
    }
    /**
     * Changes the room.
     */
    public void Change(List<Room> rooms) {
        if (isCursed) {
            Curse(rooms);
        } /*else {
            switch (rand.nextInt(2)) {
                case 0 -> Merge(neighbours.get(rand.nextInt(neighbours.size() + 1)));
                case 1 -> Split();
            }
        }*/
    }
    /**
     * Curses the room by modifying its neighbours.
     * @param rooms The list of rooms in the game.
     */
    public void Curse(List<Room> rooms) {
        Room removedNeighbour = neighbours.get(rand.nextInt(neighbours.size()));

        if(!cursedNeighbours.isEmpty()){
            Room addedNeighbour = cursedNeighbours.get(rand.nextInt(cursedNeighbours.size()));
            SetNeighboursOneWay(new ArrayList<>(Collections.singletonList(addedNeighbour)));
            cursedNeighbours.remove(addedNeighbour);
        }
        cursedNeighbours.add(removedNeighbour);
        neighbours.remove(removedNeighbour);

        for (Room room : neighbours) {
            System.out.println(this.name + ", neighbour room " + room.name);
        }   

        pcs.firePropertyChange("Cursed", null, null);

        resultLogger.log(Level.INFO,"Room " + this.name + " cursed");
    }
    /**
     * Splits the room into two rooms.
     */
    public void Split() {
        if(professors.size()+ students.size()+cleaners.size()>0) {
            resultLogger.log(Level.INFO,"Split is impossible, characters exist in the Room");
            return;
        }
        Room newRoom = new Room("Whatever");
        newRoom.maximumcapacity=this.maximumcapacity/2+1;
        ArrayList<Room> rooms = new ArrayList<>();
        for(int i = neighbours.size() / 2; i < neighbours.size(); i++) {
            rooms.add(neighbours.get(i));
        }
        newRoom.SetNeighboursOneWay(rooms);
        for (Room roomsOfNew : newRoom.neighbours) {
            neighbours.remove(roomsOfNew);
            try {
                roomsOfNew.neighbours.remove(this);
            } catch (Exception e){}
        }
        for(int i=items.size()/2;i<items.size();i++) {
            newRoom.AddItem(items.get(i));
        }
        for (Item itemsOfNew:newRoom.items) {
            items.remove(itemsOfNew);
        }
        SetNeighboursTwoWay(new ArrayList<Room>(Collections.singletonList(newRoom)));
        chart.AddRoom(newRoom);
        resultLogger.log(Level.INFO,"Split");

    }
    /**
     * Merges this room with the given room.
     * @param r The room to merge with.
     */
    public void Merge(Room r) {
        if(r.maximumcapacity<maximumcapacity) {
            for(Item itemsOfSmaller : r.items) {
                items.add(itemsOfSmaller);
            }
            for(Room neighboursOfSmaller : r.neighbours) {
                SetNeighboursTwoWay(r.neighbours);
                neighboursOfSmaller.neighbours.remove(r);
            }
            chart.RemoveRoom(r);
        } else {
            for(Item itemsOfSmaller : this.items) {
                r.items.add(itemsOfSmaller);
            }
            for(Room neighboursOfSmaller : this.neighbours) {
                SetNeighboursTwoWay(this.neighbours);
                neighboursOfSmaller.neighbours.remove(this);
            }
            chart.RemoveRoom(this);
        }
        resultLogger.log(Level.INFO,"Room merged");
    }

    //------------------Functions for testing------------------
    /**
     * Adds an item to the room for testing purposes.
     *
     * @param type The type of the item.
     * @param name The name of the item.
     * @param real Indicates whether the item is real.
     */
    public void AddItem_Test(String type, String name,boolean real) {
        switch (type) {
            case "FFP2Mask" -> items.add(real ? new FFP2Mask(name) : new FakeItem(name, "FFP2Mask"));
            case "Airfreshener" -> items.add(real ? new Airfreshener(name) : new FakeItem(name, "Airfreshener"));
            case "Camembert" -> items.add(real ? new Camembert(name) : new FakeItem(name, "Camambert"));
            case "SlideRule" -> items.add(real ? new SlideRule(name) : new FakeItem(name, "SlideRule"));
            case "StBeerCups" -> items.add(real ? new StBeerCups(name) : new FakeItem(name, "StBeerCups"));
            case "Transistor" -> items.add(real ? new Transistor(name, this) : new FakeItem(name,"Transistor"));
            case "TVSZ" -> items.add(real ? new TVSZ(name) : new FakeItem(name, "TVSZ"));
            case "WetRag" -> items.add(real ? new WetRag(name) : new FakeItem(name, "WetRag"));
        }
        String msg = (real ? "Normal" : "Fake") + " Item " + type +" "+ name + " has been added to Room " + this.name;
        resultLogger.log(Level.INFO, msg);
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
     * Initiates combat for all professors in the room for testing purposes.
     */
    public void CombatRoom_Test() {
        for (Professor prof : this.professors) {
            prof.Combat();
        }
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
        }
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
            resultLogger.log(Level.INFO, room.name);
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