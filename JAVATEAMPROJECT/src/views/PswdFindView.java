package views;

import controller_db.Controller;
import custom_component.DefaultFont;
import custom_component.NumberPadPanel;
import dao.MemberDAO;
import dao.MemberLogDAO;
import dto.MemberDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class PswdFindView extends JPanel implements ActionListener {
    private static final int FONT_SIZE = 80;
    JLabel phonelb, birthlb;
    JTextField phonetf, birthtf;
    JButton inquirybt;
    NumberPadPanel np;
    DefaultFrame parent;

    public PswdFindView(DefaultFrame p) {
        parent = p;
        this.setLayout(new BorderLayout());
        // BorderLayout의 Center
        JPanel blc = new JPanel(new GridBagLayout());
        add(blc, BorderLayout.CENTER);

            //Grid Bag Layout의 left
        JPanel glc = new JPanel(new GridLayout(4,1));

            //center-1
        glc.add(new JPanel());

            //center-2
        JPanel center2 = new JPanel(new GridBagLayout());
                //center-2의 left
        center2.add(new JPanel(), DefaultFrame.easyGridBagConstraint(0,0,1,1));
                //center-2의 right
        JPanel bcgr = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phonelb = new JLabel("연락처    : ");
        phonelb.setFont(new DefaultFont(FONT_SIZE));
        bcgr.add(phonelb);
        phonetf = new JTextField(10);
        phonetf.setFont(new DefaultFont(FONT_SIZE));
        bcgr.add(phonetf);
        center2.add(bcgr, DefaultFrame.easyGridBagConstraint(1,0,8,1));

        glc.add(center2);

            //center-3
        JPanel center3 = new JPanel(new GridBagLayout());
                //center-3의 left
        center3.add(new JPanel(),DefaultFrame.easyGridBagConstraint(0,0,1,1));
                //center-3의 right
        JPanel bcbgr = new JPanel(new FlowLayout(FlowLayout.LEFT));
        birthlb = new JLabel("생년월일 : ");
        birthlb.setFont(new DefaultFont(FONT_SIZE));
        bcbgr.add(birthlb);
        birthtf = new JTextField(10);
        birthtf.setFont(new DefaultFont(FONT_SIZE));
        bcbgr.add(birthtf);
        center3.add(bcbgr, DefaultFrame.easyGridBagConstraint(1,0,8,1));

        glc.add(center3);

            //center-4
        glc.add(new JPanel());
        blc.add(glc, DefaultFrame.easyGridBagConstraint(0,0,2,1));

            //Grid Bag Layout의 right
        np = new NumberPadPanel();
        np.setBorder(BorderFactory.createEmptyBorder(30,15,30,15));
        blc.add(np,DefaultFrame.easyGridBagConstraint(1,0,1,1));

        // BorderLayout의 South
        JPanel stp = new JPanel();
        add(stp, BorderLayout.SOUTH);
        stp.setLayout(new FlowLayout());
        stp.setBackground(Color.gray);

        inquirybt = new JButton("조회");
        inquirybt.setPreferredSize(new Dimension(200, 100));
        inquirybt.setFont(new DefaultFont(FONT_SIZE));
        inquirybt.addActionListener(this);
        stp.add(inquirybt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MemberDAO memberDAO = parent.getController().getMemberDAO();
        MemberDTO member = memberDAO.findById(phonetf.getText());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String birth;

        if (member == null) {// || !member.getBirthDate().equals(birthtf.getText()))
            JOptionPane.showMessageDialog(this, "회원정보가 일치하지 않습니다.", "비밀번호 찾기 실패", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        birth = format.format(member.getBirthDate());
        if(!birth.equals(birthtf.getText())){
            JOptionPane.showMessageDialog(this, "회원정보가 일치하지 않습니다.", "비밀번호 찾기 실패", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "당신의 비밀번호는 : " + member.getPasswd(), "비밀번호 찾기 성공", JOptionPane.INFORMATION_MESSAGE);
    }
}