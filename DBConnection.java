import java.sql.*;

public class DBConnection {
    static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fooddonation", "root", "your_password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}