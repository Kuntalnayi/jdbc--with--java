public import java.sql.*;

public class EmployeeCRUD {
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=employee;encrypt=true;trustServerCertificate=true;",
                "sa", "kunt@l1203");

            // Insert
            PreparedStatement insert = con.prepareStatement("INSERT INTO emp_detail(empname,address,pincode) VALUES(?,?,?)");
            insert.setString(1, "kuntal");
            insert.setString(2, "talod");
            insert.setInt(3, 2204);
            insert.executeUpdate();
            System.out.println("Record inserted successfully!");

            // Update
            PreparedStatement update = con.prepareStatement("UPDATE emp_detail SET address=? WHERE empid=?");
            update.setString(1, "ahmedabad");
            update.setInt(2, 2);
            update.executeUpdate();
            System.out.println("Record updated successfully!");

            // Select
            PreparedStatement select = con.prepareStatement("SELECT * FROM emp_detail");
            ResultSet rs = select.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("empid") + " " + rs.getString("empname") + " " + rs.getString("address"));
            }

            // Delete
            PreparedStatement delete = con.prepareStatement("DELETE FROM emp_detail WHERE empid=?");
            delete.setInt(1, 3);
            delete.executeUpdate();
            System.out.println("Record deleted successfully!");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 {
    
}
