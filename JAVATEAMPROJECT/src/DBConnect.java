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
                    "jdbc:mysql://34.64.197.206:3306/java_project?serverTimezone=Asia/Seoul",
                    "java",
                    "javaNoInCo$$4");
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }
}
