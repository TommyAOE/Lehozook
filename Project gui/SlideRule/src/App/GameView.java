package App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

public class GameView extends JFrame {
    ArrayList<Student> students = new ArrayList<>();
    EventManager eventManager = new EventManager(new String[]{"student", "move"});
    Chart chart = new Chart();
        JLabel character = new JLabel(new ImageIcon("src" + File.separator + "Assets" + File.separator + "character.png"));
        public GameView() {

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

            this.add(studentView1);
            this.add(studentView2);
            this.setLayout(null);
            eventManager.subscribe("student", studentView1);
            eventManager.subscribe("student", studentView2);
            eventManager.subscribe("move", studentView1);
            eventManager.subscribe("move", studentView2);
            this.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int x = studentView1.getX();
                    int y = studentView1.getY();
                    if (e.getKeyChar() == 'w') {
                        Point p = new Point(0,  - 10);
                        eventManager.notify("move",students.get(0), p);
                    } else if (e.getKeyChar() == 's') {
                        Point p = new Point(0, + 10);
                        eventManager.notify("move",students.get(0), p);
                    } else if (e.getKeyChar() == 'a') {
                        Point p = new Point( - 10, 0);
                        eventManager.notify("move",students.get(0), p);

                    } else if (e.getKeyChar() == 'd') {
                        Point p = new Point(+ 10, 0);
                        eventManager.notify("move",students.get(0), p);
                        //studentView1.setLocation(x + 10, y);
                    }else if (e.getKeyChar() == 'n') {
                        students.get(0).NameTest();
                        eventManager.notify("student", students.get(0), null);
                        System.out.println(students.get(0).GetName());
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });

        }
}
