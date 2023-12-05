package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class StocksShowPanel extends JPanel{
    public StocksShowPanel(){
    //left - JTable
        this.setLayout(new BorderLayout());

        JPanel main = new JPanel();
        JPanel left = new JPanel(new BorderLayout());


        String[] columnType = new String[]{"재고코드", "재고명", "현재 수량", "최소수량", "공급가", "날짜"};

        //columnType.setFont(new DefaultFont(50));


        Object [][] stockstData = {
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" } ,
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" } ,
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },

        };

        JTable table = new JTable(stockstData, columnType);
        add(table);

        /*
        Object[][] productData = new Object[drinkList.size()][];

        for(int i = 0; i <productData.length; i++){
            GoodsDTO goods = drinkList.get(i);

            productData[i] = new Object[]{
                    goods.getCode(), goods.getName(), goods.getCategory(), goods.getStatus(), goods.getSaleCount(),
                    goods.getDisStatus(), goods.getPrice(), goods.getCost()
            };
        }*/



        //Table에 scrollPane 붙이기
        //table.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane 올리기
        this.add(scrollPane);

    }
}

//right - 1
 class StockMenuPanel extends JPanel{

    private CalendarPanel calendarPanel;
    public StockMenuPanel(){
        this.setLayout(new BorderLayout());
        JTabbedPane jtp = new JTabbedPane();

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
        calendarPanel = new CalendarPanel();
        divCenter.add(calendarPanel);
        cCenter.add(divCenter, BorderLayout.CENTER);

        //center - 1(달력 이동) : moveMonth (moveLastMonth, moveNextMonth)
        JPanel moveMonth = new JPanel();
        moveMonth.setLayout(new FlowLayout());

        JButton moveLastMonth = new JButton("지난 달");
        JButton moveNextMonth = new JButton("다음 달");

        moveMonth.add(moveLastMonth);
        moveMonth.add(moveNextMonth);

        moveLastMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendarPanel.preMonth();
            }
        });

        moveNextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calendarPanel.nextMonth();
            }
        });

        cCenter.add(moveMonth, BorderLayout.NORTH);

        //center - 2(달력) <도움!!>







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

    public StockManagementView() {

        this.setLayout(new GridBagLayout());

        //left
        this.add(new StocksShowPanel(), DefaultFrame.easyGridBagConstraint(0,0,3,1));


        //rightMain - JTabbed (날짜, 캘린더)
        this.add(new StockMenuPanel(), DefaultFrame.easyGridBagConstraint(1,0,1,1));

    }
}


class CalendarPanel extends JPanel{
    private Calendar date;
    public static final String WEEK[] = {"일", "월", "화", "수", "목", "금", "토"};
    private JPanelOneLabel[][] space;
    private JPanelOneLabel yearMonth;
    private SimpleDateFormat format;

    public CalendarPanel(){
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