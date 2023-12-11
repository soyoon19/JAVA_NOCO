package views;

import custom_component.*;
import dto.MemberDTO;
import dto.MemberLogDTO;
import dto.RoomIfmDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;

public class RoomSelectView extends JPanel {
    private DefaultFrame parent;
    private MemberDTO member;
    public RoomSelectView(DefaultFrame prt, MemberDTO mbr){
        this.parent = prt;
        this.member = mbr;

        this.setLayout(new GridBagLayout());
        RoomViewPanel room = new RoomViewPanel(parent.getController());

        this.add(room, DefaultFrame.easyGridBagConstraint(0,0,3,2));

        //각 방의 리스너를 붙이는 작업
        for(RoomPanel r : room.getRoomPs()){
            r.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    RoomIfmDTO roomIfm = parent.getController().getRoomImfDAO().findById(r.getRoom().getNum());
                    //todo 추가 결제 로직 구현
                    if(member == null){ //비회원인 경우
                        if(roomIfm == null) //빈방인 경우
                            parent.move(new ProductListCartView(parent, r.getRoom()));
                        else { //빈방이 아닌 경우
                            JOptionPane.showMessageDialog(parent,"이미 사용 중인 방입니다.","방 사용 불가",JOptionPane.INFORMATION_MESSAGE);
                        }
                            //parent.move(new UsePurchaseSelectView(parent, r.getRoom(), member));
                    }else{  //회원이 경우
                        if(roomIfm == null){ //빈방이 경우
                            parent.move(new UsePurchaseSelectView(parent, r.getRoom(), member));
                        } else{  //다른 회원이나 다른 사람이 이미 점유한 경우
                            JOptionPane.showMessageDialog(parent,"이미 사용 중인 방입니다.","방 사용 불가",JOptionPane.INFORMATION_MESSAGE);
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

        //right
        JPanel right = new JPanel();
        right.setLayout(new GridBagLayout());

        //right-top
        JPanel rightTop = new JPanel();
        rightTop.setLayout(new GridLayout(3,1));
        right.add(rightTop, DefaultFrame.easyGridBagConstraint(0,0,1,1));

        //rightTop1
        JPanel rightT1 = new JPanel();
        rightT1.setBackground(Color.WHITE);
        rightT1.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightTop.add(rightT1);
        ImageIcon defaultImg = new ImageIcon(DefaultFrame.PATH + "/images/SoobinUse/default.png");
        JLabel defaultLb = new JLabel(defaultImg);
        defaultLb.setPreferredSize(new Dimension(40, 40));
        JLabel textLabel1 = new JLabel("사용 가능");
        textLabel1.setFont(new DefaultFont(40));

        defaultLb.setHorizontalAlignment(JLabel.CENTER);
        defaultLb.setVerticalAlignment(JLabel.CENTER);
        rightT1.add(defaultLb);
        rightT1.add(textLabel1);

        //rightTop2
        JPanel rightT2 = new JPanel();
        rightT2.setBackground(Color.WHITE);
        rightT2.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightTop.add(rightT2);
        ImageIcon useImg = new ImageIcon(DefaultFrame.PATH + "/images/SoobinUse/use.png");
        JLabel useLb = new JLabel(useImg);
        useLb.setPreferredSize(new Dimension(40, 40));
        JLabel textLabel2 = new JLabel("이용중");
        textLabel2.setFont(new DefaultFont(40));

        useLb.setHorizontalAlignment(JLabel.CENTER);
        useLb.setVerticalAlignment(JLabel.CENTER);
        rightT2.add(useLb);
        rightT2.add(textLabel2);

        //rightTop3
        JPanel rightT3 = new JPanel();
        rightT3.setBackground(Color.WHITE);
        rightT3.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightTop.add(rightT3);
        ImageIcon dontuseImg = new ImageIcon(DefaultFrame.PATH + "/images/SoobinUse/dontuse.png");
        JLabel dontuseLb = new JLabel(dontuseImg);
        dontuseLb.setPreferredSize(new Dimension(40, 40));
        JLabel textLabel3 = new JLabel("점검중");
        textLabel3.setFont(new DefaultFont(40));


        dontuseLb.setHorizontalAlignment(JLabel.CENTER);
        dontuseLb.setVerticalAlignment(JLabel.CENTER);
        rightT3.add(dontuseLb);
        rightT3.add(textLabel3);

        //right-bottom

        JPanel rightBottom = new JPanel();
        right.add(rightBottom, DefaultFrame.easyGridBagConstraint(0,1,1,1));
        rightBottom.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel feeLb = new JLabel(FreeImageIcon.resizeImageIcon(DefaultFrame.PATH + "/images/SoobinUse/feeClearBg.png", 400, 400));
        rightBottom.add(feeLb);

        JPanel feetable = new JPanel();
        feetable.setBackground(new Color(0x404040));


        this.add(right, DefaultFrame.easyGridBagConstraint(1,0,1,1));

        //Date date = new Date();
    }
}
