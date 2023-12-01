package dto;

import java.sql.Date;

public class StockDTO {

    /*
    public static final int MAIN_CATEGORY_
    */

    //category -- class //분류

    public StockDTO(){}

    private  String code, name;
    private  int amount, minAmount, cost;
    private Date date;


    public StockDTO(String code, String name,
                     int amount, int minAmount, int cost, java.sql.Date date) {

        this.code = code;
        this.name = name;
        this.amount = amount;
        this.minAmount = minAmount;
        this.cost = cost;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}