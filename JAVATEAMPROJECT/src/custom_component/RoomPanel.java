package custom_component;

import dto.RoomManageDTO;

import javax.swing.*;
import java.awt.*;

public class RoomPanel extends JPanel {

    public static final int NORMAL = 1
            , LOCK = 2, USE = 3;
    private JLabel label;
    private int mode;
    private RoomManageDTO room;
    public RoomPanel(RoomManageDTO room, int mode){
        this.mode = mode; //모드를 저장
        this.room = room;


        String info = "";
        switch (mode) {
            case NORMAL:
                info = "빈방";
                this.setBackground(new Color(156, 156, 156));
                break;
            case LOCK:
                info = "점검중";
                this.setBackground(new Color(25,23,23));
                break;
            case USE:
                info = "사용중";
                this.setBackground(new Color(64,64,64));
                break;
        }

        JLabel lbs = new JLabel(room.getNum() + "번 " + info);
        lbs.setForeground(Color.white);
        this.add(lbs);

    }

    public JLabel getLabel() {
        return label;
    }

    public RoomManageDTO getRoom() {
        return room;
    }

    public int getMode(){
        return mode;
    }
}
