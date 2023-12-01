package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class WorkerControlView extends JPanel {

    public WorkerControlView(DefaultFrame prt) {
        JPanel ct= new JPanel();
        ct.setLayout(new BorderLayout());

        //버튼 배치
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel workName= new JLabel("직책 OOO");
        JButton reg= new JButton("등록");
        JButton chg= new JButton("수정");
        JButton del= new JButton("삭제");

        top.add(workName);
        top.add(reg);
        top.add(chg);
        top.add(del);

        ct.add(top, BorderLayout.NORTH);
        //테이블 배치

        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{
                        {false, 1, "직책1", "이름1", "010-1111-1111", "id1", "password1", true, "2023-01-01"},
                        {false, 2, "직책2", "이름2", "010-2222-2222", "id2", "password2", false, "2023-01-02"},
                        // ... (직책 정보를 필요에 맞게 추가)
                },
                new Object[]{"선택", "순서", "직책", "이름", "전화번호", "ID", "Password", "관리자 권한", "등록일자"}
        );

        // JTable 생성 및 모델 설정
        JTable table = new JTable(tableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) {
                    return Boolean.class; // 첫 번째 열은 Boolean 타입 (RadioCheckBox)
                } else {
                    return super.getColumnClass(column);
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; // 첫 번째 열만 수정 가능하도록 설정
            }
        };

        // 첫 번째 열에 RadioCheckBox 추가
        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // 스크롤 가능하도록 JScrollPane에 JTable 추가
        JScrollPane scrollPane = new JScrollPane(table);
        ct.add(scrollPane, BorderLayout.CENTER);




    }
}
