package views;

import java.awt.*;
import javax.swing.*;
import custom_component.DefaultFont;


class NumberPadPane extends JPanel{
    JButton[] numberBtns;  //숫자 버튼
    JButton deleteBtn;  //삭제 버튼

    public NumberPadPane() {
        int i;
        this.setLayout(new GridLayout(4, 3));
        numberBtns = new JButton[10]; //0~9 10개 생성

        for(i = 0; i < 9; i++) { //1~9 버튼 생성
            numberBtns[i] = new JButton(String.valueOf(i + 1));
        }

        numberBtns[i] = new JButton("0"); //0 버튼 생성
        deleteBtn = new JButton("<-"); //<-(지우기 버튼 생성)
        deleteBtn.setFont(new DefaultFont()); //폰트 설정

        //버튼 붙이기
        for(i = 0; i < numberBtns.length; i++) {
            numberBtns[i].setFont(new DefaultFont());
            add(numberBtns[i]);
        }

        //빈 부분은 패널로 메꿔줌
        add(new JPanel());
        add(deleteBtn);
    }
}

class CardInfoInputPanel extends JPanel{
    JLabel cardNumLb, dateLb, cvcLb, pwLb;
    JPanel[] inputListPanel;
    JTextField[] cardNumsTf;
    JTextField cvc;
    JPasswordField pwPf;
    JButton resetBtn, payBtn;

    public CardInfoInputPanel() {
        //this.setBackground(Color.RED);
        this.setLayout(new BorderLayout());



        //center
        JPanel ctn = new JPanel();
        ctn.setLayout(new GridLayout(4, 1));

        cardNumLb = new JLabel("카드번호     ");
        dateLb = 	new JLabel("유효기간     ");
        cvcLb = 	new JLabel("CVC         ");
        pwLb = 		new JLabel("카드 비빌번호 ");

        JLabel[] lbs = {cardNumLb, dateLb, cvcLb, pwLb};

        inputListPanel = new JPanel[4];
        for(int i = 0; i < inputListPanel.length; i++) {
            inputListPanel[i] = new JPanel();
            inputListPanel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            // lbs[i].setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
            lbs[i].setFont(new DefaultFont());

            inputListPanel[i].add(lbs[i]);

            ctn.add(inputListPanel[i]);
        }

        cardNumsTf = new JTextField[4];
        for(int i = 0; i < cardNumsTf.length; i++) {
            cardNumsTf[i] = new JTextField(4);
            cardNumsTf[i].setFont(new DefaultFont());
            inputListPanel[0].add(cardNumsTf[i]);
        }

        cvc = new JTextField(3);
        cvc.setFont(new DefaultFont());
        inputListPanel[2].add(cvc);

        pwPf = new JPasswordField(4);
        pwPf.setFont(new DefaultFont());
        inputListPanel[3].add(pwPf);


        add(ctn);


        //bottom
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

public class CardInfoPopup extends JDialog{
    public static final int WIDTH = 1600,
            HEIGHT = 600;

    JPanel top, center;
    NumberPadPane numberPanel;
    CardInfoInputPanel cardinputPanel;
    JButton backBtn;
    Container cp;
    DefaultFrame parent;

    public CardInfoPopup(DefaultFrame parent) {
        super(parent, "CARD", true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cp = getContentPane();

        top = new JPanel();
        center = new JPanel();

        numberPanel = new NumberPadPane();
        cardinputPanel = new CardInfoInputPanel();

        //top

        backBtn = new JButton("뒤로가기");
        top.setLayout(new FlowLayout(FlowLayout.LEFT));
        top.add(backBtn);
        cp.add(top, BorderLayout.NORTH);

        //center
        center.setLayout(new GridBagLayout());
        cardinputPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 15));
        numberPanel.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 30));
        center.add(cardinputPanel, DefaultFrame.easyGridBagConstraint(0, 0, 5, 1));
        center.add(numberPanel, DefaultFrame.easyGridBagConstraint(1, 0, 2, 1));
        cp.add(center, BorderLayout.CENTER);
    }



}
