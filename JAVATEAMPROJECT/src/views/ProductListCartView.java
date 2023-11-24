package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;
import dao.ProductDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Tab
class ProductList extends JPanel{
    public static final int CATEGORY_NUM = 5;
    public static final String[] CATEGORY_NAME = {
            "곡", "커피", "논-커피", "베버리지", "티"
    };
    ProductCategoryTap[] productTab;
    ProductCart cart;
    public ProductList(ProductCart cart){
        this.cart = cart;
        this.setLayout(new BorderLayout());
        productTab = new ProductCategoryTap[CATEGORY_NUM];
        JTabbedPane jtp = new JTabbedPane();

        for(int i = 0; i < CATEGORY_NUM; i++) {
            JLabel tmp = new JLabel(CATEGORY_NAME[i]);
            tmp.setPreferredSize(new Dimension(100, 30));
            productTab[i] = new ProductCategoryTap("x", CATEGORY_NAME[i], cart);
            jtp.add(productTab[i], "");
            jtp.setTabComponentAt(i, tmp);
        }

        this.add(jtp);
    }

}
class ProductCart extends JPanel{
    JPanel top, center, btm;
    JScrollPane jsp;
    public ProductCart(){
        this.setLayout(new BorderLayout());
        //top
        top = new JPanel();
        JLabel listLb = new JLabel("장바구니 리스트");
        listLb.setFont(new DefaultFont(20,Font.TRUETYPE_FONT));
        top.add(listLb);
        this.add(top, BorderLayout.NORTH);

        //center
        center = new JPanel();
        JPanel tmp = new JPanel();
        tmp.setLayout(new FlowLayout(FlowLayout.LEFT));

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        tmp.add(center);
        jsp = new JScrollPane(tmp);
        add(jsp);

        //bottom
        btm = new JPanel();
        btm.setLayout(new GridLayout(2, 1));
        JPanel btmDown, btmUp;

            //bottom-1
        btmUp = new JPanel();
        JLabel totalLb;
        totalLb = new JLabel("Total : 0원");
        btmUp.add(totalLb);


            //bottom-2
        JButton buyBtn, rmBtn;
        btmDown = new JPanel();
        buyBtn = new JButton("구매");
        rmBtn = new JButton("비우기");
        btmDown.add(buyBtn);
        btmDown.add(rmBtn);

        btm.add(btmUp);
        btm.add(btmDown);
        this.add(btm, BorderLayout.SOUTH);
    }

    /*!add 수정에정!*/
    public void add(ProductDAO p){
        //원래를 DB에서 객체를 받아오고 싶지만 DB 구현이 안되어 있음으로

        center.add(new ProductCarDetailPanel(p));
    }
}

//카테고리 별로 탭
class ProductCategoryTap extends JScrollPane{
    JPanel main; //JscrollPane에 넣을 패널
    private String mainCategory, category;
    private ProductDAO[] products;
    private ProductCart cart;


    public ProductCategoryTap(String mainCategory, String category, ProductCart cart){
        this.cart = cart;
        this.mainCategory = mainCategory;
        this.category = category;
        main = new JPanel();

        //대분류와 소분류를 구분한 데이터를 가져왔다고 가정
        products = new ProductDAO[9];
        for(int i = 0; i < products.length; i++){
            products[i] =  new ProductDAO("C05", "aa(ice)",
                    "coffee", 10, false, 2000, 400,"실패");
        }


        main.setLayout(new GridLayout(products.length / 2 + 1, 2));

        for(int i = 0; i < products.length; i++){
            main.add(new ProductDetailPanel(products[i], cart));
        }
        this.setViewportView(main);
    }

}

//상품 하나의 패널
class ProductDetailPanel extends JPanel{
    private static final int IMAGE_SIZE = 150;
    private static final int FONT_SIZE = 25;
    private static final int BTN_WIDTH_SIZE = 100;

    private ProductDAO product;
    private ImageIcon producImage;
    private ProductCart cartList;
    private JButton buyBtn;
    private ProductCart cart;


    public  ProductDetailPanel(ProductDAO p, ProductCart pc){
        this.setLayout(new GridLayout(1, 2));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        product = p;
        cart = pc;

        //left
        ImageIcon ii = new ImageIcon(DefaultFrame.PATH + "/images/americano(ice).png");
        producImage = new ImageIcon(ii.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
        JLabel imageLb = new JLabel(producImage);
        imageLb.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));

        //this.cartList = cartList;

