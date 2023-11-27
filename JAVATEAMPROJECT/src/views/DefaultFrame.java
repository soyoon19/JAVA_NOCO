package views;



import custom_component.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.*;

/*
    가장 상위(parent)인 Frame이다.
    우리가 만든 View가 붙을 객체이다.
    지금은 상수와 클래스 메서드만 보자(static 키워드가 붙은 부분만 확인하자)
    easyGridBagConstraint가 이 클래스에 구현되어 있음으로 사용할 때
    DefaultFrame.easyGridBagConstraint()로 호출하여 사용하자.
 */

class DefaultTopPanel extends JPanel{
    private String topName;
    public DefaultTopPanel(String topName){
        this.topName = topName;
    }
    public String getTopName() {
        return topName;
    }
}

class DefaultUserTopPanel extends DefaultTopPanel{
    public static final int TOP_HEIGHT = 130;   //TOP의 크기이다.

    private JButton homeBtn;
    private JLabel timeLb, titleLb;
    private DefaultFrame parent;


    public DefaultUserTopPanel(String topName, DefaultFrame prt){
        super(topName);
        this.setLayout(new GridLayout(1,3));
        this.parent = prt;

        //left - 홈버튼
        JPanel topLeft = new JPanel();
        topLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        homeBtn = new JButton(FreeImageIcon.resizeImageIcon(DefaultFrame.PATH + "/images/back.png",
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
        topLeft.setBackground(DefaultFrame.TOP_BACKGROUND_COLOR);
        this.add(topLeft);


        //center - 로고
        JPanel topCenter = new JPanel();
        ImageIcon ii = new ImageIcon(DefaultFrame.PATH + "/images/title.png");
        ImageIcon productImage = new ImageIcon(ii.getImage().getScaledInstance((int)(TOP_HEIGHT * 3.24),
                TOP_HEIGHT, Image.SCALE_SMOOTH));
        titleLb = new JLabel(productImage);
        topCenter.add(titleLb);
        topCenter.setBackground(DefaultFrame.TOP_BACKGROUND_COLOR);
        this.add(topCenter);

        //top 변경을 확인하기 위해서 버튼에 테스트로 이미지를 씀
        homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.switchTop(DefaultFrame.TOP_ADMIN);
            }
        });


        //right - 날짜, 시간
        JPanel topRight = new JPanel();
        topRight.setLayout(new GridLayout(2,1));
        //날짜
        JPanelOneLabel datePLb = new JPanelOneLabel("", new FlowLayout(FlowLayout.RIGHT));


        //시간
        JPanelOneLabel timePLb = new JPanelOneLabel("", new FlowLayout(FlowLayout.RIGHT));
        datePLb.getLabel().setFont(new DefaultFont(38, Font.BOLD));
        datePLb.setBackground(DefaultFrame.TOP_BACKGROUND_COLOR);
        datePLb.getLabel().setForeground(DefaultFrame.TOP_TEXT_COLOR);
        datePLb.setBorder(BorderFactory.createEmptyBorder(15, 0,0, 22));
        //실시간 시간 구하기
        (new LiveDateLabel(datePLb.getLabel(), new SimpleDateFormat("yyyy.MM.dd(E)"))).start();

        timePLb.getLabel().setFont(new DefaultFont(38, Font.BOLD));
        timePLb.setBackground(DefaultFrame.TOP_BACKGROUND_COLOR);
        timePLb.getLabel().setForeground(DefaultFrame.TOP_TEXT_COLOR);
        timePLb.setBorder(BorderFactory.createEmptyBorder(0, 0,15, 20));
        //실시간 시간 구하기
        (new LiveDateLabel(timePLb.getLabel(), new SimpleDateFormat("HH:mm:ss"))).start();



        topRight.add(datePLb);
        topRight.add(timePLb);
        this.add(topRight);
    }
}

class DefaultAdminTopPanel extends DefaultTopPanel{
    public static final int TOP_HEIGHT = 80;   //TOP의 크기이다.

    private DefaultFrame parent;

