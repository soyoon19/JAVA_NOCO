package dto;

import java.sql.Time;

public class RoomRequestDTO {
    private String num, request;
    private Time recTime;
    private Boolean complete;

    public RoomRequestDTO() {}

    public RoomRequestDTO(String num, String request, Time recTime, Boolean complete) {
        this.num = num;
        this.request = request;
        this.recTime = recTime;
        this.complete = complete;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Time getRecTime() {
        return recTime;
    }

    public void setRecTime(Time recTime) {
        this.recTime = recTime;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}

