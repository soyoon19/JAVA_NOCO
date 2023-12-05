package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;
import dto.MemberDTO;
import dto.MemberLogDTO;
import dto.RoomIfmDTO;
import dto.RoomManageDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

class MusicUseViewPanel extends JPanel{
    private JButton useBtn;
    private JLabel usedMusicLb;
    public MusicUseViewPanel(int useMax){

        this.setLayout(new BorderLayout());

        //top
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(2, 1));
        JLabel useMusicLb = new JLabel("보유 곡 : " +useMax);
        useMusicLb.setFont(new DefaultFont(110));
        useMusicLb.setBorder(BorderFactory.createEmptyBorder(0, 100, 0,0));
        usedMusicLb = new JLabel("사용할 곡 : 0");
        usedMusicLb.setFont(new DefaultFont(110));
        usedMusicLb.setBorder(BorderFactory.createEmptyBorder(0, 100, 0,0));

        top.add(useMusicLb);
        top.add(usedMusicLb);
        this.add(top, BorderLayout.CENTER);

        //bottom
        JPanel btm = new JPanel();
        btm.setLayout(new FlowLayout(FlowLayout.RIGHT));
        useBtn = new JButton("사용");
        useBtn.setPreferredSize(new Dimension(200, 70));
        useBtn.setFont(new DefaultFont(30));
        btm.add(useBtn);
        this.add(btm, BorderLayout.SOUTH);
    }

    public JButton getUseBtn(){
        return useBtn;
    }

    public void useMusicLabelUpdate(int ea){
        usedMusicLb.setText("사용할 곡 : " + ea);
    }

}


//up, down 영역
class EAButtonSet extends JPanel{
    private static final Dimension BUTTON_DIMENSION = new Dimension(200,70);
    private JButton upBtn, downBtn;
    private int ea;

    public EAButtonSet(int ea){
        this.ea = ea;
        this.setLayout(new GridLayout(2, 1));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
        //center-1
        JLabel useMusicLb = new JLabel("< " + ea + "곡 >");
        useMusicLb.setFont(new DefaultFont(30));
        this.add(useMusicLb);
        //center-2
        JPanel center2 = new JPanel();
        center2.setLayout(new GridLayout(1, 2));
        //center2-L
        JPanel center2L= new JPanel();
        upBtn = new JButton(ea + " Up");
        upBtn.setPreferredSize(BUTTON_DIMENSION);
        upBtn.setFont(new DefaultFont(30));
        center2L.add(upBtn);
        center2.add(center2L);
        //centeri-2-right
        JPanel center2R = new JPanel();
        downBtn = new JButton(ea+ " Down");
        downBtn.setPreferredSize(BUTTON_DIMENSION);
        downBtn.setFont(new DefaultFont(30));
        center2R.add(downBtn);
        center2.add(center2R);
        this.add(center2);

    }

    public JButton getUpBtn() {
        return upBtn;
    }

    public JButton getDownBtn() {
        return downBtn;
    }

    public int getEa() {
        return ea;
    }
}

class MusicUseEditPanel extends JPanel{
    public static final int EA = 5;
    private JButton allBtn, resetBtn;
    private EAButtonSet[] eaBtns;

    public MusicUseEditPanel(){
        this.setLayout(new BorderLayout());

        //center
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3,1));
        center.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
        eaBtns = new EAButtonSet[3];


        for(int i = 0; i < eaBtns.length; i++){
            eaBtns[i] = new EAButtonSet((i + 1) * EA);
            center.add(eaBtns[i]);
        }
        this.add(center, BorderLayout.CENTER);


        //bottom
        JPanel btm = new JPanel();
        btm.setLayout(new GridLayout(1, 2));

            //bottom-left
        JPanel btmL = new JPanel();
        btmL.setLayout(new FlowLayout());
        allBtn = new JButton("ALL");
        allBtn.setFont(new DefaultFont(30));
        allBtn.setPreferredSize( new Dimension(250,90));
        btmL.add(allBtn);

            //bottom-right
        JPanel btmR = new JPanel();
        btmR.setLayout(new FlowLayout());
        resetBtn = new JButton("RESET");
        resetBtn.setFont(new DefaultFont(30));
        resetBtn.setPreferredSize( new Dimension(250,90));
        btmR.add(resetBtn);

        btm.add(btmL);
        btm.add(btmR);

        this.add(btm, BorderLayout.SOUTH);
    }

    public JButton getAllBtn() {
        return allBtn;
    }

    public JButton getResetBtn() {
        return resetBtn;
    }

    public EAButtonSet[] getEaBtns() {
        return eaBtns;
    }
}


public class MusicUseView extends JPanel {
    MusicUseViewPanel view;
    MusicUseEditPanel edit;
    DefaultFrame parent;
    private MemberDTO member;
    private RoomManageDTO room;
    private RoomIfmDTO roomIfm;

    int use = 0, useMax;

    public MusicUseView(DefaultFrame prt, RoomManageDTO rm, MemberDTO mbr, RoomIfmDTO roomIfm){
        this.setLayout(new GridBagLayout());
        this.parent  = prt;
        this.member = mbr;
        this.room = rm;
        this.roomIfm = roomIfm;

        useMax = parent.getController().getMemberLogDAO().findById(member.getHp()).getHoldSong();
        this.view = new MusicUseViewPanel(useMax);
        this.edit = new MusicUseEditPanel();

        //left
        view.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        this.add(view, DefaultFrame.easyGridBagConstraint(0,0,7,1));

        //right
        edit.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        this.add(edit, DefaultFrame.easyGridBagConstraint(1,0,2,1));


        //event
        for(EAButtonSet eaBtn : edit.getEaBtns()){
            eaBtn.getUpBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(use + eaBtn.getEa() > useMax) use = useMax;
                    else use += eaBtn.getEa();
                    view.useMusicLabelUpdate(use);
                }
            });

            eaBtn.getDownBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(use - eaBtn.getEa() < 0) use = 0;
                    else use -= eaBtn.getEa();
                    view.useMusicLabelUpdate(use);
                }
            });
        }

        edit.getResetBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                use = 0;
                view.useMusicLabelUpdate(use);
            }
        });

        edit.getAllBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                use = useMax;
                view.useMusicLabelUpdate(use);
            }
        });

        view.getUseBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(use == 0){
                    //todo 예외 처리
                    return;
                }

                roomIfm.setPaySong(use);
                parent.getController().getRoomImfDAO().insert(roomIfm);
                parent.resetMove(new UserHomeView(parent));
            }
        });
    }

    //TODO 이벤트 처리
}
