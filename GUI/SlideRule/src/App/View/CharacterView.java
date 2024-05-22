package App.View;

import App.Model.*;
import App.Model.Character;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import javax.swing.*;

public class CharacterView extends JLabel implements PropertyChangeListener{

    public Point point;
    public Character character;

    public CharacterView(Character c,int index, Point point) {
        character = c;
        //c.AddPropertyChangeListener(this);
        /*ImageIcon imageIcon = switch (c.GetName().charAt(0)) {
            case 'p' -> new ImageIcon("resources/characters/professor.png");
            case 's' ->
                switch (c.GetName()) {
                    case "s0" ->new ImageIcon("resources/characters/Alice.png");
                    case "s1" -> new ImageIcon("resources/characters/Claus.png");
                    case "s2" -> new ImageIcon("resources/characters/Felicity.png");
                    case "s3" -> new ImageIcon("resources/characters/Jeremy.png");
                    case "s4" -> new ImageIcon("resources/characters/Lee.png");
                    default -> throw new IllegalStateException("Unexpected value: " + index);
                };
            case 'c' -> new ImageIcon("resources/characters/cleaner.png");
            default -> null;
        };
        Image image = Objects.requireNonNull(imageIcon).getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 65,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        setIcon(imageIcon);*/
        //UpdateImage(index);
        if(character.GetName().startsWith("s")) {
            System.out.println("student rotate image");
            Student temp = (Student)character;
            if(temp.isStunned() > 0)
                CharacterImageRotate(character.GetName(), index);
            else{
                System.out.println("normal image");
                CharacterImageNormal(character.GetName(), index);
            }
        } else if(character.GetName().startsWith("p")) {
            System.out.println("professor rotate image");
            Professor temp = (Professor)character;
            if(temp.IsStunned() > 0)
                CharacterImageRotate(character.GetName(), index);
            else {
                System.out.println("normal image");
                CharacterImageNormal(character.GetName(), index);
            }
        } else {
            System.out.println("normal image");
            CharacterImageNormal(character.GetName(), index);
        }
        this.point = point;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
    public void UpdateImage(int index){
        if(character.GetName().startsWith("s")) {
            Student temp = (Student)character;
            if(temp.isStunned() > 0)
                CharacterImageRotate(character.GetName(), index);
        } else if(character.GetName().startsWith("p")) {
            Professor temp = (Professor)character;
            if(temp.IsStunned() > 0)
                CharacterImageRotate(character.GetName(), index);
        } else {
            CharacterImageNormal(character.GetName(), index);
        }
    }

    private void CharacterImageRotate(String name, int index){
        ImageIcon imageIcon = switch (name.charAt(0)) {
            case 'p' -> new ImageIcon("SlideRule/resources/characters laying/professor2.png");
            case 's' ->
                switch (index) {
                    case 0 -> new ImageIcon("SlideRule/resources/characters laying/Alice2.png");
                    case 1 -> new ImageIcon("SlideRule/resources/characters laying/Claus2.png");
                    case 2 -> new ImageIcon("SlideRule/resources/characters laying/Felicity2.png"); 
                    case 3 -> new ImageIcon("SlideRule/resources/characters laying/Jeremy2.png"); 
                    case 4 -> new ImageIcon("SlideRule/resources/characters laying/Lee2.png");
                    default -> throw new IllegalStateException("Unexpected value: " + index);
                };
            default -> null;
        };
        Image image = Objects.requireNonNull(imageIcon).getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        this.setIcon(imageIcon);
    }
    
    private void CharacterImageNormal(String name, int index){
        ImageIcon imageIcon = switch (name.charAt(0)) {
            case 'p' -> new ImageIcon("SlideRule/resources/characters/professor.png");
            case 's' ->
                switch (index) {
                    case 0 -> new ImageIcon("SlideRule/resources/characters/Alice.png");
                    case 1 -> new ImageIcon("SlideRule/resources/characters/Claus.png");
                    case 2 -> new ImageIcon("SlideRule/resources/characters/Felicity.png");
                    case 3 -> new ImageIcon("SlideRule/resources/characters/Jeremy.png");
                    case 4 -> new ImageIcon("SlideRule/resources/characters/Lee.png");
                    default -> throw new IllegalStateException("Unexpected value: " + index);
                };
            case 'c' -> new ImageIcon("SlideRule/resources/characters/cleaner.png");
            default -> null;
        };
        Image image = Objects.requireNonNull(imageIcon).getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        this.setIcon(imageIcon);
    }
    private void OnMove(){
    }
    private void OnDeath(){

    }

    private void OnEnterRoom(Room r){

    }
}
