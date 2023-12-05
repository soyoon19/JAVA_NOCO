package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;
import dto.GoodsDTO;
import dto.StockDTO;
import dto.WorkerDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class CustomTableCellRenderer extends DefaultTableCellRenderer { //테이블 글씨 크기 조정
    private final int fontSize;

    public CustomTableCellRenderer(int fontSize) {
        this.fontSize = fontSize; //폰트 사이즈 지정
        setHorizontalAlignment(SwingConstants.CENTER); // 셀 텍스트 가운데 정렬
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cellComponent.setFont(new Font("맑은 고딕", Font.PLAIN, fontSize));
        return cellComponent;
    }
}

public class ManagerMainView extends JPanel {
    DefaultFrame parent;
    public ManagerMainView(DefaultFrame prt, WorkerDTO worker){
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
        String [] label = {"요청 관리","주문 관리","회원 관리","매출 관리","음료 관리","재고 관리","방 설정"};

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
            case "주문 관리":
                movePage = new OrderControlView(parent);
                break;
            case "재고 관리":
                movePage = new StockManagementView();
                break;
            case "방 설정":
                movePage = new RoomSettingView(parent, worker);
                break;
            case "회원 관리":
                movePage = new MemberControlView(parent);
                break;
            case "음료 관리":
                if(worker.getPosition().equals("점장")) {
                    movePage = new DrinksManagementView(parent);
                } else {
                    JOptionPane.showMessageDialog(this,"접근이 불가합니다.","접근 권한 알림",JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "매출 관리":
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
                movePage = new WorkerControlView(parent);
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

        JTable monthSalesStatusTable = new JTable(salesData,colcumnType); //JTbale 생성
        monthSalesStatusTable.getTableHeader().setDefaultRenderer(new CustomTableCellRenderer(25)); // 원하는 글씨 크기로 설정
        monthSalesStatusTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer(16)); // 셀의 글꼴 설정

        JScrollPane scrollPane = new JScrollPane(monthSalesStatusTable); //Jscrollpane에 Jtable 추가
        monthSalesStatusTable.getTableHeader().setReorderingAllowed(false); //header 움직이기 방지

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); //스크롤팬 필요 시 자동 생성
        add(scrollPane,BorderLayout.CENTER);
    }
}

class SaleStatus extends JPanel {
    public SaleStatus(DefaultFrame parent) {
        setLayout(new BorderLayout());
        //top
        JPanelOneLabel mss = new JPanelOneLabel("금일 판매 가능 수량");
        mss.getLabel().setFont(new DefaultFont(45));
        add(mss,BorderLayout.NORTH);

        //center
        ArrayList<GoodsDTO> goods = parent.getController().getGoodsDAO().findAll();
        String [] colcumnType = new String[]{"제품명","가격","판매 가능 수량"};
        Object[][] goodsList = new Object[goods.size()][];

        int i = 0;
        for(GoodsDTO good : goods){
            if(good.getMainCategory() == 2) {
                goodsList[i] = new Object[]{
                        good.getName(),good.getPrice(), good.getSaleCount()
                };
                i++;
            }
        }

        JTable monthSalesStatusTable = new JTable(goodsList,colcumnType); //Jtable 생성
        monthSalesStatusTable.getTableHeader().setDefaultRenderer(new CustomTableCellRenderer(25)); // 원하는 글씨 크기로 설정
        monthSalesStatusTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer(16)); // 셀의 글꼴 설정

        JScrollPane scrollPane = new JScrollPane(monthSalesStatusTable); //Jscrollpane에 jtable 추가
        monthSalesStatusTable.getTableHeader().setReorderingAllowed(false); //header 움직이기 방지

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); //스크롤팬 필요 시 자동 생성
        add(scrollPane,BorderLayout.CENTER);
    }
}