package views;

import custom_component.DefaultFont;
import custom_component.NumberPadListener;
import custom_component.NumberPadPanel;
import dao.MemberDAO;
import dto.MemberDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLoginView extends JPanel implements ActionListener{
    private DefaultFrame parent;
    private JTextField hpTf;
    private JTextField pwTf;
    private NumberPadPanel np;

    public UserLoginView(DefaultFrame prt){
        parent = prt;
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
        hpLb.setFont(new DefaultFont(50));
        pwLb.setFont(new DefaultFont(50));


        hpTf = new JTextField(11);
        pwTf = new JPasswordField(11);
        hpTf.setFont(new DefaultFont(60));
        pwTf.setFont(new DefaultFont(60));

        hpLb.setBounds(30,120,200,130);
        hpTf.setBounds(200, 120, 500,130);

        center1.add(hpLb);
        center1.add(hpTf);

        pwLb.setBounds(30,270,200,130);
        pwTf.setBounds(200, 270, 500,130);

        center1.add(pwLb);
        center1.add(pwTf);

        center.add(center1, DefaultFrame.easyGridBagConstraint(0,0,6,1));

        //center2
        np = new NumberPadPanel();
        center.add(np, DefaultFrame.easyGridBagConstraint(1,0,3,1));

        this.add(center, BorderLayout.CENTER);

        //bottom
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());

        JButton loginBtn, celBtn, joinBtn, findPwBtn;
        loginBtn = new JButton("로그인");
        celBtn = new JButton("취소");
        joinBtn = new JButton("회원가입");
        findPwBtn = new JButton("PW찾기");

        loginBtn.setFont(new DefaultFont(50));
        celBtn.setFont(new DefaultFont(50));
        joinBtn.setFont(new DefaultFont(50));
        findPwBtn.setFont(new DefaultFont(50));

        bottom.add(loginBtn);
        bottom.add(celBtn);
        bottom.add(joinBtn);
        bottom.add(findPwBtn);

        this.add(bottom, BorderLayout.SOUTH);

        celBtn.addActionListener(this);
        joinBtn.addActionListener(this);
        findPwBtn.addActionListener(this);

        pwTf.addMouseListener(new NumberPadListener(pwTf, np));
        hpTf.addMouseListener(new NumberPadListener(hpTf, np));

       loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberDAO memberDAO = parent.getController().getMemberDAO();
                MemberDTO member = memberDAO.findById(hpTf.getText());

                if(member == null){
                    JOptionPane.showMessageDialog(parent, "로그인 아이디 틀림 실패");
                }else if(!member.getPasswd().equals(pwTf.getText())){
                    JOptionPane.showMessageDialog(parent, "비밀번호 틀림");
                }else{
                    JOptionPane.showMessageDialog(parent, "로그인 성공");
                    parent.move(new RoomSelectView(parent, member));
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        switch (s){
            case "취소":
                hpTf.setText("");
                pwTf.setText("");
                break;
            case "회원가입":
                parent.move(new UserJoinView(parent));
                break;
            case "PW찾기":
                parent.move(new PswdFindView(parent));
                break;
        }

    }
}