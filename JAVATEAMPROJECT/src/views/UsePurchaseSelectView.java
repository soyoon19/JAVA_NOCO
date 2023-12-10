package views;

import custom_component.DefaultFont;
import custom_component.FreeImageIcon;
import dao.MemberDAO;
import dao.MemberLogDAO;
import dto.MemberDTO;
import dto.MemberLogDTO;
import dto.RoomIfmDTO;
import dto.RoomManageDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class UsePurchaseSelectView extends JPanel implements ActionListener {
    JButton musicUse, productPurchase, roomExit;
    private static final int IMAGE_X = 500;
    private static final int IMAGE_Y = 500;
    private static final int FONT_SIZE = 80;
    private MemberDTO member;
    private RoomManageDTO room;
    private DefaultFrame parent;
    public UsePurchaseSelectView(DefaultFrame prt, RoomManageDTO room, MemberDTO member) {
        setLayout(new BorderLayout());
        parent = prt;
        this.member = member;
        this.room = room;

        //top
        JPanel bot = new JPanel(new GridLayout(1,2,15,15));
        add(bot);

        //left
        //클릭 전 버튼
        ImageIcon img1 = new FreeImageIcon(DefaultFrame.PATH+"/images/musicBefore.png",IMAGE_X,IMAGE_Y);
        musicUse = new JButton("곡 사용",img1);
        musicUse.setBackground(Color.white);
        musicUse.setFont(new DefaultFont(FONT_SIZE));
        musicUse.setContentAreaFilled(false);
        //클릭 후 버튼
        ImageIcon img2 = new FreeImageIcon(DefaultFrame.PATH+"/images/musicAfter.png",IMAGE_X,IMAGE_Y);
        musicUse.setPressedIcon(img2);

        bot.add(musicUse,BorderLayout.CENTER);

        //right
        //클릭 전 버튼
        ImageIcon img3 = new FreeImageIcon(DefaultFrame.PATH+"/images/coinBefore.png",IMAGE_X,IMAGE_Y);
        productPurchase = new JButton("상품 구매",img3);
        productPurchase.setBackground(Color.white);
        productPurchase.setFont(new DefaultFont(FONT_SIZE));
        productPurchase.setContentAreaFilled(false);
        //클릭 후 버튼
        ImageIcon img4 = new FreeImageIcon(DefaultFrame.PATH+"/images/coinAfter.png",IMAGE_X,IMAGE_Y);
        productPurchase.setPressedIcon(img4);

        bot.add(productPurchase);

        musicUse.addActionListener(this);
        productPurchase.addActionListener(this);

        //방을 사용하고 있을 때만 사용가능!
        //bottom
        if(parent.getController().getRoomImfDAO().findById(room.getNum()) != null) {
            roomExit = new JButton("방 퇴장");
            roomExit.addActionListener(this);
            roomExit.setFont(new DefaultFont(FONT_SIZE));
            roomExit.setBackground(Color.white);

            add(roomExit,BorderLayout.SOUTH);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        RoomIfmDTO roomIfm = null;
        if(s.equals("곡 사용")){
            if(parent.getController().getMemberLogDAO().findById(member.getHp()).getHoldSong() == 0){
                JOptionPane.showMessageDialog(this,"보유곡이 없습니다.","사용 불가",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            roomIfm = new RoomIfmDTO();
            Date date = new Date();
            roomIfm.setUserHp(member.getHp());
            roomIfm.setLeftSong(0);
            roomIfm.setUsing(false);
            roomIfm.setNum(room.getNum());
            roomIfm.setPaySong(0);
            roomIfm.setEnterTime(new java.sql.Time(date.getHours(), date.getMinutes(), date.getSeconds()));

            parent.move(new MusicUseView(parent, room, member, roomIfm));
        } else if(s.equals("상품 구매")){
            parent.move(new ProductListCartView(parent, room,  member));
        } else if(s.equals("방 퇴장")){
            int x = JOptionPane.showConfirmDialog(this,"방을 퇴장하시겠습니까?","방 퇴장",JOptionPane.YES_NO_OPTION);
            if(x == JOptionPane.OK_OPTION) {
                roomIfm = parent.getController().getRoomImfDAO().findById(room.getNum());
                MemberLogDTO member = new MemberLogDTO();
                member.setHoldSong(roomIfm.getLeftSong()); //roomifm에 있는 남은 곡 정보를 member의 hold song에 저장
                parent.getController().getRoomImfDAO().delete(roomIfm.getNum()); //roomifm의 num을 받아와 방정보 삭제
                parent.getController().getMemberLogDAO().update(member); //member에 담아 있는 정보로 memberlog를 업데이트
                parent.resetMove(new UserHomeView(parent));
            }
        }
    }
}