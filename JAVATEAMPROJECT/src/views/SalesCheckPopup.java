package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SalesCheckPopup extends JDialog implements ActionListener {

    DefaultFrame parent;

    public SalesCheckPopup(DefaultFrame prt) {

        super(prt, "", true);
        this.setSize(500,700);

        this.parent=prt;
        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        JPanel mainGB = new JPanel(new GridBagLayout());

        JPanel date = new JPanel();
        date.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel date1 = new JLabel("YYYY-MM-DD");
        JLabel sym = new JLabel(" ~ ");
        JLabel date2 = new JLabel("YYYY-MM-DD");
        date.add(date1);
        date.add(sym);
        date.add(date2);


        //
        JPanel gB = new JPanel();
        gB.setLayout(new GridBagLayout());
        JPanel left = new JPanel();
        left.setLayout(new GridLayout(1, 1));//rows은 .length로 수정

        JPanel leftDetail = new JPanel();
        leftDetail.setLayout(new GridLayout(4, 1));
        JPanel FL1 = new JPanel();
        FL1.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel day = new JLabel("날짜 YYYY-MM-YY");
        FL1.add(day);

        JPanel FL2 = new JPanel();
        FL2.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel sale = new JLabel("매출 n원");
        FL2.add(sale);

        JPanel FL3 = new JPanel();
        FL3.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel cost = new JLabel("원가 n원");
        FL3.add(cost);

        JPanel FL4 = new JPanel();
        FL4.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel profit = new JLabel("순이익 n원");
        FL4.add(profit);

        JPanel FL5 = new JPanel();
        FL5.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton det = new JButton("상세히");
        det.addActionListener(this);
        FL5.add(det);

        JPanel leftgB = new JPanel();
        leftgB.setLayout(new GridLayout(1, 2));
        leftgB.add(FL4);
        leftgB.add(FL5);

        leftDetail.add(FL1);
        leftDetail.add(FL2);
        leftDetail.add(FL3);
        leftDetail.add(leftgB);

        left.add(leftDetail);
        main.add(date, BorderLayout.NORTH);
        mainGB.add(left, DefaultFrame.easyGridBagConstraint(0, 0, 4, 1));

        JPanel right = new JPanel();
        right.setLayout(new BorderLayout());

        JPanel rightTop = new JPanel();
        rightTop.setLayout(new GridLayout(1, 2));
        JButton saleBtn = new JButton("총 매출");
        JButton saleList = new JButton("상품 판매 목록");

        rightTop.add(saleBtn);
        rightTop.add(saleList);

        JLabel saleDate1 = new JLabel("yyyy-mm-dd");
        JLabel space = new JLabel(" ~ ");
        JLabel saleDate2 = new JLabel("yyyy-mm-dd");
        JLabel totSale = new JLabel("총 매출 n원");
        JLabel totCost = new JLabel(" 총 원가 n원");
        JLabel saleProfit = new JLabel("영업 이익 n원");

        JPanel Date = new JPanel();
        Date.setLayout(new FlowLayout(FlowLayout.LEFT));
        Date.add(saleDate1);
        Date.add(space);
        Date.add(saleDate2);

        JPanel rightCenter1 = new JPanel();
        rightCenter1.setLayout(new BorderLayout());

        JPanel rightCenter2 = new JPanel();
        rightCenter2.setLayout(new BorderLayout());
        JPanel ifm1 = new JPanel();
        ifm1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel ifm2 = new JPanel();
        ifm2.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel ifm3 = new JPanel();
        ifm3.setLayout(new FlowLayout(FlowLayout.CENTER));
        ifm1.add(totSale);
        ifm2.add(totCost);
        ifm3.add(saleProfit);
        JPanel ifm = new JPanel();
        ifm.setLayout(new GridLayout(3, 1));
        ifm.add(ifm1);
        ifm.add(ifm2);
        ifm.add(ifm3);

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


        rightCenter1.add(Date, BorderLayout.NORTH);
        rightCenter1.add(ifm, BorderLayout.CENTER);
        rightCenter2.add(scrollPane, BorderLayout.CENTER);

        saleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                right.remove(rightCenter2);
                right.add(rightCenter1);
                right.repaint();
                right.revalidate();
            }
        });

        saleList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                right.remove(rightCenter1);
                right.add(rightCenter2);
                right.repaint();
                right.revalidate();

            }
        });

        right.add(rightTop, BorderLayout.NORTH);
        right.add(rightCenter2, BorderLayout.CENTER);
        right.add(rightCenter1, BorderLayout.CENTER);
        mainGB.add(right, DefaultFrame.easyGridBagConstraint(1, 0, 6, 1));
        main.add(mainGB, BorderLayout.CENTER);
        this.add(main);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e){
        (new SalesDetailPopup(parent)).setVisible(true);

    }


}