    private JButton homeBtn;
    public DefaultAdminTopPanel(String topName, DefaultFrame prt){
        super(topName);
        this.setLayout(new GridLayout(1,2));
        this.parent = prt;

        JPanel topLeft = new JPanel();
        topLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        homeBtn = new JButton(FreeImageIcon.resizeImageIcon(DefaultFrame.PATH + "/images/home.png",
                (int)(TOP_HEIGHT * 3 * 0.7), (int)(TOP_HEIGHT * 0.7)));
        homeBtn.setPreferredSize(new Dimension((int)(TOP_HEIGHT * 3 * 0.7),
                (int)(TOP_HEIGHT  * 0.7)));

        homeBtn.setBorderPainted(false);        //주변 테투리 이미지를 없게 한다.
        homeBtn.setContentAreaFilled(false);    //버튼 안에 기본이미지를 없게 한다.
        homeBtn.setFocusPainted(false);         //포커스 했을 때 이미지를 없게 한다.
        int borderValue = (int)(TOP_HEIGHT * 0.17);
        topLeft.setBorder(BorderFactory.createEmptyBorder(borderValue, 20, 0,0));

        this.setBackground(DefaultFrame.TOP_BACKGROUND_COLOR);
        topLeft.setBackground(DefaultFrame.TOP_BACKGROUND_COLOR);


        topLeft.add(homeBtn);

        homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.switchTop(DefaultFrame.TOP_USER);
            }
        });
        this.add(topLeft);


        //right - 날짜, 시간
        JPanel topRight = new JPanel();
        topRight.setLayout(new GridLayout(2,1));
        //날짜
        JPanelOneLabel datePLb = new JPanelOneLabel("", new FlowLayout(FlowLayout.RIGHT));


        //시간
        JPanelOneLabel timePLb = new JPanelOneLabel("", new FlowLayout(FlowLayout.RIGHT));
        datePLb.getLabel().setFont(new DefaultFont(20, Font.BOLD));
        datePLb.setBackground(DefaultFrame.TOP_BACKGROUND_COLOR);
        datePLb.getLabel().setForeground(DefaultFrame.TOP_TEXT_COLOR);
        datePLb.setBorder(BorderFactory.createEmptyBorder(15, 0,0, 22));
        //실시간 시간 구하기
        (new LiveDateLabel(datePLb.getLabel(), new SimpleDateFormat("yyyy.MM.dd(E)"))).start();

        timePLb.getLabel().setFont(new DefaultFont(20, Font.BOLD));
        timePLb.setBackground(DefaultFrame.TOP_BACKGROUND_COLOR);
        timePLb.getLabel().setForeground(DefaultFrame.TOP_TEXT_COLOR);
        timePLb.setBorder(BorderFactory.createEmptyBorder(0, 0,15, 20));
        //실시간 시간 구하기
        (new LiveDateLabel(timePLb.getLabel(), new SimpleDateFormat("HH:mm:ss"))).start();



        topRight.add(datePLb);
        topRight.add(timePLb);

        this.add(topRight);

    }
}


public class DefaultFrame extends JFrame {
    public static final int WIDTH = 1920, HEIGHT = 1080; //Frame의 사이즈 크기이다.
    //PATH 파일의 절대경을 저장하고 있는 상수이다.
    //Image을 가져올때 사용하자
    public static final String PATH = System.getProperty("user.dir") + "/JAVATEAMPROJECT/src",
            TOP_ADMIN = "ADMIN", TOP_USER = "USER", TOP_NONE = "NONE";

    //Top Background Color 색상을 저장하고 있는 상수이다.
    public static final Color TOP_BACKGROUND_COLOR = new Color(64, 64, 64),
            TOP_TEXT_COLOR = Color.white;

    private Container cp;
    private JPanel top;
    private JButton homeBtn;
    private JLabel timeLb, titleLb;

    private String nowTopName;
    DefaultTopPanel[] tops;

    /*
    예쁘게 하기위해서 tod에 귀찮은 작업들을 많이 했으니
    지금은 top(South) 부분은 안 보는 것을 추천한다.
     */

    public void switchTop(String name) {
        if (nowTopName.equals(name)) return;

        DefaultTopPanel changeTop = null;

        for (DefaultTopPanel p : tops) {
            if (p.getTopName().equals(name)) {
                changeTop = p;
                break;
            }
        }

        if (!nowTopName.equals(TOP_NONE))
            for (DefaultTopPanel p : tops)
                if (p.getTopName().equals(nowTopName)) {
                    System.out.println(p.getTopName());
                    cp.remove(p);
                    cp.revalidate();
                }

        if (!name.equals(TOP_NONE)) {
            cp.add(changeTop, BorderLayout.NORTH);
            cp.revalidate();
        }
        cp.repaint();

        nowTopName = name;
    }

    public DefaultFrame() {

        cp = getContentPane();
        DefaultUserTopPanel userTop = new DefaultUserTopPanel(TOP_USER, this);
        DefaultAdminTopPanel adminTop = new DefaultAdminTopPanel(TOP_ADMIN, this);
        tops = new DefaultTopPanel[]{userTop, adminTop};

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
        cp.add(userTop, BorderLayout.NORTH);
        nowTopName = TOP_USER;
        //cp.add(adminTop,BorderLayout.NORTH);
    }

    public Component add(Component c) {
        cp.add(c, BorderLayout.CENTER);
        return c;
    }


    public static GridBagConstraints easyGridBagConstraint(int x, int y, double weightX, double weightY, int gridWidth, int gridHeight) {

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
