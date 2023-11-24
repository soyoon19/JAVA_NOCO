package custom_component;

import javax.swing.*;
import java.awt.*;

/*
단순히 JPanel안에 JLabel 하나만 사용할 때
편하게 사용할 수 있게 클래스로 구현항였다.

사용방법은 Text만 주면 FlowLayout에 입력한 내용의 JLabel이
들어가는 형태이다.

따로 레이아웃을 정하고 싶다면 Text와 추가로 Layout을 생성자에 주면
원하는 레이웃으로 만들어 준다.

 */
public class JPanelOneLabel extends JPanel {
    private JLabel label;
    public JPanelOneLabel(String name){
        label = new JLabel(name);
        this.add(label);
    }

    public JPanelOneLabel(String name, LayoutManager layout){
        this(name);
        this.setLayout(layout);
    }

    //JLabel을 폰트를 수정하고 싶다면 이 클래스를 호출해야 한다는 점을 잊지 말자
    public JLabel getLabel(){
        return label;
    }
}
