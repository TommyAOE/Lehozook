package App.View;

import App.Model.*;
import App.Model.Character;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

public class CharacterView extends JLabel implements PropertyChangeListener{

    private Point p;
    private Character character;

    public CharacterView(Character c,int index, Point point) {
        character = c;
        //c.AddPropertyChangeListener(this);
        ImageIcon imageIcon = null;
        switch (c.GetName().charAt(0)){
            case 'p':
                imageIcon = new ImageIcon("resources/characters/professor.png");
                break;
            case 's':
                imageIcon = new ImageIcon("resources/characters/Alice.png");
                break;
            case 'c':
                imageIcon = new ImageIcon("resources/characters/cleaner.png");
                break;
        }
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        setIcon(imageIcon);
        this.p = point;
        this.setBounds(p.x, p.y, 50, 50);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
    private void OnDeath(){

    }

    private void OnEnterRoom(Room r){

    }
}
