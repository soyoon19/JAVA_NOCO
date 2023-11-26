package views;

import javax.swing.*;
import java.awt.*;

public class DrinksManagementView extends JPanel{
    JPanel drinksCategoryPanel, drinksDetailPanel;


    //left: drinksCategoryPanel | Right: drinksDetailPanel
    public DrinksManagementView(){
        this.setLayout(new GridBagLayout());

        //left : drinksCategoryPanel
        drinksCategoryPanel = new JPanel();
        drinksCategoryPanel.setLayout(new BorderLayout());
        this.add(drinksCategoryPanel, DefaultFrame.easyGridBagConstraint(0, 0, 2, 1));
        drinksCategoryPanel.setBackground(Color.red);


        //right : drinksDetailPanel
        drinksDetailPanel = new JPanel();
        drinksDetailPanel.setLayout(new FlowLayout());
        this.add(drinksDetailPanel, DefaultFrame.easyGridBagConstraint(1,0,5,1));
        drinksDetailPanel.setBackground(Color.red);

    }

    class drinksCategoryPanel extends JPanel {


        //top : 음료명 검색
        JPanel top = new JPanel();


        //center : 카테고리
        JPanel center = new JPanel();


        //bottom : 추가/편집/삭제/일괄설정 버튼 넣기
        JPanel bottom = new JPanel();
    }

    class drinksDetailPanel extends  JPanel {
        //상품 정보 출력 (JTable 이용)\
        String[] columnType = {"음료코드", "음료명", "분류", "상태", "판매 가능 개수", "이벤트 여부", "판매가", "단가"};
        Object[][] drinksData = {
                //테스트를 위한 데이터
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
                {"CF01", "아메리카노", "커피", "판매", 20, "X", 3000, 800},
        };

        JTable table = new JTable(drinksData, columnType);
        JScrollPane scrollPane = new JScrollPane(table);



    }

}