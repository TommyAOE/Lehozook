package App.View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GasView extends JLabel{

    public GasView(){
        super(new ImageIcon("resources/room/gas.png"));
        setBounds(0, 0, 150, 150);
    }
}
