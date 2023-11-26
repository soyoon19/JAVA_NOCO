package views;
import custom_component.DefaultFont;

import java.awt.*;
import javax.swing.*;
class ManagerLoginView extends JPanel {
    private static final int FONT_SIZE = 80;
    JLabel idlb, pwlb;
    JTextField idtf, pwtf;
    JButton loginbt;
    public ManagerLoginView(){
        this.setLayout(new BorderLayout());

        /// BorderLayout의 Center
        JPanel blc = new JPanel(new GridLayout(4,1));
            //center-1
        blc.add(new JPanel());
            //center-2
        JPanel center2 = new JPanel();
        center2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        idlb = new JLabel("ID : ");
        idlb.setFont(new DefaultFont(FONT_SIZE));
        center2.add(idlb);
        idtf = new JTextField(10);
        idtf.setFont(new DefaultFont(FONT_SIZE));
        center2.add(idtf);
        blc.add(center2);
            //center-3
        JPanel center3 = new JPanel();
        center3.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        pwlb = new JLabel("PW : ");
        pwlb.setFont(new DefaultFont(FONT_SIZE));
        center3.add(pwlb);
        pwtf = new JTextField(10);
        pwtf.setFont(new DefaultFont(FONT_SIZE));
        center3.add(pwtf);
        blc.add(center3);
            //center-4
        blc.add(new JPanel());

        add(blc, BorderLayout.CENTER);

        //BorderLayout의 South
        JPanel bls = new JPanel();
        bls.setLayout(new FlowLayout());
        bls.setBackground(Color.gray);

        loginbt = new JButton("LOGIN");
        loginbt.setPreferredSize(new Dimension(500, 100));
        loginbt.setFont(new DefaultFont(FONT_SIZE));
        bls.add(loginbt);

        add(bls, BorderLayout.SOUTH);
    }
}
