package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;
import dto.StockDTO;
import dto.WorkerDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagerMainView extends JPanel {
    DefaultFrame parent;
    WorkerDTO worker;
    public ManagerMainView(DefaultFrame prt, WorkerDTO worker){
        this.worker = worker;
        this.parent = prt;
        this.setLayout(new GridBagLayout());
        //Grid Bag Layout의 left
        add(new ManagerButtonListPanel(parent, worker),DefaultFrame.easyGridBagConstraint(0,0,1,1));

        //Grid Bag Layout의 right
        JPanel right = new JPanel(new GridBagLayout());
        add(right,DefaultFrame.easyGridBagConstraint(1,0,8,1));
        right.add(new MonthSaleStatus(),DefaultFrame.easyGridBagConstraint(0,0,1,4));
        right.add(new SaleStatus(parent),DefaultFrame.easyGridBagConstraint(0,1,1,3));
    }
}

class ManagerButtonListPanel extends JPanel implements ActionListener {
    private static final int FONT_SIZE = 40;
    JLabel loginName;
    JButton staffManage, logout;
    DefaultFrame parent;
    JPanel nowPanel;
    WorkerDTO worker;
    public ManagerButtonListPanel(DefaultFrame prt, WorkerDTO worker) {
        this.setLayout(new BorderLayout());
        this.parent = prt;
        this.worker = worker;

        //Border Layout의 North
        JPanel bln = new JPanel();
        loginName = new JLabel("접속자:"+worker.getPosition()+" "+worker.getName() +"님");
        loginName.setFont(new DefaultFont(FONT_SIZE));
        bln.add(loginName);
        add(bln, BorderLayout.NORTH);

        //Border Layout의 Center
        JPanel blc = new JPanel();
        blc.setLayout(new GridLayout(7,1));

        JButton [] btns = new JButton[7];
        String [] label = {"주문 내역","재고 관리","방 설정","회원 목록","음료 편집","매출 현황","요청 관리"};

        for (int i = 0 ; i < label.length ; i++) {
            btns[i] = new JButton(label[i]);
            blc.add(btns[i]);
            btns[i].setFont(new DefaultFont(FONT_SIZE));
            btns[i].addActionListener(this);
        }

        add(blc, BorderLayout.CENTER);

        //Border Layout의 South
        JPanel bls = new JPanel();

        staffManage = new JButton("직원 관리");
        staffManage.addActionListener(this);
        logout = new JButton("LOGOUT");
        logout.addActionListener(this);
        bls.add(staffManage);
        bls.add(logout);

        staffManage.setFont(new DefaultFont(20));
        logout.setFont(new DefaultFont(20));

        staffManage.setPreferredSize(new Dimension(150,60));
        logout.setPreferredSize(new Dimension(150,60));

        bls.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(bls, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        JPanel movePage = null;
        switch (s) {
            case "주문 내역":
                movePage = new OrderControlView(parent, worker);
                break;
            case "재고 관리":
                movePage = new StockManagementView();
                break;
            case "방 설정":
                movePage = new RoomSettingView(parent, worker);
                break;
            case "회원 목록":
                movePage = new MemberControlView(parent);
                break;
            case "음료 편집":
                if(worker.getPosition().equals("점장")) {
                    movePage = new DrinksManagementView(parent);
                } else {
                    JOptionPane.showMessageDialog(this,"접근이 불가합니다.","접근 권한 알림",JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "매출 현황":
                movePage = new SalesAnalysisView(parent);
                break;
            case "요청 관리":
                break;
            case "LOGOUT":
                int x = JOptionPane.showConfirmDialog(this, "로그아웃하시겠습니까?","LOGUT",JOptionPane.YES_NO_OPTION);
                if(x == JOptionPane.OK_OPTION){
                    parent.switchTop(DefaultFrame.TOP_USER);
                    parent.resetMove(new UserHomeView(parent));
                }
                break;
            case "직원 관리":
                movePage = new WorkerControlView(parent, worker);
                break;
        }

        if(movePage != null) parent.move(movePage);
    }
}

class MonthSaleStatus extends JPanel { //scrollpane으로 해야댐
    public MonthSaleStatus() {
        setLayout(new BorderLayout());
        //top
        JPanelOneLabel mss = new JPanelOneLabel("이달의 매출 현황");
        mss.getLabel().setFont(new DefaultFont(45));
        add(mss,BorderLayout.NORTH);

        //center
        String [] colcumnType = new String [] {"날짜","일 매출"};
        Object [] [] salesData = {
                {"2023.11.01","222,000원"},
                {"2023.11.02","543,000원"},
                {"2023.11.03","137,000원"},
                {"2023.11.04","454,000원"},
        };
        JTable monthSalesStatusTable = new JTable(salesData,colcumnType);
        JScrollPane scrollPane = new JScrollPane(monthSalesStatusTable);
        add(scrollPane,BorderLayout.CENTER);
    }
}

class SaleStatus extends JPanel {
    public SaleStatus(DefaultFrame parent) {
        setLayout(new BorderLayout());
        //top
        JPanelOneLabel mss = new JPanelOneLabel("판매 현황");
        mss.getLabel().setFont(new DefaultFont(45));
        add(mss, BorderLayout.NORTH);

        //center
        ArrayList<StockDTO> stocks = parent.getController().getStockDAO().findAll();
        String[] colcumnType = new String[]{"제품명", "가격", "어제", "금일", "총합"};
        Object[][] stockList = new Object[stocks.size()][];

        int i = 0;
        for(StockDTO stock : stocks){
            stockList[i] = new Object[]{
                    stock.getName(), stock.getCost(), 0, stock.getAmount(), stock.getAmount()
            };
            i++;
        }

        JTable monthSalesStatusTable = new JTable(stockList, colcumnType);
        JScrollPane scrollPane = new JScrollPane(monthSalesStatusTable);
        add(scrollPane, BorderLayout.CENTER);

    }
}