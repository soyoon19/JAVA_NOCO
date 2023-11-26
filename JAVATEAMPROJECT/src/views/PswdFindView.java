package views;

import custom_component.DefaultFont;
import custom_component.NumberPadPanel;
import javax.swing.*;
import java.awt.*;

public class PswdFindView extends JPanel {
    private static final int FONT_SIZE = 70;
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
        JPanel glc = new JPanel(new GridLayout(4,1));

            //center-1
        glc.add(new JPanel());

            //center-2
        JPanel center2 = new JPanel(new GridBagLayout());
                //center-2의 left
        GridBagConstraints bcglc = new GridBagConstraints();
        bcglc.weightx = 1;
        center2.add(new JPanel(), DefaultFrame.easyGridBagConstraint(0,0,1,1));
                //center-2의 right
        JPanel bcgr = new JPanel(new FlowLayout(FlowLayout.LEFT));
        phonelb = new JLabel("연락처    : ");
        phonelb.setFont(new DefaultFont(FONT_SIZE));
        bcgr.add(phonelb);
        phonetf = new JTextField(10);
        phonetf.setFont(new DefaultFont(FONT_SIZE));
        bcgr.add(phonetf);
        center2.add(bcgr, DefaultFrame.easyGridBagConstraint(1,0,8,1));

        glc.add(center2);

            //center-3
        JPanel center3 = new JPanel(new GridBagLayout());
                //center-3의 left
        GridBagConstraints bcbglc = new GridBagConstraints();
        bcbglc.weightx = 1;
        center3.add(new JPanel(),DefaultFrame.easyGridBagConstraint(0,0,1,1));
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
        center3.add(bcbgr, DefaultFrame.easyGridBagConstraint(1,0,8,1));

        glc.add(center3);

            //center-4
        glc.add(new JPanel());


        blc.add(glc, DefaultFrame.easyGridBagConstraint(0,0,2,1));

            //Grid Bag Layout의 right
        JPanel gbr = new JPanel();
        GridBagConstraints gnp = new GridBagConstraints();
        gnp.weightx = 1;

        np = new NumberPadPanel();
        //gbr.add(np);
        np.setBorder(BorderFactory.createEmptyBorder(30,15,30,15));
        blc.add(np,DefaultFrame.easyGridBagConstraint(1,0,1,1));

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