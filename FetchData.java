import java.sql.*;

public class FetchData {
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=employee;encrypt=true;trustServerCertificate=true;",
                "sa", "kunt@l1203");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM emp_detail",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = ps.executeQuery();

            rs.next();
            System.out.println("First Record: " + rs.getString("empname"));
            rs.last();
            System.out.println("Last Record: " + rs.getString("empname"));
            rs.previous();
            System.out.println("Previous Record: " + rs.getString("empname"));

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
