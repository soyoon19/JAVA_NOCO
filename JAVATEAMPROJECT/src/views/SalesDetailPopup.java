package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SalesDetailPopup extends JDialog {

    public SalesDetailPopup(DefaultFrame prt) {
        super(prt,"",true);
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.LEFT));

        main.add(top, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());

        JPanel centerLeft = new JPanel();
        centerLeft.setLayout(new BorderLayout());
        JLabel centerLeftTop = new JLabel("날짜 YYYY-MM-DD");

        JPanel centerLeftBtm = new JPanel();
        centerLeftBtm.setLayout(new GridLayout(3, 1));

        JPanel FL1 = new JPanel();
        FL1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel T_FL1 = new JLabel("총 매출 x원");
        JPanel FL2 = new JPanel();
        FL2.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel T_FL2 = new JLabel("총 원가 y원");
        JPanel FL3 = new JPanel();
        FL3.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel T_FL3 = new JLabel("영업이익 x-y원");
        FL1.add(T_FL1);
        FL2.add(T_FL2);
        FL3.add(T_FL3);
        centerLeftBtm.add(FL1);
        centerLeftBtm.add(FL2);
        centerLeftBtm.add(FL3);
        centerLeft.add(centerLeftTop, BorderLayout.NORTH);
        centerLeft.add(centerLeftBtm, BorderLayout.CENTER);
        center.add(centerLeft, DefaultFrame.easyGridBagConstraint(0, 0, 4, 1));

        JPanel centerRight = new JPanel();
        centerRight.setLayout(new BorderLayout());

        JLabel centerRightTop = new JLabel("판매 상품");

        Object[][] data = {
                {"상품1", 10, 500},
                {"상품2", 5, 300},
                {"상품3", 8, 700},
                // Add more rows as needed
        };

        // Column names
        String[] columnNames = {"상품 명", "개수", "판매금액"};

        // Create a DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Create the JTable
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);


        centerRight.add(centerRightTop, BorderLayout.NORTH);
        centerRight.add(scrollPane, BorderLayout.CENTER);

        center.add(centerRight,DefaultFrame.easyGridBagConstraint(1,0,6,1));

        main.add(center,BorderLayout.CENTER);
        this.add(main);

    }
}
