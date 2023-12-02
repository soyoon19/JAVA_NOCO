package dto;

import java.sql.Date;

public class MemberDTO {
    private String hp, passwd;
    private Date birthDate, joinDate;
    public MemberDTO() {}

    public MemberDTO(String hp, String passwd, Date birthDate, Date joinDate) {
        this.hp = hp;
        this.passwd = passwd;
        this.birthDate = birthDate;
        this.joinDate = joinDate;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
