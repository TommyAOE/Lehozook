package App;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import App.Items.*;

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
    boolean isFull;
    //////////////


    public Room(String name, String type) {
        this.name = name;
        this.isCursed= Objects.equals(type, "Cursed");
        this.neighbours = new ArrayList<Room>();
        this.items = new ArrayList<Item>();
        this.professors = new ArrayList<Professor>();
        this.students = new ArrayList<Student>();
        this.cleaners = new ArrayList<Cleaner>();
        this.isFull = false;
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
    }
    //szobaval kapcsolatos metodusok
    public void Curse()
    {
        System.out.println("Szoba:Curse()");
    }
    public void Split()
    {
        System.out.println("Szoba:Split");
    }
    public void Merge(Room r)
    {
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
    public void Change()
    {
        if (isCursed)
        {
            Curse();
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
    public Item PopItem(Item i)
    {
        System.out.println("App.Room: PopItem()");
        Item seged = null;
        for (Item item : items) 
        {
            if(item.equals(i))
            {
                seged=item;
                items.remove(item);
            }
        }
            return seged;

        
    }
    //////////////////////////////////

    //karakterekkel kapcsolatos

    /** 
     * Gets the professors in the room.
     */
    public ArrayList<Professor> GetProfessors()
    {
        System.out.println("App.Room: GetProfessors()");
        return professors;

    }

    /** 
     * Gets the students in the room.
     */
    public ArrayList<Student> GetStudents()
    {
        System.out.println("App.Room: GetStudents()");
        return students;

    }

    public void GetCleaners()
    {
        System.out.println("App.Room: GetCleaners()");
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
        System.out.println("App.Room: AddGas()");
    }

    /** 
     * Signals that the gas in the room has expired.
     */
    public void GasExpired()
    {
        System.out.println("App.Room: GasExpired()");
    }

    public boolean HasGas()//boolean lesz
    {
        return gas != null;
    }

    public void Clean()
    {
        System.out.println("App.Room:Clean()");
    }

    public void AddItem_Test(String type,String name,boolean real)
    {
        switch (type) {
            case "FFP2Mask" -> items.add(real ? new FFP2Mask(name) : new FakeItem(name, "FFP2Mask"));
            case "Airfreshener" -> items.add(real ? new Airfreshener(name) : new FakeItem(name, "Airfreshener"));
            case "Camembert" -> items.add(real ? new Camembert(name) : new FakeItem(name, "Camambert"));
            case "SlideRule" -> items.add(real ? new SlideRule(name) : new FakeItem(name, "SlideRule"));
            case "StBeerCups" -> items.add(real ? new StBeerCups(name) : new FakeItem(name, "StBeerCups"));
            case "Transistor" -> items.add(real ? new Transistor(name) : new FakeItem(name, "Transistor"));
            case "TVSZ" -> items.add(real ? new TVSZ(name) : new FakeItem(name, "TVSZ"));
            case "WetRag" -> items.add(real ? new WetRag(name) : new FakeItem(name, "WetRag"));
            default -> {
            }
        }
    }

    public void Change_Test(String type) {
        switch (type) {
            case "Curse" -> Curse();
            case "Split" -> Split();
            default -> {
            }
        }
    }

    public void GooGlue_Test() {
        goo.Activate_Test();
    }

    public void GooUnglue_Test() {
        goo.Deactivate_Test();
    }

    public void InfoAll_Test() {
        System.out.println("Room: "+name);
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
        System.out.println("Room: "+name);
        System.out.println("Neighbours: "+neighbours.size());
        for (Room room : neighbours) {
            System.out.println("Room: "+room.name);
        }
        System.out.println("Items: "+items.size());
        for (Item item : items) {
            System.out.println("Item: "+item.GetName());
        }
        System.out.println("Professors: "+professors.size());
        for (Professor professor : professors) {
            System.out.println("Professor: "+professor.GetName());
        }
        System.out.println("Students: "+students.size());
        for (Student student : students) {
            System.out.println("Student: "+student.GetName());
        }
        System.out.println("Cleaners: "+cleaners.size());
        for (Cleaner cleaner : cleaners) {
            System.out.println("Cleaner: "+cleaner.GetName());
    }
    }

    ///////    
}
package App;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import App.Items.Item;

/**
 * Represents a room in the game.
 */
public class Room {

    //attributomok
    int maximumcapacity;
    int characterCount;
    List<Room> neighbours;
    boolean isCursed;
    ArrayList<Item> items;
    List<Professor> professors;
    List<Student> students;
    List<Cleaner> cleaners;
    Gas gas;
    Goo goo;
    boolean isFull;
    //////////////
    
    /** The ID of the room. */
    public int id;
//szobaval kapcsolatos metodusok
    public void Curse()
    {
        System.out.println("Szoba:Curse()");
    }
    public void split()
    {
        System.out.println("Szoba:Split");
    }
    public void Merge(Room r)
    {
        System.out.println("Szoba:"+r+"osszeolvadt");
    }
    /** 
     * Gets the neighbors of the room.
     */
    public void GetNeighbours()
    {
        
        System.out.println("Room: GetNeighbours()");
        
    }
    /** 
     * Sets the neighbors of the room.
     */
    public void SetNeighbours()
    {
        System.out.println("Room: SetNeighbours()");
    }
    /** 
     * Changes the room.
     */
    public void Change()
    {
        System.out.println("Room: Change()");
    }
    //////////////////////////////////


    //Itemekkel kapcsolatos fuggvenyek

    public List<Item> GetItems()//szuksegem volt ra a gooban, visszaadja az adott szobaban levo itemeket
    {
        return items;
    }
    /** 
     * Searches for an item in the room.
     */
    public void SearchItem()
    {
        System.out.println("Room: SearchItem()");
    }

    /** 
     * Adds an item to the room.
     */
    public void AddItem()
    {
        System.out.println("Room: AddItem()");
    }

    /** 
     * Pops an item from the room.
     */
    public void PopItem()
    {
        System.out.println("Room: PopItem()");
       /*  Item seged;
        for (Item item : items) 
        {
            if(item.equals(i))
            {
                seged=item;
                items.remove(item);
            }
        }
            return seged;*/
        
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
    public void CharacterEntered()
    {
        System.out.println("Room: CharacterEntered()");
    }

     /** 
     * Signals that a character has left the room.
     */
    public void CharacterLeft()
    {
        System.out.println("Room: CharacterLeft()");
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
        return (gas==null)? false:true;
    }

    public void Clean()
    {
        System.out.println("Room:Clean()");
    }
    ///////    
}
