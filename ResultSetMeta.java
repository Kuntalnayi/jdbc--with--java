import java.sql.*;

public class ResultSetMeta {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=employee;encrypt=true;trustServerCertificate=true;",
                "sa", "kunt@l1203");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM emp_detail");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            int colCount = rsmd.getColumnCount();
            System.out.println("Column Count: " + colCount);
            for (int i = 1; i <= colCount; i++) {
                System.out.println(rsmd.getColumnName(i) + " - " + rsmd.getColumnTypeName(i));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
