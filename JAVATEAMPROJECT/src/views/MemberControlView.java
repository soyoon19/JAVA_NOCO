package views;

import custom_component.DefaultFont;
import dao.MemberDAO;
import dao.MemberLogDAO;
import dto.MemberDTO;
import dto.MemberLogDTO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class MemberControlView extends JPanel implements ActionListener {
    private JTable table;
    //모든 회원의 정보를 저장할 ArrayList을 생성
    private ArrayList<MemberDTO> members;
    private ArrayList<MemberLogDTO> memberLogs;
    private DefaultFrame parent;
    private Vector<Vector<Object>> memberList;
    JComboBox strCombo1;

    public MemberControlView(DefaultFrame prt) {
        this.parent=prt;
        this.setLayout(new BorderLayout());
        //회원의 모든 정적인 정보를 가져온다.
        members = parent.getController().getMemberDAO().findAll();
        //회원의 모든 동적인 정보를 가져온다.
        memberLogs = parent.getController().getMemberLogDAO().findAll();


        this.setSize(WIDTH, HEIGHT);

        JPanel ct = new JPanel();
        ct.setLayout(new GridBagLayout());


        //위부분 레이아웃 배치
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

        //맨 위쪽 뒤로 가기 버튼 레이아웃


        JPanel topBtm = new JPanel();
        topBtm.setLayout(new GridLayout(1, 2));

        // 왼쪽
        JPanel topLeft = new JPanel();
        topLeft.setLayout(new GridLayout(2, 1));
        JPanel topLeftT = new JPanel();
        topLeftT.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel topLeftB = new JPanel();
        topLeftB.setLayout(new FlowLayout(FlowLayout.LEFT));

        // 오른쪽
        JPanel topRight = new JPanel();
        topRight.setLayout(new GridLayout(2, 1));
        JPanel topRightT = new JPanel();
        topRightT.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel topRightB = new JPanel();
        topRightB.setLayout(new FlowLayout(FlowLayout.RIGHT));


        //topleftT 왼쪽 위 부분 회원 레이아웃

        JLabel mem = new JLabel("회원");

        // 회원 JComboBox
        String[] memCategory = new String[]{"회원", "비회원"};
        strCombo1 = new JComboBox(memCategory);
        strCombo1.addActionListener(this);

        topLeftT.add(mem);
        topLeftT.add(strCombo1);

        // topleftB 왼쪽 아래 부분 전화번호 레이아웃

        JLabel hp = new JLabel("전화번호");
        JTextField T_hp = new JTextField();
        T_hp.setPreferredSize(new Dimension(120,20));
        JButton B_hp = new JButton("search");
        topLeftB.add(hp);
        topLeftB.add(T_hp);
        topLeftB.add(B_hp);

        // 오른쪽 윗 부분 날짜 설정 레이아웃


        JCheckBox c = new JCheckBox();
        String[] date = new String[]{"가입일자", " 마지막사용일자"};
        JComboBox strCombo2 = new JComboBox(date);


        JLabel firDate = new JLabel("YYYY-MM-DD");
        JButton fir = new JButton("날짜 선택");
        JLabel sym = new JLabel("  ~  ");
        JLabel secDate = new JLabel("YYYY-MM-DD");
        JButton sec = new JButton("날짜 선택");


        topRightT.add(c);
        topRightT.add(strCombo2);
        topRightT.add(firDate);
        topRightT.add(fir);
        topRightT.add(sym);
        topRightT.add(secDate);
        topRightT.add(sec);

        //오른쪽 아랫 부분 버튼 레이아웃
        JButton detail = new JButton("조회");
        JButton cor = new JButton("수정");
        JButton del = new JButton("삭제");
        detail.addActionListener(this);
        del.addActionListener(this);
        cor.addActionListener(this);
        topRightB.add(detail);
        topRightB.add(cor);
        topRightB.add(del);

        topLeft.add(topLeftT);
        topLeft.add(topLeftB);
        topRight.add(topRightT);
        topRight.add(topRightB);
        topBtm.add(topLeft);
        topBtm.add(topRight);


        top.add(topBtm, BorderLayout.CENTER);
        ct.add(top, DefaultFrame.easyGridBagConstraint(0, 0, 1, 3));

        //아랫부분 테이블 레이아웃 배치
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        Object[] colum = new Object[]{"선택", "회원 전화번호", "비밀번호", "생년월일", "총 결제 금액", "등급", "가입 일자", "마지막로그인일자"};
        Vector<Object> colvec = new Vector<>();
        for (int i = 0; i < colum.length; i++) {
            colvec.add(colum[i]);
        }


        memberList = new Vector<>(new Vector<>());



        /*Object[][] memberList = new Object[members.size()][];


        for (int i = 0; i < members.size(); i++) {
            MemberDTO member = members.get(i);
            MemberLogDTO memberLog = parent.getController().getMemberLogDAO().findById(member.getHp());

            memberList[i] = new Object[]{
                    false, member.getHp(), member.getPasswd(), member.getBirthDate(),
                    memberLog.getTotalPay(), String.valueOf(memberLog.getM_rate()), member.getJoinDate().toString(),
                    memberLog.getLastLogin().toString()
            };
        }*/

        // JTable 모델
        DefaultTableModel tableModel = new DefaultTableModel(
                memberList, colvec
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class; // Checkbox column
                } else {
                    return String.class; // Other columns
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // Make only the checkbox column editable
            }
        };


        // JTable 생성 및 모델 설정
        table = new JTable(tableModel);
        MtableUpdate();


        table.getColumnModel().getColumn(0).setCellRenderer(new NoMemberControlView.CheckBoxRenderer());
        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        // 스크롤 가능하도록 JScrollPane에 JTable 추가
        JScrollPane scrollPane = new JScrollPane(table);
        main.add(scrollPane, BorderLayout.CENTER);
        ct.add(main, DefaultFrame.easyGridBagConstraint(0, 1, 1, 7));
        this.add(ct);
    }

    //Jcombox 액션
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        MemberDTO member = members.get(table.getSelectedRow());
        JPanel movePage = null;
        switch (s) {
            case "삭제":
                System.out.println(table.getSelectedRow());
                if (table.getSelectedRow() < 0) {
                    //JOptionPane
                    return;
                }
                (new MemberDeletePopup(parent, member.getHp())).setVisible(true);
                break;
            case "수정":
                (new MemberDetailCorrectPopup(parent, member.getHp())).setVisible(true);
                break;

            case "조회":
                (new MemberDetailPopup(parent, member.getHp())).setVisible(true);
                break;
            case"comboBoxChanged":
                if("비회원".equals(strCombo1.getSelectedItem())){
                    (new NoMemberControlView(parent)).setVisible(true);
        }
        }

        MtableUpdate();

        //JComboBox cb = (JComboBox) e.getSource();
        //int index = cb.getSelectedIndex();
    }

    private static class CheckBoxRenderer extends DefaultTableCellRenderer {
        private final JCheckBox checkBox = new JCheckBox();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            checkBox.setSelected((Boolean) value);
            return checkBox;
        }
    }


    public void MtableUpdate() {
        memberList.removeAllElements();
        members = parent.getController().getMemberDAO().findAll();

        for (int i = 0; i < members.size(); i++) {
            MemberDTO member = members.get(i);
            MemberLogDTO memberLog = parent.getController().getMemberLogDAO().findById(member.getHp());
            Vector<Object> rowData = new Vector<>();

            rowData.add(false);
            rowData.add(member.getHp());
            rowData.add(member.getPasswd());
            rowData.add(member.getBirthDate());
            rowData.add(memberLog.getTotalPay());
            rowData.add(String.valueOf(memberLog.getM_rate()));
            rowData.add(member.getJoinDate().toString());
            rowData.add(memberLog.getLastLogin().toString());

            memberList.add(rowData);
        }
        table.updateUI();
    }

}