package App.View;

import App.Model.Student;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JList;

public class ItemListView extends JList implements PropertyChangeListener{

    private Coordinates coordinates;
    private Student student;
    private String type;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

    public void ChangeStudent(Student s){
        student = s;
    }

}
