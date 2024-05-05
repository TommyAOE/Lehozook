package App;
import App.Items.*;
import javax.swing.*;
import java.awt.*;

public class ItemCellRenderer extends JLabel implements ListCellRenderer {
    public ItemCellRenderer() {
        setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Item item = (Item) value;
        ImageIcon imageIcon = new ImageIcon("src\\Assets\\"+item.GetType()+".png"); // load the image to a imageIcon
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
