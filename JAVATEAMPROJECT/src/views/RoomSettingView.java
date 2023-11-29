package views;

import custom_component.DefaultFont;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoomSettingView extends JPanel { //메인뷰

    public static final int FONT_SIZE = 30;
    public static final int MIDDLE_FONT_SIZE = 40;
    public static final int BETWEEN_FONT = 60;
    public static final int BUTTON_FONT_SIZE = 80;

    private DefaultFrame parent;

    public RoomSettingView(DefaultFrame parent){
        this.parent = parent;
        this.setLayout(new BorderLayout());
        //탭 생성
        JTabbedPane rsv = new JTabbedPane();
        RoomEditPanel rep = new RoomEditPanel();
        RoomManagePanel rmp = new RoomManagePanel(parent);
        rsv.addTab("편집",rep);
        rsv.addTab("관리",rmp);
        rsv.setFont(new DefaultFont(FONT_SIZE));
        add(rsv);
    }
}

class RoomEditPanel extends JPanel { //방편집 패널
    public RoomEditPanel() {
        this.setLayout(new BorderLayout());
        JPanel review = new JPanel(new GridBagLayout());
        //Grid Bag Layout의 left
        JPanel gbl = new JPanel();
        gbl.setBackground(Color.yellow); //TODO : 방편집 패널 추가하기
        review.add(gbl,DefaultFrame.easyGridBagConstraint(0,0,3,1));
        //Grid Bag Layout의 right
        JTabbedPane gbr = new JTabbedPane();
        ScreenEditPanelMini se = new ScreenEditPanelMini();
        RoomSettingPanelMini rs = new RoomSettingPanelMini();
        gbr.add("화면 편집",se);
        gbr.add("방 설정",rs);
        gbr.setFont(new DefaultFont(RoomSettingView.FONT_SIZE));
        review.add(gbr,DefaultFrame.easyGridBagConstraint(1,0,1,1));
        add(review);
    }
}


class ScreenEditPanelMini extends JPanel implements ActionListener{ //화면 편집 패널
    JButton roomAdd, roomDelete;
    JPanel sep;
    public ScreenEditPanelMini() {
        this.setLayout(new BorderLayout());
        sep = new JPanel();
        sep.setLayout(new GridLayout(2,1));
        roomAdd = new JButton("방 추가");
        roomDelete = new JButton("방 삭제");
        roomAdd.setFont(new DefaultFont(RoomSettingView.BUTTON_FONT_SIZE));
        roomDelete.setFont(new DefaultFont(RoomSettingView.BUTTON_FONT_SIZE));
        sep.add(roomAdd);
        sep.add(roomDelete);
        add(sep);

        roomAdd.addActionListener(this);
        roomDelete.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("방 추가")){
            this.remove(sep);
            this.add(new RoomAdd(this));

            this.repaint();
            this.revalidate();
        } else if (s.equals("방 삭제")) {
            this.remove(sep);
            this.add(new RoomDelete(this));

            this.repaint();
            this.revalidate();
        }
    }
}

class RoomAdd extends  JPanel implements ActionListener { //방추가 버튼
    JButton beforeBtn, addBtn, cancleBtn;
    JLabel roomNum, roomSize, roomlcX, roomlcY;
    JTextField roomNumtf, roomlcXtf, roomlcYtf;
    JComboBox roomSizeXY;
    ScreenEditPanelMini mini;
    public RoomAdd(ScreenEditPanelMini mini) {
        this.mini = mini;
        this.setLayout(new GridLayout(6,1));
        //1행
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        beforeBtn = new JButton("이전");
        beforeBtn.setFont(new DefaultFont(RoomSettingView.FONT_SIZE, Font.BOLD));
        beforeBtn.addActionListener(this);
        p1.add(beforeBtn);
        add(p1);

        //2행
        JPanel p2 = new JPanel(new FlowLayout());
        roomNum = new JLabel("방 번호    :");
        roomNum.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        roomNumtf = new JTextField(3);
        roomNumtf.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p2.add(roomNum);
        p2.add(roomNumtf);
        add(p2);

        //3행
        JPanel p3 = new JPanel(new FlowLayout());
        String [] roomSizeLabel = {"3X3","6X3","3X6"};
        roomSize = new JLabel("방 크기 XY :");
        roomSize.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        roomSizeXY = new JComboBox(roomSizeLabel);
        roomSizeXY.setFont(new DefaultFont(RoomSettingView.FONT_SIZE));
        p3.add(roomSize);
        p3.add(roomSizeXY);
        add(p3);

        //4행
        JPanel p4 = new JPanel(new FlowLayout());
        roomlcX = new JLabel("방 위치 X :");
        roomlcX.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        roomlcXtf = new JTextField(3);
        roomlcXtf.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p4.add(roomlcX);
        p4.add(roomlcXtf);
        add(p4);

        //5행
        JPanel p5 = new JPanel(new FlowLayout());
        roomlcY = new JLabel("방 위치 Y :");
        roomlcY.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        roomlcYtf = new JTextField(3);
        roomlcYtf.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p5.add(roomlcY);
        p5.add(roomlcYtf);
        add(p5);

        //6행
        JPanel p6 = new JPanel(new FlowLayout());
        addBtn = new JButton("추가");
        addBtn.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        cancleBtn = new JButton("취소");
        cancleBtn.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p6.add(addBtn);
        p6.add(cancleBtn);
        addBtn.addActionListener(this);
        cancleBtn.addActionListener(this);
        add(p6);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("추가")){
            JOptionPane.showConfirmDialog(this, "추가하시겠습니까?","방 추가",JOptionPane.YES_NO_OPTION);
            JOptionPane.showMessageDialog(this, "방 추가가 완료되었습니다.","방 추가 확인",JOptionPane.INFORMATION_MESSAGE);
        } else if (s.equals("취소")) { //TODO combobox 디폴트로 세팅
            roomNumtf.setText("");
            roomSizeXY.setSelectedIndex(0);
            roomlcXtf.setText("");
            roomlcYtf.setText("");
        } else if(s.equals("이전")){
            mini.remove(this);
            mini.add(mini.sep);

            mini.repaint();
            mini.revalidate();
        }
    }
}

