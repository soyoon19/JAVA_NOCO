package views;

import custom_component.DefaultFont;
import dto.MemberDTO;
import dto.MemberLogDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MemberDetailCorrectPopup extends JDialog implements ItemListener, ActionListener {
    private MemberDTO member;
    private MemberLogDTO memberLog;
    private DefaultFrame parent;

    private JTextField T_passwd;
    private JTextField T_hp;
    private JTextField T_r_passwd;

    JRadioButton[] Jrate = new JRadioButton[4];
    char Set_rate;

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
        T_passwd = new JTextField(20);
        T_passwd.setText(member.getPasswd());

        JLabel r_passwd = new JLabel("비밀번호 확인");
        T_r_passwd = new JTextField(20);

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

        String[] names = new String[]{"Common", "Bronze", "Silver", "Gold"};
        ButtonGroup g = new ButtonGroup();
        for (int i = 0; i < Jrate.length; i++) {
            Jrate[i] = new JRadioButton(names[i]);
            Jrate[i].addItemListener(this);
            g.add(Jrate[i]);
            btmTop.add(Jrate[i]);
        }

        //불러온 등급에 따라 버튼 체크
        if (rate1.equals("c")) {
            Jrate[0].setSelected(true);
        } else if (rate1.equals("b")) {
            Jrate[1].setSelected(true);
        } else if (rate1.equals("s")) {
            Jrate[2].setSelected(true);
        } else if (rate1.equals("g")) {
            Jrate[3].setSelected(true);
        }


        //버튼 배열
        JPanel btmBtm = new JPanel();
        btmBtm.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton chg = new JButton("수정");
        chg.addActionListener(this);

        btmBtm.add(chg);
        btm.add(btmBtm, BorderLayout.SOUTH);

        ct.add(btm, DefaultFrame.easyGridBagConstraint(0, 1, 1, 3));
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {


        member.setPasswd(T_passwd.getText());
        memberLog.setM_rate(Set_rate);
        parent.getController().getMemberDAO().update(member);
        parent.getController().getMemberLogDAO().update(memberLog);

        JOptionPane.showMessageDialog(parent, "수정 성공!", "알림", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();

    }

    public void itemStateChanged(ItemEvent ie) {
        if (Jrate[0].isSelected()) {
            Set_rate = 'c';
        } else if (Jrate[1].isSelected()) {
            Set_rate = 'b';
        } else if (Jrate[2].isSelected()) {
            Set_rate = 's';
        } else if (Jrate[3].isSelected()) {
            Set_rate = 'g';
        }
    }


}
