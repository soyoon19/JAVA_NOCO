package views;

import custom_component.DefaultFont;

import javax.swing.*;
import java.awt.*;

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


        //center : 날짜, 달력
        JPanel cCenter = new JPanel();

        /*
        DrinksCart가 Border에 의해서 고정적인 공간이 생기는 걸 피하기 위해서
        FlowLayout을 가지고 있는 패널(tmp)를 만들고 JScrollPanel 넣음.
        가변적인 패널 생성을 위해 팀장의 조언을 받아 Box layout 사용함.

        box layout 설명
        가변적으로 컴포너트가 추가, 삭제되는 되는 패널을 만들 때 유용
        BoxLayout을 설정하려하는 JPanel과 수평, 수직으로 배치할지 선택 필요
        add을 수행하면 정한 방향(수평 혹은 수직)으로 계속 추가 가능
         */

        //스크롤 팬
        JPanel tmp = new JPanel();
        JScrollPane jsp = new JScrollPane(tmp);
        //jsp.setBackground(Color.orange);


        //bottom : 버튼 설정
        //1. 선택(클릭 후 버튼 클릭시 해당 날짜 재고 show, 초기화 선택시 오늘 재고 상태를 show)
        //2. 추가/편집/삭제/일괄설정 버튼 넣기 (색깔 지정)
        JPanel cBottom = new JPanel();
        this.add(cBottom, BorderLayout.SOUTH);

        //stock

        String[] drinkMangement = {"추가", "편집", "삭제", "<HTML><body style='text-align:center;'>일괄<br>설정</body></HTML>"};
        String[] dMButtonColor = {"green", "yellow", "RED", "orange"};
        Color[] btnColors = {Color.green, Color.YELLOW, new Color(255, 0, 0), Color.orange};

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
        add(jsp, BorderLayout.CENTER);
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