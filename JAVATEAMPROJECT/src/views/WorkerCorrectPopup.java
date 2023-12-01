package views;

import custom_component.DefaultFont;

import javax.swing.*;
import java.awt.*;

public class WorkerCorrectPopup extends JDialog {
    public static final int WIDTH = 500, HEIGHT = 700;
    public static final int WIDTH1=300, HEIGHT1=300;
    DefaultFrame parent;
    public WorkerCorrectPopup(DefaultFrame prt) {
        super(prt, "", true);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        Container ct = getContentPane();
        ct.setLayout(new GridBagLayout());

        //top
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

        //top-top
        JPanel topTop = new JPanel();
        JLabel infoLb = new JLabel("직원 정보 수정");
        topTop.add(infoLb);
        top.add(topTop, BorderLayout.NORTH);

        //top-bottom
        JPanel topBtm = new JPanel();

        JLabel id= new JLabel("아이디");

        JTextField T_id = new JTextField(10);

        JLabel passwd= new JLabel("비밀번호");

        JTextField T_passwd = new JTextField(10);

        JLabel r_passwd= new JLabel("비밀번호 확인");

        JTextField T_r_passwd = new JTextField(10);

        JLabel name= new JLabel("이름");

        JTextField T_name = new JTextField(10);

        JLabel hp= new JLabel("전화번호");

        JTextField T_hp = new JTextField(10);


        JLabel[] lbs = new JLabel[]{
                id, passwd, r_passwd, name, hp
        };

        JTextField[] tfs = new JTextField[]{
                T_id, T_passwd,T_r_passwd, T_name, T_hp
        };

        topBtm.setLayout(new GridLayout(lbs.length, 1));

        for(int i = 0; i < lbs.length; i++){
            lbs[i].setFont(new DefaultFont(20));
            tfs[i].setFont(new DefaultFont(20));
            GridBagConstraints gbc= new GridBagConstraints();
            gbc.gridx=0;
            gbc.gridy=i;
            gbc.insets= new Insets(5,5,5,5);
            topBtm.add(lbs[i]);

            gbc.gridx=1;
            topBtm.add(tfs[i],gbc);
        }

        top.add(topBtm,BorderLayout.WEST);
        ct.add(top, DefaultFrame.easyGridBagConstraint(0,0,1,5));

        //bottom
        JPanel btm = new JPanel();
        btm.setLayout(new BorderLayout());

        //bottomCenter
        JPanel btmTop = new JPanel();
        btm.add(btmTop, BorderLayout.CENTER);
        JLabel position = new JLabel("직책");
        btmTop.add(position);
        JRadioButton[] post = new JRadioButton[3];
        String[] post_name = {"작원", "매니저", "점장"};


        ButtonGroup g = new ButtonGroup();
        for (int i = 0; i < post.length; i++) {
            post[i] = new JRadioButton(post_name[i]);
            g.add(post[i]);
            btmTop.add(post[i]);
        }

        //bottomBottom
        JPanel btmBtm = new JPanel();
        btmBtm.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton cor = new JButton("수정");

        btmBtm.add(cor);
        btm.add(btmBtm, BorderLayout.SOUTH);
        ct.add(btm, DefaultFrame.easyGridBagConstraint(0,1,1,3));

    }

}
