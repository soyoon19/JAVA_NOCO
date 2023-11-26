package views;

import java.awt.*;
import javax.swing.*;
import custom_component.DefaultFont;


class NumberPadPane extends JPanel{
    private JButton[] numberBtns;  //숫자 버튼
    private JButton deleteBtn;  //삭제 버튼

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

    public JButton[] getNumberBtns(){
        return  numberBtns;
    }

    public JButton getDeleteBtn(){
        return deleteBtn;
    }
}

class CardInfoInputPanel extends JPanel{
    JLabel cardNumLb, dateLb, cvcLb, pwLb; //입력받을 라벨들
    JPanel[] inputListPanel; //각 라인별 FL
    JTextField[] cardNumsTf; //카드 넘버를 받을 필드 4개
    JTextField cvc;         //cvc
    JPasswordField pwPf;    //card password Field
    JButton resetBtn, payBtn; //초기화, 결제 버튼

    public CardInfoInputPanel() {
        //this.setBackground(Color.RED);
        this.setLayout(new BorderLayout());


        //center
        JPanel ctn = new JPanel();
        ctn.setLayout(new GridLayout(4, 1));

        //라벨의 맞는 텍스트 넣어주기
        cardNumLb = new JLabel("카드번호     ");
        dateLb = 	new JLabel("유효기간     ");
        cvcLb = 	new JLabel("CVC         ");
        pwLb = 		new JLabel("카드 비빌번호 ");

        //배열에 담는다.
        JLabel[] lbs = {cardNumLb, dateLb, cvcLb, pwLb};


        //한 행에 라벨이 하나씩 들어가고 같은 레이아웃을 가지고 있다.
        //그래서 반복문으로 처리 했다.
        inputListPanel = new JPanel[4];
        for(int i = 0; i < inputListPanel.length; i++) {
            inputListPanel[i] = new JPanel();
            inputListPanel[i].setLayout(new FlowLayout(FlowLayout.LEFT));
            // lbs[i].setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
            lbs[i].setFont(new DefaultFont());

            inputListPanel[i].add(lbs[i]);

            ctn.add(inputListPanel[i]);
        }

        //0 index 카드번호 패널이다.
        cardNumsTf = new JTextField[4]; //동일한 텍스트 필드 4개를 만든다.
        for(int i = 0; i < cardNumsTf.length; i++) {
            cardNumsTf[i] = new JTextField(4); //textField 생성할 때 int 넣으면 자리수에 맞는 라벨이 생긴다.
            cardNumsTf[i].setFont(new DefaultFont());
            inputListPanel[0].add(cardNumsTf[i]);
        }

        //2 index CVC 패널이다.
        cvc = new JTextField(3);
        cvc.setFont(new DefaultFont());
        inputListPanel[2].add(cvc);

        //3 index 비밀번호 패널이다.
        pwPf = new JPasswordField(4);
        pwPf.setFont(new DefaultFont());
        inputListPanel[3].add(pwPf);


        add(ctn);


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
    public static final int WIDTH = 1600,
            HEIGHT = 600;
    public static final String TITLE = "결제화면";
    JFrame parent;
    JPanel top, center;
    NumberPadPane numberPanel;
    CardInfoInputPanel cardinputPanel;
    JButton backBtn;
    Container cp;


    public CardInfoPopup(JFrame parent) {
        super(parent, TITLE, true);
        setSize(WIDTH, HEIGHT);

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
