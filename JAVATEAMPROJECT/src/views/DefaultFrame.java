package views;



import controller_db.Controller;
import custom_component.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Stack;
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

    private JLabel timeLb, titleLb;
    private DefaultFrame parent;
    private JButton backBtn;


    public DefaultUserTopPanel(String topName, DefaultFrame prt){
        super(topName);
        this.setLayout(new GridLayout(1,3));
        this.parent = prt;

        //left - 홈버튼
        JPanel topLeft = new JPanel();
        topLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
        backBtn = new JButton(FreeImageIcon.resizeImageIcon(DefaultFrame.PATH + "/images/back.png",
                (int)(TOP_HEIGHT * 3 * 0.7), (int)(TOP_HEIGHT * 0.7)));
        backBtn.setPreferredSize(new Dimension((int)(TOP_HEIGHT * 3 * 0.7),
                (int)(TOP_HEIGHT  * 0.7)));

        //버튼 투명하게 --> 이미지를 버튼으로 활용하고 싶을 때
        backBtn.setBorderPainted(false);        //주변 테투리 이미지를 없게 한다.
        backBtn.setContentAreaFilled(false);    //버튼 안에 기본이미지를 없게 한다.
        backBtn.setFocusPainted(false);         //포커스 했을 때 이미지를 없게 한다.
        int borderValue = (int)(TOP_HEIGHT * 0.17);
        topLeft.setBorder(BorderFactory.createEmptyBorder(borderValue, 20, 0,0));
        topLeft.add(backBtn);
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

        topCenter.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 3){
                    //로고 3번 연속 클릭시 관리자 로그인 화면으로 이동
                    parent.move(new ManagerLoginView(parent));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        //top 변경을 확인하기 위해서 버튼에 테스트로 이미지를 씀
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.back();
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

    public JButton getBackButton(){
        return backBtn;
    }
}

class DefaultAdminTopPanel extends DefaultTopPanel{
    public static final int TOP_HEIGHT = 80;   //TOP의 크기이다.

    private DefaultFrame parent;

    private JButton homeBtn;
    public DefaultAdminTopPanel(String topName, DefaultFrame prt){
        super(topName);
        this.setLayout(new GridLayout(1,3));
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
                parent.back();
            }
        });
        this.add(topLeft);

        //center - 노인 코래방
        JPanelOneLabel topCenter = new JPanelOneLabel("노인 코래방");
        topCenter.setBackground(DefaultFrame.TOP_BACKGROUND_COLOR);
        topCenter.getLabel().setFont(new DefaultFont(50, Font.BOLD));
        topCenter.getLabel().setForeground(Color.white);
        this.add(topCenter);


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

    public JButton getHomeBtn(){
        return homeBtn;
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
    private JPanel main;
    private Stack<JPanel> views = new Stack<>();

    private String nowTopName;
    private Controller controller;
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

    public DefaultFrame(Controller controller) {
        this.controller = controller;
        cp = getContentPane();
        DefaultUserTopPanel userTop = new DefaultUserTopPanel(TOP_USER, this);
        DefaultAdminTopPanel adminTop = new DefaultAdminTopPanel(TOP_ADMIN, this);
        tops = new DefaultTopPanel[]{userTop, adminTop};

        /*
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
        */
        main = new JPanel();
        main.setLayout(new BorderLayout());

        cp.add(userTop, BorderLayout.NORTH);
        cp.add(main, BorderLayout.CENTER);
        nowTopName = TOP_USER;
        //cp.add(adminTop,BorderLayout.NORTH);
    }

    public Component add(Component c) {
        main.add(c, BorderLayout.CENTER);
        return c;
    }

    public Controller getController() {
        return controller;
    }

    public void move(JPanel nowView, JPanel nextView){
        views.add(nowView);
        main.remove(nowView);
        main.add(nextView);
        main.repaint();
        main.revalidate();
    }

    public void move(JPanel nextView){
        move((JPanel) main.getComponent(0), nextView);
    }

    //현재 위치도 저장하지 않고 이동
    public void resetMove(JPanel nowView, JPanel nextView){
        views.clear();  //전에 있던 값을 모두 제거
        main.remove(nowView);
        main.add(nextView);
        main.repaint();
        main.revalidate();
    }

    public void resetMove(JPanel nextView){
        resetMove((JPanel) main.getComponent(0), nextView);
    }

    public boolean back(JPanel nowView){
        if(views.empty()) return false;
        main.remove(nowView);
        main.repaint();
        main.revalidate();

        main.add(views.pop());
        return true;
    }

    public boolean back(){
        return back((JPanel)main.getComponent(0));
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