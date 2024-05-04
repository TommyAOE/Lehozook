package App;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class StudentView extends JLabel implements EventListener {
    Student student ;
    public StudentView(Student student) {
        this.student = student;
        this.setText(student.GetName());
        this.setBounds(10, 10, 50, 50);
        this.setBorder(BorderFactory.createEtchedBorder());;
    }

    @Override
    public void update(String eventType, Object target, Object data){
        Student s = (Student) target;
        if (Objects.equals(s, student)){
                System.out.println("StudentView: " + s.GetName());
                this.setText(s.GetName());

            if (eventType.equals("move")) {

                Point p = (Point) data;
                this.setLocation(this.getX()+p.x, this.getY()+p.y);
            }else {
                System.out.println("StudentView: " + s.GetName());
                this.setText(s.GetName());}
            }
        }
    }
