package custom_component;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


//NumberPad와 Textfiled을 연결해 이벤트 발생시 연결해 리스너
//TextFiled을 Nubmerpad을 마우스 클릭했을 때 사용 가능하게 해주는 리스너이다
//CardInfoPopup을 참고하고 잘 모르겠다면 팀장에게 물어보자.
public class NumberPadListener implements MouseListener {
    JTextField target;
    NumberPadPanel np;

    public NumberPadListener(JTextField target, NumberPadPanel np){
        this.target = target;
        this.np = np;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        np.setTarget(target); //마우스가 클릭 됬을 때 포커스를 변경한다.
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}