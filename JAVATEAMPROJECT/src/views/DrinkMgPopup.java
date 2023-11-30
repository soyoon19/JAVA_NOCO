package views;


import custom_component.DefaultFont;

import javax.swing.*;
import java.awt.*;

//부족한 재고를 보여주는 팝업창 (관리자 로그인 시, 재고관리 페이지에서 확인 가능)
public class DrinkMgPopup  extends JDialog{

    public static final int WIDTH = 1200, HEIGHT = 800;

    DefaultFrame parent;

    public DrinkMgPopup (DefaultFrame parent) {
        super(parent, "음료 상세 관리 팝업", true);
        this.parent = parent;
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());


        //left -1 (JLable)
        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());


        //left -1 (JTable)
        JTable table;

        String[] columnType = {"음료코드", "음료명", "분류", "상태", "판매 가능 개수", "이벤트 여부", "판매가", "단가"};

        //columnType.setFont(new DefaultFont(50));

        Object[][] drinksData = {
                //테스트를 위한 데이터
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
        };


        table = new JTable(drinksData, columnType);
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

        //Table에 scrollPane 붙이기
        //table.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane 올리기
        main.add(scrollPane);


        //!!! 팀장님이랑 생각해보기!
        //right - 1 (해당 음료 이미지, 이미지 변경 버튼, 취소, 저장 버튼)
        JPanel right = new JPanel();
        //right.setLayout(new );
        JButton okBtn = new JButton("확인");
        okBtn.setFont(new DefaultFont(20));
        right.add(okBtn);
        main.add(right, BorderLayout.EAST);

        //호출시 보이기 설정
        this.add(main);
        this.setVisible(true);

    }
}