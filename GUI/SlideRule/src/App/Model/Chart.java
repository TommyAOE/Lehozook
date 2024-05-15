package App.Model;

import App.Model.Items.*;

import static App.Program.resultLogger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Represents a chart in the game.
 */
public class Chart {


    private ArrayList<Room> rooms; // List of rooms in the chart

    /**
     * Constructs a new chart from a map file.
     */
    public Chart() {
        rooms = new ArrayList<Room>();
        ArrayList<String[]> lines = ReadFile(new File("./SlideRule/resources/map.txt"));
        for(String[] command : lines){
            BuildMap(command);
        }
        /*for(Room r : rooms){
            r.Info_Test();
        }*/
    }
    /**
     * Reads a file and returns its content as an ArrayList of String arrays.
     *
     * @param input The file to read.
     * @return The content of the file as an ArrayList of String arrays.
     */
    private ArrayList<String[]> ReadFile(File input) {
        ArrayList<String[]> lines = new ArrayList<>();
        String[] currentLine;
        try{
            Scanner sc = new Scanner(input);

            while(sc.hasNextLine()){
                currentLine = sc.nextLine().split(" ");
                lines.add(currentLine);
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
        }
        return lines;
    }
    /**
     * Builds the chart from the commands read from the map file.
     *
     * @param command The command to execute.
     */
    private void BuildMap(String[] command){
        switch (command[0]) {
            case "AddRoom":
                Room r = new Room(command[1], command[2], this);
                AddRoom(r);
                break;

            case "SetNeighboursOneWay":
                Room current = FindRoomByName(command[1]);
                if(current != null){
                    ArrayList<Room> neighbours = CollectNeighbours(current, command);
                    current.SetNeighboursOneWay(neighbours);
                }
                break;

            case "SetNeighboursTwoWay":
                Room current2 = FindRoomByName(command[1]);
                if(current2 != null){
                    ArrayList<Room> neighbours = CollectNeighbours(current2, command);
                    current2.SetNeighboursTwoWay(neighbours);
                }
                break;
            
            default:
                break;
        }
    }
    /**
     * Finds a room in the chart by its name.
     *
     * @param name The name of the room to find.
     * @return The room with the specified name, or null if not found.
     */
    public Room FindRoomByName(String name){
        for (Room room : rooms) {
            if (room.name.equals(name)) {
                return room;
            }
        }
        return null; // Returns null if the room with the given name is not found
    }
    /**
     * Collects the neighbouring rooms for a given room.
     *
     * @param current The room for which neighbours are collected.
     * @param command The command containing neighbour room names.
     * @return The list of neighboring rooms.
     */
    private ArrayList<Room> CollectNeighbours(Room current, String[] command){
        ArrayList<Room> neighbours = new ArrayList<>();
        //command[0] command
        //command[1] room to set neighbours to
        for(int i = 2; i < command.length; i++){
            Room neighbourRoom = FindRoomByName(command[i]);
            if(neighbourRoom != null){
                neighbours.add(neighbourRoom);
            }
        }
        return neighbours;
    }
    /**
     * Gets all rooms in the chart.
     *
     * @return List of all rooms in the chart.
     */
    public List<Room> GetAllRooms() {
        return rooms;
    }
    /**
     * Adds a room to the chart.
     * @param r The room to be added.
     */
    public void AddRoom(Room r) {
        rooms.add(r);
        //System.out.println("added " + r.name);
    }
    /**
     * Removes a room from the chart.
     * @param r The room to be removed.
     */
    public void RemoveRoom(Room r) {
        try {
            rooms.remove(r);
        } catch (Exception e) {
        }
    }
    /** 
     * Iterates through all rooms in the chart and triggers room changes.
     */
    public void IterateForRoomChanges(){
        for (Room room : rooms) {
            room.Change(rooms);
        }
    }

    public void IterateForItemSpawn(boolean initial, int playerCount){
        if(initial){
            rooms.get(new Random().nextInt(1, rooms.size())).AddItem(new SlideRule());
            if(playerCount < 3){
                for (Room room : rooms) {
                    room.SpawnItem();
                }
            }else{
                for (Room room : rooms) {
                    room.SpawnItem();
                    room.SpawnItem();
                }
            }
        }
        else{
            Random r = new Random();
            for (Room room : rooms) {
                if(r.nextInt(100) < playerCount * 10){
                    room.SpawnItem();
                }
            }
        }
    }
    
    //------------------Functions for testing------------------
    /**
     * Adds a new room to the chart.
     *
     * @param name The name of the room to be added.
     * @param type The type of the room to be added.
     */
    public void AddRoom_Test(String name, String type) {
        Room r = new Room(name, type, this);
        AddRoom(r);
        String msg = "Room " + name + " added to chart";
        resultLogger.log(Level.INFO, msg);
    }
    /**
     * Finds a room in the chart by its name.
     *
     * @param name The name of the room to find.
     * @return The room with the specified name, or null if not found.
     */
    public Room findRoomByName_Test(String name) {
        return FindRoomByName(name);
    }
    /**
     * Finds a character in the chart by their name.
     *
     * @param name The name of the character to find.
     * @return The character with the specified name, or null if not found.
     */
    public Character findCharacterByName_Test(String name) {
        for (Room room : rooms) {
            for (Character character : room.professors) {
                if (character.GetName().equals(name)) {
                    return character;
                }
            }
            for (Character character : room.students) {
                if (character.GetName().equals(name)) {
                    return character;
                }
            }
            for (Character character : room.cleaners) {
                if (character.GetName().equals(name)) {
                    return character;
                }
            }
        }
        return null; // Returns null if the character with the given name is not found
    }
    /**
     * Sets two rooms as neighbors.
     *
     * @param name1 The name of the first room.
     * @param name2 The name of the second room.
     */
    public void SetNeighbours_Test(String name1, String name2) {
        Room r1 = findRoomByName_Test(name1);
        Room r2 = findRoomByName_Test(name2);
        r1.SetNeighboursOneWay(new ArrayList<Room>(Collections.singletonList(r2)));
    }
    /**
     * Adds a character to a room in the chart.
     *
     * @param name     The name of the character.
     * @param type     The type of the character (Professor, Student, or Cleaner).
     * @param roomname The name of the room where the character will be added.
     */
    public void AddCharacter_Test(String name, String type, String roomname) {
        Room r = FindRoomByName(roomname);
        if (type.equals("Professor")) {
            Professor p = new Professor(name, r);
            r.professors.add(p);
            String msg = "Character " + name + " added to Room " + roomname;
            resultLogger.log(Level.INFO, msg);

        } else if (type.equals("Student")) {
            Student s = new Student(name, r);
            r.students.add(s);
            String msg = "Character " + name + " added to Room " + roomname;
            resultLogger.log(Level.INFO, msg);

        } else if (type.equals("Cleaner")) {
            Cleaner c = new Cleaner(name, r);
            r.cleaners.add(c);
            String msg = "Character " + name + " added to Room " + roomname;
            resultLogger.log(Level.INFO, msg);
        }
    }
    /**
     * Logs information about all rooms in the chart.
     */
    public void InfoAll_Test() {
        for (Room room : rooms) {
            room.InfoAll_Test();
        }
    }
    /**
     * Logs information about a specific entity in the chart (Room, Student, Professor, Cleaner, or Item).
     *
     * @param name The name of the entity.
     * @param type The type of the entity (Room, Student, Professor, Cleaner, or Item).
     */
    public void Info_Test(String name, String type) {
        switch (type) {
            case "Room" -> {
                Room r = findRoomByName_Test(name);
                assert r != null;
                r.Info_Test();
            }
            case "Student" -> {
                Student s = (Student) findCharacterByName_Test(name);
                assert s != null;
                s.Info_Test();
            }
            case "Professor" -> {
                Professor p = (Professor) findCharacterByName_Test(name);
                assert p != null;
                p.Info_Test();
            }
            case "Cleaner" -> {
                Cleaner c = (Cleaner) findCharacterByName_Test(name);
                assert c != null;
                c.Info_Test();
            }
            case "Item" -> {
                Item i = findItemByName_Test(name);
                assert i != null;
                i.Info_Test();
            }
            default -> System.out.println("Invalid type");
        }
    }
    /**
     * Finds an item in the chart by its name.
     *
     * @param name The name of the item to find.
     * @return The item with the specified name, or null if not found.
     */
    public Item findItemByName_Test(String name) {
        for (Room room : rooms) {
            for (Item item : room.items) {
                if (item.GetName().equals(name)) {
                    return item;
                }
            }
        }
        for (Room room : rooms) {
            for (Student student : room.GetStudents()) {
                for(Item item : student.GetItems())
                if (item.GetName().equals(name)) {
                    return item;
                }
            }
        }
        return null; 
    }
    /**
     * Resets the chart, clearing all rooms and their contents.
     */
    public void Reset_Test() {
        for (Room room : rooms) {
            room.Reset_Test();
        }
        rooms.clear();
    }
}
