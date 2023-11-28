package views;
//완성
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WorkerDeleteRCPopup extends JDialog {
    public static final int width=300, height=300;
    public WorkerDeleteRCPopup(DefaultFrame prt){
        super(prt,"",true);
        this.setSize(width,height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container ct= getContentPane();
        ct.setLayout(new BoxLayout(ct,BoxLayout.Y_AXIS));

        JLabel message= new JLabel("삭제가 완료되었습니다.");
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setBorder(new EmptyBorder(80, 0, 0, 0));


        JPanel bLayout=new JPanel();
        bLayout.setLayout(new FlowLayout());

        JButton y= new JButton("YES");


        bLayout.add(y);


        ct.add(message);
        ct.add(bLayout);

    }
}
