package views;

import custom_component.DefaultFont;
import custom_component.NumberPadPanel;

import javax.swing.*;
import java.awt.*;

//직원호출페이지
//2021011017 김수빈

public class StaffCallView extends JPanel {

    public static Dimension BUTTON_SIZE = new Dimension(400,200);

    public StaffCallView() {
        this.setLayout(new GridBagLayout());
        //left
        JPanel left = new JPanel();
        left.setLayout(new GridLayout(2,2));
        left.setBorder(BorderFactory.createEmptyBorder(70,0,0,0));

        //left-1
        JPanel left1 = new JPanel();
        JButton cleanBtn = new JButton("정리요청");
        cleanBtn.setPreferredSize(new Dimension(BUTTON_SIZE));
        cleanBtn.setFont(new DefaultFont(50));
        left1.add(cleanBtn);
        left.add(left1);
        //left-2
        JPanel left2 = new JPanel();
        JButton coverBtn = new JButton("마이크 커버");
        coverBtn.setPreferredSize(new Dimension(BUTTON_SIZE));
        coverBtn.setFont(new DefaultFont(50));
        left2.add(coverBtn);
        left.add(left2);
        //left-3
        JPanel left3 = new JPanel();
        JButton errorBtn = new JButton("기기 오류");
        errorBtn.setPreferredSize(new Dimension(BUTTON_SIZE));
        errorBtn.setFont(new DefaultFont(50));
        left3.add(errorBtn);
        left.add(left3);
        //left-4
        JPanel left4 = new JPanel();
        JButton staffBtn = new JButton("직원 호출");
        staffBtn.setPreferredSize(new Dimension(BUTTON_SIZE));
        staffBtn.setFont(new DefaultFont(50));
        left4.add(staffBtn);
        left.add(left4);

        this.add(left, DefaultFrame.easyGridBagConstraint(0,0,7,1));

        //right
        JPanel right = new JPanel();
        right.setLayout(new GridBagLayout());

        //right-1
        JPanel right1 = new JPanel();
        JLabel whatyouwant = new JLabel
                ("<HTML><body style='text-align:center;'>도움이 필요한<br>방 번호를 입력해주세요.</body></HTML> ");
        whatyouwant.setFont(new DefaultFont(50));
        right1.add(whatyouwant);
        right.add(right1, DefaultFrame.easyGridBagConstraint(0,0,1,1));
        //right-2
        JPanel right2 = new JPanel();
        //right2.setLayout(new GridBagLayout());
        right2.setBackground(Color.PINK);
        right.add(right2, DefaultFrame.easyGridBagConstraint(0,1,1,1));
        //right-3
        NumberPadPanel np = new NumberPadPanel();
        right.add(np, DefaultFrame.easyGridBagConstraint(0,2,1,2));

        this.add(right, DefaultFrame.easyGridBagConstraint(1,0,3,1));
    }
}
