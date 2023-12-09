package views;

import custom_component.DefaultFont;
import custom_component.FreeImageIcon;
import custom_component.JPanelOneLabel;
import custom_component.RoomPanel;
import dao.GoodsDAO;
import dto.GoodsDTO;
import dto.MemberDTO;
import dto.RoomManageDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

//상품 목록을 보여주는 패널이다.
class ProductList extends JPanel{
    //임시로 카테고리 개수와 카테고리를 생성했다.

    ProductCategoryTap[] productTab; //각 탭마다 상품의 정보가 표시되는 패널이다.
    ProductCart cart;
    public ProductList(GoodsDAO goodsDAO, ProductCart cart){
        this.cart = cart;
        this.setLayout(new BorderLayout()); //큰 의미는 없다. FlowLayout로 하면 혹시 문제가 생길까 BorderLayout로 변경했다.
        //만약 고정적인 공간이 필요하면 BorderLayout을 가변적인 공간이 필요하면 FlowLayout을 사용하자

        productTab = new ProductCategoryTap[GoodsDTO.CATEGORY.length]; //카테고리 만큼 탭을 만든다.
        JTabbedPane jtp = new JTabbedPane();

        /*
        만약 클릭하는 탭의 크기를 키우고 싶다면
        원하는 탭의 크기의 라벨을 만들 후에
        setTabComponentAt(Tab 번호, JLabel)을 주면된다.
        이때 주의하는 점은 탭이 존재해야 한다. (jtp.add() 보다 아래 있어야 한다.
         */
        ArrayList<GoodsDTO> goodsArr = goodsDAO.findAll();//상품의 모든 정보를 가져온다.
        ArrayList<GoodsDTO>[] goodsCategoryArr = new ArrayList[GoodsDTO.CATEGORY.length];
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < GoodsDTO.CATEGORY.length; i++) {
            goodsCategoryArr[i] = new ArrayList<>();
            map.put(GoodsDTO.CATEGORY[i], i);
        }
        System.out.println(map.keySet());

        for(int i = 0; i < goodsArr.size(); i++){
            System.out.println(map.get(goodsArr.get(i).getCategory()));

            if(("판매").equals(goodsArr.get(i).getStatus())) {
                System.out.println(goodsArr.get(i));
                goodsCategoryArr[map.get(goodsArr.get(i).getCategory())].add(goodsArr.get(i)); //맞는 카테고리에 추가한다.
            }
        }

        for(int i = 0; i < GoodsDTO.CATEGORY.length; i++) {
            JLabel tmp = new JLabel(GoodsDTO.CATEGORY[i]);
            tmp.setPreferredSize(new Dimension(100, 30));
            productTab[i] = new ProductCategoryTap(cart, goodsCategoryArr[i]);
            jtp.add(productTab[i], "");
            jtp.setTabComponentAt(i, tmp);
        }

        this.add(jtp);
    }

}

//카트 리스트를 보여주는 패널이다.
class ProductCart extends JPanel{
    private DefaultFrame parent;
    private JPanel top, center, btm;
    private JScrollPane jsp;
    //DB 구축이 완료되면 사용할 변수이다.
    private HashMap<String, ProductCarDetailPanel> cartListMap;
    private HashMap<String, Integer> goodsCountInfoMap; //숫자를 저장하는 변수
    //임시로 사용한다.
    private MemberDTO member;
    private JLabel totalLb;
    private RoomManageDTO room;
    private int total;

    //나중에 JDialog을 사용하기 위해서 JFrame(DefaultFrame)을 매개변수로 받아둔다.

    public ProductCart(DefaultFrame parent, RoomManageDTO room){
        this(parent, room, null);
    }

