package dto;

import java.sql.Date;
import java.sql.Time;

public class OrderDTO {
    public static final int STATUS_ORDER = 1
            ,STATUS_COMPLETE = 2, STATUS_CANCEL = 3;

    private String o_code, hp, id, code;
    private Date date;
    private  Time time, comptime;
    private int pay, discount, status;

    public OrderDTO(){}

    public OrderDTO(String o_code, String hp, String id, String code, Date date,
                    Time time, Time comptime, int pay, int discount, int status) {
        this.o_code = o_code;
        this.hp = hp;
        this.id = id;
        this.code = code;
        this.date = date;
        this.time = time;
        this.comptime = comptime;
        this.pay = pay;
        this.discount = discount;
        this.status = status;
    }


    public String getO_code() {
        return o_code;
    }

    public void setO_code(String o_code) {
        this.o_code = o_code;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getComptime() {
        return comptime;
    }

    public void setComptime(Time comptime) {
        this.comptime = comptime;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
