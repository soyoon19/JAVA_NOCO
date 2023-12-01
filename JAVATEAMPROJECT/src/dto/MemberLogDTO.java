    package dto;

    import java.sql.Date;

    public class MemberLogDTO {
        private String phone;
        private char m_rate;
        private  int holdSong;
        private Date lastLogin;
        private float totalPay;

        public MemberLogDTO(){}

        public MemberLogDTO(String phone, char m_rate, int holdSong, Date lastLogin, float totalPay) {
            this.phone = phone;
            this.m_rate = m_rate;
            this.holdSong = holdSong;
            this.lastLogin = lastLogin;
            this.totalPay = totalPay;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public char getM_rate() {
            return m_rate;
        }

        public void setM_rate(char m_rate) {
            this.m_rate = m_rate;
        }

        public int getHoldSong() {
            return holdSong;
        }

        public void setHoldSong(int holdSong) {
            this.holdSong = holdSong;
        }

        public Date getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(Date lastLogin) {
            this.lastLogin = lastLogin;
        }

        public float getTotalPay() {
            return totalPay;
        }

        public void setTotalPay(float totalPay) {
            this.totalPay = totalPay;
        }
    }
