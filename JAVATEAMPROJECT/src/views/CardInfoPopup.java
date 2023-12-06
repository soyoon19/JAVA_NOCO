package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

import controller_db.Controller;
import custom_component.DefaultFont;
import custom_component.NumberPadListener;
import custom_component.NumberPadPanel;
import dao.OrderDAO;
import dto.*;


class CardInfoInputPanel extends JPanel implements ActionListener{
    public static final int FONT_SIZE = 40;
    JLabel cardNumLb, dateLb, cvcLb, pwLb; //입력받을 라벨들
    JTextField[] cardNumsTf; //카드 넘버를 받을 필드 4개
    JTextField cvc;         //cvc
    JPasswordField pwPf;    //card password Field
    JButton resetBtn, payBtn; //초기화, 결제 버튼

    JComboBox yearCB, monthCB;

    NumberPadPanel np;

    CardInfoPopup cardInfoPopup;

    public CardInfoInputPanel(CardInfoPopup cardInfoPopup) {
        //this.setBackground(Color.RED);
        this.setLayout(new BorderLayout());
        this.cardInfoPopup = cardInfoPopup;
        this.np = cardInfoPopup.getNumberPanel();


        //center
        JPanel ctn = new JPanel();
        ctn.setLayout(new GridBagLayout());

        //라벨의 맞는 텍스트 넣어주기
        cardNumLb = new JLabel("카드번호");
        dateLb = 	new JLabel("CVC");
        cvcLb = 	new JLabel("유효기간");
        pwLb = 		new JLabel("카드 비빌번호");

        //배열에 담는다.
        JLabel[] lbs = {cardNumLb, dateLb, cvcLb, pwLb};


        //한 행에 라벨이 하나씩 들어가고 같은 레이아웃을 가지고 있다.
        //그래서 반복문으로 처리 했다.
        //centerL
        JPanel centerL = new JPanel();
        centerL.setLayout(new GridLayout(4,1));

        for(int i = 0; i < lbs.length; i++) {
            lbs[i].setFont(new DefaultFont(FONT_SIZE));
            centerL.add(lbs[i]);
        }


        //centerR
        JPanel centerR = new JPanel();
        centerR.setLayout(new GridLayout(4,1));

        //centerR-1
        //0 index 카드번호 패널이다.
        JPanel centerR_1 = new JPanel();
        centerR_1.setLayout(new FlowLayout(FlowLayout.LEFT));

        cardNumsTf = new JTextField[4]; //동일한 텍스트 필드 4개를 만든다.
        for(int i = 0; i < cardNumsTf.length; i++) {
            if(i < 2)
                cardNumsTf[i] = new JTextField(4); //textField 생성할 때 int 넣으면 자리수에 맞는 라벨이 생긴다.
            else
                cardNumsTf[i] = new JPasswordField(4);

            cardNumsTf[i].setEditable(false);
            cardNumsTf[i].setBackground(Color.WHITE);
            cardNumsTf[i].addMouseListener(new NumberPadListener(cardNumsTf[i], np));

            cardNumsTf[i].setFont(new DefaultFont(FONT_SIZE));
            centerR_1.add(cardNumsTf[i]);
        }
        centerR.add(centerR_1);

        //centerR-2
        //2 index CVC 패널이다.
        JPanel centerR_2 = new JPanel();
        centerR_2.setLayout(new FlowLayout(FlowLayout.LEFT));

        cvc = new JTextField(3);
        cvc.setFont(new DefaultFont(FONT_SIZE));
        cvc.setEditable(false);
        cvc.setBackground(Color.WHITE);
        cvc.addMouseListener(new NumberPadListener(cvc, np));
        centerR_2.add(cvc);
        centerR.add(centerR_2);


        //centerR-3
        //2 index 유효기간
        JPanel centerR_3 = new JPanel();
        centerR_3.setLayout(new FlowLayout(FlowLayout.LEFT));
        String[] years = new String[15];
        int nowYear = (new Date()).getYear();
        nowYear -= nowYear / 100 * 100;

        for(int i = nowYear; i < years.length + nowYear; i++){
            years[i - nowYear] = String.valueOf(i);
        }
        yearCB = new JComboBox(years);
        yearCB.setFont(new DefaultFont(FONT_SIZE));

        String[] month = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12"};
        monthCB = new JComboBox(month);
        monthCB.setFont(new DefaultFont(FONT_SIZE));
        centerR_3.add(monthCB);
        JLabel tLb = new JLabel("/");
        tLb.setFont(new DefaultFont(FONT_SIZE));
        centerR_3.add(tLb);
        centerR_3.add(yearCB);
        centerR.add(centerR_3);




        //centerR-4
        //3 index 비밀번호 패널이다.
        JPanel centerR_4 = new JPanel();
        centerR_4.setLayout(new FlowLayout(FlowLayout.LEFT));
        pwPf = new JPasswordField(4);
        System.out.println(pwPf.getBorder());
        pwPf.setFont(new DefaultFont());
        pwPf.setEditable(false);
        pwPf.setBackground(Color.WHITE);
        pwPf.addMouseListener(new NumberPadListener(pwPf, np));
        centerR_4.add(pwPf);
        centerR.add(centerR_4);




        ctn.add(centerL, DefaultFrame.easyGridBagConstraint(0,0,1,1));
        ctn.add(centerR, DefaultFrame.easyGridBagConstraint(1,0,4,1));
        add(ctn, BorderLayout.CENTER);


        //bottom
        /*
        우측 하단의  버튼이 있는 패널이다.
        하단의 배치하기 위해서 미리 이 객체의 레이아웃을 BorderLayout로 만들었다.
        우측에 두기 위해서 FlowLayout.RIGHT을 사용해 우측 정렬을 사용했다.
        JButton 사이즈는 setSize는 생각하는 것 처럼 잘 작동이 안되는 경우가 있음으로
        가능하면 setPreferredSize을 활용하자
         */
        JPanel btm = new JPanel();
        btm.setLayout(new FlowLayout(FlowLayout.RIGHT));
        resetBtn = new JButton("초기화");
        payBtn = new JButton("결제");

        resetBtn.setPreferredSize(new Dimension(150, 80));
        payBtn.setPreferredSize(new Dimension(150, 80));

        resetBtn.addActionListener(this);
        payBtn.addActionListener(this);

        btm.add(resetBtn); btm.add(payBtn);

        add(btm, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if(s.equals("초기화")){

        }else if(s.equals("결제")){
            //Todo 결졔 예외처리
            GoodsDTO[] goodsArr = cardInfoPopup.getGoodsArr();
            int[] nums = cardInfoPopup.getNums();
            MemberDTO member = cardInfoPopup.getMember();
            Controller controller = cardInfoPopup.getParent().getController();
            MemberLogDTO memberLog = null;
            if(member != null)
                memberLog = controller.getMemberLogDAO().findById(member.getHp());
            RoomManageDTO room = cardInfoPopup.getRoom();

            Date date = new Date();

            //방 정보 생성
            RoomIfmDTO roomImf = new RoomIfmDTO(member != null ? member.getHp() : null
                    , new java.sql.Time(date.getHours(), date.getMinutes(), date.getSeconds())
                    , room.getNum(), 0, 0, 0, true);


            //일련번호 갱신
            OrderDTO lastOrder = controller.getOrderDAO().findLastRow();
            String code;
            if(lastOrder == null){
                code = OrderDTO.PREFIX + "0001";
            }else{
                String lastCode = lastOrder.getO_code();
                System.out.println("x : " + lastCode);

                lastCode = lastCode.substring(1);
                System.out.println("y : " + lastCode);


                int intCode = Integer.parseInt(lastCode);
                intCode++;

                String needZero = ""; //빈 공간을 0으로 채울 변수

                lastCode = String.valueOf(intCode);
                while(lastCode.length() + needZero.length() < 4)
                    needZero += "0";

                code = OrderDTO.PREFIX + needZero + lastCode;
            }

            int total = 0, totalDiscount = 0, num, price, discount;
            int y, m, d;
            y = date.getYear(); m = date.getMonth(); d = date.getDate();

            String state;
            for(int i = 0; i < goodsArr.length; i++){
                num = nums[i];
                state = goodsArr[i].getIce() ? "ICE" : goodsArr[i].getHot() ? "HOT" :
                        "None";

                price = goodsArr[i].getPrice() * num;
                discount = 0;

                if(goodsArr[i].getDisStatus() && member != null)
                    discount = (int)(price * (MemberDTO.gradeToDiscount(memberLog.getM_rate()) * 0.01));

                totalDiscount += discount;
                total += price;

                //주문 상세 insert
                controller.getOrderHDAO().insert(new OrderHDTO(
                        code, goodsArr[i].getCode(),
                        state, num, price,
                        discount ,
                        goodsArr[i].getCost() * num, new java.sql.Date(y, m, d)
                ));
            }

            //주문 정보 insert
            controller.getOrderDAO().insert(new OrderDTO(
                    code, member == null ? null : member.getHp()
                    , "",room.getCode(),  new java.sql.Date(y, m, d),
                    new java.sql.Time(date.getHours(), date.getMinutes(), date.getSeconds()),
                    null,total, totalDiscount, OrderDTO.STATUS_ORDER
            ));

            //todo 회원 할인 정보
            //회원 등급 설정



            int i = 0;
            //멤버 음악 개수와 굿즈  update & Goods 수량 update
            for(GoodsDTO goods : goodsArr){
                if(goods.getMainCategory() == GoodsDTO.MAIN_CATEGORY_MUSIC){
                    int music = Integer.parseInt(goods.getName().substring(0, goods.getName().length() - 1));
                    if(member != null) memberLog.setHoldSong(memberLog.getHoldSong() + music);  //맴베인 경우 방에 사용할 곡을 추가한다.
                    else roomImf.setLeftSong(roomImf.getLeftSong() + music);        //맴버가 아닌 경우 룸에 구한 곡을 추가한다 
                }else if(goods.getMainCategory() == GoodsDTO.MAIN_CATEGORY_DRINK){ //Goods 수량 업데이트
                    GoodsDTO pastGoods = controller.getGoodsDAO().findById(goods.getCode());
                    pastGoods.setSaleCount(pastGoods.getSaleCount() - nums[i]);
                    controller.getGoodsDAO().update(pastGoods);
                }
                i++;
            }

            //room 정보 insert
            if(member != null) {
                roomImf.setUserHp(member.getHp());
                //memberLog.setTotalPay();
                cardInfoPopup.getParent().move(new MusicUseView(cardInfoPopup.getParent(), room, member, roomImf));
            }else{
                controller.getRoomImfDAO().insert(roomImf);
                cardInfoPopup.getParent().resetMove(new UserHomeView(cardInfoPopup.getParent()));
            }
            System.out.println("insert Complete!");
            cardInfoPopup.dispose();
        }

    }
}

/*
가장 메인이 되는 Popup 페이지이다.
이 페이지에는 카드 정보를 입력, NumberPad을 다른 패널로 만들어기에
여기서는 붙여주기만 한다.
 */
public class CardInfoPopup extends JDialog implements ActionListener{
    public static final int WIDTH = 1400,
            HEIGHT = 600;
    public static final String TITLE = "결제화면";
    private DefaultFrame parent;
    private JPanel top, center;
    private NumberPadPanel numberPanel;
    private CardInfoInputPanel cardinputPanel;
    private JButton backBtn;
    private Container cp;
    private MemberDTO member;
    private GoodsDTO[] goodsArr;
    private RoomManageDTO room;
    private int[] nums;

