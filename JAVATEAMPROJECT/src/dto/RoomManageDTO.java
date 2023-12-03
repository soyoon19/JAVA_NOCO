package dto;

import java.sql.Time;
import java.util.Stack;

public class RoomManageDTO {
    private String num, userHp;
    private Time enterTime;
    private int useSong, leftSong, paySong;
    private boolean using;

    RoomManageDTO(){}

    public RoomManageDTO(String userHp, Time enterTime, String num, int useSong, int leftSong, int paySong, boolean using) {
        this.userHp = userHp;
        this.enterTime = enterTime;
        this.num = num;
        this.useSong = useSong;
        this.leftSong = leftSong;
        this.paySong = paySong;
        this.using = using;
    }

    public String getUserHp() {
        return userHp;
    }

    public void setUserHp(String userHp) {
        this.userHp = userHp;
    }

    public Time getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Time enterTime) {
        this.enterTime = enterTime;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getUseSong() {
        return useSong;
    }

    public void setUseSong(int useSong) {
        this.useSong = useSong;
    }

    public int getLeftSong() {
        return leftSong;
    }

    public void setLeftSong(int leftSong) {
        this.leftSong = leftSong;
    }

    public int getPaySong() {
        return paySong;
    }

    public void setPaySong(int paySong) {
        this.paySong = paySong;
    }

    public boolean isUsing() {
        return using;
    }

    public void setUsing(boolean using) {
        this.using = using;
    }
}
