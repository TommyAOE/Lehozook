package App;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import App.Items.*;

/**
 * Represents a student character in the game.
 */
public class Student extends Character implements IFighter {

    /**
     * The list of items the student possesses.
     */
    ArrayList<Item> items;

    /**
     * Indicates whether the student is in combat.
     */
    //new
    boolean inCombat;

    public void SetIsProtected(int isProtected) {
        this.isProtected += isProtected;
    }

    /**
     * The protection state of the student.
     */
    int isProtected;

    /**
     * The stun duration of the student.
     */
    int isStunned;


    /**
     * Constructs a new student character with the specified name and initial location.
     *
     * @param name     the name of the student character
     * @param location the initial location of the student character
     */

    public Student(String name, Room location){
        super(name, location);
        isProtected = 0;
        isStunned = 0;
        items = new ArrayList<>();
    }

    public ArrayList<Item> GetItems(){
        return items;
    }

    public void DropItem(Item item){
        if(items.contains(item)){
            items.remove(item);
            location.AddItem(item);
            if(item.GetType() == "Transistor"){
                ((Transistor)item).location = location;
            }
        }
    }
    /**
     * Performs the student's turn actions. During the turn, the student can perform up to three actions
     * unless they are in combat. The available actions include moving to another room,
     * picking up an item, dropping an item, or using an item. The method prompts the user for input
     * to select the desired action and executes the corresponding functionality.
     *
     * If the student is in combat, no actions are performed during the turn.
     */

    @Override
    public void Turn() {
        if (--isStunned > 0) {
            return;
        }
        for (int i = 0; i < 3&&!inCombat; i++) {
            System.out.println("Add meg az akciódat: ");
            System.out.println("1. Mozgás");
            System.out.println("2. Tárgy felvétele");
            System.out.println("3. Tárgy eldobása");
            System.out.println("4. Tárgy használata");
            int action = new Scanner(System.in).nextInt();
            switch (action) {
                case 1 -> {
                    System.out.println("Add meg a szobaszámot(" + 0 + "-" + location.neighbours.size() + "): ");
                    int room = new Scanner(System.in).nextInt();
                    if(!EnterRoom(location.neighbours.get(room))){
                        System.out.println("A szoba tele van!");
                        i--;
                    }
                }
                case 2 -> {
                    System.out.println("Add meg a tárgy számát: ");
                    for (int j = 0; j < location.SearchItem().size(); j++) {
                        System.out.println(j + ". " + location.SearchItem().get(j).GetType());
                    }
                    int item = Integer.parseInt(new Scanner(System.in).nextLine());
                    location.PopItem(location.SearchItem().get(item));
                }
                case 3 -> {
                    System.out.println("Add meg a tárgy számát: ");
                    for (int j = 0; j < items.size(); j++) {
                        System.out.println(j + ". " + items.get(j).GetType());
                    }
                    int item2 = Integer.parseInt(new Scanner(System.in).nextLine());
                    location.AddItem(items.get(item2));
                }
                case 4 -> {
                    System.out.println("Add meg a tárgy számát: ");
                    for (int j = 0; j < items.size(); j++) {
                        System.out.println(j + ". " + items.get(j).GetType());
                    }
                    int item3 = Integer.parseInt(new Scanner(System.in).nextLine());
                    if (items.get(item3).GetType()=="Transistor"){
                        System.out.println("Add meg, az akciód: ");
                        System.out.println("1. Aktiválás");
                        System.out.println("2. Használat");
                        int action2 = Integer.parseInt(new Scanner(System.in).nextLine());
                        switch (action2){
                            case 1 -> (items.get(item3)).ApplyEffect();
                            case 2 -> ((Transistor) items.get(item3)).Activate();
                            default -> {
                                System.out.println("Nem megfelelő számot adtál meg!");
                                i--;
                            }
                        }
                    }else
                    items.get(item3).ApplyEffect();
                }
                default -> {
                    System.out.println("Nem megfelelő számot adtál meg!");
                    i--;
                }
            }
        }

        System.out.println("App.Student: Turn()");
    }
    /**
     * Moves the student to the specified room. Before moving, the student is removed from their current location
     * and added to the new room. If the new room contains professors, indicating combat,
     * the student's combat state is set to true.
     *
     * @param r the room to enter
     */
    @Override
    public boolean EnterRoom(Room r) {
        if (r.isFull){
            System.out.println("A szoba tele van!");
            return false;
        }
        location.CharacterLeft(this);
        r.CharacterEntered(this);
        location=r;
        if (!location.GetProfessors().isEmpty()){
            inCombat = true;
        }
        return true;
    }

    @Override
    public void InfoAll_Test() {
        System.out.println("Student: "+name);
    }

    @Override
    public void Info_Test() {
        System.out.println("Student: "+name);
        System.out.println("Location: "+location.name);
        System.out.println("Items: "+items.size());
        System.out.println("In combat: "+inCombat);
        System.out.println("Protected: "+isProtected);
        System.out.println("Stunned: "+isStunned);
    }

