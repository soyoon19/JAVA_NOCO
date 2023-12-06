package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;
import dto.StockDTO;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.VolatileCallSite;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;


class StocksShowPanel extends JPanel{

    /**/

    ArrayList<StockDTO>[] categoryStockList; //재고 카테고리 받기
    StockViewPanel[] stockViews;
    ArrayList<StockDTO> stocsk; //DTO의 모든 정보를 받기
    DefaultFrame parent; //부모 정보 받기

    //LEFT (1) : 모든 JTable을 보여줄 패널
    public StocksShowPanel(DefaultFrame prt){
        this.parent = prt;

        //left - JTable
        this.setLayout(new BorderLayout());

        //탭 생성
        JTabbedPane stockJTabb = new JTabbedPane();
        stocsk = parent.getController().getStockDAO().findAll();

        //category 만큼 반복
        categoryStockList = new ArrayList[StockDTO.ITEMS_KOREA_NAME.length]; //StockDTO에 있는 카테고리 정보를 길이만큼 받기
        stockViews = new StockViewPanel[categoryStockList.length]; //categoryStockList 개수만큼 stockView를 만듦
        for(int i = 0; i < StockDTO.ITEMS_KOREA_NAME.length; i++){
            categoryStockList[i] = new ArrayList<>(); //categoryStockList ??
            //stocks에서 원하는 카테고리 아이템을 가져온다.
            for(int j = 0; j < stocsk.size(); j++){
                if(i == stocsk.get(j).getCategory()){ //Category 번호가 서로 같다면
                    categoryStockList[i].add(stocsk.get(j)); //해당하는 카테고리 패널에 추가
                }
            }
            stockViews[i] = new StockViewPanel(categoryStockList[i]);
            stockJTabb.addTab(StockDTO.CATEGORY_TO_KOREANNAME(i), stockViews[i]);

        }

        //탭 글자 크기 보기
        stockJTabb.setFont(new DefaultFont(50));

        this.add(stockJTabb);

    }
}

//탭 생성 : 실온(0), 냉장, 냉동, 기타(3) 
//실온식품 패널
class StockViewPanel extends JPanel {
    //1. DB로 불러와서 비교하기 / 2. 해당 카테고리에 맞는 JTable 붙이기
    ArrayList<StockDTO> stokcs;
    public StockViewPanel(ArrayList<StockDTO> stocks){
        this.setLayout(new BorderLayout());
        this.add(new JLabel("test"), BorderLayout.NORTH);

        this.stokcs = stocks;

        String[] columnType = new String[]{"재고코드", "재고명", "현재 수량", "최소수량", "공급가", "날짜"};
        Vector<Object> colVec = new Vector<>();
        for(int i = 0; i < columnType.length; i++)
            colVec.add(columnType[i]);

        Vector<Vector<Object>> stockList = new Vector<>(new Vector<>());
        for(StockDTO stock : stocks){
            stockList.add(new Vector<>());
            int x = stockList.size() - 1;
            stockList.get(x).add(stock.getCode());
            stockList.get(x).add(stock.getName());
            stockList.get(x).add(stock.getAmount());
            stockList.get(x).add(stock.getMinAmount());
            stockList.get(x).add(stock.getCost());
            stockList.get(x).add((new Date()).toString());
        }

        DefaultTableModel tableModel = new DefaultTableModel(stockList, colVec){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        
        JTable table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);

        // header 움직이기 방지
        table.getTableHeader().setReorderingAllowed(false);

        //터치 막기
        table = new JTable(tableModel);

        this.add(sp);
        this.setBackground(Color.orange);
    }

}

class StcokEditPanel extends JPanel{

}
/*
class RefrigeratedItemsPane extends JPanel {

}
class FrozenItems  extends JPanel {


}
class OtherItems extends JPanel {

}
*/



