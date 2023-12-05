package views;

import custom_component.DefaultFont;
import custom_component.JPanelOneLabel;
import dto.GoodsDTO;
import dto.MemberDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    GoodsDTO[] goods;
    int[] num;

    public ProductCartResultListPanel(GoodsDTO[] gs, int[] num){
        JPanel main = new JPanel();
        this.goods = gs;
        this.num = num;

        main.setLayout(new GridLayout(gs.length, 1));
        for(int i = 0; i < gs.length; i++)
            main.add(new ProductCartResultListDetailPanel(gs[i], num[i]));

        this.setViewportView(main);
    }
}

class ProductCartResultListDetailPanel extends JPanel{
    private static final int IMAGE_SIZE = 100;
    private static final int FONT_SZIE = 25;
    private static final int BTN_SIZE = 25;
    private GoodsDTO goods;
    private int num;
    ImageIcon productImage;

    public ProductCartResultListDetailPanel(GoodsDTO g, int n){

        this.goods = g;
        this.num = n;

        this.setLayout(new GridBagLayout());
        //image
        ImageIcon ii = new ImageIcon(DefaultFrame.PATH + "/images/goods/" + g.getCode() + ".png");
        productImage = new ImageIcon(ii.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));

        //right
        JLabel imageLb = new JLabel(productImage);
        imageLb.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //left
        JPanel left = new JPanel();
        left.setLayout(new GridLayout(3, 1));
        left.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        //left-1 - 상품 이름
        JLabel nameLb = new JLabel(goods.getName());
        nameLb.setFont(new DefaultFont(FONT_SZIE));
        nameLb.setSize(200, 30);
        left.add(nameLb);

        //left-2 - 상품 가격
        JLabel priceLb = new JLabel(goods.getPrice() * num + "원");
        priceLb.setFont(new DefaultFont(FONT_SZIE));
        left.add(priceLb);

        //left-3 - 수량
        JLabel numLb = new JLabel("수량 : " + num);
        numLb.setFont(new DefaultFont((FONT_SZIE)));
        left.add(priceLb);


        //JPanel
        this.add(imageLb, DefaultFrame.easyGridBagConstraint(0,0,1,1));
        this.add(left, DefaultFrame.easyGridBagConstraint(1,0,3,1));

    }
}

public class ProductCartResultPopup extends JDialog implements ActionListener{
    private static final String TITLE = "상품 확인";
    private static final int WIDTH = 400, HEIGHT = 900;
    private GoodsDTO[] goodsArr;
    private int[] nums;
    private int tot, pay;
    JFrame parent;
    ProductCartResultListPanel productCartResultListPanel;
    ProductCartResultPricePanel productCartResultPricePanel;
    JButton backBtn, payBtn;
    MemberDTO mmeber;


    public ProductCartResultPopup(GoodsDTO[] gs, int[] ns, JFrame parent, MemberDTO member) {
        super(parent, TITLE, true);
        this.setLayout(new BorderLayout());

        this.mmeber = member;
        this.goodsArr = gs;
        this.nums = ns;
        this.parent = parent;


        tot = 0;
        for(int i = 0; i < goodsArr.length; i++)
            tot += goodsArr[i].getPrice() * nums[i];

        productCartResultListPanel = new ProductCartResultListPanel(goodsArr, nums);
        productCartResultPricePanel = new ProductCartResultPricePanel(tot, 0, tot);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());
        //top
        JLabel buyListLb = new JLabel("구매 리스트");
        main.add(buyListLb, BorderLayout.NORTH);

        //center
        JPanel center = new JPanel();
        center.setLayout(new GridBagLayout());
        center.add(productCartResultListPanel, DefaultFrame.easyGridBagConstraint(0,0,1,5));
        center.add(productCartResultPricePanel, DefaultFrame.easyGridBagConstraint(0,1,1,2));
        main.add(center, BorderLayout.CENTER);

        //bottom
        JPanel btm = new JPanel();

        backBtn = new JButton("돌아가기");
        payBtn = new JButton("결제");
        backBtn.addActionListener(this);
        payBtn.addActionListener(this);
        //backBtn.setPreferredSize();
        //payBtn.setPreferredSize();
        btm.add(backBtn); btm.add(payBtn);
        main.add(btm, BorderLayout.SOUTH);


        this.add(main);

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
        if(e.getActionCommand().equals("결제")){
            CardInfoPopup popup = new CardInfoPopup((DefaultFrame) parent, mmeber);
            popup.setVisible(true);
        }
    }
}
