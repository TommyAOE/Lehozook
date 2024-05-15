package App.View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GasView extends JLabel{

    private RoomView room;

    public GasView(){
        super(new ImageIcon("./SlideRule/resources/room/gas.png"));
        this.setOpaque(true);
    }

}
