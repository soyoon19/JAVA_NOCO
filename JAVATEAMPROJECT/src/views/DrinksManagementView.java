package views;

import javax.swing.*;
import java.awt.*;
//left
class DrinksCategoryPanel extends JPanel {
    public static final String[] drinksCategorys = {
            "전체 메뉴 보기", "커피", "논-커피", "티", "스무디", "상품"
    };
    DrinksCategoryPanel() {
        this.setLayout(new BorderLayout());

        //top : 음료 카테고리 보기 (JComboBox)
        JPanel cTop = new JPanel();
        setBackground(Color.WHITE);

        //JComboBox로 카테고리 만들기
        JComboBox CategoryComboBox = new JComboBox(drinksCategorys);
        cTop.setLayout(new BorderLayout());
        cTop.add(CategoryComboBox);



        //center : 변경할 상품 담는
        JPanel cCenter = new JPanel();
        this.add(cCenter, BorderLayout.CENTER);
        setBackground(Color.BLACK);


        //bottom : 추가/편집/삭제/일괄설정 버튼 넣기
        JPanel cBottom = new JPanel();
        this.add(cBottom, BorderLayout.SOUTH);

        String [] categoryButton = {"추가", "편집", "삭제", "일괄 설정"};

        //JButton[] dCategory = new JButton[];

        for(int i=0; i<categoryButton.length; i++) {
            //반복문으로 categoryButton 개수만큼 버튼 생성
            //dCategory = new JButton[categoryButton.length];
            //버튼에 내용 넣기
            //dCategory[] = new JButton(categoryButton[i]);
        }

        add(cTop, BorderLayout.NORTH);
        add(new JButton("Text"), BorderLayout.CENTER);

    }

}

//right
class DrinksDetailPanel extends  JPanel {
    //상품 정보 출력 (JTable 이용)

    DrinksDetailPanel() {
        this.setLayout(new BorderLayout());
        setBackground(Color.BLUE);

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
        //scrollPane 올리기
        this.add(scrollPane);
        this.setVisible(true);


    }
}

public class DrinksManagementView extends JPanel{

    DrinksDetailPanel drinksDetailPanel;
    DrinksCategoryPanel drinksCategoryPanel;
    //음료 카테고리 정보

    //left: drinksCategoryPanel | Right: drinksDetailPanel
    public DrinksManagementView(){
        this.setLayout(new GridBagLayout());

        drinksDetailPanel = new DrinksDetailPanel();
        drinksCategoryPanel = new DrinksCategoryPanel();

        //left : drinksCategoryPanel
        drinksCategoryPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,15));
        this.add(drinksCategoryPanel, DefaultFrame.easyGridBagConstraint(0, 0, 2, 1));
       // drinksCategoryPanel.setBackground(Color.orange);


        //right : drinksDetailPanel
        drinksDetailPanel.setBorder(BorderFactory.createEmptyBorder(30,15,30,30));
        this.add(drinksDetailPanel, DefaultFrame.easyGridBagConstraint(1,0,5,1));
        drinksDetailPanel.setBackground(Color.red);
    }






}