        //right
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(3, 1));
        imageLb.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));

            //right-1 상품명
        /* 대체!
        JPanel right1 = new JPanel();
        JLabel nameLb = new JLabel(product.getName());
        right1.add(nameLb);
         */
        JPanelOneLabel right1 = new JPanelOneLabel(product.getName(),
                new FlowLayout(FlowLayout.LEFT));
        right1.getLabel().setFont(new DefaultFont(FONT_SIZE));
        right.add(right1);


            //right-2 가격
        JPanelOneLabel right2 = new JPanelOneLabel(String.valueOf(product.getSellPrice() + "원"),
                new FlowLayout(FlowLayout.LEFT));
        right2.getLabel().setFont(new DefaultFont(FONT_SIZE));
        right.add(right2);

            //rigth-3 구매
        JPanel right3 = new JPanel();
        buyBtn = new JButton("구매");
        buyBtn.setPreferredSize(new Dimension(BTN_WIDTH_SIZE, 30));
        buyBtn.setFont(new DefaultFont(FONT_SIZE));
        buyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cart.add(product);
                cart.revalidate(); //갱신
            }
        });
        right3.add(buyBtn);
        right.add(right3);

        this.add(imageLb);
        this.add(right);
    }

}

//cart 안에 들어있는 하나의 항목
class ProductCarDetailPanel extends JPanel{
    private static final int IMAGE_SIZE = 100;
    private static final int FONT_SZIE = 25;
    private static final int BTN_SIZE = 25;
    private ProductDAO prodcut;
    private ImageIcon producImage; //사이즈가 조정된 imageIcon
    private JButton upBtn, downBtn;
    private JLabel numLb, priceLb;
    private int num = 1;


    public ProductCarDetailPanel(ProductDAO p) {
        this.prodcut = p;
        //this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setLayout(new GridBagLayout());
        //image
        ImageIcon ii = new ImageIcon(DefaultFrame.PATH + "/images/americano(ice).png");
        producImage = new ImageIcon(ii.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));


        //right
        JLabel imageLb = new JLabel(producImage);
        imageLb.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //left
        JPanel left = new JPanel();
        left.setLayout(new GridLayout(3, 1));
        left.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

            //left-1 - 상품 이름
        JLabel nameLb = new JLabel(prodcut.getName());
        nameLb.setFont(new DefaultFont(FONT_SZIE));
        nameLb.setSize(200, 30);
        left.add(nameLb);

            //left-2 - 상품 가격
        priceLb = new JLabel(prodcut.getSellPrice() + "원");
        priceLb.setFont(new DefaultFont(FONT_SZIE));
        left.add(priceLb);

            //left-3 - 수량 조절 & 삭제
        JPanel left3 = new JPanel();
        //left3.setLayout();

        numLb = new JLabel(String.valueOf(num));
        downBtn = new JButton("<");
        upBtn = new JButton(">");
        JButton delBtn = new JButton("X");


        downBtn.setPreferredSize(new Dimension(30, BTN_SIZE));
        upBtn.setPreferredSize(new Dimension(30, BTN_SIZE));
        delBtn.setPreferredSize(new Dimension(BTN_SIZE, BTN_SIZE));


        downBtn.setFont(new DefaultFont(10));
        upBtn.setFont(new DefaultFont(10));
        delBtn.setFont(new DefaultFont(8));

        downBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(num > 1) {
                    num--;
                    numLb.setText(String.valueOf(num));
                    priceLb.setText(num * prodcut.getSellPrice() + "원");
                }
            }
        });

        upBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num++;
                numLb.setText(String.valueOf(num));
                priceLb.setText(num * prodcut.getSellPrice() + "원");
            }
        });

        left3.add(downBtn);
        left3.add(numLb);
        left3.add(upBtn);
        left3.add(delBtn);

        left.add(left3);

        //JPanel
        this.add(imageLb, DefaultFrame.easyGridBagConstraint(0,0,1,1));
        this.add(left, DefaultFrame.easyGridBagConstraint(1,0,3,1));
    }

    public int getNum(){
        return num;
    }
}


public class ProductListCartView extends JPanel {
    private ProductList pl;
    private ProductCart pc;


    public ProductListCartView() {
        this.setLayout(new GridBagLayout());
        pc = new ProductCart();
        pl = new ProductList(pc);


        pl.setBackground(Color.red);
        pc.setBackground(Color.orange);

        pl.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 15));
        pc.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 30));
        add(pl, DefaultFrame.easyGridBagConstraint(0, 0, 8, 1));
        add(pc, DefaultFrame.easyGridBagConstraint(1, 0, 2, 1));
    }

}