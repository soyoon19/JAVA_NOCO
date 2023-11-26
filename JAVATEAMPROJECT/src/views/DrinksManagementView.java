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
        drinksDetailPanel.setBackground(Color.red);


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

    }

}