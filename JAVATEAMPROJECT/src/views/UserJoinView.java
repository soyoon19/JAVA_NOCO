package views;

import custom_component.DefaultFont;
import custom_component.NumberPadListener;
import custom_component.NumberPadPanel;
import dao.MemberDAO;
import dto.MemberDTO;
import dto.MemberLogDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class UserJoinView extends JPanel implements ActionListener {
    public static final int HP_INDEX = 0
            , BIRTH_INDEX = 1
            , PW_INDEX = 2
            , PW_CHECK_INDEX = 3;
    DefaultFrame parent;
    private JTextField[] joinTfs;
    private JButton okBtn, noBtn;

    private JCheckBox agreeCB;

    public UserJoinView(DefaultFrame prt){
        this.parent = prt;
        this.setLayout(new BorderLayout());

        NumberPadPanel np = new NumberPadPanel();

        //top
        JPanel top = new JPanel();
        JLabel loginLb = new JLabel("회원가입");
        loginLb.setFont(new DefaultFont());
        top.add(loginLb);
        this.add(top, BorderLayout.NORTH);



        //center
        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());

        //centerL
        JPanel centerL = new JPanel();
        centerL.setLayout(new GridLayout(5, 1));
        String[] joinStr = {"연락처 : ", "생년월일 : ", "비밀번호 : ", "비밀번호 재입력 : "};
        JLabel[] joinLbs = new JLabel[joinStr.length];
        joinTfs = new JTextField[joinStr.length];
        //joinTfs.toString(new NumberPadListener(joinTfs, np));

        //cetnerL1~centerL4
        for(int i = 0; i < joinStr.length; i++){
            JPanel p = new JPanel();
            p.setLayout(new FlowLayout(FlowLayout.LEFT));
            joinLbs[i] = new JLabel(joinStr[i]);
            if(i < 2 ) joinTfs[i] = new JTextField(11);
            else joinTfs[i] = new JPasswordField(11);

            joinLbs[i].setFont(new DefaultFont(50));
            joinTfs[i].setFont(new DefaultFont(50));
            joinTfs[i].addMouseListener(new NumberPadListener(joinTfs[i],np));

            p.add(joinLbs[i]); p.add(joinTfs[i]);
            centerL.add(p);
        }

        //centerL5
        JPanel centerL5 = new JPanel();
        centerL5.setLayout(new GridLayout(2,1));
        //centerL5-1
        JPanel centerL5_1 = new JPanel();
        centerL5_1.setLayout(new FlowLayout(FlowLayout.LEFT));
        centerL5.add(centerL5_1);

        //centerL5-2
        JPanel centerL5_2 = new JPanel();
        centerL5_2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel agreeLb = new JLabel("\">>개인정보 처리 방침 동의");
        agreeCB = new JCheckBox();
        agreeCB.setEnabled(false); //사용자가 임의로 선택하지 못하게
        agreeLb.setFont(new DefaultFont(30));
        centerL5_2.add(agreeLb);
        centerL5_2.add(agreeCB);
        centerL5.add(centerL5_2);

        centerL.add(centerL5);

        center.add(centerL, DefaultFrame.easyGridBagConstraint(0,0,6,1));

        //centerR
        center.add(np, DefaultFrame.easyGridBagConstraint(1,0,3,1));
        this.add(center, BorderLayout.CENTER);

        //centerBtom
        JPanel centerBtom = new JPanel();
        centerBtom.setLayout(new FlowLayout());

        JButton celBtn, checkBtn;
        celBtn = new JButton("");
        checkBtn = new JButton();

        celBtn.setFont(new DefaultFont(30));
        checkBtn.setFont(new DefaultFont(30));

        //bottom
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        // 확인 버튼 초기 상태는 비활성화
        okBtn = new JButton("확인");
        okBtn.addActionListener(this);
        okBtn.setFont(new DefaultFont(50));
        bottom.add(okBtn);

        // 취소 버튼
        noBtn = new JButton("취소");
        noBtn.addActionListener(this);
        noBtn.setFont(new DefaultFont(50));
        bottom.add(noBtn);

        okBtn.setFont(new DefaultFont(50));
        noBtn.setFont(new DefaultFont(50));
        bottom.add(okBtn);
        bottom.add(noBtn);



        this.add(bottom, BorderLayout.SOUTH);

        agreeLb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                (new UserJoinPerssonInfoPopup(parent,agreeCB)).setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                agreeLb.setFont(new DefaultFont(30, Font.BOLD));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                agreeLb.setFont(new DefaultFont(30, Font.TRUETYPE_FONT));
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        MemberDAO memberDAO = parent.getController().getMemberDAO();
        MemberDTO member = memberDAO.findById(joinTfs[0].getText());
        Date date = new Date();

        // 예외처리
        if (s.equals("확인")) {
            Date birthDate = null;
            try {

                //1. checkBox를 안 하는 경우
                if(!agreeCB.isSelected()){
                    JOptionPane.showMessageDialog(this, "회원 동의가 필요합니다.", "가입 오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //2. 전화번호가 11자리가 아닌 경우
                if (joinTfs[HP_INDEX].getText().length() != 11) {
                    JOptionPane.showMessageDialog(this, "올바른 형식의 전화번호를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //3. 이미 회원가입된 전화번호인 경우
                if (member != null){
                    JOptionPane.showMessageDialog(this, "이미 가입된 회원입니다.", "가입 오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }



                // TODO: Check if the date is valid (you may use a JComboBox for date selection)
                // 3. 날짜가 잘못 입력된 경우 --> ComboBox로 구현해도 가능
                // 콤

                 try{
                     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                     formatter.setLenient(false);

                     birthDate = formatter.parse(joinTfs[BIRTH_INDEX].getText());
                 }catch(ParseException e2){
                     JOptionPane.showMessageDialog(this, "유효하지 않은 날짜입니다", "입력 오류", JOptionPane.ERROR_MESSAGE);
                     System.out.println(birthDate);
                     return;
                 }

                 if(date.compareTo(birthDate) < 0){ //입력한 날짜가 미래라면 처리
                     JOptionPane.showMessageDialog(this, "유효하지 않은 날짜입니다!", "입력 오류", JOptionPane.ERROR_MESSAGE);
                     return;
                 }


                // Check if passwords match
                if (!joinTfs[PW_INDEX].getText().equals(joinTfs[PW_CHECK_INDEX].getText())) {
                    JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Proceed with registration if all checks pass
                member = new MemberDTO();
                member.setHp(joinTfs[HP_INDEX].getText());
                member.setPasswd(joinTfs[PW_INDEX].getText());

                // TODO: 날짜 유효성 검사 (날짜 선택을 위해 JComboBox 사용 가능)


                member.setJoinDate(new java.sql.Date(date.getYear(), date.getMonth(), date.getDate()));
                member.setBirthDate(new java.sql.Date(birthDate.getYear(), birthDate.getMonth(), birthDate.getDate()));

                MemberLogDTO memberLogDTO = new MemberLogDTO();
                memberLogDTO.setHoldSong(0);
                memberLogDTO.setLastLogin(new java.sql.Date(date.getYear(), date.getMonth(), date.getDate()));
                memberLogDTO.setPhone(joinTfs[HP_INDEX].getText());
                memberLogDTO.setM_rate('C');
                memberLogDTO.setTotalPay(0);

                // TODO: 회원 및 회원 로그를 데이터베이스에 삽입
                // parent.getController().getMemberDAO().insert(member);
                // parent.getController().getMemberLogDAO().insert(memberLogDTO);

                // TODO: 회원가입 성공 메시지 표시
                JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
                // UserLoginView로 이동
                parent.move(new UserLoginView(parent));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (s.equals("취소")) {
            for (int i = 0; i < joinTfs.length; i++)
                joinTfs[i].setText("");

            // 홈 화면으로 이동
            parent.move(new UserHomeView(parent));
        }
    }
}
