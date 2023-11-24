package dao;

import java.util.Date;

public class MemberDAO {
    private String phoneId, passwd;
    private Date birth, joinDate;

    public MemberDAO(String phoneId, String passwd, Date birth, Date joinDate) {
        this.phoneId = phoneId;
        this.passwd = passwd;
        this.birth = birth;
        this.joinDate = joinDate;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