    public CardInfoPopup(DefaultFrame parent, MemberDTO member, RoomManageDTO room, GoodsDTO[] goods, int[] nums ) {
        super(parent, TITLE, true);
        setSize(WIDTH, HEIGHT);
        this.parent = parent;
        this.member = member;
        this.goodsArr = goods;
        this.nums = nums;
        this.room = room;

        cp = getContentPane();

        top = new JPanel();
        center = new JPanel();

        numberPanel = new NumberPadPanel();
        cardinputPanel = new CardInfoInputPanel(this);

        //top
        backBtn = new JButton("뒤로가기");
        backBtn.addActionListener(this);
        top.setLayout(new FlowLayout(FlowLayout.LEFT));
        top.add(backBtn);
        cp.add(top, BorderLayout.NORTH);

        //center
        center.setLayout(new GridBagLayout());
        cardinputPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 15));
        numberPanel.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 30));
        center.add(cardinputPanel, DefaultFrame.easyGridBagConstraint(0, 0, 7, 1));
        center.add(numberPanel, DefaultFrame.easyGridBagConstraint(1, 0, 2, 1));
        cp.add(center, BorderLayout.CENTER);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("뒤로가기")){
            dispose();
        }
    }

    public NumberPadPanel getNumberPanel(){
        return numberPanel;
    }

    public MemberDTO getMember() {
        return member;
    }

    public GoodsDTO[] getGoodsArr() {
        return goodsArr;
    }

    @Override
    public DefaultFrame getParent() {
        return parent;
    }

    public int[] getNums() {
        return nums;
    }

    public RoomManageDTO getRoom() {
        return room;
    }
}