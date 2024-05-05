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

            this.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    System.out.println(e.getKeyChar());
                    if (e.getKeyChar() == 'w') {
                        Point p = new Point(0,  - 10);
                    } else if (e.getKeyChar() == 's') {
                        Point p = new Point(0, + 10);
                    } else if (e.getKeyChar() == 'a') {
                        Point p = new Point( - 10, 0);
                    } else if (e.getKeyChar() == 'd') {
                        Point p = new Point(+ 10, 0);
                    }else if (e.getKeyChar() == 'n') {
                        students.get(0).NameTest();
                        System.out.println(students.get(0).GetName());
                    }
                }
                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.println(e.getKeyChar());
                }
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
            this.add(studentView1);
            this.add(studentView2);
            ListTest listTest = new ListTest();
            this.add(listTest);
        }
}
