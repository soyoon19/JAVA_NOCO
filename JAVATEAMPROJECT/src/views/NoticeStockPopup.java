package views;


import custom_component.DefaultFont;
import dao.StockDAO;
import dto.StockDTO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//부족한 재고를 보여주는 팝업창 (관리자 로그인 시, 재고관리 페이지에서 확인 가능)
public class NoticeStockPopup extends JDialog{

    public static final int WIDTH = 1200, HEIGHT = 800;

    DefaultFrame parent;
    StockDAO stockDAO;


    public void popup(){
        /*
        getStockDAO()가 DB에 재고 테이블에 있는 테이블에 모든 정보를
        ArrayList<StockDTO> 형태로 가져온다.
         */
        ArrayList<StockDTO> stocks = stockDAO.findAll();

        /*
        Table 형태에 맞추기 위해서 Object[]로 변환해준다.
         */
        ArrayList<Object[]> notice = new ArrayList<>();

        for(StockDTO stock : stocks){
            if(stock.getAmount() < stock.getMinAmount()){ //최소 수량보다 현재 수량이 적다면
                //문자열은 그대로 넣고
                //숫자는 문자열로 변환하고 String.valueOf(int x)
                //Date는 toString() 메서드를 사용한다.
                notice.add(new Object[]{stock.getCode(), stock.getName(), String.valueOf(stock.getAmount()),
                        String.valueOf(stock.getMinAmount()), String.valueOf(stock.getCost()),
                        StockDTO.CATEGORY_TO_KOREANNAME(stock.getCategory())}); //Category는 숫자로 관리하기 때문에 정해지 약속에 따라서 변환한다.
            }
        }
        //너무해.....

        //만약 재고가 적은게 없다면 종료한다.
        if(notice.size() == 0) return;

        //1. ArrayList<Object[]>의 사이즈를 구한다. --> 행 --> size
        //2. Object[] 배열의 사이즈를 구한다. --> 열 --> length
        Object[][] rst = new Object[notice.size()][notice.get(0).length];
        for(int i = 0; i  <notice.size(); i++) //ArrayList 길이만큼 반복한다.
            rst[i] = notice.get(i);


        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());


        //left -1 (JLable)
        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());

        JLabel msg = new JLabel("해당 재고들이 최소 수량보다 작습니다!");
        msg.setFont(new DefaultFont(40));
        main.add(msg, BorderLayout.NORTH);


        //left -2 (JTable)
        JTable table;

        String[] columnType = new String[]{"재고코드", "재고명", "현재 수량", "최소수량", "공급가", "날짜", "카테고리"};

        //columnType.setFont(new DefaultFont(50));


        table = new JTable(rst, columnType);
        left.add(table, BorderLayout.CENTER);


       /* Object[][] productData = new Object[drinkList.size()][];

        for(int i = 0; i <productData.length; i++){
            GoodsDTO goods = drinkList.get(i);

            productData[i] = new Object[]{
                    goods.getCode(), goods.getName(), goods.getCategory(), goods.getStatus(), goods.getSaleCount(),
                    goods.getDisStatus(), goods.getPrice(), goods.getCost()
            };
        }

        */
        /*
        Object[][] drinksData = {
                //테스트를 위한 데이터
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
        }; */

        //Table에 scrollPane 붙이기
        //table.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane 올리기
        main.add(scrollPane);



/*
        JPanel t = new JPanel();
        t.setLayout(new GridLayout(1,1));
        JPanel right = new JPanel();
        right.setLayout(null);
        right.setBounds(0, 0, 40, 50);

        JButton okBtn = new JButton("확인");
        okBtn.setFont(new DefaultFont(20));
        okBtn.setBounds(0, 0, 40, 50);
*/
        JPanel right = new JPanel();
        right.setLayout(new FlowLayout());

        JButton okBtn = new JButton("확인");
        okBtn.setFont(new DefaultFont(20));

        right.add(okBtn);
        main.add(right, BorderLayout.EAST);

        //호출시 보이기 설정
        this.add(main);
        this.setVisible(true);
    }

    public NoticeStockPopup(DefaultFrame parent) {
        super(parent, "부족한 재고를 보여주는 팝업창", true);

        this.parent = parent;
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        stockDAO = parent.getController().getStockDAO();
    }

}
