package custom_component;

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

    //이벤트 발생여부
    protected EventSwitch sw;


    public RoomViewPanel(ArrayList<RoomManageDTO> rooms, ArrayList<RoomOptionDTO> options){
        this.rooms = rooms;
        this.options = options;

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

        //그리드된 화면에서 방을 표시한다.
        RoomOptionDTO option;
        int x, y, width, height;
        for(RoomManageDTO room : rooms){
            x = room.getX(); y = room.getY();
            option = options.get(room.getOption() - 1);
            width = option.getWidth();
            height = option.getHeight();


            for(int i = y; i < y + height ; i++) {
                for (int j = x; j < x + width; j++) {
                    this.remove(jps[i][j]); //원래 점유하고 있던 패널을 삭제한다.
                    jps[i][j].setUse(false); //이 패널을 사용하지 않는다는 표시를 한다.
                }

                //방 붙이기
                JPanel roomPs = new JPanel();
                JLabel lbs = new JLabel(room.getNum() + "번");
                roomPs.setBackground(Color.GREEN);
                roomPs.add(lbs);
                roomPs.setBorder(new TitledBorder(new LineBorder(Color.black, 3)));
                //방의 좌표와 크기만큼 영역을 지정해 준다.
                this.add(roomPs, DefaultFrame.easyGridBagConstraint(x, y,1,1, width ,height));
            }
        }
    }



    public void eventActivate(){
        sw.setSw(true);
    }

    public void eventUnActivate(){
        sw.setSw(false);
    }
}
