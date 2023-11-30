package dto;

import java.sql.Time;
import java.util.Stack;

public class RoomManageDTO {
    private String code, num;
    private int x, y, option;
    private boolean check;

    public RoomManageDTO(){}

    public RoomManageDTO(String code, String num,
                      int x, int y, int option, boolean check) {
        this.code = code;
        this.num = num;
        this.x = x;
        this.y = y;
        this.option = option;
        this.check = check;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
