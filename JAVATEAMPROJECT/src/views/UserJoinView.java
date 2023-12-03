package views;

import custom_component.DefaultFont;
import custom_component.NumberPadPanel;

import javax.swing.*;
import java.awt.*;

public class UserJoinView extends JPanel {
    public UserJoinView(){
        this.setLayout(new BorderLayout());

        //top
        JPanel top = new JPanel();
        JLabel loginLb = new JLabel("회원가입");
        loginLb.setFont(new DefaultFont());
        top.add(loginLb);
        this.add(top, BorderLayout.NORTH);



        //center
        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());

        //centerL
        JPanel centerL = new JPanel();
        centerL.setLayout(new GridLayout(5, 1));
        String[] joinStr = {"연락처 : ", "생년월일 : ", "비밀번호 : ", "비밀번호 재입력 : "};
        JLabel[] joinLbs = new JLabel[joinStr.length];
        JTextField[] joinTfs = new JTextField[joinStr.length];

        //cetnerL1~centerL4
        for(int i = 0; i < joinStr.length; i++){
            JPanel p = new JPanel();
            p.setLayout(new FlowLayout(FlowLayout.LEFT));
            joinLbs[i] = new JLabel(joinStr[i]);
            joinTfs[i] = new JTextField(11);

            joinLbs[i].setFont(new DefaultFont(50));
            joinTfs[i].setFont(new DefaultFont(50));

            p.add(joinLbs[i]); p.add(joinTfs[i]);
            centerL.add(p);
        }

        //centerL5
        JPanel centerL5 = new JPanel();
        centerL5.setLayout(new GridLayout(2,1));
        //centerL5-1
        JPanel centerL5_1 = new JPanel();
        centerL5_1.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel checkLb = new JLabel("xxx");
        checkLb.setFont(new DefaultFont(30));
        centerL5_1.add(checkLb);
        centerL5.add(centerL5_1);

        //centerL5-2
        JPanel centerL5_2 = new JPanel();
        centerL5_2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JCheckBox agreeCB = new JCheckBox(">>개인정보 처리 방침 동의");
        agreeCB.setFont(new DefaultFont(30));
        centerL5_2.add(agreeCB);
        centerL5.add(centerL5_2);

        centerL.add(centerL5);

        center.add(centerL, DefaultFrame.easyGridBagConstraint(0,0,6,1));

        //centerR
        NumberPadPanel np = new NumberPadPanel();
        center.add(np, DefaultFrame.easyGridBagConstraint(1,0,3,1));


        this.add(center, BorderLayout.CENTER);

        //centerBtom
        JPanel centerBtom = new JPanel();
        centerBtom.setLayout(new FlowLayout());

        JButton celBtn, checkBtn;
        celBtn = new JButton("");
        checkBtn = new JButton();

        celBtn.setFont(new DefaultFont(30));
        checkBtn.setFont(new DefaultFont(30));

        //bottom
        JButton okBtn, noBtn;
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        okBtn = new JButton("확인");
        noBtn = new JButton("취소");
        bottom.add(okBtn); //확인
        bottom.add(noBtn); //취소

        okBtn.setFont(new DefaultFont(50));
        noBtn.setFont(new DefaultFont(50));
        bottom.add(okBtn);
        bottom.add(noBtn);


        this.add(bottom, BorderLayout.SOUTH);
    }

}

//체크박스가 뒤에 가야하는데 앞에 있음
//어캄?