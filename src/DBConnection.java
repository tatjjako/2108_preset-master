import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnection {
    String DB = "jdbc:mysql://localhost:3306/2108_login";
    String USER = "root";
    String PASS = "qwe123@A";

    public Connection connectToDB() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

