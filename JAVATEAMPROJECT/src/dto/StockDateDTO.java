package dto;

import java.sql.Date;

public class StockDateDTO {
    private int num;
    private Date date;
    private String code;
    private int category;

    public StockDateDTO() {}

    public StockDateDTO(int num, Date date, String code, int category) {
        this.num = num;
        this.date = date;
        this.code = code;
        this.category = category;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
