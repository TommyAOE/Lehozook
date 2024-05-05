package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListTest extends JList {
    ArrayList<Student> students = new ArrayList<>();
    public ListTest(){

        Room room = new Room("r1", "normal", new Chart());
        students.add(new Student("Alice", room));
        students.add(new Student("Bob", room));
        students.add(new Student("Charlie", room));
        this.setListData(students.toArray());
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
                JPopupMenu popup = new JPopupMenu();
                popup.add(new JMenuItem("Test"));
                popup.add(new JMenuItem("Test2"));
                popup.add(new JMenuItem("Test3")    );
                ListTest.this.setSelectedIndex(ListTest.this.locationToIndex(e.getPoint())); //select the item
                    popup.show(ListTest.this, e.getX(), e.getY()); //and show the menu
                ListTest.this.clearSelection(); //clear the selection
            }
        });
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setVisible(true);
        this.setBounds(300,300,200,200);
    }
}
