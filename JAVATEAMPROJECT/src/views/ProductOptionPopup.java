package views;

import custom_component.FreeImageIcon;
import custom_component.JPanelOneLabel;
import dto.GoodsDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductOptionPopup extends JDialog implements ActionListener {
    public static final int WIDTH = 350, HEIGHT = 800;

    private JButton cancelBtn;
    private JButton iceRdBtn, hotRdBtn;
    private  ProductListCartView productListCartView;
    private GoodsDTO goods;
    private DefaultFrame parent;
    public String rst = "";

    //public ProductOptionPopup(ProductListCartView productListCartView, ProductDAO p){
    public ProductOptionPopup(DefaultFrame prt, GoodsDTO g){
        super(prt, "ICE HOT", true);
        this.setSize(WIDTH, HEIGHT);

        //this.setUndecorated(true);
        JPanel main = new JPanel(new BorderLayout());
        //this.productListCartView = productListCartView;
        this.goods = g;

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
                DefaultFrame.PATH + "/images/goods/" + g.getCode() + ".png",
                300, 300
        ));
        center1.add(imageLb);
        cancelBtn.add(center1, DefaultFrame.easyGridBagConstraint(0,0,1,2));

            //center-2
        JPanelOneLabel nameLb = new JPanelOneLabel(goods.getName());
        center.add(nameLb, DefaultFrame.easyGridBagConstraint(0,1,1,1));

            //center-3
        JPanelOneLabel priceLb = new JPanelOneLabel(goods.getPrice() + "원");
        center.add(priceLb, DefaultFrame.easyGridBagConstraint(0,2,1,1));
        main.add(center, BorderLayout.CENTER);


        //bottom
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(2,1));
            //bottom-1
        JPanel bottom1 = new JPanel();
        bottom1.setLayout(new GridLayout(1, 2));
                //bottom-1-1
        JPanel bottom1_1 = new JPanel();
        iceRdBtn = new JButton("ICE");
        iceRdBtn.setPreferredSize(new Dimension(200, 70));
        bottom1_1.add(iceRdBtn);
        bottom1.add(bottom1_1);

                //bottom-1-2
        JPanel bottom1_2 = new JPanel();
        hotRdBtn = new JButton("HOT");
        hotRdBtn.setPreferredSize(new Dimension(200, 70));

        bottom1_2.add(hotRdBtn);
        bottom1.add(bottom1_2);

        bottom.add(bottom1);
        this.add(bottom, BorderLayout.SOUTH);


        //RadioGroup
        /*
        JRadioButton temperatureBtnGrp = new JRadioButton();
        temperatureBtnGrp.add(iceRdBtn);
        temperatureBtnGrp.add(hotRdBtn);*/
        hotRdBtn.addActionListener(this);
        iceRdBtn.addActionListener(this);

        this.add(main);
    }

    public String getRst(){
        return rst;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand(); //TODO: Radio Event 처리
        if(s.equals("ICE")){
            rst = "ICE";
            this.dispose();
        }else if(s.equals("HOT")){
            rst = "HOT";
            this.dispose();
        }
    }
}
