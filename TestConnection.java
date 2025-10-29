import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver loaded successfully.");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=employee;encrypt=true;trustServerCertificate=true;";
            Connection con = DriverManager.getConnection(url, "sa", "kunt@l1203");
            System.out.println("Database connection successful!");

            con.close();
        } catch (Exception e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}
