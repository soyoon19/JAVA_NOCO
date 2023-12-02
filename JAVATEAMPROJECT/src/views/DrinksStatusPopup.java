package views;


//음료 상태(판매, 품절, 숨김(사용자에게 안보이게))를 변경해주는 팝업창 (개별 음료, 일관 음료 상태 설정에 사용)
import custom_component.DefaultFont;

import javax.swing.*;
import java.awt.*;


//음료 상태를 보여주는 팝업창
public class DrinksStatusPopup extends JDialog {


    public static final int WIDTH = 660, HEIGHT = 355;

    DefaultFrame parent;

    public DrinksStatusPopup(DefaultFrame parent) {
        super(parent, "음료 상태를 보여주는 팝업창", true);
        this.parent = parent;
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JLabel msg = new JLabel("변경하고 싶은 음료의 상태를 선택해주세요.");
        //상태
        JButton b1 = new JButton("판매");
        JButton b2 = new JButton("품절");
        JButton b3 = new JButton("숨김");

        JButton okBtn = new JButton("확인");
        JButton cancleBtn = new JButton("취소");

        msg.setBounds(30,10,600, 65);
        msg.setFont(new DefaultFont(30));

        b1.setBounds(30, 95, 180, 70);
        b2.setBounds(230, 95, 180, 70);
        //b3.setBounds(330, 95, 180, 70);
        b3.setBounds(430, 95, 180, 70);

        okBtn.setBounds(30, 195, 180, 60);
        cancleBtn.setBounds(430, 195, 180, 60);

        this.add(msg);
        this.add(b1);
        this.add(b2);
        this.add(b3);

        this.add(okBtn);
        this.add(cancleBtn);

        this.setLayout(null);
        this.setVisible(true);
    }

}

