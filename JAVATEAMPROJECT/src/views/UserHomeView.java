package views;

import custom_component.DefaultFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//유저 홈화면 (entrance)
//2021011017 김수빈
public class UserHomeView extends JPanel implements ActionListener {
    public static Dimension BUTTON_SIZE = new Dimension(500,200);
    private DefaultFrame parent;
    public UserHomeView(DefaultFrame prt) {
        this.parent = prt;
        this.setLayout(new BorderLayout());


        //center
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2,1));
            //center 1
        JPanel center1 = new JPanel();
        center1.setLayout(new GridLayout(1,2));
        center1.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
                //center1_1
        JPanel center1_1 = new JPanel();
        JButton memEntBtn = new JButton
                ("<HTML><body style='text-align:center;'>회원으로<br>입장하기</body></HTML> ");
        memEntBtn.setPreferredSize(new Dimension(BUTTON_SIZE));
        memEntBtn.setFont(new DefaultFont(60));
        center1_1.add(memEntBtn);
                //center1_2
        JPanel center1_2 = new JPanel();
        JButton nomemEntBtn = new JButton
                ("<HTML><body style='text-align:center;'>비회원으로<br>입장하기</body></HTML> ");
        nomemEntBtn.setPreferredSize(new Dimension(BUTTON_SIZE));
        nomemEntBtn.setFont(new DefaultFont(60));
        center1_2.add(nomemEntBtn);
        center1.add(center1_1);
        center1.add(center1_2);

            //center 2
        JPanel center2 = new JPanel();
        JButton callStaBtn = new JButton("<HTML><body style='text-align:center;'>직원<br>호출하기</body></HTML> ");
        callStaBtn.setPreferredSize(new Dimension(BUTTON_SIZE));
        callStaBtn.setFont(new DefaultFont(60));
        center2.add(callStaBtn);
        center2.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));

        center.add(center1);
        center.add(center2);
        this.add(center);

        //bottom
        JPanel btom = new JPanel();
        btom.setLayout(new FlowLayout());

        JLabel introLb = new JLabel("너와 나의 노래방, 노인코래방");
        introLb.setFont(new DefaultFont(70,Font.BOLD));
        btom.add(introLb);
        this.add(btom, BorderLayout.SOUTH);

        nomemEntBtn.addActionListener(this);
        memEntBtn.addActionListener(this);
        callStaBtn.addActionListener(this);
    }

    //페이지 이동
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().contains("직원")){
            //(new ManagerLoginView())
        }else if(e.getActionCommand().contains("비회원")){
            //비회원이 경우 상품 리스트로 이동한다.
            parent.move(this, new RoomSelectView(parent, null));
        }else if(e.getActionCommand().contains("회원")){
            //회원이 경우 로그인 화면으로 이동한다.
            parent.move(this, new UserLoginView());
        }
    }
}
