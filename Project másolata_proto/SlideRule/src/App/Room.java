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

    //attributomok
    Random rand = new Random();
    String name;
    int maximumcapacity;
    int characterCount;
    public ArrayList<Room> neighbours;
    boolean isCursed;
    ArrayList<Item> items;
    ArrayList<Professor> professors;
    ArrayList<Student> students;
    ArrayList<Cleaner> cleaners;
    Gas gas;
    Goo goo;
    Chart chart;

    public boolean IsFull() {
        return isFull;
    }

    boolean isFull;
    //////////////


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
    public void SetNeighbours(Room r){
        neighbours.add(r);
        resultLogger.log(Level.INFO, r.name+"szoba hozzáadva szomédkéént a "+name+" szobához");
    }
    //szobaval kapcsolatos metodusok
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
        System.out.println("Szoba:Curse() \n "+curRoom+"added, "+delRoom+"deleted");
    }
    public void Split()
    {
        if(professors.size()+ students.size()+cleaners.size()>0){
            System.out.println("Szoba: Split is impossible");
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
        System.out.println("Szoba:Split");

    }
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
        System.out.println("Szoba:"+r+"osszeolvadt");
    }
    /** 
     * Gets the neighbors of the room.
     */
    public ArrayList<Room> GetNeighbours()
    {
        System.out.println("App.Room: GetNeighbours()");
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

        System.out.println("App.Room: Change()");
    }
    //////////////////////////////////

    //Itemekkel kapcsolatos fuggvenyek

    /** 
     * Searches for an item in the room.
     */
    public ArrayList<Item> SearchItem()
    {
        System.out.println("App.Room: SearchItem()");

        return items;
    }

    /** 
     * Adds an item to the room.
     * @param item
     */
    public void AddItem(Item item)
    {
        items.add(item);
        System.out.println("App.Room: AddItem()");
    }

    /** 
     * Pops an item from the room.
     */
    public Item PopItem(Item item)
    {
        System.out.println("App.Room: PopItem()");
        Item seged = null;
        for (int i = 0; i < items.size(); i++) 
        {
            if(items.get(i).equals(item))
            {
                if(!items.get(i).IsGlued()){
                    seged = items.get(i);
                    items.remove(i);
                    System.out.println("Item picked up");
                }else{
                    System.out.println("Could not pick up Item" + item.GetName());
                }
            }
        }
        return seged;
        
    }
    //////////////////////////////////

    //karakterekkel kapcsolatos

    /** 
     * Gets the professors in the room.
     */
    public List<Professor> GetProfessors()//visszaadja a professzorokat az adott szobaban
    {
        System.out.println("Room: GetProfessors()");
        return professors;
    }

    /** 
     * Gets the students in the room.
     */
    public List<Student> GetStudents()//visszaadja a tanulokat az adott szobaban
    {
        System.out.println("Room: GetStudents()");
        return students;
    }

    public List<Cleaner> GetCleaners()
    {
        System.out.println("Room: GetCleaners()");
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
                break;
            case 's':
                students.add((Student)c);
                break;
            case 'c':
                cleaners.add((Cleaner)c);
                break;
            default:
                System.out.println("Valami nem okés a karakterrel");
                break;
        }
        System.out.println("App.Room: CharacterEntered()");

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
                break;
            case 'c':
                cleaners.remove(c);
                break;
            default:
                System.out.println("Nem talalhato karakter a szobaban");
                break;
        }
    }

    ///////////////////////////

    //egyeb

    /** 
     * Adds gas to the room.
     */
    public void AddGas()
    {
        gas=new Gas(this);
        System.out.println("Room: AddGas()");
    }

    /** 
     * Signals that the gas in the room has expired.
     */
    public void GasExpired()//
    {
        System.out.println("Room: GasExpired()");
        this.gas=null;
    }

    public boolean HasGas()//boolean lesz
    {
        return gas != null;
    }

    public void Clean()
    {
        if(goo == null){
            goo = new Goo(this);
        }
        else{
            goo.ResetVisitorsCount();
        }
        System.out.println("App.Room:Clean()");
    }

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
        resultLogger.log(Level.INFO, name+"-es item hozzáadva a "+this.name+" -es szobához");
    }

    public void Change_Test(String type) {
        switch (type) {
            case "Curse" -> Curse(chart.GetAllRooms());
            case "Split" -> Split();
            default -> {
            }
        }
    }
    public void CombatRoom_Test(){
        for (Professor prof : this.professors) {
            prof.Combat();
        }
    }


    public void GooGlue_Test() {
        goo.Activate_Test();
    }

    public void GooUnglue_Test() {
        goo.ResetVisitorsCount();
    }

    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "Room "+name);
        //System.out.println("Room "+name);
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

    public void Info_Test() {
        //System.out.println("Room: "+name);
        resultLogger.log(Level.INFO, name+".neighbours "+neighbours.size());
        //System.out.println("Neighbours: "+neighbours.size());
        for (Room room : neighbours) {
            resultLogger.log(Level.INFO, "Room "+room.name);
            //System.out.println("Room: "+room.name);
        }
        resultLogger.log(Level.INFO, name+".items "+items.size());
        //System.out.println("Items: "+items.size());
        for (Item item : items) {
            resultLogger.log(Level.INFO, "Item "+item.GetName());
            //System.out.println("Item: "+item.GetName());
        }
        resultLogger.log(Level.INFO, name+".professors "+professors.size());
        //System.out.println("Professors: "+professors.size());
        for (Professor professor : professors) {
            resultLogger.log(Level.INFO, "Professor "+professor.GetName());
            //System.out.println("Professor: "+professor.GetName());
        }
        resultLogger.log(Level.INFO, name+".students "+students.size());
        //System.out.println("Students: "+students.size());
        for (Student student : students) {
            resultLogger.log(Level.INFO, "Student "+student.GetName());
            //System.out.println("Student: "+student.GetName());
        }
        resultLogger.log(Level.INFO, name+".cleaners "+cleaners.size());
        //System.out.println("Cleaners: "+cleaners.size());
        for (Cleaner cleaner : cleaners) {
            resultLogger.log(Level.INFO, "Cleaner "+cleaner.GetName());
            //System.out.println("Cleaner: "+cleaner.GetName());
        }
    }

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
        resultLogger.log(Level.INFO, "A játék alaphelyzetbe állítva");
    }
}