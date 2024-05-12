package App.Model;

import java.util.ArrayList;

/**
 * Represents a scheduler in the game.
 */
public class Scheduler {
    //attribútomok
    Chart chart;
    ArrayList<Character> characters;
    int length;
    //
    /** 
     * Plays the scheduler's role.
     */
    public void Play(){
        System.out.println("App.Scheduler: Play()");
        for (int i = 0; i < length; i++) {
            for (Character character : characters) {
                {
                    character.Turn();
                }
            }
            for (Character character : characters) {
                {
                    if(character.GetName().charAt(0)=='s')
                    {
                        Student curr=(Student)character;
                        curr.Combat();
                    }else if(character.GetName().charAt(0)=='p')
                    {
                        Professor curr=(Professor)character;
                        curr.Combat();
                    }
                }
            }
        }
    }
    public void init(int cleaners,int student, int professors)
    {/*
        for (int i = 1; i <= cleaners; i++) {
            characters.add(new Cleaner());
        }
        for (int i = 1; i <= student; i++) {
            characters.add(new Student( "s"+i,new Room()));
        }
        for (int i = 1; i <= professors; i++) {
            characters.add(new Professor("p"+i,new Room()));
        }
        chart=new Chart();
        chart.init();*/
    }
        public void updateLists()
        {
            characters.clear();
            for (Room r: chart.GetAllRooms()) {

            }
        }
}
