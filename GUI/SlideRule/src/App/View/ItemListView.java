package App.View;

import App.ListController;
import App.Model.Items.*;
import App.Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;
import javax.swing.*;

public class ItemListView extends JList<Item> implements PropertyChangeListener{

    ListController controller;
    private Student student;
    private String type;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("RoomChanged")&&Objects.equals(type, "Room")){
                this.setListData(student.GetLocation().SearchItem().toArray(new Item[0]));
        }else if(evt.getPropertyName().equals("BackpackChanged")&&Objects.equals(type, "Player")){
            this.setListData(student.GetItems().toArray(new Item[0]));
        }else if(evt.getPropertyName().equals("StudentChanged")){
            student.RemovePropertyChangeListener(this);
            student = (Student) evt.getNewValue();
            student.AddPropertyChangeListener(this);
            if (Objects.equals(type, "Room")){
                this.setListData(student.GetLocation().SearchItem().toArray(new Item[0]));}
            else if (Objects.equals(type, "Player")){
                this.setListData(student.GetItems().toArray(new Item[0]));
            }
        }
    }
    public ItemListView(Point p, Student s, String t,Model m){
        controller = new ListController(m);
        student = s;
        type = t;
        m.AddPropertyChangeListener(this);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setVisible(true);
        this.setCellRenderer(new ItemCellRenderer());
        setBackground(Color.decode("#69430A"));
        setVisibleRowCount(-1);
        setLayoutOrientation(JList.HORIZONTAL_WRAP);
        if(Objects.equals(t, "Room")){
            this.setListData(student.GetLocation().SearchItem().toArray(new Item[0]));
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Item item =  ItemListView.this.getSelectedValue();
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem pickUp = new JMenuItem("Pick Up");
                    pickUp.setAction(new AbstractAction("Pick Up") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            controller.PickUpItem(item);
                        }
                    });
                    popup.add(pickUp);
                    popup.show(ItemListView.this, e.getX(), e.getY()); //and show the menu
                    clearSelection(); //clear the selection
                }
            });
            this.setBounds(p.x,p.y,53* student.GetItems().size(),56);
        }
        else if(Objects.equals(t, "Player")){
            this.setListData(student.GetItems().toArray(new Item[0]));
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Item item = ItemListView.this.getSelectedValue();
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem use = new JMenuItem("Use");
                    JMenuItem drop = new JMenuItem("Use");
                    if (Objects.equals(item.GetType(), "Transistor")){
                        Transistor transistor = (Transistor) ItemListView.this.getSelectedValue();
                        JMenuItem activate = new JMenuItem("Activate");
                        activate.setAction(new AbstractAction() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                controller.ActivateItem(transistor);
                            }
                        });
                    }
                    use.setAction(new AbstractAction("Use") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            controller.UseItem(item);
                        }
                    });
                    drop.setAction(new AbstractAction("Drop") {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            controller.DropItem(item);
                        }
                    });
                    popup.add(use);
                    popup.add(new JMenuItem("Drop"));
                    popup.show(ItemListView.this, e.getX(), e.getY()); //and show the menu
                    clearSelection(); //clear the selection
                }
            });
            this.setBounds(300,300,53* student.GetLocation().SearchItem().size(),56);
            student.AddPropertyChangeListener(this);
        }
        else{
            throw new IllegalArgumentException("Invalid type");
        }

    }

}
