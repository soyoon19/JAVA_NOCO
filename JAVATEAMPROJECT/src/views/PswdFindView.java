package views;

import custom_component.DefaultFont;
import custom_component.NumberPadPanel;
import javax.swing.*;
import java.awt.*;

public class PswdFindView extends JPanel {
    private static final int FONT_SIZE = 80;
    JLabel phonelb, birthlb;
    JTextField phonetf, birthtf;
    JButton inquirybt;
    NumberPadPanel np;

    public PswdFindView() {
        this.setLayout(new BorderLayout());
        // BorderLayout의 Center
        JPanel blc = new JPanel(new GridBagLayout());
        add(blc, BorderLayout.CENTER);

            //Grid Bag Layout의 left
        GridBagConstraints gtf = new GridBagConstraints();
        gtf.weightx = 2;
        JPanel glc = new JPanel(new GridLayout(4,1));

            //center-1
        glc.add(new JPanel());

            //center-2
        JPanel center2 = new JPanel(new GridBagLayout());
                //center-2의 left
        GridBagConstraints bcglc = new GridBagConstraints();
        bcglc.weightx = 1;
        center2.add(new JPanel());
                //center-2의 right
        GridBagConstraints bctgrc = new GridBagConstraints();
        bctgrc.weightx = 8;
        JPanel bcgr = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phonelb = new JLabel("연락처 : ");
        phonelb.setFont(new DefaultFont(FONT_SIZE));
        bcgr.add(phonelb);
        phonetf = new JTextField(10);
        phonetf.setFont(new DefaultFont(FONT_SIZE));
        bcgr.add(phonetf);
        center2.add(bcgr, bctgrc);

        glc.add(center2);

            //center-3
        JPanel center3 = new JPanel(new GridBagLayout());
                //center-3의 left
        GridBagConstraints bcbglc = new GridBagConstraints();
        bcbglc.weightx = 1;
        center3.add(new JPanel());
                //center-3의 right
        GridBagConstraints bcbgrc = new GridBagConstraints();
        bcbgrc.weightx = 8;
        JPanel bcbgr = new JPanel(new FlowLayout(FlowLayout.LEFT));
        birthlb = new JLabel("생년월일 : ");
        birthlb.setFont(new DefaultFont(FONT_SIZE));
        bcbgr.add(birthlb);
        birthtf = new JTextField(10);
        birthtf.setFont(new DefaultFont(FONT_SIZE));
        bcbgr.add(birthtf);
        center3.add(bcbgr, bcbgrc);

        glc.add(center3);

            //center-4
        glc.add(new JPanel());
        blc.add(glc,gtf);

            //Grid Bag Layout의 right
        JPanel gbr = new JPanel();
        GridBagConstraints gnp = new GridBagConstraints();
        gnp.weightx = 1;

        np = new NumberPadPanel();
        gbr.add(np);
        blc.add(gbr,gnp);

        // BorderLayout의 South
        JPanel stp = new JPanel();
        add(stp, BorderLayout.SOUTH);
        stp.setLayout(new FlowLayout());
        stp.setBackground(Color.gray);

        inquirybt = new JButton("조회");
        inquirybt.setPreferredSize(new Dimension(200, 100));
        inquirybt.setFont(new DefaultFont(FONT_SIZE));
        stp.add(inquirybt);
    }

}