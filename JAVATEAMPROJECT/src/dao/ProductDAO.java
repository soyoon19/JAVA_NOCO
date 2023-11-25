package dao;

import javax.swing.*;

public class ProductDAO{
    private String name, category, state, code;
    private int num, sellPrice, originalPrice;
    private boolean discountCheck;
    private ImageIcon i;

    public ProductDAO(String code, String name, String category,
                      int num, boolean discountCheck, int sellPrice,
                      int originalPrice ,String state){
        this.code = code;
        this.name = name;
        this.category = category;
        this.num = num;
        this.discountCheck = discountCheck;
        this.sellPrice = sellPrice;
        this.originalPrice = originalPrice;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public boolean isDiscountCheck() {
        return discountCheck;
    }

    public void setDiscountCheck(boolean discountCheck) {
        this.discountCheck = discountCheck;
    }

    public ImageIcon getI() {
        return i;
    }

    public void setI(ImageIcon i) {
        this.i = i;
    }
}
