import java.sql.*;

public class CallProcedure {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=employee;encrypt=true;trustServerCertificate=true;",
                "sa", "kunt@l1203");

            CallableStatement cs = con.prepareCall("{call getEmpName(?,?)}");
            cs.setInt(1, 1);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();

            System.out.println("Employee Name: " + cs.getString(2));
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
