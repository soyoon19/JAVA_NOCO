package views;

import custom_component.JPanelOneLabel;
import dao.ProductDAO;

import javax.swing.*;
import java.awt.*;

class ProductCartResultPricePanel extends JPanel{
    public ProductCartResultPricePanel(int tot, int discount, int pay){
        setLayout(new GridLayout(3, 2));

        //row-1
        JPanelOneLabel row1L = new JPanelOneLabel("총 금액",
                new FlowLayout(FlowLayout.LEFT));
        JPanelOneLabel row1R = new JPanelOneLabel(tot + "원",
                new FlowLayout(FlowLayout.RIGHT));
        add(row1L); add(row1R);

        //row-2
        JPanelOneLabel row2L = new JPanelOneLabel("회원 할인",
                new FlowLayout(FlowLayout.LEFT));
        JPanelOneLabel row2R = new JPanelOneLabel(discount + "원",
                new FlowLayout(FlowLayout.RIGHT));
        add(row2L); add(row2R);

        //row-3
        JPanelOneLabel row3L = new JPanelOneLabel("결제 금액",
                new FlowLayout(FlowLayout.LEFT));
        JPanelOneLabel row3R = new JPanelOneLabel(pay + "원",
                new FlowLayout(FlowLayout.RIGHT));
        add(row3L); add(row3R);
    }
}

class ProductCartResultListPanel extends JScrollPane{
    ProductDAO[] products;
    public ProductCartResultListPanel(ProductDAO[] ps){
        JPanel main = new JPanel();
        this.products = ps;

        main.setLayout(new GridLayout(ps.length, 1));
        add(main);


    }
}

class ProductCartResultListDetailPanel{
    ProductDAO product;
    /*
    public ProductCartResultPricePanel(ProductDAO p){
        this.product = p;

    }*/
}



public class ProductCartResultPopup extends JDialog {
    private static final int WIDTH = 400, HEIGHT = 900;
    ProductDAO[] products;
    int[] nums;
    ProductListCartView productListCartView;
    JButton backBtn, payBtn;


    public ProductCartResultPopup(ProductDAO[] ps, int[] ns, ProductListCartView plcv){
        this.products = ps;
        this.nums = ns;
        this.productListCartView = plcv;

        JPanel main = new JPanel();
        //top
        JLabel buyListLb = new JLabel("구매 리스트");
        main.add(buyListLb, BorderLayout.NORTH);

        //center
        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());


        //bottom
        JPanel btm = new JPanel();

        backBtn = new JButton("돌아가기");
        payBtn = new JButton("결제");
        //backBtn.setPreferredSize();
        //payBtn.setPreferredSize();
        btm.add(backBtn); btm.add(payBtn);
        main.add(btm, BorderLayout.SOUTH);

        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
}
