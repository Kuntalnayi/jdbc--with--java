package New folder;

public package jdbc_lab;

import java.sql.*;

public class CallableStatementExample {

    public static void main(String[] args) {

        Connection con = null;
        CallableStatement cs = null;

        try {
            // Step 1: Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish Connection
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_db",
                    "bethu kuntal",
                    "kunta1203"
            );

            System.out.println("✅ Database connected successfully!");

            // Prepare CallableStatement for Stored Procedure
            cs = con.prepareCall("{CALL getStudentEmail(?, ?)}");

 
            cs.setInt(1, 1); 

            cs.registerOutParameter(2, Types.VARCHAR);

            cs.execute();

      
            String email = cs.getString(2);

            System.out.println("------------------------------------");
            System.out.println("📘 Student ID: 1");
            System.out.println("📧 Student Email: " + email);
            System.out.println("------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 8: Close Connection
            try {
                if (cs != null) cs.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
 {
    
}
