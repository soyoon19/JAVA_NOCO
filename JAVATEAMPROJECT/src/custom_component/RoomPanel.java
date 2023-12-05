package custom_component;

import dto.RoomManageDTO;

import javax.swing.*;
import java.awt.*;

public class RoomPanel extends JPanel {
    private JLabel label;
    private RoomManageDTO room;
    public RoomPanel(RoomManageDTO room){
        this.room = room;
        JLabel lbs = new JLabel(room.getNum() + "번");
        lbs.setForeground(Color.white);
        this.setBackground(new Color(156,156,156));
        this.add(lbs);
    }

    public JLabel getLabel() {
        return label;
    }

    public RoomManageDTO getRoom() {
        return room;
    }
}
