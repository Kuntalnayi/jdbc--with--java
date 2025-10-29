import java.sql.*;

public class DBMetaInfo {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=employee;encrypt=true;trustServerCertificate=true;",
                "sa", "kunt@l1203");

            DatabaseMetaData dbmd = con.getMetaData();
            System.out.println("Database Name: " + dbmd.getDatabaseProductName());
            System.out.println("Database Version: " + dbmd.getDatabaseProductVersion());
            System.out.println("Driver Name: " + dbmd.getDriverName());

            ResultSet tables = dbmd.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("Tables:");
            while (tables.next()) {
                System.out.println(tables.getString("TABLE_NAME"));
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
