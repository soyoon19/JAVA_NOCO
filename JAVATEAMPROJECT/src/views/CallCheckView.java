package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 요청 관리 페이지(관리자)
// 2021011017 김수빈


class CheckCellRender extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if(column == 4)
            return new JButton("check");
        return null;
    }
}

public class CallCheckView extends JPanel {

    public CallCheckView() {
        setLayout(new BorderLayout());
        JPanelOneLabel mss = new JPanelOneLabel("요청 관리 페이지");
        mss.getLabel().setFont(new DefaultFont(60));
        add(mss,BorderLayout.NORTH);

        String [] colcumType = new String [] {"순서", "접수시간","방번호","접수내용","완료여부"};
        Object [] [] requestData = {
                {"1","10:34:12","1", "기기 고장", false},
                {"2","14:54:58","2", "마이크 커버",false},
                {"3","19:28:37","6", "방 정리",false}
        };

        JTable table = new JTable(requestData,colcumType);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        //table.getColumn("완료여부").setCellRenderer(new CheckCellRender());

        add(scrollPane, BorderLayout.CENTER);
    }
}