package App;

import App.Items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chart in the game.
 */
public class Chart {
    //attributomok
    private ArrayList<Room> rooms;
    //
    public Chart() {
        rooms = new ArrayList<Room>();
    }
    /** 
     * Iterates for room changes in the chart.
     */
    public void IterateForRoomChanges(){
        for (Room room : rooms) {
            room.Change();
        }
        System.out.println("App.Characters.Chart: IterateForRoomChanges()");
    }
    public void init()
    {
        System.out.println("App.Chart loaded");
    }
    public List<Room> GetAllRooms()
    {
        return rooms;
    }
    public void AddRoom_Test(String name,String type)
    {
        Room r=new Room(name,type);
        rooms.add(r);
    }
    public Room findRoomByName_Test(String name) {
        for (Room room : rooms) {
            if (room.name.equals(name)) {
                return room;
            }
        }
        return null; // Ha nem található a megadott névvel szoba
    }
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
        return null; // Ha nem található a megadott névvel karakter
    }
    public void SetNeighbours_Test(String name1,String name2)
    {
        Room r1= findRoomByName_Test(name1);
        Room r2= findRoomByName_Test(name2);
        r1.SetNeighbours(r2);
    }

    public void AddCharacter_Test(String name, String type, String roomname) {
        Room r = findRoomByName_Test(roomname);
        if (type.equals("Professor")) {
            Professor p = new Professor(name,r);
            r.professors.add(p);
        } else if (type.equals("Student")) {
            Student s = new Student(name,r);
            r.students.add(s);
        } else if (type.equals("Cleaner")) {
            Cleaner c = new Cleaner(name,r);
            r.cleaners.add(c);
        }
    }


    public void InfoAll_Test() {
        for (Room room : rooms) {
            room.InfoAll_Test();
        }
    }

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

    private Item findItemByName_Test(String name) {
        for (Room room : rooms) {
            for (Item item : room.items) {
                if (item.GetName().equals(name)) {
                    return item;
                }
            }
        }
        return null; // Ha nem található a megadott névvel item
    }
}
