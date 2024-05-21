package App.View;

import App.ItemListController;
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

    ItemListController controller;
    Point p;
    private Student student;
    private String type;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if((evt.getPropertyName().equals("RoomChanged")||evt.getPropertyName().equals("BackpackChanged"))&&Objects.equals(type, "Room")){
                this.setListData(student.GetLocation().SearchItem().toArray(new Item[0]));
                this.setBounds(p.x,p.y,54* (student.GetLocation().SearchItem().isEmpty()?1:student.GetLocation().SearchItem().size()),56);

        }else if(evt.getPropertyName().equals("BackpackChanged")&&Objects.equals(type, "Player")){
            this.setListData(student.GetItems().toArray(new Item[0]));
            this.setBounds(p.x,p.y,54* (student.GetItems().isEmpty()?1:student.GetItems().size()),56);
        }
        else if(evt.getPropertyName().equals("StudentChanged")){
            student.RemovePropertyChangeListener(this);
            student = (Student) evt.getNewValue();
            student.AddPropertyChangeListener(this);
            if (Objects.equals(type, "Room")){
                this.setListData(student.GetLocation().SearchItem().toArray(new Item[0]));
                this.setBounds(p.x,p.y,54* (student.GetLocation().SearchItem().isEmpty()?1:student.GetLocation().SearchItem().size()),56);
            }else if (Objects.equals(type, "Player")){
            this.setListData(student.GetItems().toArray(new Item[0]));
            this.setBounds(p.x,p.y,54* (student.GetItems().isEmpty()?1:student.GetItems().size()),56);
            }
        }
    }

    public ItemListView(Point p, Student s, String t,Model m){
        controller = new ItemListController(m);
        student = s;
        type = t;
        this.p = p;
        m.AddPropertyChangeListener(this);
        if(Objects.equals(t, "Room")){
            this.setListData(student.GetLocation().SearchItem().toArray(new Item[0]));
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Item item =  ItemListView.this.getSelectedValue();
                    if (item == null){
                        return;
                    }
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
            this.setBounds(p.x,p.y,54* (student.GetLocation().SearchItem().isEmpty()?1:student.GetLocation().SearchItem().size()),56);
        }
        else if(Objects.equals(t, "Player")){

            this.setListData(student.GetItems().toArray(new Item[0]));
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Item item = ItemListView.this.getSelectedValue();
                    if (item == null){
                        return;
                    }
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem use = new JMenuItem("Use");
                    JMenuItem drop = new JMenuItem("Drop");
                    JMenuItem activate = new JMenuItem("Activate");
                    if (Objects.equals(item.GetType(), "Transistor")){
                        Transistor transistor = (Transistor) ItemListView.this.getSelectedValue();
                        activate.setAction(new AbstractAction("Activate") {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                controller.ActivateItem(transistor);
                            }
                        });
                        popup.add(activate);
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
                    popup.add(drop);
                    popup.show(ItemListView.this, e.getX(), e.getY()); //and show the menu
                    clearSelection(); //clear the selection
                }
            });


            this.setBounds(p.x,p.y,54* (student.GetItems().isEmpty()?1:student.GetItems().size()),56);
        }
        else{
            throw new IllegalArgumentException("Invalid type");
        }
        student.AddPropertyChangeListener(this);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setVisible(true);
        this.setCellRenderer(new ItemCellRenderer());
        setBackground(Color.decode("#69430A"));
        setVisibleRowCount(-1);
        setLayoutOrientation(JList.HORIZONTAL_WRAP);

    }

}
