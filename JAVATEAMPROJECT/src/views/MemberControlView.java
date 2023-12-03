package views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MemberControlView extends JPanel {
DefaultFrame parent;
    public MemberControlView(DefaultFrame prt) {

        parent==prt;
        this.setSize(WIDTH,HEIGHT);

        JPanel ct = new JPanel();
        ct.setLayout(new GridBagLayout());


        //위부분 레이아웃 배치
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());

        //맨 위쪽 뒤로 가기 버튼 레이아웃

        JPanel topTop = new JPanel();
        topTop.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton back = new JButton("icon");

        topTop.add(back);

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
        JComboBox strCombo1 = new JComboBox(memCategory);
        //strCombo1.addActionListener(this);

        topLeftT.add(mem);
        topLeftT.add(strCombo1);

        // topleftB 왼쪽 아래 부분 전화번호 레이아웃

        JLabel hp = new JLabel("전화번호");
        JTextField T_hp = new JTextField();
        JButton B_hp = new JButton("icon");
        topLeftB.add(hp);
        topLeftB.add(T_hp);
        topLeftB.add(B_hp);

        // 오른쪽 윗 부분 날짜 설정 레이아웃


        JCheckBox c = new JCheckBox();
        String[] date = new String[]{"가입일자", " 마지막사용일자"};
        JComboBox strCombo2 = new JComboBox(date);


        JLabel firDate = new JLabel("YYYY-MM-DD");
        JButton fir = new JButton("icon");
        JLabel sym = new JLabel("  ~  ");
        JLabel secDate = new JLabel("YYYY-MM-DD");
        JButton sec = new JButton("icon");


        topRightT.add(c);
        topRightT.add(strCombo2);
        topRightT.add(firDate);
        topRightT.add(fir);
        topRightT.add(sym);
        topRightT.add(secDate);
        topRightT.add(sec);

        //오른쪽 아랫 부분 버튼 레이아웃
        JButton cor = new JButton("수정");
        JButton del = new JButton("삭제");

        topRightB.add(cor);
        topRightB.add(del);

        topLeft.add(topLeftT);
        topLeft.add(topLeftB);
        topRight.add(topRightT);
        topRight.add(topRightB);
        topBtm.add(topLeft);
        topBtm.add(topRight);

        top.add(topTop, BorderLayout.NORTH);
        top.add(topBtm, BorderLayout.CENTER);
        ct.add(top, DefaultFrame.easyGridBagConstraint(0, 0, 1, 3));

        //아랫부분 테이블 레이아웃 배치
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        // JTable 모델
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{
                        {false, "", "", "", "", "", ""},
                        {false, "", "", "", "", "", ""},
                },
                new Object[]{"선택", "순서", "주문코드", "지불금액", "주문내역", "사용일자"}
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
        JTable table = new JTable(tableModel);
        table.getColumnModel().getColumn(0).setCellRenderer(new NoMemberControlView.CheckBoxRenderer());
        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // 스크롤 가능하도록 JScrollPane에 JTable 추가
        JScrollPane scrollPane = new JScrollPane(table);
        main.add(scrollPane,BorderLayout.CENTER);
        ct.add(main, DefaultFrame.easyGridBagConstraint(0, 1, 1, 7));
        this.add(ct);
    }

    //Jcombox 액션
    public void actionPeformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        int index = cb.getSelectedIndex();
    }
    private static class CheckBoxRenderer extends DefaultTableCellRenderer {
        private final JCheckBox checkBox = new JCheckBox();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            checkBox.setSelected((Boolean) value);
            return checkBox;
        }
    }

}
