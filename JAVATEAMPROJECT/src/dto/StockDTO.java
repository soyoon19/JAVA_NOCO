package dto;

import java.sql.Date;

public class StockDTO {

    /*
    public static final int MAINCATEGORY
    */

    public static final int TEMPERATURE_ITEMS = 0
            , REFRIGERATED_ITEMS = 1, FROZEN_ITEMS = 2, ORTHER_ITEMS = 3;

    public static final String[] ITEMS_KOREA_NAME = {
            "실온", "냉장", "냉동", "기타"
    };

    public static String CATEGORY_TO_KOREANNAME(int category){
        return ITEMS_KOREA_NAME[category];
    }


    //category -- class //분류

    public StockDTO(){}

    private  String code, name;
    private  int amount, minAmount, cost, category;


    public StockDTO(String code, String name,
                    int amount, int minAmount, int cost, int category) {

        this.code = code;
        this.name = name;
        this.amount = amount;
        this.minAmount = minAmount;
        this.cost = cost;
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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


}