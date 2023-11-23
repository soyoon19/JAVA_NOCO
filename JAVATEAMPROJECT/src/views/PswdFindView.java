package views;

import javax.swing.*;
import java.awt.*;

public class PswdFindView extends JPanel {
    JLabel phonelb,birthlb;
    JTextField phontf,birthtf;
    JButton inquirybt;
    public PswdFindView(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.red);

        //BorderLayout의 Center
        JPanel ctp = new JPanel();
        ctp.setLayout(new FlowLayout());
        ctp.setBackground(Color.yellow);

        //ctp JPanel의 top 부분
        JPanel ctptop = new JPanel();
        ctptop.setLayout(new FlowLayout());
        ctptop.setBackground(Color.pink);

        phonelb = new JLabel("연락처 :");
        phontf = new JTextField(8);

        ctptop.add(phonelb);
        ctptop.add(phontf);

        //ctp JPanel의 bottom 부분
        JPanel ctpbtm = new JPanel();
        ctpbtm.setLayout(new FlowLayout());
        ctpbtm.setBackground(Color.blue);

        birthlb = new JLabel("생년월일 :");
        birthtf = new JTextField(7);

        ctpbtm.add(birthlb);
        ctpbtm.add(birthtf);

        ctp.add(ctptop);
        ctp.add(ctpbtm);

        add(ctp,BorderLayout.CENTER);

        //BorderLayout의 South
        JPanel stp = new JPanel();
        stp.setLayout(new FlowLayout());
        stp.setBackground(Color.gray);

        inquirybt = new JButton("조회");
        stp.add(inquirybt);

        add(stp,BorderLayout.SOUTH);
    }


}
