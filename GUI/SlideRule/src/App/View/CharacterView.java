package App.View;

import App.Model.*;
import App.Model.Character;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import javax.swing.*;

public class CharacterView extends JLabel implements PropertyChangeListener{

    public Point point;
    public Character character;

    public CharacterView(Character c, int index, Point point) {
        character = c;
        //c.AddPropertyChangeListener(this);
        ImageIcon imageIcon = switch (c.GetName().charAt(0)) {
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
        setIcon(imageIcon);
        UpdateImage(index);
        this.point = point;
    }

    /*
    public static BufferedImage imageIconToBufferedImage(ImageIcon icon) {
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.createGraphics();
        icon.paintIcon(null, graphics, 0, 0);
        graphics.dispose();
        return bufferedImage;
    }*/


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
    /*
    private Image turn90degrees(BufferedImage image,double degrees)
    {
        // Calculate the new size of the image based on the angle of rotaion
    double radians = Math.toRadians(degrees);
    double sin = Math.abs(Math.sin(radians));
    double cos = Math.abs(Math.cos(radians));
    int newWidth = (int) Math.round(image.getWidth() * cos + image.getHeight() * sin);
    int newHeight = (int) Math.round(image.getWidth() * sin + image.getHeight() * cos);

    // Create a new image
    BufferedImage rotate = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = rotate.createGraphics();
    // Calculate the "anchor" point around which the image will be rotated
    int x = (newWidth - image.getWidth()) / 2;
    int y = (newHeight - image.getHeight()) / 2;
    // Transform the origin point around the anchor point
    AffineTransform at = new AffineTransform();
    at.setToRotation(radians, x + (image.getWidth() / 2), y + (image.getHeight() / 2));
    at.translate(x, y);
    g2d.setTransform(at);
    // Paint the originl image
    g2d.drawImage(image, 0, 0, null);
    g2d.dispose();
    return rotate;
    }*/
    /*
    public static Image rotate(Image image, double angle) {
        BufferedImage bufImg = toBufferedImage(image);
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = bufImg.getWidth(), h = bufImg.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
        BufferedImage result = new BufferedImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(bufImg, null);
        g.dispose();
        return result;
     }*/
    /*
     public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
           return (BufferedImage) image;
        }
        BufferedImage buff = new BufferedImage(image.getWidth(null), image.getHeight(null),
        BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buff.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return buff;
     }
     */
    private void OnEnterRoom(Room r){

    }
}
