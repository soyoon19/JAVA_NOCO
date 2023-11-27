import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    Connection conn;

    public DBConnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + DBLoginInfo.DB_IP +  ":3306/" + DBLoginInfo.DB_NAME  + " ?serverTimezone=Asia/Seoul",
                    DBLoginInfo.DB_ID,
                    DBLoginInfo.DB_PW);
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }
}