    public ProductCart(DefaultFrame parent, RoomManageDTO room, MemberDTO member){
        total = 0;
        this.parent = parent;
        this.setLayout(new BorderLayout());
        this.room = room;
        cartListMap = new HashMap<>();
        goodsCountInfoMap = new HashMap<>();
        //top
        top = new JPanel();
        JLabel listLb = new JLabel("장바구니 리스트");
        listLb.setFont(new DefaultFont(20));
        top.add(listLb);
        this.add(top, BorderLayout.NORTH);
        this.member = member;

        //center
        center = new JPanel();
        //ProductCart을 Border에 의해서 고정적인 공간이 생기는 걸 피하기 위해서
        //FlowLayout을 가지고 있는 tmp패널을 만들고 JScrollPanel을 넣었다.
        //이해하기 조금 난해한 내용이니 가변적인 패널을 만들고 싶은 경우 팀장에게 물어보자.
        JPanel tmp = new JPanel();
        tmp.setLayout(new FlowLayout(FlowLayout.LEFT));

        /*
        box layout
        가변적으로 컴포너트가 추가되거나 삭제되는 되는 패널을 만들 때 유용하다.
        BoxLayout을 설정할 때는 설정할려고는 하는 JPanel과 수평으로 배칠할 것인지
        수직으로 배치할 것이니지 정한다.
        add을 수행하면 정한 방향으로 계속추가된다.
         */
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS)); //수직 방향으로 설정하였다.
        tmp.add(center);
        jsp = new JScrollPane(tmp);
        add(jsp);

        //bottom
        //총액과 버튼을 나누기위해서 2행으로 곧 2줄로 나눴다.
        btm = new JPanel();
        btm.setLayout(new GridLayout(2, 1));
        JPanel btmDown, btmUp;

        //bottom-1
        btmUp = new JPanel();
        totalLb = new JLabel("Total : 0원");
        btmUp.add(totalLb);


        //bottom-2
        JButton buyBtn, rmBtn;
        btmDown = new JPanel();
        buyBtn = new JButton("구매");
        buyBtn.addActionListener(new BuyButtonAction());
        rmBtn = new JButton("비우기");
        btmDown.add(buyBtn);
        btmDown.add(rmBtn);
        rmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel();
            }
        });

        btm.add(btmUp);
        btm.add(btmDown);
        this.add(btm, BorderLayout.SOUTH);
    }

    public void add(GoodsDTO g){  //add 함수 실행시 선택한 상품이 추가된다.

        GoodsDTO gCopy = new GoodsDTO(g.getCode(), g.getName(), g.getCategory(), g.getStatus(),
                g.getMainCategory(), g.getSaleCount(), g.getPrice(), g.getCost(), g.getDisStatus(),
                g.getIce(), g.getHot());

        String tp = "";
        if(gCopy.getHot() && gCopy.getIce()) { //Option이 둘다 있으면
            ProductOptionPopup popup = new ProductOptionPopup(parent, g);
            popup.setVisible(true);
            tp = popup.getRst();
            if(tp.equals("")) return;


            if(tp.equals("HOT")){
                gCopy.setIce(false);
            }else if(tp.equals("ICE")){
                gCopy.setHot(false);
            }
        }
        if(cartListMap.get(gCopy.getCode() + ":" + tp) != null) return; //이미 장바구니에 있는 요소라면 접근을 막는다.

        if(goodsCountInfoMap.get(g.getCode()) != null &&
                goodsCountInfoMap.get(g.getCode()) <= 0){
            JOptionPane.showMessageDialog(parent, "재고가 부족하여 더 이상 구매할 수 없습니다.");
            return;
        }

        if(goodsCountInfoMap.get(g.getCode()) == null)  //숫자 정보를 받는다.
            goodsCountInfoMap.put(g.getCode(), g.getSaleCount());

        //재고 1마이너스
        goodsCountInfoMap.replace(g.getCode(), goodsCountInfoMap.get(g.getCode()) - 1);

        updateTot(g.getPrice());
        ProductCarDetailPanel p = new ProductCarDetailPanel(gCopy, this);
        cartListMap.put(g.getCode() + ":" + tp , p);

        center.add(p);
    }

    public void clearPanel()
    {
        center.removeAll();
        center.repaint();
        center.revalidate();
        cartListMap.clear();
        goodsCountInfoMap.clear();
        totalLb.setText("Total : 0원");
    }

    public void removeCartList(ProductCarDetailPanel p, int n){
        center.remove(p);
        center.repaint();
        center.revalidate();
        updateTot(-(p.getNum() * p.getGoods().getPrice()));
        String tp = p.getGoods().getIce() ? "ICE" : p.getGoods().getHot() ? "HOT" : "";

        cartListMap.remove(p.getGoods().getCode() + ":" + tp);

        //삭제를 시도하는 경우 p.getGoods().getSaleCount() != n + goodsCountInfoMap 이라면 HOT, ICE가 둘 다 존재한다는 것이고
        //그 때는 HashMap에 추가만 해야된다. --> 생각해 보니까 꼭 삭제할 필요가 있을까..?
        if(p.getGoods().getSaleCount() == n + goodsCountInfoMap.get(p.getGoods().getCode()))
            goodsCountInfoMap.remove(p.getGoods().getCode());
        else
            goodsCountInfoMap.replace(p.getGoods().getCode(),
                    goodsCountInfoMap.get(p.getGoods().getCode()) + n);
    }

    class BuyButtonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = 0;

            ProductCarDetailPanel[] goodsArr = cartListMap.values().toArray(new ProductCarDetailPanel[0]);

            GoodsDTO[] goods = new GoodsDTO[goodsArr.length];
            int[] nums = new int[goodsArr.length];

            boolean findMusic = false;
            for(ProductCarDetailPanel p : goodsArr){
                goods[i] = goodsArr[i].getGoods();
                if(goods[i].getMainCategory() == GoodsDTO.MAIN_CATEGORY_MUSIC)
                    findMusic = true;
                nums[i] = goodsArr[i].getNum();
                i++;
            }

            if(member == null || parent.getController().getMemberLogDAO().findById(member.getHp()).getHoldSong() == 0){
                if(!findMusic){
                    JOptionPane.showMessageDialog(parent, "곡을 구매해 주세요");
                    return;
                }
            }
            ProductCartResultPopup popup = new ProductCartResultPopup(goods, nums, parent, member, room);
        }
    }

    public void updateTot(int addOrDel) {
        total += addOrDel;
        totalLb.setText("Total : " + total + "원");
    }

    public HashMap<String, Integer> getGoodsCountInfoMap() { //카운트를 살펴볼 이벤트
        return goodsCountInfoMap;
    }
}

