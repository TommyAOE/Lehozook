package App;

import App.Items.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListTest extends JList {
    ArrayList<Item> items = new ArrayList<>();
    public ListTest(){

        items.add(new FFP2Mask("i1"));
        items.add(new StBeerCups("i2"));
        items.add(new Camembert("i3"));
        items.add(new TVSZ("i4"));
        items.add(new Airfreshener("i5"));
        this.setListData(items.toArray());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)  {check(e);}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseDragged(MouseEvent e) {}

            public void check(MouseEvent e) {
                System.out.println("clicked");
                Item item = (Item) ListTest.this.getSelectedValue();
                System.out.println(item.GetType());
                JPopupMenu popup = new JPopupMenu();
                JMenuItem use = new JMenuItem("Use");
                use.setAction(new AbstractAction("Use") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        item.ApplyEffect();
                    }
                });
                popup.add(use);
                popup.add(new JMenuItem("Drop"));
                ListTest.this.setSelectedIndex(ListTest.this.locationToIndex(e.getPoint())); //select the item
                    popup.show(ListTest.this, e.getX(), e.getY()); //and show the menu
                ListTest.this.clearSelection(); //clear the selection
            }
        });
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setVisible(true);
        this.setBounds(300,300,265,56);
        this.setCellRenderer(new ItemCellRenderer());
        setBackground(Color.decode("#69430A"));
        setVisibleRowCount(-1);
        setLayoutOrientation(JList.HORIZONTAL_WRAP);
    }
}
