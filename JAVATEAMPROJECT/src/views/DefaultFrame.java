package views;


import java.awt.*;
import javax.swing.*;

public class DefaultFrame extends JFrame{
    public static final int WIDTH = 1920, HEIGHT = 1080;
    public static final String PATH = System.getProperty("user.dir") + "/JAVATEAMPROJECT/src";
    private Container cp;
    private JPanel top;
    private JButton homeBtn;
    private JLabel timeLb, titleLb;

    public DefaultFrame() {

        cp = getContentPane();

        top = new JPanel();
        top.setLayout(new BorderLayout());

        homeBtn = new JButton("처음으로");
        JPanel btnP = new JPanel();
        btnP.add(homeBtn);
        homeBtn.setPreferredSize(new Dimension(150, 80));
        timeLb = new JLabel("날짜");

        //top-Center
        titleLb = new JLabel(new ImageIcon("src/product_list/images/title2.png"));


        top.add(btnP, BorderLayout.WEST);
        top.add(titleLb, BorderLayout.CENTER);
        top.add(timeLb, BorderLayout.EAST);

        cp.add(top, BorderLayout.NORTH);
    }

    public Component add(Component c) {
        cp.add(c, BorderLayout.CENTER);
        return c;
    }

    public static GridBagConstraints easyGridBagConstraint(int x, int y, double weightX, double weightY, int gridWidth, int gridHeight){

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridWidth;
        gbc.gridheight = gridHeight;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        gbc.fill = GridBagConstraints.BOTH;

        return gbc;
    }

    public static GridBagConstraints easyGridBagConstraint(int x, int y, double weightX, double weightY) {
        return easyGridBagConstraint(x, y, weightX, weightY, 1, 1);
    }


}
