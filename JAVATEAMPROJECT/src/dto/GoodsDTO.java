package dto;

import javax.swing.*;

public class GoodsDTO {
    public static final int MAIN_CATEGORY_MUSIC = 1;
    public static final int MAIN_CATEGORY_DRINK = 2;

    public static String[] CATEGORY = {
            "노래", "커피", "논커피", "티", "스무디", "캔음료"
    };

    //category -- class //분류
    //mainCategory -- category //대분류
    private String code, name, category, status;
    private int mainCategory, saleCount, price, cost;
    private boolean disStatus, ice, hot;

    public GoodsDTO(){}

    public GoodsDTO(String code, String name, String category, String status, int mainCategory,
                    int saleCount, int price, int cost, boolean disStatus, boolean ice, boolean hot) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.status = status;
        this.mainCategory = mainCategory;
        this.saleCount = saleCount;
        this.price = price;
        this.cost = cost;
        this.disStatus = disStatus;
        this.ice = ice;
        this.hot = hot;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(int mainCategory) {
        this.mainCategory = mainCategory;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean getDisStatus() {
        return disStatus;
    }

    public void setDisStatus(boolean disStatus) {
        this.disStatus = disStatus;
    }

    public boolean getIce() {
        return ice;
    }

    public void setIce(boolean ice) {
        this.ice = ice;
    }

    public boolean getHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }
}
