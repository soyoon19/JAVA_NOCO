package views;

import custom_component.RoomPanel;
import custom_component.RoomViewPanel;
import dto.MemberDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoomSelectView extends JPanel {
    private DefaultFrame parent;
    private MemberDTO member;
    public RoomSelectView(DefaultFrame prt, MemberDTO mbr){
        this.parent = prt;
        this.member = mbr;

        this.setLayout(new GridBagLayout());
        RoomViewPanel room = new RoomViewPanel(parent.getController());
            this.add(room, DefaultFrame.easyGridBagConstraint(0,0,6,1));

        //각 방의 리스너를 붙이는 작업
        for(RoomPanel r : room.getRoomPs()){
            r.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(member == null) {
                        parent.move(new ProductListCartView(parent, r.getRoom()));
                    }else{
                        parent.move(new UsePurchaseSelectView(parent, r.getRoom(), member));
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {
                    Color c = r.getBackground();
                    r.setBackground(new Color(c.getRed() + 10, c.getGreen() + 10, c.getBlue() + 10));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Color c = r.getBackground();
                    r.setBackground(new Color(c.getRed() - 10, c.getGreen() - 10, c.getBlue() - 10));
                }
            });
        }

        JPanel right = new JPanel();
        right.setBackground(Color.red);

        this.add(right, DefaultFrame.easyGridBagConstraint(1,0,4,1));
    }
}
