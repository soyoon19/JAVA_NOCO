package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 요청 관리 페이지(관리자)
// 2021011017 김수빈
public class CallCheckView extends JPanel {

    public CallCheckView() {
        this.setLayout(new BorderLayout());

        // Center
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());

        // JTable 생성
        JTable callTable = new JTable();
        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);

        //데이터 불러오기


        // 요청사항 내에서 페이지 이동 액션리스너
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton leftButton = new JButton("◀");
        JButton rightButton = new JButton("▶");

        // Bottom
        JPanel bottom = new JPanel();
        this.add(bottom, BorderLayout.SOUTH);
    }
}
