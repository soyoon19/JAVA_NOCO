package custom_component;

import dto.RoomManageDTO;
import dto.RoomOptionDTO;
import views.RoomSettingView;

import java.awt.*;
import java.awt.event.MouseListener;

public abstract class AreaSelectMouseListener implements MouseListener {
    private RoomOptionDTO option;
    private AreaJPanel[][] jps;
    private int x, y;
    private EventSwitch sw;
    RoomSettingView roomSettingView;

    public AreaSelectMouseListener(int x, int y, RoomOptionDTO option, AreaJPanel[][] jps, EventSwitch sw) {
        this.sw  = sw;
        this.option = option;
        this.jps = jps;
        this.x = x;
        this.y = y;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        if(!sw.getSw()) return;
        boolean empty = true;

        for(int i = y; i < option.getWidth() + y && i < RoomViewPanel.MAX_HEIGHT; i++)
            for(int j = x; j < option.getHeight() + x && j < RoomViewPanel.MAX_WIDTH; j++)
                if(!jps[i][j].getUse()) { //JPanel이 사용되고 있지 않으면 == 방이 이미 점유되어 있면
                    empty = false;
                    break;
                }


        for(int i = y; i < option.getWidth() + y && i < RoomViewPanel.MAX_HEIGHT; i++)
            for(int j = x; j < option.getHeight() + x && j < RoomViewPanel.MAX_WIDTH; j++) {
                if(empty) jps[i][j].setBackground(new Color(122,138,250));
                else jps[i][j].setBackground(new Color(242,101,101));    //방이 이미 점유되어 있으면 빨간색으로 변경한다.
            }

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        if(!sw.getSw()) return;

        for(int i = y; i < option.getWidth() + y && i < RoomViewPanel.MAX_HEIGHT; i++)
            for(int j = x; j < option.getHeight() + x && j < RoomViewPanel.MAX_WIDTH; j++) {
                jps[i][j].setBackground(Color.white);
            }

    }

    @Override
    abstract public void mousePressed(java.awt.event.MouseEvent e);

    @Override
    abstract public void mouseReleased(java.awt.event.MouseEvent e);

    public RoomOptionDTO getOption(){
        return option;
    }

    public void setOption(RoomOptionDTO option){
        this.option = option;
    }

}
