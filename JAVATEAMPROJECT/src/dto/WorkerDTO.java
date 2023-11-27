package dto;

import java.sql.Date;

public class WorkerDTO {
    private String id, passwd, name, hp, position;
    private boolean admin;
    private Date regDate;

    public WorkerDTO(){}

    public WorkerDTO(String id, String passwd, String name, String hp, String position, boolean admin, Date regDate) {
        this.id = id;
        this.passwd = passwd;
        this.name = name;
        this.hp = hp;
        this.position = position;
        this.admin = admin;
        this.regDate = regDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
