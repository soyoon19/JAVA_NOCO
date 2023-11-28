package views;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.*;
import custom_component.DefaultFont;
import custom_component.NumberPadListener;
import custom_component.NumberPadPanel;


class CardInfoInputPanel extends JPanel {
    public static final int FONT_SIZE = 40;
    JLabel cardNumLb, dateLb, cvcLb, pwLb; //입력받을 라벨들
    JTextField[] cardNumsTf; //카드 넘버를 받을 필드 4개
    JTextField cvc;         //cvc
    JPasswordField pwPf;    //card password Field
    JButton resetBtn, payBtn; //초기화, 결제 버튼

    JComboBox yearCB, monthCB;

    NumberPadPanel np;

    public CardInfoInputPanel(NumberPadPanel np) {
        //this.setBackground(Color.RED);
        this.setLayout(new BorderLayout());
        this.np = np;


        //center
        JPanel ctn = new JPanel();
        ctn.setLayout(new GridBagLayout());

        //라벨의 맞는 텍스트 넣어주기
        cardNumLb = new JLabel("카드번호");
        dateLb = 	new JLabel("유효기간");
        cvcLb = 	new JLabel("CVC");
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

        btm.add(resetBtn); btm.add(payBtn);

        add(btm, BorderLayout.SOUTH);

    }


}

/*
가장 메인이 되는 Popup 페이지이다.
이 페이지에는 카드 정보를 입력, NumberPad을 다른 패널로 만들어기에
여기서는 붙여주기만 한다.
 */
public class CardInfoPopup extends JDialog{
    public static final int WIDTH = 1400,
            HEIGHT = 600;
    public static final String TITLE = "결제화면";
    JFrame parent;
    JPanel top, center;
    NumberPadPanel numberPanel;
    CardInfoInputPanel cardinputPanel;
    JButton backBtn;
    Container cp;


    public CardInfoPopup(JFrame parent) {
        super(parent, TITLE, true);
        setSize(WIDTH, HEIGHT);

        cp = getContentPane();

        top = new JPanel();
        center = new JPanel();

        numberPanel = new NumberPadPanel();
        cardinputPanel = new CardInfoInputPanel(numberPanel);

        //top
        backBtn = new JButton("뒤로가기");
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



}
