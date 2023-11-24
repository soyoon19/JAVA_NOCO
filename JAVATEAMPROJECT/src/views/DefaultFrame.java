package views;


import custom_component.DefaultFont;
import custom_component.FreeImageIcon;
import custom_component.JPanelOneLabel;

import java.awt.*;
import javax.swing.*;

/*
    가장 상위(parent)인 Frame이다.
    우리가 만든 View가 붙을 객체이다.
    지금은 상수와 클래스 메서드만 보자(static 키워드가 붙은 부분만 확인하자)
    easyGridBagConstraint가 이 클래스에 구현되어 있음으로 사용할 때
    DefaultFrame.easyGridBagConstraint()로 호출하여 사용하자.
 */

public class DefaultFrame extends JFrame{
    public static final int WIDTH = 1920, HEIGHT = 1080, //Frame의 사이즈 크기이다.
            TOP_HEIGHT = 130;   //TOP의 크기이다.
    //PATH 파일의 절대경을 저장하고 있는 상수이다.
    //Image을 가져올때 사용하자
    public static final String PATH = System.getProperty("user.dir") + "/JAVATEAMPROJECT/src";
    //Top Background Color 색상을 저장하고 있는 상수이다.
    public static final Color TOP_BACKGROUND_COLOR = new Color(64,64,64),
            TOP_TEXT_COLOR = Color.white;

    private Container cp;
    private JPanel top;
    private JButton homeBtn;
    private JLabel timeLb, titleLb;


    /*
    예쁘게 하기위해서 tod에 귀찮은 작업들을 많이 했으니
    지금은 top(South) 부분은 안 보는 것을 추천한다.
     */
    public DefaultFrame() {
        cp = getContentPane();

        top = new JPanel();
        top.setLayout(new GridLayout(1,3));

        //left - 홈버튼
        JPanel topLeft = new JPanel();
        topLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        homeBtn = new JButton(FreeImageIcon.resizeImageIcon(DefaultFrame.PATH + "/images/home.png",
                (int)(TOP_HEIGHT * 3 * 0.7), (int)(TOP_HEIGHT * 0.7)));
        homeBtn.setPreferredSize(new Dimension((int)(TOP_HEIGHT * 3 * 0.7),
                (int)(TOP_HEIGHT  * 0.7)));

            //버튼 투명하게 --> 이미지를 버튼으로 활용하고 싶을 때
        homeBtn.setBorderPainted(false);        //주변 테투리 이미지를 없게 한다.
        homeBtn.setContentAreaFilled(false);    //버튼 안에 기본이미지를 없게 한다.
        homeBtn.setFocusPainted(false);         //포커스 했을 때 이미지를 없게 한다.
        int borderValue = (int)(TOP_HEIGHT * 0.17);
        topLeft.setBorder(BorderFactory.createEmptyBorder(borderValue, 20, 0,0));
        topLeft.add(homeBtn);
        topLeft.setBackground(TOP_BACKGROUND_COLOR);
        top.add(topLeft);


        //center - 로고
        JPanel topCenter = new JPanel();
        ImageIcon ii = new ImageIcon(DefaultFrame.PATH + "/images/title.png");
        ImageIcon producImage = new ImageIcon(ii.getImage().getScaledInstance((int)(TOP_HEIGHT * 3.24), TOP_HEIGHT, Image.SCALE_SMOOTH));
        titleLb = new JLabel(producImage);
        topCenter.add(titleLb);
        topCenter.setBackground(TOP_BACKGROUND_COLOR);
        top.add(topCenter);


        //right - 날짜, 시간
        JPanel topRight = new JPanel();
        topRight.setLayout(new GridLayout(2,1));
            //날짜
        JPanelOneLabel datePLb = new JPanelOneLabel("2023.11.24(월)", new FlowLayout(FlowLayout.RIGHT));


            //시간
        JPanelOneLabel timePLb = new JPanelOneLabel("20:40:11", new FlowLayout(FlowLayout.RIGHT));
        datePLb.getLabel().setFont(new DefaultFont(38, Font.BOLD));
        datePLb.setBackground(TOP_BACKGROUND_COLOR);
        datePLb.getLabel().setForeground(TOP_TEXT_COLOR);
        datePLb.setBorder(BorderFactory.createEmptyBorder(15, 0,0, 22));

        timePLb.getLabel().setFont(new DefaultFont(38, Font.BOLD));
        timePLb.setBackground(TOP_BACKGROUND_COLOR);
        timePLb.getLabel().setForeground(TOP_TEXT_COLOR);
        timePLb.setBorder(BorderFactory.createEmptyBorder(0, 0,15, 20));


        topRight.add(datePLb);
        topRight.add(timePLb);
        top.add(topRight);


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
