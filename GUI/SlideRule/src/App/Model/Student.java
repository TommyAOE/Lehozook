package App.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

import static App.Program.resultLogger;
import App.Model.Items.*;
import App.View.GameView;
import App.Model.*;
import App.*;


/**
 * Represents a student character in the game.
 */
public class Student extends Character implements IFighter {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public void AddPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public void RemovePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    /**
     * The list of items the student possesses.
     */
    ArrayList<Item> items;

    /**
     * Indicates whether the student is in combat.
     */
    //new
    public boolean inCombat;
    public boolean canMove;

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
        super(name);
        EnterRoom(location);
        isProtected = 0;
        isStunned = 0;
        items = new ArrayList<>();
        canMove = true;
        inCombat = false;
    }
    /**
     * Retrieves a list of items in the room.
     *
     * @return The list of items in the room.
     */
    public ArrayList<Item> GetItems(){
        return items;
    }
    /**
     * Drops an item in the room.
     *
     * @param item The item to drop.
     */
    public void DropItem(Item item){
        if(isStunned > 0){
            GameView.info.append("Invalid operation, you are stunned" + "\n");
            return;
        }   
        if(items.contains(item)){
            items.remove(item);
            location.AddItem(item);

            if(item.GetType() == "Transistor"){
                ((Transistor)item).location = location;
            }
            pcs.firePropertyChange("BackpackChanged", null, item);
        }
    }
    /**
     * Removes an item from the room.
     *
     * @param item The item to remove.
     */
    public void RemoveItem(Item item){
        if(items.contains(item)){
            items.remove(item);
            pcs.firePropertyChange("BackpackChanged", null, item);
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
    public void Turn() {}
    /**
     * Moves the student to the specified room. Before moving, the student is removed from their current location
     * and added to the new room. If the new room contains professors, indicating combat,
     * the student's combat state is set to true.
     *
     * @param r the room to enter
     */
    @Override
    public boolean EnterRoom(Room r) {
        if (r.IsFull()){
            resultLogger.log(Level.INFO, "Room "+r.GetName()+" is full");
            GameView.info.append("Room "+r.GetName()+" is full \n");
            return false;
        }
        //May cause problems with using Transistor
        if(location != null && !this.location.GetNeighbours().contains(r)){
            resultLogger.log(Level.INFO, "Room "+r.GetName()+" is not neighbour of the character's current room");
            GameView.info.append("Room "+r.GetName()+" is not neighbour of the character's current room");
            return false;
        }
        if(location != null)
            location.CharacterLeft(this);
        r.CharacterEntered(this);
        location=r;
        resultLogger.log(Level.INFO, "Character "+ name + " has entered Room " + r.GetName());
        GameView.info.append("Character "+ name + " has entered Room " + r.GetName() + "\n");
        if (!location.GetProfessors().isEmpty()){
            inCombat = true;
            for(Professor p: location.GetProfessors()){
                if(p.isStunned == 0)
                    p.inCombat = true;
            }
        }
        canMove = false;
        pcs.firePropertyChange("RoomChanged", null, null);
        return true;
    }

    public boolean TravelWithTransistor(Room r) {
        if (r.IsFull()){
            resultLogger.log(Level.INFO, "Room "+r.GetName()+" is full");
            GameView.info.append("Room "+r.GetName()+" is full" + "\n");
            return false;
        }
        if(location != null){
            location.CharacterLeft(this);
        }

        r.CharacterEntered(this);
        location=r;
        resultLogger.log(Level.INFO, "Character "+ name + " has entered Room " + r.GetName());
        GameView.info.append("Character "+ name + " has entered Room " + r.GetName() + "\n");
        if (!location.GetProfessors().isEmpty()){
            inCombat = true;
            for(Professor p: location.GetProfessors()){
                if(p.isStunned == 0)
                    p.inCombat = true;
            }
        }
        return true;
    }

    @Override
    /**
     * Logs information about the student.
     */
    public void InfoAll_Test() {
        resultLogger.log(Level.INFO, "Student: "+name);
    }

    @Override
    /**
     * Logs detailed information about the student, including location, items, combat status, protection status, and stun status.
     */
    public void Info_Test() {
        resultLogger.log(Level.INFO, name+".location : "+location.GetName());
        resultLogger.log(Level.INFO, name+".items : "+items.size());
        resultLogger.log(Level.INFO, name+".inCombat : "+inCombat);
        resultLogger.log(Level.INFO, name+".isProtected : "+isProtected);
        resultLogger.log(Level.INFO, name+".isStunned : "+ ((isStunned > 0) ? "positive" : "0"));
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
        boolean hasMask = false;
        if(items == null)   return false;
        for (Item i :items) {
            if(i.GetType().equals("FFP2Mask"))
            {
                FFP2Mask mask = (FFP2Mask)i;
                hasMask = true;
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
        if(!hasMask){
            stun= true;
            GameView.info.append("Player " + this.GetName() + " stunned" + "\n");
            isStunned+=stunDuration;
        }
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
            GameView.info.append("No items to use" + "\n");
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
    public void NextTurn(){
        if (isStunned > 0) isStunned--;
        inCombat = false;
        canMove = true;
    }
    /**
     * Custom method to handle student's death.
     */
    public boolean Death(){
        if (--isProtected==-1){
            Room temp = location;
            location = null;
            temp.CharacterLeft(this);
            GameView.info.append("Student " + name + " died" + "\n");
            return true;
        }
        return false;
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
        if (items.size() == 1){
            Item item = items.remove(0);
            location.AddItem(item);
        }else if (items.size() > 1){
            location.AddItem(items.remove(new Random().nextInt(items.size())));
        }
        pcs.firePropertyChange("BackpackChanged", null, null);

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
                items.add(real ? new TVSZ(name, this) : new FakeItem(name, "TVSZ", this));
                break;
            case "WetRag":
                items.add(real ? new WetRag(name, this) : new FakeItem(name, "WetRag", this));
                break;
            default:
                return;

        }
        String msg = (real ? "Normal" : "Fake") + " Item " + type +" "+ name + " has been added to Student " + this.name;
        resultLogger.log(Level.INFO, msg);
    }
    /**
     * Drops the specified item from the student's inventory into the current room.
     *
     * @param name the name of the item to drop
     */
    public void DropItem_Test(String name)
    {
        for (Item i:items) {
            if (i.GetName().equals(name))
            {
                DropItem(i);
                resultLogger.log(Level.INFO,"Character "+ this.name+" dropped Item "+i.GetName()+" in Room " +location.GetName());
                break;
            }
        }

    }

    public void UseItem(Item i) {
        if(isStunned > 0){
            GameView.info.append("Invalid operation, you are stunned" + "\n");
            return;
        } 
        i.ApplyEffect();
    }
    public void ActivateTransistor(Transistor t){
        t.Activate();
    }

    /**
     * Uses the specified item from the student's inventory.
     *
     * @param name the name of the item to use
     */
    public void UseItem_Test(String name)
    {
        for (Item i:items) {
            if (i.GetName().equals(name))
            {
                UseItem(i);
                resultLogger.log(Level.INFO, "Character "+ this.name+ " used Item "+ i.GetName());
                break;
            }
        }
    }
    /**
     * Picks up the specified item from the current room and adds it to the student's inventory.
     *
     * @param i the name of the item to pick up
     */
    public void PickUpItem(Item i) {
        if(isStunned > 0){
            GameView.info.append("Invalid operation, you are stunned" + "\n");
            return;
        } 
        Item tempItem = location.PopItem(i);
        if (tempItem != null)
        {
            items.add(tempItem);
            tempItem.SetOwner(this);
            resultLogger.log(Level.INFO,"Character "+ this.name+ " picked up Item "+ tempItem.GetName());
            if (tempItem.GetType().startsWith("Fake")){
                tempItem.ApplyEffect();
            }
            if(tempItem.GetType() == "SlideRule"){
                tempItem.ApplyEffect();
            }
            pcs.firePropertyChange("BackpackChanged", null, null);

        }else{
            resultLogger.log(Level.INFO,"Character "+ this.name+ " could not pick up Item "+ i.GetName());
        }
    }
    public void PickupItem_Test(String name)
    {
        for (Item i:location.SearchItem()) {
            if (i.GetName().equals(name))
            {
                PickUpItem(i);
                break;
            }
        }
    }
    @Override
    /**
     * Resets the student's state and clears their inventory.
     */
    public void Reset_Test() {
        super.Reset_Test();
        items.clear();
    }
}
