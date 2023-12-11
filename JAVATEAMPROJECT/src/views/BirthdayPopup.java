package views;

import custom_component.DefaultFont;
import custom_component.FreeImageIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BirthdayPopup extends JDialog {
    private static final int WIDTH = 600, HEIGHT = 700;
    DefaultFrame parent;
    JButton check;
    JLabel countLb;
    public BirthdayPopup(DefaultFrame parent){
        super(parent, "birthday!", true);
        this.parent = parent;
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel main = new JPanel();
        main.setLayout(new BorderLayout());

        //top
        JPanel top = new JPanel();
        JLabel congratsLb = new JLabel("<생일 축하드려요 !>");
        congratsLb.setFont(new DefaultFont(25));
        top.add(congratsLb);
        main.add(top, BorderLayout.NORTH);

        //center
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        //center-top
        JPanel centerTop = new JPanel();
        JLabel plusLb = new JLabel("1곡을 서비스로 드립니다.!");
        plusLb.setFont(new DefaultFont(20));
        centerTop.add(plusLb);
        center.add(centerTop, BorderLayout.NORTH);

        //center-center
        JPanel centerCenter = new JPanel();
        centerCenter.add(new JLabel(FreeImageIcon.resizeImageIcon(
                DefaultFrame.PATH + "/images/birth.png", 500,500)));
        center.add(centerCenter, BorderLayout.CENTER);

        //center-bottom
        JPanel centerBtm = new JPanel();
        JButton check = new JButton("확 인");
        check.setFont(new DefaultFont(15));
        check.setPreferredSize(new Dimension(500, 30));
        centerBtm.add(check);
        center.add(centerBtm, BorderLayout.SOUTH);

        main.add(center, BorderLayout.CENTER);

        //bottom
        JDialog jDialog = this;

        Thread t = new Thread(){
          public void run(){
              for(int i = 10; i > 0; i--){
                  countLb.setText(i + "초 뒤에 자동으로 닫힙니다.");

                  try {
                      sleep(1000);
                  } catch (InterruptedException e) {
                      throw new RuntimeException(e);
                  }
              }

              jDialog.dispose();
          }
        };

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        });

        JPanel btm = new JPanel();
        countLb = new JLabel("초 뒤에 자동으로 닫힙니다.");
        countLb.setFont(new DefaultFont(10));
        btm.add(countLb);
        main.add(btm, BorderLayout.SOUTH);

        this.add(main);
    }
}
