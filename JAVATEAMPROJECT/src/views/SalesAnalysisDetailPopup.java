package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.*;

public class SalesAnalysisDetailPopup extends JDialog {
    public SalesAnalysisDetailPopup(DefaultFrame prt) {
        super(prt, "", true);
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel ct= new JPanel();
        ct.setLayout(new GridBagLayout());

        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{
                        {"", "", "", ""},
                        {"", "", "", ""},
                },
                new Object[]{"날짜", "상품 명", "판매금액", "판매수량"}
        );

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        ct.add(scrollPane,DefaultFrame.easyGridBagConstraint(0,0,3,1));
        this.add(ct);
        setLocationRelativeTo(null);


        //

    }


}
