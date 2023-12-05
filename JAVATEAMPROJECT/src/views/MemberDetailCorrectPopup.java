package views;

import custom_component.DefaultFont;
import dto.MemberDTO;
import dto.MemberLogDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberDetailCorrectPopup extends JDialog implements ActionListener {
    MemberDTO member;
    MemberLogDTO memberLog;
    DefaultFrame parent;

    public MemberDetailCorrectPopup(DefaultFrame prt, String M_hp) {
        super(prt, "멤버 정보 수정", true);
        this.setSize(500, 700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        parent = prt;
        member = parent.getController().getMemberDAO().findById(M_hp);
        memberLog = parent.getController().getMemberLogDAO().findById(M_hp);

        Container ct = getContentPane();
        ct.setLayout(new GridBagLayout());

        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

        //상단 부분
        JPanel topTop = new JPanel();
        JLabel title = new JLabel("회원 정보 수정");

        topTop.add(title);
        top.add(topTop, BorderLayout.NORTH);

        //상단 아래부분


        JLabel hp = new JLabel("전화번호");
        JTextField T_hp = new JTextField(15);
        T_hp.setText(member.getHp());

        JLabel passwd = new JLabel("비밀번호");
        JTextField T_passwd = new JTextField(20);
        T_passwd.setText(member.getPasswd());

        JLabel r_passwd = new JLabel("비밀번호 확인");
        JTextField T_r_passwd = new JTextField(20);

        JLabel[] jlb = new JLabel[]{hp, passwd, r_passwd};
        JTextField[] jtf = new JTextField[]{T_hp, T_passwd, T_r_passwd};

        JPanel topBtm = new JPanel();
        topBtm.setLayout(new GridLayout(jtf.length, 1));

        for (int i = 0; i < jtf.length; i++) {
            jlb[i].setFont(new DefaultFont(20));
            jtf[i].setFont(new DefaultFont(20));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = new Insets(5, 0, 5, 5);
            topBtm.add(jlb[i]);

            gbc.gridx = 1;
            topBtm.add(jtf[i], gbc);
        }


        top.add(topBtm, BorderLayout.WEST);
        ct.add(top, DefaultFrame.easyGridBagConstraint(0, 0, 1, 5));


        //하단
        JPanel btm = new JPanel();
        btm.setLayout(new BorderLayout());
        JPanel btmTop = new JPanel();
        JLabel rate = new JLabel("등급");
        String rate1 = String.valueOf(memberLog.getM_rate());


        btmTop.add(rate);
        btm.add(btmTop, BorderLayout.CENTER);

        //등급 라디오 버튼 배치
        JRadioButton[] Jrate = new JRadioButton[3];
        String[] names = new String[]{"Bronze", "Silver", "Gold"};
        ButtonGroup g = new ButtonGroup();
        for (int i = 0; i < Jrate.length; i++) {
            Jrate[i] = new JRadioButton(names[i]);
            g.add(Jrate[i]);
            btmTop.add(Jrate[i]);
        }

        //불러온 등급에 따라 버튼 체크
        if (rate1.equals("b")) {
            Jrate[0].setSelected(true);
        } else if (rate1.equals("s")) {
            Jrate[1].setSelected(true);
        } else if (rate1.equals("g")) {
            Jrate[2].setSelected(true);
        }

        //버튼 배열
        JPanel btmBtm = new JPanel();
        btmBtm.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton chg = new JButton("수정");
        btmBtm.add(chg);
        btm.add(btmBtm, BorderLayout.SOUTH);

        ct.add(btm, DefaultFrame.easyGridBagConstraint(0, 1, 1, 3));

    }

    public void actionPerformed(ActionEvent e) {

        //Newhp=T_hp.getText();
       // Newpasswd=T_passwd.getText();

       // parent.getController().getMemberDAO().insert();//아이디
        //parent.getController().getMemberDAO().insert(T_passwd);//비밀번호

    }
}