//카테고리에 맞는 상품의 리스트 정보를 저장하고 보여주는 패널이다.(탭)
 class ProductCategoryTap extends JScrollPane{
    JPanel main; //JscrollPane에 넣을 패널


    public ProductCategoryTap(ProductCart cart, ArrayList<GoodsDTO> goodsArr){
        main = new JPanel();


        main.setLayout(new GridLayout(goodsArr.size() / 2 + 1, 2));

        for(int i = 0; i < goodsArr.size(); i++){
            main.add(new ProductDetailPanel(goodsArr.get(i), cart));
        }

        //만약 JScrollPanel 생성후에 Panel의 스크롤 기능을 만들고 싶다면
        //꼭  setViewportView(JPanel)을 사용해야 한다는 점을 잊지 말자.
        this.setViewportView(main);
    }

}

//하나 상품의 정보를 보여주는 패널(반복적인 패널)
class ProductDetailPanel extends JPanel{
    private static final int IMAGE_SIZE = 150;
    private static final int FONT_SIZE = 25;
    private static final int BTN_WIDTH_SIZE = 100;

    private GoodsDTO goods;
    private ImageIcon producImage;
    private ProductCart cartList;
    private JButton buyBtn;
    private ProductCart cart;


    public  ProductDetailPanel(GoodsDTO g, ProductCart pc){
        this.setLayout(new GridLayout(1, 2));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        goods = g;
        cart = pc;


        //left
        /* 이미지 조절을 위한 원래 필요한 코드이다. FreeImageIcon.resizeImageIcon로
           데체 가능하니 쉽게 사용하자
        ImageIcon ii = new ImageIcon(DefaultFrame.PATH + "/images/americano(ice).png");
        producImage = new ImageIcon(ii.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
        */
        producImage = FreeImageIcon.resizeImageIcon(DefaultFrame.PATH + "/images/goods/" + g.getCode() + ".png", IMAGE_SIZE,IMAGE_SIZE);

        JLabel imageLb = new JLabel(producImage);
        imageLb.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));

        //this.cartList = cartList;

        //right - (상품명, 가격, 구매)
        JPanel right = new JPanel();
        right.setLayout(new GridLayout(3, 1));
        imageLb.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));

        //right-1 상품명
        /* 대체!
        하나의 패널의 하나의 JLabel만 들어가는 경우 JPanelOneLabel을 사용해 보자.
        JPanel right1 = new JPanel();
        JLabel nameLb = new JLabel(product.getName());
        right1.add(nameLb);
         */
        JPanelOneLabel right1 = new JPanelOneLabel(goods.getName(),
                new FlowLayout(FlowLayout.LEFT));
        right1.getLabel().setFont(new DefaultFont(FONT_SIZE));
        right.add(right1);


        //right-2 가격
        JPanelOneLabel right2 = new JPanelOneLabel(String.valueOf(goods.getPrice() + "원"),
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
                cart.add(goods);
                cart.revalidate(); //갱신
            }
        });
        right3.add(buyBtn);
        right.add(right3);

        this.add(imageLb);
        this.add(right);
    }

    public GoodsDTO getGoods(){
        return goods;
    }

}

