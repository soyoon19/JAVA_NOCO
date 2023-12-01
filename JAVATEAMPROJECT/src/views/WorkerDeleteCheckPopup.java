package views;
//완성
import custom_component.DefaultFont;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
public class WorkerDeleteCheckPopup extends JDialog {
    public static final int width=300, height=300;
    public WorkerDeleteCheckPopup(DefaultFrame prt){
        super(prt,"확인창",true);
        this.setSize(width,height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container ct= getContentPane();
        ct.setLayout(new BoxLayout(ct,BoxLayout.Y_AXIS));

        JLabel message= new JLabel("OOO님을 삭제 하시겠습니까?");
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setBorder(new EmptyBorder(80, 0, 0, 0));


        JPanel bLayout=new JPanel();
        bLayout.setLayout(new FlowLayout());

        JButton y= new JButton("YES");
        JButton n= new JButton("NO");

        bLayout.add(y);
        bLayout.add(n);

        ct.add(message);
        ct.add(bLayout);

    }
}
