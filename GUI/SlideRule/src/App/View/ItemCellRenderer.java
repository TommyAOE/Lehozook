package App.View;

import java.awt.*;
import javax.swing.*;
import App.Model.Items.*;

public class ItemCellRenderer extends JLabel implements ListCellRenderer {


    public ItemCellRenderer() {
        setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Item item = (Item) value;
        String type = item.GetType();
        if (item.GetType().startsWith("Fake")) {
            type = item.GetType().substring(4);
        }
        ImageIcon imageIcon = new ImageIcon("resources\\items\\"+type+".png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        setIcon(imageIcon);
        setBorder(BorderFactory.createLineBorder(Color.decode("#69430A"), 1));
        setBackground(Color.decode("#FDB69A"));
        setSize(50,50);
        return this;

    }

}
