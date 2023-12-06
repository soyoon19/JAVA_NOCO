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

    public static int gradeToDiscount(char grade){
        switch (grade){
            case 'c':
                return 0;
            case 'b':
                return 1;
            case 's':
                return 3;
            case 'g':
                return 5;
            default:
                return 0;
        }
    }

    public static char gradeCodtion(int pay){
        if(pay < 100000) return 'c';
        else if(pay < 300000) return 'b';
        else if(pay < 800000) return 's';
        else return 'g';
    }
}
