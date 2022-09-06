import java.sql.*;

public class DBlogic extends DBConnection {


    // register
    public void register(String username, String password) {
        try {
            Connection conn = connectToDB();
            String insert = "INSERT INTO users (username, password) VALUES (?, ?)";

            PreparedStatement ps = conn.prepareStatement(insert);

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();
            conn.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }


    }

    // get user
    public String select(String username) {
        try {
            Connection conn = connectToDB();
            String select  = "SELECT username from users WHERE username = ?";

            PreparedStatement ps = conn.prepareStatement(select);

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                System.out.println("User already exists");
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return username;
    }

    // login
    public boolean login(String username, String password) {
        boolean isLoggedIn = false;

        try {
            Connection conn = connectToDB();
            String select  = "SELECT username, password from users WHERE username = ? AND password = ?";

            PreparedStatement ps = conn.prepareStatement(select);

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                isLoggedIn = true;
            } else {
                System.out.println("User not found");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isLoggedIn;
    }
}
