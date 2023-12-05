package views;

import custom_component.DefaultFont;
import dto.MemberDTO;
import dto.MemberLogDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberDeletePopup extends JDialog {

    MemberDTO member;
    MemberLogDTO memberLog;
    DefaultFrame parent;

    public MemberDeletePopup(DefaultFrame prt, String M_hp) {
        super(prt, "멤버 정보 삭제", true);
        this.setSize(500, 700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        parent = prt;
        member = parent.getController().getMemberDAO().findById(M_hp);
        memberLog = parent.getController().getMemberLogDAO().findById(M_hp);


        Container ct = getContentPane();
        ct.setLayout(new GridBagLayout());

        //위부분

        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

        //제목 배치
        JPanel topTop = new JPanel();
        JLabel title = new JLabel("회원 정보 삭제");

        topTop.add(title);
        top.add(topTop, BorderLayout.NORTH);

        // 회원 정보 배치 윗부분

        JPanel topBtm = new JPanel();
        JLabel hp = new JLabel("전화번호");
        JTextField T_hp = new JTextField(15);
        T_hp.setText(member.getHp());
        T_hp.setEditable(false);// 변경불가 값
        T_hp.setBackground(Color.WHITE);


        JLabel passwd = new JLabel("비밀번호");
        JTextField T_passwd = new JTextField(20);
        T_passwd.setText(member.getPasswd());
        T_passwd.setEditable(false);

        JLabel birth = new JLabel("생년월일");
        JTextField T_birth = new JTextField(15);
        T_birth.setText(member.getBirthDate().toString());
        T_birth.setEditable(false);

        JLabel pay = new JLabel("총 결제 금액");
        JTextField T_pay = new JTextField("");
        T_pay.setText(String.valueOf(memberLog.getTotalPay()));
        T_pay.setEditable(false);

        JLabel rate = new JLabel("등급");
        JLabel T_rate = new JLabel("");
        T_rate.setText(String.valueOf(memberLog.getM_rate()));


        // 배열로 입력준비
        JLabel[] jlb1 = new JLabel[]{hp, passwd, birth, pay, rate};
        JComponent[] match1 = new JComponent[]{T_hp, T_passwd, T_birth, T_pay, T_rate};

        topBtm.setLayout(new GridLayout(5, 1));

        for (int i = 0; i < 5; i++) {
            jlb1[i].setFont(new DefaultFont(20));
            match1[i].setFont(new DefaultFont(20));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = new Insets(5, 5, 5, 5);
            topBtm.add(jlb1[i]);
            gbc.gridx = 1;
            topBtm.add(match1[i], gbc);
        }

        top.add(topBtm, BorderLayout.CENTER);
        ct.add(top, DefaultFrame.easyGridBagConstraint(0, 0, 1, 5));
        //하단 부분
        JPanel btm = new JPanel();
        btm.setLayout(new BorderLayout());
        JPanel btmTop = new JPanel();

        JLabel totSong = new JLabel("총 보유곡");
        JLabel T_totSong = new JLabel("");
        T_totSong.setText(String.valueOf(memberLog.getHoldSong()));

        JLabel joinDate = new JLabel("가입일자");
        JTextField T_joinDate = new JTextField();
        T_joinDate.setText(member.getJoinDate().toString());
        T_joinDate.setEditable(false);

        JLabel lastDate = new JLabel("마지막 사용 일자");
        JTextField T_lastDate = new JTextField();
        T_lastDate.setText(memberLog.getLastLogin().toString());
        T_lastDate.setEditable(false);
        JLabel[] jlb2 = new JLabel[]{totSong, joinDate, lastDate};
        JComponent[] match2 = new JComponent[]{T_totSong, T_joinDate, T_lastDate};

        btmTop.setLayout(new GridLayout(3, 1));

        for (int i = 0; i < 3; i++) {
            jlb2[i].setFont(new DefaultFont(20));
            match2[i].setFont(new DefaultFont(20));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = new Insets(5, 5, 5, 5);
            btmTop.add(jlb2[i]);
            gbc.gridx = 1;
            btmTop.add(match2[i], gbc);
        }
        btm.add(btmTop, BorderLayout.CENTER);

        JPanel btmBtm = new JPanel();
        btmBtm.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton del = new JButton("삭제");
        btmBtm.add(del);
        btm.add(btmBtm, BorderLayout.SOUTH);
        ct.add(btm, DefaultFrame.easyGridBagConstraint(0, 1, 1, 3));

        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getController().getMemberLogDAO().delete(member.getHp());
                parent.getController().getMemberDAO().delete(member.getHp());
            }
        });
    }
}