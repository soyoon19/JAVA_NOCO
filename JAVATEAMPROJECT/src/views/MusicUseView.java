package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;
import dto.MemberLogDTO;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

class MusicUseViewPanel extends JPanel{
    private MemberLogDTO member;
    JButton useBtn;
    public MusicUseViewPanel(MemberLogDTO m){
        member = m;

        this.setLayout(new BorderLayout());

        //top
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(2, 1));
        JLabel useMusicLb = new JLabel("보유 곡   100");
        useMusicLb.setFont(new DefaultFont(110));
        useMusicLb.setBorder(BorderFactory.createEmptyBorder(0, 100, 0,0));
        JLabel usedMusicLb = new JLabel("사용할 곡 0");
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
}

class MusicUseEditPanel extends JPanel{
    private static final Dimension BUTTON_DIMENSION = new Dimension(200,70);
    private static final int EA = 5;
    JButton allBtn, resetBtn;
    JLabel[] useMusicLb;
    JButton[] upBtn, downBtn;

    public MusicUseEditPanel(){
        this.setLayout(new BorderLayout());

        //center
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3,1));
        center.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
        useMusicLb = new JLabel[3];
        upBtn = new JButton[3]; downBtn = new JButton[3];

        for(int i = 0; i < useMusicLb.length; i++){
            //center-i
            JPanel centerI = new JPanel();
            centerI.setLayout(new GridLayout(2, 1));
            centerI.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
                //center-i-1
            useMusicLb[i] = new JLabel("< " + (i + 1) * EA + "곡 >");
            useMusicLb[i].setFont(new DefaultFont(30));
            centerI.add(useMusicLb[i]);
                //center-i-2
            JPanel centerI2 = new JPanel();
            centerI2.setLayout(new GridLayout(1, 2));
                    //centeri-2-left
            JPanel centerI2L= new JPanel();
            upBtn[i] = new JButton((i + 1) * EA + " Up");
            upBtn[i].setPreferredSize(BUTTON_DIMENSION);
            upBtn[i].setFont(new DefaultFont(30));
            centerI2L.add(upBtn[i]);
            centerI2.add(centerI2L);
                    //centeri-2-right
            JPanel centerI2R = new JPanel();
            downBtn[i] = new JButton((i + 1) * EA  + " Down");
            downBtn[i].setPreferredSize(BUTTON_DIMENSION);
            downBtn[i].setFont(new DefaultFont(30));
            centerI2R.add(downBtn[i]);
            centerI2.add(centerI2R);
            centerI.add(centerI2);

            center.add(centerI);
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
        allBtn.setPreferredSize(BUTTON_DIMENSION);
        btmL.add(allBtn);

            //bottom-right
        JPanel btmR = new JPanel();
        btmR.setLayout(new FlowLayout());
        resetBtn = new JButton("RESET");
        resetBtn.setFont(new DefaultFont(30));
        resetBtn.setPreferredSize(BUTTON_DIMENSION);
        btmR.add(resetBtn);

        btm.add(btmL);
        btm.add(btmR);

        this.add(btm, BorderLayout.SOUTH);
    }
}


public class MusicUseView extends JPanel {
    MusicUseViewPanel view;
    MusicUseEditPanel edit;
    DefaultFrame parent;
    public MusicUseView(DefaultFrame prt){
        this.setLayout(new GridBagLayout());
        this.parent  = prt;
        this.view = new MusicUseViewPanel(new MemberLogDTO());
        new MemberLogDTO();
        this.edit = new MusicUseEditPanel();

        //left
        view.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        this.add(view, DefaultFrame.easyGridBagConstraint(0,0,7,1));

        //right
        edit.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));
        this.add(edit, DefaultFrame.easyGridBagConstraint(1,0,2,1));
    }
}