//cart 안에 들어있는 하나의 항목(반복적인 패널)
class ProductCarDetailPanel extends JPanel implements ActionListener{
    private static final int IMAGE_SIZE = 100;
    private static final int FONT_SZIE = 25;
    private static final int BTN_SIZE = 25;
    private GoodsDTO goods;
    private ImageIcon producImage; //사이즈가 조정된 imageIcon
    private JButton upBtn, downBtn;
    private JLabel numLb, priceLb;
    private int num = 1;
    private ProductCart cart;


    public ProductCarDetailPanel(GoodsDTO g, ProductCart cart) {
        this.goods = g;
        //this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.cart = cart;
        this.setLayout(new GridBagLayout());
        //image
        ImageIcon ii = new ImageIcon(DefaultFrame.PATH + "/images/goods/" + g.getCode() + ".png");
        producImage = new ImageIcon(ii.getImage().getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));


        //right
        JLabel imageLb = new JLabel(producImage);
        imageLb.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //left
        JPanel left = new JPanel();
        left.setLayout(new GridLayout(3, 1));
        left.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        //left-1 - 상품 이름
        JLabel nameLb = new JLabel(goods.getName() +
                (goods.getIce() ? "ICE" : goods.getHot() ? "HOT" : ""));
        nameLb.setFont(new DefaultFont(FONT_SZIE));
        nameLb.setSize(200, 30);
        left.add(nameLb);

        //left-2 - 상품 가격
        priceLb = new JLabel(goods.getPrice() + "원");
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


        downBtn.setFont(new DefaultFont(5));
        upBtn.setFont(new DefaultFont(5));
        delBtn.setFont(new DefaultFont(3));

        downBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(num > 1) {
                    num--;

                    numLb.setText(String.valueOf(num));
                    priceLb.setText(num * goods.getPrice() + "원");
                    cart.updateTot(-goods.getPrice());
                    int saleCount = cart.getGoodsCountInfoMap().get(g.getCode());
                    cart.getGoodsCountInfoMap().replace(g.getCode(), saleCount + 1); //hasp 맵 변경
                }
            }
        });

        upBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //만약 상품의 개수가 팔수 있는 재고보다 적다면
                int saleCount = cart.getGoodsCountInfoMap().get(g.getCode());
                System.out.println("count : " + saleCount);
                if(g.getMainCategory() != GoodsDTO.MAIN_CATEGORY_MUSIC)    //재고 보다 많이 담을려고 하는 경우
                    if(saleCount - 1 < 0) {
                        JOptionPane.showMessageDialog(cart.getParent(), "재고가 부족하여 더 이상 구매할 수 없습니다.");
                        return;
                    }
                cart.getGoodsCountInfoMap().replace(g.getCode(), saleCount - 1); //hasp 맵 변경
                num++;
                numLb.setText(String.valueOf(num));
                priceLb.setText(num * goods.getPrice() + "원");
                cart.updateTot(goods.getPrice());
            }
        });

        delBtn.addActionListener(this);

        left3.add(downBtn);
        left3.add(numLb);
        left3.add(upBtn);
        left3.add(delBtn);

        left.add(left3);

        //JPanel
        this.add(imageLb, DefaultFrame.easyGridBagConstraint(0,0,1,1));
        this.add(left, DefaultFrame.easyGridBagConstraint(1,0,3,1));
    }

    public GoodsDTO getGoods(){
        return goods;
    }


    public int getNum() {
        return num;
    }

    public JButton getUpBtn(){
        return upBtn;
    }

    public JButton getDownBtn(){
        return downBtn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cart.removeCartList(this, num);
    }
}


public class ProductListCartView extends JPanel {
    private ProductList pl;
    private ProductCart pc;
    private DefaultFrame parent;



    public ProductListCartView(DefaultFrame parent, RoomManageDTO room){
        this(parent, room, null);
    }
    public ProductListCartView(DefaultFrame parent, RoomManageDTO room, MemberDTO member) {
        this.parent = parent;
        this.setLayout(new GridBagLayout());
        pc = new ProductCart(parent, room, member);
        pl = new ProductList(parent.getController().getGoodsDAO(), pc);


        pl.setBackground(DefaultFrame.TOP_BACKGROUND_COLOR);
        pc.setBackground(DefaultFrame.TOP_BACKGROUND_COLOR);

        pl.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 15));
        pc.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 30));
        add(pl, DefaultFrame.easyGridBagConstraint(0, 0, 8, 1));
        add(pc, DefaultFrame.easyGridBagConstraint(1, 0, 2, 1));
    }

}