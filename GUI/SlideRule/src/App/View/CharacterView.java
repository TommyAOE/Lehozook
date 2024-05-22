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
        ImageIcon imageIcon = switch (c.GetName().charAt(0)) {
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
        setIcon(imageIcon);
        this.point = point;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
    private void OnMove(){
    }
    private void OnDeath(){

    }

    private void OnEnterRoom(Room r){

    }
}
