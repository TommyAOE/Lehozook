package App.View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GooView extends JLabel{

    public GooView(){
        super(new ImageIcon("SlideRule/resources/room/goo.png"));
        setBounds(0, 0, 150, 150);
    }
}
