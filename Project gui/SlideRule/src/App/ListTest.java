package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListTest extends JFrame {
    ArrayList<Student> students = new ArrayList<>();
    public ListTest(){

        super("List Test");
        Room room = new Room("r1", "normal", new Chart());
        students.add(new Student("Alice", room));
        students.add(new Student("Bob", room));
        students.add(new Student("Charlie", room));
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JList list = new JList(students.toArray());
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)  {check(e);}

            public void check(MouseEvent e) {
                JPopupMenu popup = new JPopupMenu();
                popup.add(new JMenuItem("Test"));
                popup.add(new JMenuItem("Test2"));
                popup.add(new JMenuItem("Test3")    );
                    list.setSelectedIndex(list.locationToIndex(e.getPoint())); //select the item
                    popup.show(list, e.getX(), e.getY()); //and show the menu

            }
        });
        list.setBorder(BorderFactory.createEtchedBorder());
        this.add(list);
        list.setLocation(300,300);
    }
}
