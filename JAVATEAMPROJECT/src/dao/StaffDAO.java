package dao;

import java.util.Date;

public class StaffDAO {
    private String id, name, password, phone, position;
    private boolean permission;
    private Date registerDate;

    public StaffDAO(String id, String name, String password, String phone, String position, boolean permission, Date registerDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.position = position;
        this.permission = permission;
        this.registerDate = registerDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
