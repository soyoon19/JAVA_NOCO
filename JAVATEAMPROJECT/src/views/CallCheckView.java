package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 요청 관리 페이지(관리자)
// 2021011017 김수빈
public class CallCheckView extends JPanel {

    public CallCheckView() {
        setLayout(new BorderLayout());
        JPanelOneLabel mss = new JPanelOneLabel("요청 관리 페이지");
        mss.getLabel().setFont(new DefaultFont(60));
        add(mss,BorderLayout.NORTH);

        String [] colcumType = new String [] {"순서", "접수시간","방번호","완료여부"};
        Object [] [] requestData = {
                {"1","10:34:12","1",new JButton()},
                {"2","14:54:58","2",},
                {"3","19:28:37","6",}
        };

        JTable callTable = new JTable(requestData,colcumType);
        JScrollPane scrollPane = new JScrollPane(callTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}