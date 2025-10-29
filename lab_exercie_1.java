import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQL {
    public static void main(String[] args) {
        try {
            // 1. Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver loaded successfully.");

            // 2. Establish connection
            String url = "jdbc:mysql://localhost:3306/employee_db";
            String username = "root";
            String password = "your_password";
            Connection con = DriverManager.getConnection(url, username, password);

            System.out.println("Connection established successfully!");
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