    /**
     * Attempts to stun the student for a specified duration.
     * If the student has an FFP2Mask item in their inventory,
     * it checks if the mask is functional. If the mask is functional,
     * it applies its effect and returns false, indicating that the stun attempt failed.
     * If the mask is broken or not present, it sets the student's stun state, applies the mask's effect,
     * and returns true, indicating that the stun attempt succeeded.
     *
     * @param stunDuration the duration of the stun effect
     * @return true if the student is successfully stunned, false otherwise
     */
    @Override
    public boolean Stun(int stunDuration) {
        boolean stun = false;
        for (Item i :items
                ) {
            if(i.GetType().equals("FFP2Mask"))
            {
                FFP2Mask mask = (FFP2Mask)i;
                if (mask.GetCounter() > 0)
                {
                    mask.ApplyEffect();
                    stun= false;
                }else{
                    if (mask.IsBroken())
                    {
                        items.remove(i);
                        this.isStunned+=stunDuration;
                        return true;
                    }
                    isStunned = 1;
                    mask.ApplyEffect();
                    stun= true;
                    isStunned+=stunDuration;
                }
            }
        }
        System.out.println("App.Student: Stun()");
        return stun;

    }

    /**
     * Initiates combat for the student. If the student has no items in their inventory,
     * it prints a message indicating the lack of usable items and returns.
     *
     * If the student has items in their inventory, they are prompted to select an item to use during combat.
     * The method loops until a valid item selection is made.
     *
     * Once a valid item is selected, its effect is applied, and the combat ends.
     */
    @Override
    public void Combat() {
        if (items.size() == 0) {
            System.out.println("Nincs tárgyad, amit használhatsz!");
            return;
        }
        while (true) {
            System.out.println("Válassz egy itemet, amit használni szeretnél:");
            for (int i = 0; i < items.size(); i++) {

                System.out.println(i + "" + items.get(i).GetType());
            }
            int in = Integer.parseInt(new Scanner(System.in).nextLine());
            if (in < items.size() && in >= 0) {
                items.get(in).ApplyEffect();
                break;
            } else {
                System.out.println("Nem megfelelő számot adtál meg");
            }
        }
        System.out.println("App.Student: Combat()");

    }
    /**
     * Returns the current stun state of the student.
     *
     * @return the stun state of the student
     */
    @Override
    public int IsStunned() {
        return isStunned;
    }

    /**
     * Custom method to handle student's death.
     */
    public void Death(){
        System.out.println("App.Student: Death()");
        if (--isProtected==-1){
            location.CharacterLeft(this);
        }
    }

    /**
     * Returns the location of the student.
     *
     * @return the location of the student
     */
    public Room GetLocation()
    {
        return location;
    }
    /**
     * Simulates the effect of being drunk by randomly removing an item from the student's inventory
     * and adding it to the current location.
     */
    public void Drunk()
    {
        location.AddItem(items.remove(new Random().nextInt(items.size()+1)));
    }
    /**
     * Returns the current stun state of the student.
     *
     * @return the stun state of the student
     */
    public int isStunned()
    {
        return isStunned;
    }
    /**
     * Adds an item to the student's inventory.
     *
     * @param name the name of the item
     * @param type the type of the item
     * @param real indicates whether the item is real or fake
     */
    public void AddItem_Test(String type ,String name ,boolean real)
    {
        switch (type) {
            case "FFP2Mask":
                items.add(real ? new FFP2Mask(name, this) : new FakeItem(name, "FFP2Mask", this));
                break;
            case "Airfreshener":
                items.add(real ? new Airfreshener(name, this) : new FakeItem(name, "Airfreshener", this));
                break;
            case "Camembert":
                items.add(real ? new Camembert(name, this) : new FakeItem(name, "Camambert", this));
                break;
            case "SlideRule":
                items.add(real ? new SlideRule(name, this) : new FakeItem(name, "SlideRule", this));
                break;
            case "StBeerCups":
                items.add(real ? new StBeerCups(name, this) : new FakeItem(name, "StBeerCups", this));
                break;
            case "Transistor":

                Transistor newTransistor = null;
                if(real){
                    newTransistor = new Transistor(name, this);
                }else{
                    items.add(new FakeItem(name, "Transistor", this));
                }
                for(Item item : items){
                    if(item.GetType().equals("Transistor")){
                        Transistor temp = (Transistor)item;
                        if(!temp.HasPair()){
                            if(newTransistor != null){
                                newTransistor.SetPair((Transistor)item);
                            }
                        }
                    }
                }
                if(newTransistor != null)   items.add(newTransistor);
                break;
            case "TVSZ":
                items.add(real ? new TVSZ(name) : new FakeItem(name, "TVSZ"));
                break;
            case "WetRag":
                items.add(real ? new WetRag(name) : new FakeItem(name, "WetRag"));
                break;
            default:
                break;
        }
        
    }
    public void DropItem_Test(String name)
    {
        for (Item i:items
             ) {
            if (i.GetName().equals(name))
            {
                location.AddItem(i);
                items.remove(i);
                break;
            }
        }
    }
    public void UseItem_Test(String name)
    {
        for (Item i:items
        ) {
            if (i.GetName().equals(name))
            {
                i.ApplyEffect();
                break;
            }
        }
    }
    public void PickupItem_Test(String name)
    {
        for (Item i:location.SearchItem()
        ) {
            if (i.GetName().equals(name))
            {
                items.add(location.PopItem(i));
                break;
            }
        }
    }
}
