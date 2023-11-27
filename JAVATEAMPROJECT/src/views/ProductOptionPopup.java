package views;

import custom_component.FreeImageIcon;
import custom_component.JPanelOneLabel;
import dao.ProductDAO;

import javax.swing.*;
import java.awt.*;

public class ProductOptionPopup extends JDialog {
    public static final int WIDTH = 350, HEIGHT = 800;

    private JButton cancelBtn, checkBtn;
    private JRadioButton iceRdBtn, hotRdBtn;
    private  ProductListCartView productListCartView;
    private ProductDAO product;
    private DefaultFrame parent;

    //public ProductOptionPopup(ProductListCartView productListCartView, ProductDAO p){
    public ProductOptionPopup(DefaultFrame prt, ProductDAO p){
        super(prt, "ICE HOT", true);
        this.setSize(WIDTH, HEIGHT);

        this.setUndecorated(true);
        JPanel main = new JPanel(new BorderLayout());
        //this.productListCartView = productListCartView;
        this.product = p;

        //top
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.RIGHT));
        cancelBtn = new JButton();
        top.add(cancelBtn);
        main.add(top, BorderLayout.NORTH);

        //center
        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());

            //center-1
        JPanel center1 = new JPanel();
        JLabel imageLb = new JLabel(FreeImageIcon.resizeImageIcon(
                DefaultFrame.PATH + "/images/" + "americano" + ".png",
                300, 300
        ));
        center1.add(imageLb);
        cancelBtn.add(center1, DefaultFrame.easyGridBagConstraint(0,0,1,2));

            //center-2
        JPanelOneLabel nameLb = new JPanelOneLabel(p.getName());
        center.add(nameLb, DefaultFrame.easyGridBagConstraint(0,1,1,1));

            //center-3
        JPanelOneLabel priceLb = new JPanelOneLabel(p.getSellPrice() + "원");
        center.add(priceLb, DefaultFrame.easyGridBagConstraint(0,2,1,1));
        main.add(center, BorderLayout.CENTER);


        //bottom
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(2,1));
            //bottom-1
        JPanel bottom1 = new JPanel();
        bottom1.setLayout(new GridLayout(2, 1));
                //bottom-1-1
        JPanel bottom1_1 = new JPanel();
        iceRdBtn = new JRadioButton("ICE");
        bottom1_1.add(iceRdBtn);
        bottom1.add(bottom1_1);

                //bottom-1-2
        JPanel bottom1_2 = new JPanel();
        hotRdBtn = new JRadioButton("HOT");
        bottom1_2.add(hotRdBtn);
        bottom1.add(bottom1_1);

        bottom.add(bottom1_1);

        //bottom-2
        JPanel bottom2 = new JPanel();
        checkBtn = new JButton("확 인");
        bottom2.add(checkBtn);
        bottom.add(bottom2);

        main.add(bottom, BorderLayout.SOUTH);
        //RadioGroup
        JRadioButton temperatureBtnGrp = new JRadioButton();
        temperatureBtnGrp.add(iceRdBtn);
        temperatureBtnGrp.add(hotRdBtn);

        this.add(main);
    }
}
