package dto;

import java.sql.Date;

public class OrderHDTO {
    private String orderCode, goodsCode, H_temp;
    private int count, price, discount, cost;
    private Date date;

    public OrderHDTO(){}

    public OrderHDTO(String orderCode, String goodsCode, String H_temp, int count,
                     int price, int discount, int cost, Date date) {
        this.orderCode = orderCode;
        this.goodsCode = goodsCode;
        this.H_temp = H_temp;
        this.count = count;
        this.price = price;
        this.discount = discount;
        this.cost = cost;
        this.date = date;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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

    public String getH_temp() {
        return H_temp;
    }

    public void setH_temp(String h_temp) {
        H_temp = h_temp;
    }
}
