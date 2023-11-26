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
        drinksCategoryPanel.setBackground(Color.orange);

        //right : drinksDetailPanel
        drinksDetailPanel = new JPanel();
        drinksDetailPanel.setBackground(Color.red);


        this.add(drinksCategoryPanel, DefaultFrame.easyGridBagConstraint(0,0,2,1));
        this.add(drinksDetailPanel, DefaultFrame.easyGridBagConstraint(1,0,5,1));
    }

}