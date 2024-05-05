package App;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class StudentView extends JLabel implements PropertyChangeListener {
    Student student ;
    ArrayList<Student> students = new ArrayList<>();

    public StudentView(Student student) {
        this.student = student;
        this.student.AddPropertyChangeListener(this);
        this.setText(student.GetName());
        this.setBounds(10, 10, 50, 50);
        this.setBorder(BorderFactory.createEtchedBorder());;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("name")) {
            this.setText((String) evt.getNewValue());
        }
    }
}
