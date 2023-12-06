package views;

import custom_component.RoomPanel;
import custom_component.RoomViewPanel;
import dto.MemberDTO;
import dto.RoomIfmDTO;

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
                    //todo 이미 방이 점유된 경우 안내해주기!
                    RoomIfmDTO roomIfm = parent.getController().getRoomImfDAO().findById(r.getRoom().getNum());

                    //todo 추가 결제 로직 구현
                    if(member == null){ //비회원인 경우
                        if(roomIfm == null) //빈방인 경우
                            parent.move(new ProductListCartView(parent, r.getRoom()));
                        else{

                        } //빈방이 아닌 경우
                            //parent.move(new UsePurchaseSelectView(parent, r.getRoom(), member));
                    }else{  //회원이 경우
                        if(roomIfm == null){ //빈방이 경우
                            parent.move(new UsePurchaseSelectView(parent, r.getRoom(), member));
                        }else if(roomIfm.getUserHp() == member.getHp()){ //방이 점유되어 있는데 방을 점유한 회원과 같은 경우

                        }else{  //다른 회원이나 다른 사람이 이미 점유한 경우
                            //알림!
                        }
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
