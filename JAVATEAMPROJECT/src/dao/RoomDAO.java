package dao;

public class RoomDAO {
    private String Code;
    private int Number, x, y, ption;
    private boolean check;

    public RoomDAO(String code, int number, int x, int y, int ption, boolean check) {
        Code = code;
        Number = number;
        this.x = x;
        this.y = y;
        this.ption = ption;
        this.check = check;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
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

    public int getPtion() {
        return ption;
    }

    public void setPtion(int ption) {
        this.ption = ption;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
