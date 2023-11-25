package dao;

import java.util.Date;

public class MemberLogDAO extends MemberDAO{
    int remainMusic, chargeMoney;
    char grade;
    Date lastLogin;

    public MemberLogDAO(String phoneId, String passwd, Date birth, Date joinDate,
                        int remainMusic, int chargeMoney, char grade, Date lastLogin) {
        super(phoneId, passwd, birth, joinDate);
        this.remainMusic = remainMusic;
        this.chargeMoney = chargeMoney;
        this.grade = grade;
        this.lastLogin = lastLogin;
    }

    public int getRemainMusic() {
        return remainMusic;
    }

    public void setRemainMusic(int remainMusic) {
        this.remainMusic = remainMusic;
    }

    public int getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(int chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