class RoomDelete extends JPanel implements ActionListener { //방삭제 버튼
    JButton beforeBtn, deleteBtn;
    JLabel roomNum, roomSize,roomlcX,roomlcY;
    JComboBox roomNumtf;
    ScreenEditPanelMini mini;
    public RoomDelete(ScreenEditPanelMini mini) {
        this.mini = mini;
        this.setLayout(new BorderLayout());
        JPanel rdp = new JPanel(new BorderLayout());

        //Bordeer Layout의 North
        JPanel p1 = new JPanel();
        beforeBtn = new JButton("이전");
        beforeBtn.setFont(new DefaultFont(RoomSettingView.FONT_SIZE, Font.BOLD));
        beforeBtn.addActionListener(this);
        p1.add(beforeBtn);
        p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(p1,BorderLayout.NORTH);

        //Border layout의 Center
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(4,1));

        //1행
        JPanel mini1 = new JPanel();
        mini1.setLayout(new FlowLayout());
        roomNum = new JLabel("방 번호:");
        roomNum.setFont(new DefaultFont(RoomSettingView.BETWEEN_FONT));
        String [] roomNumList = {" 1 "," 2 "," 3 "," 4 "," 5 "," 6 "," 7 "," 8 "," 9 "};
        roomNumtf = new JComboBox(roomNumList);
        roomNumtf.setFont(new DefaultFont(RoomSettingView.BETWEEN_FONT - 10));
        mini1.add(roomNum);
        mini1.add(roomNumtf);

        //2행
        JPanel mini2 = new JPanel();
        mini2.setLayout(new FlowLayout());
        roomSize = new JLabel("방 크기 XY :");
        roomSize.setFont(new DefaultFont(RoomSettingView.BETWEEN_FONT));
        mini2.add(roomSize);

        //3행
        JPanel mini3 = new JPanel();
        mini3.setLayout(new FlowLayout());
        roomlcX = new JLabel("방 위치 X :");
        roomlcX.setFont(new DefaultFont(RoomSettingView.BETWEEN_FONT));
        mini3.add(roomlcX);

        //4행
        JPanel mini4 = new JPanel();
        mini4.setLayout(new FlowLayout());
        roomlcY = new JLabel("방 위치 Y :");
        roomlcY.setFont(new DefaultFont(RoomSettingView.BETWEEN_FONT));
        mini4.add(roomlcY);

        p2.add(mini1);
        p2.add(mini2);
        p2.add(mini3);
        p2.add(mini4);
        add(p2,BorderLayout.CENTER);

