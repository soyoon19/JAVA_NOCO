package custom_component;

import javax.swing.*;
import java.awt.*;

/*
넘버 패드를 그리드로 구현한 클래스이다.
넘버 패드를 사용한다면 이 클래스를 사용하거나
상속받아 커스텀에 사용하자

디자인이 마음에 안 들다면 다음에 팀장에게 어떻게
수정하고 싶은지 생각해서 알려주자
 */
public class NumberPadPanel extends  JPanel{
    protected JButton[] numberBtns;  //숫자 버튼
    protected JButton deleteBtn;  //삭제 버튼

    public NumberPadPanel() {
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
