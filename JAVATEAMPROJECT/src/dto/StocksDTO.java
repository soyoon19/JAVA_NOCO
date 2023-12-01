
package dto;

public class StocksDTO {

    /*
    public static final int MAIN_CATEGORY_
    */

    //category -- class //분류

    private  String code, name;
    private  int amount, minAmount, cost;
    private java.sql.Date date;

    public StocksDTO(String code, String name,
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
    public  void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }
    public  void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getMinAmount(Integer minAmount) {
        return minAmount;
    }
    public  void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getCost (Integer cost) {
        return cost;
    }
    public  void setcost(Integer cost) {
        this.amount = cost;
    }

    public java.sql.Date date(java.sql.Date date) {
        return date;
    }
    public void getDate (java.sql.Date date) {
        this.date = date;
    }

}
