package views;

import controller_db.Controller;
import custom_component.*;
import dto.*;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class RoomSettingView extends JPanel { //메인뷰

    public static final int FONT_SIZE = 30;
    public static final int MIDDLE_FONT_SIZE = 40;
    public static final int BETWEEN_FONT = 50;
    public static final int BUTTON_FONT_SIZE = 80;

    private DefaultFrame parent;

    public RoomSettingView(DefaultFrame prt, WorkerDTO worker){
        this.parent = prt;
        this.setLayout(new BorderLayout());
        //탭 생성
        JTabbedPane rsv = new JTabbedPane();
        RoomEditPanel rep = new RoomEditPanel(parent);
        RoomManagePanel rmp = new RoomManagePanel(parent);

        if(worker.getPosition().equals("점장"))
            rsv.addTab("편집",rep);

        rsv.addTab("관리",rmp);
        rsv.setFont(new DefaultFont(FONT_SIZE));


        add(rsv);
    }
}

class RoomEditPanel extends JPanel { //방편집 패널
    private RoomSettingPanelMini roomSettingPanelMini;

    private ScreenEditPanelMini se;
    private RoomSettingPanelMini rs;
    private DefaultFrame parent;


    private RoomEditViewPanel roomEditViewPanel;

    public RoomEditPanel(DefaultFrame prt) {
        this.parent = prt;
        this.setLayout(new BorderLayout());
        JPanel review = new JPanel(new GridBagLayout());
        //Grid Bag Layout의 left
        ArrayList<RoomManageDTO> rooms = prt.getController().getRoomManageDAO().findAll();
        ArrayList<RoomOptionDTO> options = prt.getController().getRoomOptionDAO().findAll();

        roomEditViewPanel = new RoomEditViewPanel(parent.getController(), this);
        review.add(roomEditViewPanel,DefaultFrame.easyGridBagConstraint(0,0,3,1));
        //Grid Bag Layout의 right
        JTabbedPane gbr = new JTabbedPane();
        se = new ScreenEditPanelMini(parent, roomEditViewPanel);
        rs = new RoomSettingPanelMini();

        gbr.add("화면 편집",se);
        gbr.add("방 설정",rs);
        gbr.setFont(new DefaultFont(RoomSettingView.FONT_SIZE));
        review.add(gbr,DefaultFrame.easyGridBagConstraint(1,0,1,1));
        add(review);


        JComboBox x = new JComboBox(new Vector());


        //방 삭제와, 방 설정 이벤트 설정
        for(RoomPanel rp : roomEditViewPanel.getRoomPs()){
            rp.addMouseListener(new MouseListener() {
                Color color;
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(rs.getEventSwitch().getSw()){
                        rs.setRoomInfo(rp.getRoom());
                    }

                    if(se.getRoomDel().getEventSwitch().getSw()){
                        se.getRoomDel().setRoomInfo(rp.getRoom());
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {
                    if(rs.getEventSwitch().getSw() || se.getRoomDel().getEventSwitch().getSw()){
                        color = rp.getBackground();
                        rp.setBackground(new Color(color.getRed() + 20, color.getGreen()+ 20, color.getRed() + 20));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if(rs.getEventSwitch().getSw() || se.getRoomDel().getEventSwitch().getSw()){
                        rp.setBackground(color);
                    }
                }
            });
        }

        //Button의 getter을
        //방의 대한 정보를 확인해서
        //se.getRoomDel().get
    }

    public ScreenEditPanelMini getSe(){
        return se;
    }

    public RoomSettingPanelMini getRs(){
        return rs;
    }

}

class RoomEditViewPanel extends RoomViewPanel{

    private RoomEditPanel roomEditPanel;
    /*RoomViewPanel로 부터 상속받음!
        protected ArrayList<RoomManageDTO> rooms; //방 정보
    protected ArrayList<RoomOptionDTO> options;
     */

    RoomOptionDTO selectAreaOption = null; //나중에 선택된 경우 옵션이 중간에 변경이 되는 경우를 생각해 과거의 옵션을 백업한다.


    public RoomEditViewPanel(Controller controller, RoomEditPanel roomEditPanel) {
        super(controller);
        this.roomEditPanel = roomEditPanel;
        this.sw.setSw(false);


        for(int i = 0; i < jps.length; i++){
            for(int j = 0; j < jps[i].length; j++){
                int x = j, y = i;

                jps[i][j].setBorder(new TitledBorder(new LineBorder(Color.black, 1)));

                jps[i][j].addMouseListener(new MouseListener() {
                    RoomOptionDTO option = null;
                    //Color backupColor;
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        int i = 0, j = 0;

                        //ComboBox의 정보를 가져온다.
                        if(!sw.getSw()) return;
                        int choice =  roomEditPanel.getSe().getRoomAdd().getRoomSizeXY().getSelectedIndex();

                        System.out.println(choice);
                        option = options.get(choice);

                        boolean empty = true;
                        for( i = y; i < option.getHeight() + y && i < RoomViewPanel.MAX_HEIGHT; i++)
                            for( j = x; j < option.getWidth() + x && j < RoomViewPanel.MAX_WIDTH; j++)
                                if(!jps[i][j].getUse()) { //JPanel이 사용되고 있지 않으면 == 방이 이미 점유되어 있면
                                    empty = false;
                                    break;
                                }

                        if(i == RoomEditViewPanel.MAX_HEIGHT || j == RoomEditViewPanel.MAX_WIDTH) empty = false;

                        for( i = y; i < option.getHeight() + y && i < RoomViewPanel.MAX_HEIGHT; i++)
                            for( j = x; j < option.getWidth() + x && j < RoomViewPanel.MAX_WIDTH; j++) {
                                //backupColor = jps[i][j].getBackground();
                                if(empty)
                                    jps[i][j].setBackground(new Color(122,138,250));
                                else
                                    jps[i][j].setBackground(new Color(242,101,101));    //방이 이미 점유되어 있으면 빨간색으로 변경한다.
                            }

                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        if(!sw.getSw()) return;

                        for(int i = y; i < option.getHeight() + y && i < RoomViewPanel.MAX_HEIGHT; i++)
                            for(int j = x; j < option.getWidth() + x && j < RoomViewPanel.MAX_WIDTH; j++) {
                                jps[i][j].setBackground(Color.white);
                            }
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(!sw.getSw()) return;
                        int i = 0, j = 0;
                        boolean empty = true;
                        for(i = y; i < option.getHeight() + y && i < RoomViewPanel.MAX_HEIGHT; i++)
                            for(j = x; j < option.getWidth() + x && j < RoomViewPanel.MAX_WIDTH; j++)
                                if(!jps[i][j].getUse()) { //JPanel이 사용되고 있지 않으면 == 방이 이미 점유되어 있면
                                    empty = false;
                                    break;
                                }
                        if(i == RoomEditViewPanel.MAX_HEIGHT || j == RoomEditViewPanel.MAX_WIDTH) empty = false;

                        if(empty) { //빈 방이라면
                            /*
                            if(selectAreaOption != null){ //이미 선택된 경우라면
                                selectUnLock();
                            }
                            for(i = y; i < option.getHeight() + y && i < RoomViewPanel.MAX_HEIGHT; i++)
                                for(j = x; j < option.getWidth() + x && j < RoomViewPanel.MAX_WIDTH; j++) {
                                    backupColor = new Color(30,125,30);
                                    jps[i][j].setBackground(backupColor);
                                }*/
                            roomEditPanel.getSe().getRoomAdd().getRoomlcXtf().setText(String.valueOf(x));
                            roomEditPanel.getSe().getRoomAdd().getRoomlcYtf().setText(String.valueOf(y));
                            //selectAreaOption = option;
                        }else{
                            JOptionPane.showMessageDialog(roomEditPanel,"이미 지정된 위치입니다.","방 위치 오류",JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(!sw.getSw()) return;
                    }

                });

                /*
                jps[i][j].addMouseListener(new AreaSelectMouseListener(x, y, optionDTO, jps, sw) {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(!sw.getSw()) return;
                        roomEditPanel.getSe().getRoomAdd().getRoomlcXtf().setText(String.valueOf(x));
                        roomEditPanel.getSe().getRoomAdd().getRoomlcYtf().setText(String.valueOf(y));

                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(!sw.getSw()) return;


                    }
                });*/
            }
        }
    }

    public void selectUnLock() { //todo 방 선택히 흔적 해결
        /*
        int x = Integer.parseInt(roomEditPanel.getSe().getRoomAdd().getRoomlcXtf().getText());
        int y = Integer.parseInt(roomEditPanel.getSe().getRoomAdd().getRoomlcYtf().getText());
        selectUnLock(x, y);*/
    }

    public void selectUnLock(int x, int y) {
        for(int i = y; i < selectAreaOption.getHeight() + y && i < RoomViewPanel.MAX_HEIGHT; i++)
            for(int j = x; j < selectAreaOption.getWidth() + x && j < RoomViewPanel.MAX_WIDTH; j++) {
                jps[i][j].setBackground(Color.white);
            }
        selectAreaOption = null;
    }
}


class ScreenEditPanelMini extends JPanel implements ActionListener{ //화면 편집 패널
    JButton roomAddBtn, roomDeleteBtn;
    RoomAdd roomAdd; RoomDelete roomDel;
    JPanel sep;
    DefaultFrame parent;
    RoomEditViewPanel roomEditViewPanel;
    private static final int IMAGE_X = 140;
    private static final int IMAGE_Y = 140;
    public ScreenEditPanelMini(DefaultFrame prt, RoomEditViewPanel roomEditViewPanel) {
        this.roomEditViewPanel = roomEditViewPanel;
        this.parent = prt;
        this.setLayout(new BorderLayout());
        roomAdd = new RoomAdd(prt, this);
        roomDel = new RoomDelete(prt, this);

        sep = new JPanel();
        sep.setLayout(new GridLayout(2,1));

        //추가 버튼 클릭 전
        ImageIcon img1 = new FreeImageIcon(DefaultFrame.PATH+"/images/roomAddBefore.png",IMAGE_X,IMAGE_Y);
        roomAddBtn = new JButton("방 추가",img1);
        roomAddBtn.setBackground(Color.white);

        //클릭 후
        ImageIcon img2 = new FreeImageIcon(DefaultFrame.PATH+"/images/roomAddAfter.png",IMAGE_X,IMAGE_Y);
        roomAddBtn.setPressedIcon(img2);
        //삭제 버튼 클릭 전
        ImageIcon img3 = new FreeImageIcon(DefaultFrame.PATH+"/images/roomDeleteBefore.png",IMAGE_X,IMAGE_Y);
        roomDeleteBtn = new JButton("방 삭제",img3);
        roomDeleteBtn.setBackground(Color.white);
        //클릭 후
        ImageIcon img4 = new FreeImageIcon(DefaultFrame.PATH+"/images/roomDeleteAfter.png",IMAGE_X,IMAGE_Y);
        roomDeleteBtn.setPressedIcon(img4);

        roomAddBtn.setFont(new DefaultFont(RoomSettingView.BUTTON_FONT_SIZE-10));
        roomDeleteBtn.setFont(new DefaultFont(RoomSettingView.BUTTON_FONT_SIZE-10));
        sep.add(roomAddBtn);
        sep.add(roomDeleteBtn);
        add(sep);

        roomAddBtn.addActionListener(this);
        roomDeleteBtn.addActionListener(this);

        Insets margin = new Insets(20,20,20,20);
        roomAddBtn.setMargin(margin);
        roomDeleteBtn.setMargin(margin);

        //방 삭제시 일어나는 이벤트 설정
        for(int i = 0; i < roomEditViewPanel.getRoomPs().length; i++){

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("방 추가")){
            this.remove(sep);
            this.add(roomAdd);
            roomEditViewPanel.eventActivate();
            this.repaint();
            this.revalidate();
        } else if (s.equals("방 삭제")) {
            roomDel.getEventSwitch().setSw(true);
            this.remove(sep);
            this.add(roomDel);

            this.repaint();
            this.revalidate();
        }
    }

    public RoomAdd getRoomAdd(){
        return roomAdd;
    }

    public RoomDelete getRoomDel(){
        return roomDel;
    }
    public RoomEditViewPanel getRoomEditViewPanel(){
        return roomEditViewPanel;
    }

}

class RoomAdd extends  JPanel implements ActionListener { //방추가 버튼
    private JButton beforeBtn, addBtn, cancleBtn;
    private JLabel roomNum, roomSize, roomlcX, roomlcY;
    private JTextField roomNumtf, roomlcXtf, roomlcYtf;
    private JComboBox roomSizeXY;
    ScreenEditPanelMini mini;
    DefaultFrame parent;
    private ArrayList<RoomOptionDTO> options;
    public RoomAdd(DefaultFrame prt, ScreenEditPanelMini mini) {
        this.mini = mini;
        this.setLayout(new GridLayout(6,1));
        parent = prt;

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

        options = parent.getController().getRoomOptionDAO().findAll();
        String [] roomSizeLabel = new String[options.size()];

        for(int i = 0; i < roomSizeLabel.length; i++){
            int width = options.get(i).getWidth(), height = options.get(i).getHeight();
            roomSizeLabel[i] = width + "X" + height;
        }

        roomSize = new JLabel("방 크기 XY :");
        roomSize.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        roomSizeXY = new JComboBox(roomSizeLabel);
        roomSizeXY.setFont(new DefaultFont(RoomSettingView.FONT_SIZE));
        p3.add(roomSize);
        p3.add(roomSizeXY);
        roomSizeXY.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //콤보박스 변경시 입력된 좌표 초기화
                mini.getRoomEditViewPanel().selectUnLock();
                roomlcXtf.setText("");
                roomlcYtf.setText("");
            }
        });
        add(p3);

        //4행
        JPanel p4 = new JPanel(new FlowLayout());
        roomlcX = new JLabel("방 위치 X :");
        roomlcX.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        roomlcXtf = new JTextField(3);
        roomlcXtf.setEditable(false);
        roomlcXtf.setBackground(Color.white);
        roomlcXtf.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p4.add(roomlcX);
        p4.add(roomlcXtf);
        add(p4);

        //5행
        JPanel p5 = new JPanel(new FlowLayout());
        roomlcY = new JLabel("방 위치 Y :");
        roomlcY.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        roomlcYtf = new JTextField(3);
        roomlcYtf.setEditable(false);
        roomlcYtf.setBackground(Color.white);
        roomlcYtf.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        p5.add(roomlcY);
        p5.add(roomlcYtf);
        add(p5);

        //6행
        JPanel p6 = new JPanel(new FlowLayout());
        addBtn = new JButton("추가");
        addBtn.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        //todo 보더 주기
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
        String num = roomNumtf.getText().trim();

        if (s.equals("추가")){
            //문자열을 입력했을 경우
            try { //문자열을 입력했을 경우 예외처리
                Integer.parseInt(num);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(this,"숫자를 입력해 주세요.","입력 오류",JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            RoomManageDTO room = parent.getController().getRoomManageDAO().findById(num);
            if(room != null){
                JOptionPane.showMessageDialog(this,"이미 존재하는 방 번호입니다.","방 번호 오류",JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int x = JOptionPane.showConfirmDialog(this, "추가하시겠습니까?","방 추가",JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.OK_OPTION){ //todo 코드 생성 로직?
                RoomManageDTO newRoom = new RoomManageDTO("xxx"+num,num,Integer.parseInt(roomlcXtf.getText()),
                        Integer.parseInt(roomlcYtf.getText()),roomSizeXY.getSelectedIndex()+1,true);
                parent.getController().getRoomManageDAO().insert(newRoom);
                JOptionPane.showMessageDialog(this, "방 추가가 완료되었습니다.","방 추가 확인",JOptionPane.INFORMATION_MESSAGE);
                mini.getRoomEditViewPanel().update();
            }

        } else if (s.equals("취소")) { //초기화
            roomNumtf.setText("");
            roomSizeXY.setSelectedIndex(0);
            roomlcXtf.setText("");
            roomlcYtf.setText("");
        } else if(s.equals("이전")){ //초기화 및 페이지 이동
            roomNumtf.setText("");
            roomSizeXY.setSelectedIndex(0);
            roomlcXtf.setText("");
            roomlcYtf.setText("");

            mini.remove(this);
            mini.getRoomEditViewPanel().eventUnActivate();
            mini.add(mini.sep);
            mini.repaint();
            mini.revalidate();
        }
    }

    public JTextField getRoomlcXtf() {
        return roomlcXtf;
    }

    public JTextField getRoomlcYtf(){
        return roomlcYtf;
    }

    public JComboBox getRoomSizeXY(){return roomSizeXY;}

}

class RoomDelete extends JPanel implements ActionListener { //방삭제 버튼
    private JButton beforeBtn, deleteBtn, cancleBtn;
    private JLabel roomNum, roomSize,roomlcX,roomlcY;
    private JLabel roomNumtf;
    private ScreenEditPanelMini mini;
    private DefaultFrame parent;
    private ArrayList<RoomOptionDTO> options;

    EventSwitch eventSwitch;
    public RoomDelete(DefaultFrame prt,ScreenEditPanelMini mini) {
        parent = prt;
        this.mini = mini;
        this.setLayout(new BorderLayout());
        JPanel rdp = new JPanel(new BorderLayout());
        eventSwitch = new EventSwitch();
        eventSwitch.setSw(false);

        options = parent.getController().getRoomOptionDAO().findAll();

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

        //방 번호의 대한 정보를 가져온다.
        ArrayList<RoomManageDTO> rooms = parent.getController().getRoomManageDAO().findAll();
        ArrayList<String> nums = new ArrayList<>();

        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getNum() != null)
                nums.add(rooms.get(i).getNum());
        }

        String[] strs = new String[nums.size()];
        for(int i = 0; i < strs.length; i++)
            strs[i] = nums.get(i);
        Arrays.sort(strs);

        roomNumtf = new JLabel("");

        roomNumtf.setFont(new DefaultFont(RoomSettingView.BETWEEN_FONT));
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
        cancleBtn = new JButton("취소");
        cancleBtn.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        cancleBtn.addActionListener(this);
        p3.add(deleteBtn);
        p3.add(cancleBtn);
        p3.setLayout(new FlowLayout());
        add(p3,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("삭제")){
            JOptionPane.showConfirmDialog(this, "삭제하시겠습니까?","방 삭제",JOptionPane.YES_NO_OPTION);
            JOptionPane.showMessageDialog(this, "방 삭제가 완료되었습니다.","방 삭제 확인",JOptionPane.INFORMATION_MESSAGE);
        } else if(s.equals("이전")){ //초기화 및 이전 페이지 이동
            roomNumtf.setText("");
            roomSize.setText("방 크기 XY :");
            roomlcX.setText("방 위치 X :");
            roomlcY.setText("방 위치 Y :");

            this.eventSwitch.setSw(false);
            mini.remove(this);
            mini.add(mini.sep);

            mini.repaint();
            mini.revalidate();
        } else if(s.equals("취소")){ //초기화
            roomNumtf.setText("");
            roomSize.setText("방 크기 XY :");
            roomlcX.setText("방 위치 X :");
            roomlcY.setText("방 위치 Y :");
        }
    }

    public EventSwitch getEventSwitch() {
        return eventSwitch;
    }


    public void setRoomInfo(RoomManageDTO room){
        RoomOptionDTO option = options.get(room.getOption() - 1);

        roomNumtf.setText(room.getNum());
        roomSize.setText(roomSize.getText().split(":")[0] + ": " + option.getWidth() +  "x" + option.getHeight());
        roomlcX.setText(roomlcX.getText().split(":")[0] + ": " + room.getX());
        roomlcY.setText(roomlcY.getText().split(":")[0] + ": " + room.getY());
    }
}
class RoomSettingPanelMini extends JPanel implements ActionListener { //방설정 패널
    JLabel roomNum;
    JTextField roomNumtf;
    JButton roomActivate, roomUnActivate, applyBtn, cancleBtn;

    private EventSwitch eventSwitch;


    public RoomSettingPanelMini() {
        this.setLayout(new BorderLayout());
        JPanel rsg = new JPanel();
        rsg.setLayout(new GridLayout(5,1));
        eventSwitch = new EventSwitch();
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
        cancleBtn = new JButton("취소");
        cancleBtn.setFont(new DefaultFont(RoomSettingView.MIDDLE_FONT_SIZE));
        cancleBtn.addActionListener(this);
        p5.add(applyBtn);
        p5.add(cancleBtn);
        rsg.add(p5);

        add(rsg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("방 활성화")){
            //todo 로직 구현

        } else if (s.equals("방 비활성화")) {
            //로직 구현
        } else if (s.equals("적용")) {
            JOptionPane.showConfirmDialog(this, "적용하시겠습니까?","방 설정",JOptionPane.YES_NO_OPTION);
            JOptionPane.showMessageDialog(this, "방 설정이 완료되었습니다.","방 설정 확인",JOptionPane.INFORMATION_MESSAGE);
        } else if (s.equals("취소")) {
            roomNumtf.setText("");
        }
    }

    public EventSwitch getEventSwitch() {
        return eventSwitch;
    }

    public void setRoomInfo(RoomManageDTO room){
        roomNumtf.setText(room.getNum());
    }
}

class RoomManagePanel extends JPanel implements ActionListener { //방관리 패널
    JButton musicAdd, forcedExit;
    DefaultFrame parent;
    public RoomManagePanel(DefaultFrame parent) {
        this.parent = parent;
        this.setLayout(new BorderLayout());
        JPanel rmview = new JPanel(new GridBagLayout());

        ArrayList<RoomManageDTO> rooms = new ArrayList<>();
        rooms.add(new RoomManageDTO("v101", "1",3,3,1, true));
        rooms.add(new RoomManageDTO("v102", "2",6,6,2, true));
        rooms.add(new RoomManageDTO("v103", "3",6,10,1, true));
        rooms.add(new RoomManageDTO("v104", "4",15,15,1, true));

        ArrayList<RoomOptionDTO> options = new ArrayList<>();
        options.add(new RoomOptionDTO(1,3,3));
        options.add(new RoomOptionDTO(1,6,3));
        options.add(new RoomOptionDTO(1,3,6));

        //Grid Bag Layout의 left
        rmview.add(new RoomViewPanel(parent.getController()),DefaultFrame.easyGridBagConstraint(0,0,3,1));

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
        addBtn = new JButton("추가");
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