//right - 1
 class StockMenuPanel extends JPanel{

    private CalendarPanel calendarPanel;
    private DefaultFrame parent;
    public StockMenuPanel(DefaultFrame prt){
        parent = prt;
        this.setLayout(new BorderLayout());
        JTabbedPane jtp = new JTabbedPane();

        //right
        //탭에 들어갈 내용 (날짜 grid로)
        //right: 날짜, 캘린더, 재고 검색, 버튼
        //top(cTop) : 재고 검색하기 (JTextFIeld, JLable)
        JPanel cTop = new JPanel();
        cTop.setBackground(Color.WHITE);
        cTop.setLayout(new BorderLayout());

        //검색바
        JTextField searchBar = new JTextField();
        searchBar.setFont(new DefaultFont(50));

        cTop.add(searchBar, BorderLayout.CENTER);

        //검색 버튼
        JButton searchBtn = new JButton("검색");
        cTop.add(searchBtn, BorderLayout.EAST);

        this.add(cTop);


        //center : 달력
        JPanel cCenter = new JPanel();
        cCenter.setLayout(new BorderLayout());

        JPanel divCenter = new JPanel();
        divCenter.setBackground(Color.BLACK);
        divCenter.setLayout(new BorderLayout());
        calendarPanel = new CalendarPanel(parent);
        divCenter.add(calendarPanel);
        cCenter.add(divCenter, BorderLayout.CENTER);

        //center - 1(달력 이동) : moveMonth (moveLastMonth, moveNextMonth)
        JPanel moveMonth = new JPanel();
        moveMonth.setLayout(new FlowLayout());

        JButton moveLastMonth = new JButton("지난 달");
        JButton moveNextMonth = new JButton("다음 달");

        moveMonth.add(moveLastMonth);
        moveMonth.add(moveNextMonth);

        //Event: 이전 달로 넘어가기
        moveLastMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendarPanel.preMonth();
            }
        });

        //Event: 다음 달로 넘어가기
        moveNextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendarPanel.nextMonth();
            }
        });

        cCenter.add(moveMonth, BorderLayout.NORTH);


        //bottom : 버튼 설정
        //1. 선택(클릭 후 버튼 클릭시 해당 날짜 재고 show, 초기화 선택시 오늘 재고 상태를 show)
        //2. 추가/편집/삭제/일괄설정 버튼 넣기 (색깔 지정)
        JPanel cCenterBtm = new JPanel();
        JButton selBtn = new JButton("선택"), reBtn = new JButton("초기화");
        cCenterBtm.add(selBtn); cCenterBtm.add(reBtn);
        cCenter.add(cCenterBtm, BorderLayout.SOUTH);

        //stock
        JPanel cBottom = new JPanel();
        String[] drinkMangement = {"추가", "편집", "삭제", "<HTML><body style='text-align:center;'>선택<br>해제</body></HTML>"};
        String[] dMButtonColor = {"green", "yellow", "RED", "orange"};
        Color[] btnColors = {Color.green, Color.YELLOW, new Color(255, 0, 0),Color.white};

        //1. 반복문으로 drinkMangement 개수만큼 버튼 생성
        JButton[] dMButton = new JButton[drinkMangement.length];

        for (int i = 0; i < drinkMangement.length; i++) {

            //2. 버튼에 내용 넣기
            dMButton[i] = new JButton(drinkMangement[i]);
            dMButton[i].setPreferredSize(StockManagementView.BUTTON_SIZE);

            //4. 버튼에 색깔 지정
            dMButton[i].setBackground(btnColors[i]);
            //5. 버튼 생성
            cBottom.add(dMButton[i]);
        }

        add(cTop, BorderLayout.NORTH);
        add(cCenter, BorderLayout.CENTER);
        add(cBottom, BorderLayout.SOUTH);

    }
}
public class StockManagementView extends JPanel {
    public static Dimension BUTTON_SIZE = new Dimension(80, 80);
    public static final String[] stocksCategory = {
            "재고코드", "재고명", "현재 수향", "최소 수량", "공급가", "날짜"
    };
    private JPanel cCenter;
    private DefaultFrame parent;
    public StockManagementView(DefaultFrame prt) {
        this.parent = prt;
        this.setLayout(new GridBagLayout());

        //left
        this.add(new StocksShowPanel(parent), DefaultFrame.easyGridBagConstraint(0,0,3,1));


        //rightMain - JTabbed (날짜, 캘린더)
        this.add(new StockMenuPanel(parent), DefaultFrame.easyGridBagConstraint(1,0,1,1));

    }
}

//center - 2(달력)
class CalendarPanel extends JPanel{
    private Calendar date;
    public static final String WEEK[] = {"일", "월", "화", "수", "목", "금", "토"};
    private JPanelOneLabel[][] space;
    private JPanelOneLabel yearMonth;
    private SimpleDateFormat format;
    private DefaultFrame parent;

    public CalendarPanel(DefaultFrame prt){
        this.parent = prt;
        date = Calendar.getInstance();
        this.setLayout(new BorderLayout());
        format = new SimpleDateFormat("yyyy-MM");
        //top
        yearMonth = new JPanelOneLabel(format.format(date.getTime()));
        this.add(yearMonth, BorderLayout.NORTH);

        //center
        JPanel cal = new JPanel();
        cal.setLayout(new GridLayout(WEEK.length, WEEK.length));
        space = new JPanelOneLabel[WEEK.length][WEEK.length];

        for(int i = 0; i < WEEK.length; i++) {
            for (int j = 0; j < WEEK.length; j++) {
                space[i][j] = new JPanelOneLabel("");
                space[i][j].setBorder(new LineBorder(Color.BLACK, 1));
                cal.add(space[i][j]);
            }
        }

        for(int i = 0; i < WEEK.length; i++){
            space[0][i].setBackground(Color.ORANGE);
            space[0][i].getLabel().setText(WEEK[i]);
        }

        this.update();
        this.add(cal);
    }

    public void nextMonth(){
        if(date.compareTo(Calendar.getInstance()) < 0) //date가 더 작을 경우에만
            date.add(Calendar.MONTH, 1);
        update();
    }

    public void preMonth(){
        date.add(Calendar.MONTH, -1);
        update();
    }

    public void resetDate(){
        date = Calendar.getInstance();
    }

    public void update(){
        yearMonth.getLabel().setText(format.format(date.getTime()));
        for(int y = 1; y < WEEK.length; y++)
            for(int x = 0; x < WEEK.length; x++)
                space[y][x].getLabel().setText("");

        Calendar month = Calendar.getInstance();
        month.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), 1);

        int start =  month.get(Calendar.DAY_OF_WEEK) - 1 + WEEK.length;

        int end = month.getActualMaximum(Calendar.DAY_OF_MONTH) + start;

        for(int i = start; i < end; i++){
            space[i / WEEK.length][i % WEEK.length].getLabel().setText(
                    String.valueOf(i - start + 1));
        }
    }

}