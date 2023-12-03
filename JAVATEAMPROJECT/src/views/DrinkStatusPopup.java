package views;


import custom_component.DefaultFont;

import javax.swing.*;
import java.awt.*;

//부족한 재고를 보여주는 팝업창 (관리자 로그인 시, 재고관리 페이지에서 확인 가능)
public class DrinkStatusPopup extends JDialog{

    public static final int WIDTH = 800, HEIGHT = 300;

    DefaultFrame parent;

    public DrinkStatusPopup (DefaultFrame parent) {
        super(parent, "부족한 재고를 보여주는 팝업창", true);
        this.parent = parent;
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());


        //left -1 (JLable)
        JLabel msg = new JLabel("재고테이블");
        msg.setFont(new DefaultFont(30));
        main.add(msg, BorderLayout.NORTH);


        //left -2 (JTable)
        JTable table;
        String[] columnType = {"음료코드", "음료명", "분류", "상태", "판매 가능 개수", "이벤트 여부", "판매가", "단가"};

        //columnType.setFont(new DefaultFont(50));


        Object[][] drinksData = {
                //테스트를 위한 데이터
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
        };

        table = new JTable(drinksData, columnType);
        main.add(table, BorderLayout.CENTER);


       /*
        Object[][] productData = new Object[drinkList.size()][];

        for(int i = 0; i <productData.length; i++){
            GoodsDTO goods = drinkList.get(i);

            productData[i] = new Object[]{
                    goods.getCode(), goods.getName(), goods.getCategory(), goods.getStatus(), goods.getSaleCount(),
                    goods.getDisStatus(), goods.getPrice(), goods.getCost()
            };
        }

        */

        //Table에 scrollPane 붙이기
        //table.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane 올리기
        main.add(scrollPane);


        //left - 3 (Button)
        JPanel btn = new JPanel();
        btn.setLayout(new FlowLayout());

        //저장 버튼
        JButton okBtn = new JButton("저장");
        okBtn.setFont(new DefaultFont(20));
        btn.add(okBtn);

        //취소 버튼
        JButton cancleBtn = new JButton("취소");
        cancleBtn.setFont(new DefaultFont(20));
        btn.add(cancleBtn);

        main.add(btn, BorderLayout.SOUTH);

        //호출시 보이기 설정
        this.add(main);
        this.setVisible(true);

    }
}
