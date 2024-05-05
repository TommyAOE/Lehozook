package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class GameView extends JFrame {
    ArrayList<Student> students = new ArrayList<>();
    Chart chart = new Chart();
        public GameView() {
            this.setLayout(null);
            Room room = new Room("r1", "normal", chart);
            Student student1 = new Student("s1",room);
            Student student2 = new Student("s2",room);
            students.add(student1);
            students.add(student2);
            StudentView studentView1 = new StudentView(students.get(0));
            StudentView studentView2 = new StudentView(students.get(1));
            studentView2.setLocation(100, 100);
            this.setTitle("Game Window");
            this.setSize(1000, 1000);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JButton button = new JButton("Test");
            button.setBounds(50, 50, 100, 50);
            button.addActionListener(e -> {
                students.get(0).NameTest();
            });
            add(button);
            this.add(studentView1);
            this.add(studentView2);
            ListTest listTest = new ListTest();
            this.add(listTest);
        }
}
