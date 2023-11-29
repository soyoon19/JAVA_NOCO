package views;


import custom_component.DefaultFont;

import javax.swing.*;
import java.awt.*;

public class NoticeStockPopup extends JDialog{

    public static final int WIDTH = 1200, HEIGHT = 800;

    DefaultFrame parent;

    public NoticeStockPopup(DefaultFrame parent) {
        super(parent, "부족한 재고를 보여주는 팝업창", true);
        this.parent = parent;
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());


        //left -1 (JLable)
        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());

        JLabel msg = new JLabel("해당 재고들이 최소 수량보다 작습니다!");
        msg.setFont(new DefaultFont(20));
        left.add(msg, BorderLayout.NORTH);


        //left -2 (JTable)
        JTable table;

        String[] stockstData = {"재고코드", "재고명", "현재 수량", "최소수량", "공급가", "날짜"};

        //columnType.setFont(new DefaultFont(50));


        Object [][] columnType = {
            {"A12", "사이다", "3", "5", "30000", "2023.11.29" } ,
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" },
                {"A12", "사이다", "3", "5", "30000", "2023.11.29" }

        };

        table = new JTable(columnType, stockstData);
        left.add(table, BorderLayout.SOUTH);

        add(left, BorderLayout.SOUTH);

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



        //table.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(left);
        //scrollPane 올리기
        this.add(scrollPane);
        this.setVisible(true);


        //right - 1 (Button)
        JPanel right = new JPanel();
        JButton okBtn = new JButton("확인");
        okBtn.setFont(new DefaultFont(20));
        right.add(okBtn);
        add(right, BorderLayout.EAST);


        this.add(main);


    }
}
