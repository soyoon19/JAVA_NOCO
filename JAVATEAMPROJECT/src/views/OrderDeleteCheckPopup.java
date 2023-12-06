package views;

import dto.OrderDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderDeleteCheckPopup extends JDialog implements ActionListener {
    public static final int width=300, height=300;

    DefaultFrame parent;

    OrderDTO order;

    public OrderDeleteCheckPopup(DefaultFrame prt){
        super(prt,"확인창",true);
        this.setSize(width,height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container ct= getContentPane();
        ct.setLayout(new BoxLayout(ct,BoxLayout.Y_AXIS));

        JLabel message= new JLabel("해당 주문 내역을 취소 하시겠습니까?");
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setBorder(new EmptyBorder(80, 0, 0, 0));


        JPanel bLayout=new JPanel();
        bLayout.setLayout(new FlowLayout());

        JButton y= new JButton("YES");
        JButton n= new JButton("NO");

        bLayout.add(y);
        y.addActionListener(this);
        n.addActionListener(this);

        bLayout.add(n);

        ct.add(message);
        ct.add(bLayout);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e){
        String s=e.getActionCommand();
        switch (s) {
            case "YES":
                (new OrderDeleteRCPopup(parent)).setVisible(true);
                this.dispose();
                break;

            case"NO":
                dispose();
                break;
        }

    }
}
