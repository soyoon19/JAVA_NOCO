package custom_component;

import controller_db.Controller;
import dao.RoomManageDAO;
import dao.RoomOptionDAO;
import dto.RoomManageDTO;
import dto.RoomOptionDTO;
import views.DefaultFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class RoomViewPanel extends JPanel {


    //pixcel 크기
    public static int MAX_WIDTH = 25,
            MAX_HEIGHT = 25;

    //각 좌표의 지점
    protected AreaJPanel[][] jps;
    protected ArrayList<RoomManageDTO> rooms; //방 정보
    protected ArrayList<RoomOptionDTO> options;
    protected RoomPanel[] roomPs;

    //이벤트 발생여부
    protected EventSwitch sw;
    protected Controller controller;

    public void mapSet(){
        ArrayList<RoomManageDTO> tempRooms = controller.getRoomManageDAO().findAll();
        rooms = new ArrayList<RoomManageDTO>();

        //RoomNum이 null이 아닌 값을 가져온다.
        for(int i = 0; i < tempRooms.size(); i++)
            if(tempRooms.get(i).getNum() != null)
                rooms.add(tempRooms.get(i));


        this.options = controller.getRoomOptionDAO().findAll();

        this.setLayout(new GridBagLayout());
        jps = new AreaJPanel[MAX_HEIGHT][MAX_WIDTH];
        sw = new EventSwitch();

        for(int i = 0; i < MAX_HEIGHT; i++) {
            for (int j = 0; j < MAX_WIDTH; j++) {

                jps[i][j] = new AreaJPanel(i, j);   //가록 세로 크기만큼 패널을 붙인다.
                jps[i][j].setBackground(Color.white);


                this.add(jps[i][j], DefaultFrame.easyGridBagConstraint(j,i,1,1));
            }
        }
    }

    public void roomSet(){
        //그리드된 화면에서 방을 표시한다.
        roomPs = new RoomPanel[rooms.size()];
        RoomOptionDTO option;
        int x, y, width, height, k = 0, mode;
        for(RoomManageDTO room : rooms){
            mode = RoomPanel.NORMAL;
            if(room.isCheck()) mode = RoomPanel.LOCK;
            else if(controller.getRoomImfDAO().findById(room.getNum()) != null) mode = RoomPanel.USE;

            roomPs[k] = new RoomPanel(room, mode);
            x = room.getX(); y = room.getY();
            option = options.get(room.getOption() - 1);
            width = option.getWidth();
            height = option.getHeight();


            for(int i = y; i < y + height ; i++) {
                for (int j = x; j < x + width; j++) {
                    this.remove(jps[i][j]); //원래 점유하고 있던 패널을 삭제한다.
                    jps[i][j].setUse(false); //이 패널을 사용하지 않는다는 표시를 한다.
                }
            }

            this.add(roomPs[k], DefaultFrame.easyGridBagConstraint(x, y,1,1, width ,height));
            k++;
        }
    }


    public RoomViewPanel(Controller controller){
        this.controller = controller;

        this.mapSet();
        this.roomSet();
    }

    public void update(){
        this.removeAll();
        this.mapSet();
        this.roomSet();

        this.repaint();
        this.revalidate();
    }


    public void eventActivate(){
        sw.setSw(true);
    }

    public void eventUnActivate(){
        sw.setSw(false);
    }

    public RoomPanel[] getRoomPs(){
        return roomPs;
    }
}
