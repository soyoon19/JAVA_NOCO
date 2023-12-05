package custom_component;

import views.DefaultFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
넘버 패드를 그리드로 구현한 클래스이다.
넘버 패드를 사용한다면 이 클래스를 사용하거나
상속받아 커스텀에 사용하자

디자인이 마음에 안 들다면 다음에 팀장에게 어떻게
수정하고 싶은지 생각해서 알려주자
 */
public class NumberPadPanel extends  JPanel implements ActionListener {
    protected JButton[] numberBtns;  //숫자 버튼
    protected JButton deleteBtn;  //삭제 버튼
    protected JButton allDelBtn;
    private JTextField target = null;

    public NumberPadPanel() {
        int i;
        this.setLayout(new GridLayout(4, 3));
        numberBtns = new JButton[10]; //0~9 10개 생성

        for(i = 0; i < 9; i++) { //1~9 버튼 생성
            numberBtns[i] = new JButton(String.valueOf(i + 1));
            numberBtns[i].addActionListener(this);
        }


        allDelBtn = new JButton("입력취소");
        allDelBtn.setFont(new DefaultFont(30));
        allDelBtn.addActionListener(this);

        numberBtns[i] = new JButton("0"); //0 버튼 생성
        numberBtns[i].addActionListener(this);


        deleteBtn = new JButton(""); //<-(지우기 버튼 생성)
        deleteBtn.setFont(new DefaultFont()); //폰트 설정
        deleteBtn.addActionListener(this);
        deleteBtn.setIcon(FreeImageIcon.resizeImageIcon(DefaultFrame.PATH +
                "/images/deleteBtn.png", 100, 100));


        //버튼 붙이기
        for(i = 0; i < 9; i++) {
            numberBtns[i].setFont(new DefaultFont());
            add(numberBtns[i]);
        }
        numberBtns[i].setFont(new DefaultFont());

        //빈 부분은 패널로 메꿔줌
        add(allDelBtn);
        add(numberBtns[i]);
        add(deleteBtn);
    }

    public void setTarget(JTextField target) {
        if(this.target != null) this.target.setBorder(target.getBorder());  //전에 target이 있다면 Border 준것을 해제한다.
        target.setBorder(new LineBorder(Color.YELLOW, 5));  //포커스가 잡혀 있다는 것을 보여줌
        this.target = target;
    }

    public JButton[] getNumberBtns(){
        return  numberBtns;
    }

    public JButton getDeleteBtn(){
        return deleteBtn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(target == null) return;

        String str = target.getText();

        String s = e.getActionCommand();
        if(s.equals("")){
            if(str.length() > 0) {  //문자 길이가 0보다 크다면 == 삭제할 문자가 있을 면
                target.setBorder(new LineBorder(Color.YELLOW, 5));
                str = str.substring(0, str.length() - 1);
            }else
                target.setBorder(new LineBorder(Color.RED, 5)); //문제가 있다걸 알려줌

        }else if(s.equals("입력취소")){
            str ="";
        }else{
            if(str.length() != target.getColumns()){    //설정한 문자 길이 이상으로는 못 들어가게 한다.
                target.setBorder(new LineBorder(Color.YELLOW, 5));
                str += s; //선택한 숫자랑 더함
            }else
                target.setBorder(new LineBorder(Color.RED, 5));

        }

        target.setText(str);
    }
}