        //Border layout의 North
        JPanel p3 = new JPanel();
        deleteBtn = new JButton("삭제");
        deleteBtn.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        deleteBtn.addActionListener(this);
        p3.add(deleteBtn);
        p3.setLayout(new FlowLayout());
        add(p3,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("삭제")){
            JOptionPane.showConfirmDialog(this, "삭제하시겠습니까?","방 삭제",JOptionPane.YES_NO_OPTION);
            JOptionPane.showMessageDialog(this, "방 삭제가 완료되었습니다.","방 삭제 확인",JOptionPane.INFORMATION_MESSAGE);
        } else if(s.equals("이전")){
            mini.remove(this);
            mini.add(mini.sep);

            mini.repaint();
            mini.revalidate();
        }
    }
}
class RoomSettingPanelMini extends JPanel implements ActionListener { //방설정 패널
    JLabel roomNum;
    JTextField roomNumtf;
    JButton roomActivate, roomUnActivate, applyBtn;
    public RoomSettingPanelMini() {
        this.setLayout(new BorderLayout());
        JPanel rsg = new JPanel();
        rsg.setLayout(new GridLayout(5,1));

        //1행
        rsg.add(new JPanel());

        //2행
        JPanel p2 = new JPanel();
        roomNum = new JLabel("방 번호 :");
        roomNum.setFont(new DefaultFont(RoomSettingView.BETWEEN_FONT));
        roomNumtf = new JTextField(3);
        roomNumtf.setFont(new DefaultFont(RoomSettingView.BETWEEN_FONT));
        p2.add(roomNum);
        p2.add(roomNumtf);
        rsg.add(p2);

        //3행
        JPanel p3 = new JPanel();
        roomActivate = new JButton("방 활성화");
        roomActivate.setFont(new DefaultFont(RoomSettingView.BETWEEN_FONT));
        roomActivate.addActionListener(this);
        p3.add(roomActivate);
        roomActivate.setPreferredSize(new Dimension(400,80));
        rsg.add(p3);

        //4행
        JPanel p4 = new JPanel();
        roomUnActivate = new JButton("방 비활성화");
        roomUnActivate.setFont(new DefaultFont(RoomSettingView.BETWEEN_FONT));
        roomUnActivate.addActionListener(this);
        p4.add(roomUnActivate);
        roomUnActivate.setPreferredSize(new Dimension(400,80));
        rsg.add(p4);

        //5행
        JPanel p5 = new JPanel();
        applyBtn = new JButton("적용");
        applyBtn.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        applyBtn.addActionListener(this);
        p5.add(applyBtn);
        rsg.add(p5);

        add(rsg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("방 활성화")){
            //로직 구현
        } else if (s.equals("방 비활성화")) {
            //로직 구현
        } else if (s.equals("적용")) {
            JOptionPane.showConfirmDialog(this, "적용하시겠습니까?","방 설정",JOptionPane.YES_NO_OPTION);
            JOptionPane.showMessageDialog(this, "방 설정이 완료되었습니다.","방 설정 확인",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

class RoomManagePanel extends JPanel implements ActionListener { //방관리 패널
    JButton musicAdd, forcedExit;
    DefaultFrame parent;
    public RoomManagePanel(DefaultFrame parent) {
        this.parent = parent;
        this.setLayout(new BorderLayout());
        JPanel rmview = new JPanel(new GridBagLayout());

        //Grid Bag Layout의 left
        JPanel gbl = new JPanel();
        gbl.setBackground(Color.yellow); //TODO : 방편집 패널 추가하기
        rmview.add(gbl,DefaultFrame.easyGridBagConstraint(0,0,3,1));

        //Grid Bag Layout의 right
        JPanel gbr = new JPanel(new GridBagLayout());
        rmview.add(gbr,DefaultFrame.easyGridBagConstraint(1,0,1,1));
            //Jpanel의 top
        RoomManageInfoPanel gbt = new RoomManageInfoPanel();
        gbr.add(gbt,DefaultFrame.easyGridBagConstraint(0,0,1,4));
            //JPannel의 bottom
        JPanel gbb = new JPanel();
        gbr.add(gbb,DefaultFrame.easyGridBagConstraint(0,1,1,1));
        musicAdd = new JButton("곡 추가");
        musicAdd.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        musicAdd.addActionListener(this);
        forcedExit = new JButton("강제 퇴장");
        forcedExit.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        forcedExit.addActionListener(this);
        gbb.add(musicAdd);
        gbb.add(forcedExit);

        add(rmview);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("곡 추가")){
            (new MusicAddPopup(parent)).setVisible(true);
        } else if (s.equals("강제 퇴장")) {
            (new ForcedExitPopup(parent)).setVisible(true);
        }
    }
}

class MusicAddPopup extends JDialog implements ActionListener { //곡추가 팝업
    private static final String TITLE = "곡 추가";
    private static final int WIDTH = 370, HEIGHT = 350;
    Container ct = getContentPane();
    JLabel musicAccount;
    JTextField musicAccountTf;
    JButton addBtn, cancleBtn;
    MusicAddPopup(JFrame parent){
        super(parent, TITLE, true);
        musicAccount = new JLabel("곡 수 :");
        musicAccount.setFont(new DefaultFont(RoomSettingView.FONT_SIZE));
        musicAccountTf = new JTextField(3);
        musicAccountTf.setFont(new DefaultFont(RoomSettingView.FONT_SIZE));
        addBtn = new JButton("추가"); //TODO 버튼 이벤트 넣을 지 고민
        addBtn.setFont(new DefaultFont(RoomSettingView.FONT_SIZE));
        addBtn.addActionListener(this);
        cancleBtn = new JButton("취소");
        cancleBtn.addActionListener(this);
        cancleBtn.setFont(new DefaultFont(RoomSettingView.FONT_SIZE));

        ct.setLayout(null);
        musicAccount.setBounds(100,80,100,40);
        musicAccountTf.setBounds(200,80,50,40);
        addBtn.setBounds(40,180,120,100);
        cancleBtn.setBounds(200,180,120,100);

        ct.add(musicAccount);
        ct.add(musicAccountTf);
        ct.add(addBtn);
        ct.add(cancleBtn);

        setLocation(((parent.getWidth()-WIDTH)/2), ((int)((parent.getHeight() - HEIGHT) * 0.3333)));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("추가")){
            JOptionPane.showMessageDialog(this, "곡이 추가되었습니다.","방 추가",JOptionPane.INFORMATION_MESSAGE);
        } else if (s.equals("취소")){
            this.dispose();
        }
    }
}

class ForcedExitPopup extends JDialog implements ActionListener { //강제퇴장 팝업
    private static final String TITLE = "강제 퇴장";
    private static final int WIDTH = 370, HEIGHT = 350;
    Container ct = getContentPane();
    JLabel musicAccount;
    JButton addBtn, cancleBtn;
    JFrame parent;
    ForcedExitPopup(JFrame parent){
        super(parent, TITLE, true);
        this.parent = parent;
        musicAccount = new JLabel("번 방을 강제 퇴장하시겠습니까?");
        musicAccount.setFont(new DefaultFont(RoomSettingView.FONT_SIZE-10));
        addBtn = new JButton("확인");
        addBtn.addActionListener(this);
        addBtn.setFont(new DefaultFont(RoomSettingView.FONT_SIZE));
        cancleBtn = new JButton("취소");
        cancleBtn.addActionListener(this);
        cancleBtn.setFont(new DefaultFont(RoomSettingView.FONT_SIZE));

        ct.setLayout(null);
        musicAccount.setBounds(30,80,300,40);
        addBtn.setBounds(40,180,120,100);
        cancleBtn.setBounds(200,180,120,100);

        ct.add(musicAccount);
        ct.add(addBtn);
        ct.add(cancleBtn);

        setLocation(((parent.getWidth()-WIDTH)/2), ((int)((parent.getHeight() - HEIGHT) * 0.3333)));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("확인")){
            JOptionPane.showMessageDialog(this, "번방이 강제퇴장되었습니다.","방 강제퇴장",JOptionPane.INFORMATION_MESSAGE);
        } else if (s.equals("취소")){
            this.dispose();
        }
    }
}

class RoomManageInfoPanel extends JPanel { //방관리정보 패널
    JLabel division, phone,birth, inTime,useMusic, remainMusic, payMusic;
    public RoomManageInfoPanel () {
        this.setLayout(new BorderLayout());
        JPanel rmi = new JPanel();
        rmi.setLayout(new GridLayout(7,1));
        //1행
        JPanel p1 = new JPanel(new FlowLayout());
        division = new JLabel("구분 : ");
        division.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p1.add(division);
        rmi.add(p1);
        //2행
        JPanel p2 = new JPanel(new FlowLayout());
        phone = new JLabel("연락처 : ");
        phone.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p2.add(phone);
        rmi.add(p2);
        //3행
        JPanel p3 = new JPanel(new FlowLayout());
        birth = new JLabel("생년월일 : ");
        birth.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p3.add(birth);
        rmi.add(p3);
        //4행
        JPanel p4 = new JPanel(new FlowLayout());
        inTime = new JLabel("입장 시각 : ");
        inTime.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p4.add(inTime);
        rmi.add(p4);
        //5행
        JPanel p5 = new JPanel(new FlowLayout());
        useMusic = new JLabel("사용곡 수 : ");
        useMusic.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p5.add(useMusic);
        rmi.add(p5);
        //6행
        JPanel p6 = new JPanel(new FlowLayout());
        remainMusic = new JLabel("잔여곡 수 : ");
        remainMusic.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p6.add(remainMusic);
        rmi.add(p6);
        //7행
        JPanel p7 = new JPanel(new FlowLayout());
        payMusic = new JLabel("결제곡 수 : ");
        payMusic.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p7.add(payMusic);
        rmi.add(p7);

        add(rmi);
    }
}