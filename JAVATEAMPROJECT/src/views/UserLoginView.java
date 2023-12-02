package views;

import custom_component.DefaultFont;
import custom_component.NumberPadPanel;

import javax.swing.*;
import java.awt.*;

public class UserLoginView extends JPanel {
    public UserLoginView(){
        this.setLayout(new BorderLayout());

        //top
        JPanel top = new JPanel();
        JLabel loginLb = new JLabel("LOGIN");
        loginLb.setFont(new DefaultFont());
        top.add(loginLb);
        this.add(top, BorderLayout.NORTH);



        //center
        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());

            //center1
        JPanel center1 = new JPanel();
        center1.setLayout(null);
        JLabel hpLb = new JLabel("HP : "), pwLb = new JLabel("PW : ");
        hpLb.setFont(new DefaultFont(30));
        pwLb.setFont(new DefaultFont(30));


        JTextField hpTf = new JTextField(11);
        JTextField pwTf = new JTextField(11);
        hpTf.setFont(new DefaultFont(30));
        pwTf.setFont(new DefaultFont(30));

        hpLb.setBounds(30,30,100,30);
        hpTf.setBounds(200, 30, 400,30);

        center1.add(hpLb);
        center1.add(hpTf);

        pwLb.setBounds(30,100,100,30);
        pwTf.setBounds(200, 100, 400,30);

        center1.add(pwLb);
        center1.add(pwTf);

        center.add(center1, DefaultFrame.easyGridBagConstraint(0,0,6,1));

            //center2
        NumberPadPanel np = new NumberPadPanel();
        center.add(np, DefaultFrame.easyGridBagConstraint(1,0,3,1));

        this.add(center, BorderLayout.CENTER);

        //bottom
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());

        JButton celBtn, joinBtn, findPwBtn;
        celBtn = new JButton("");
        joinBtn = new JButton();
        findPwBtn = new JButton();

        celBtn.setFont(new DefaultFont(30));
        joinBtn.setFont(new DefaultFont(30));
        findPwBtn.setFont(new DefaultFont(30));



        bottom.add(celBtn);
        bottom.add(joinBtn);
        bottom.add(findPwBtn);

        bottom.setBackground(Color.RED);
        this.add(bottom, BorderLayout.SOUTH); //박스레이아웃 어렵지만 다양성을 위해 사용하고 싶음
    }



}
