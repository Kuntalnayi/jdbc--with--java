package jdbc_lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentJDBCCrud {

    public static void main(String[] args) {

        // Step 1: Load JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Failed to load MySQL JDBC Driver.");
            e.printStackTrace();
            return;
        }

        // Step 2: Establish Connection
        String url = "jdbc:mysql://localhost:3306/student_db"; 
        String username = "bethu kuntal"; 
        String password = "kunta1203";    

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("✅ Database connection established successfully!\n");
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to database!");
            e.printStackTrace();
            return;
        }

        // Step 3: Insert Operation
        try {
            String insertQuery = "INSERT INTO student (student_name, course, marks) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertQuery);
            ps.setString(1, "Kuntal");
            ps.setString(2, "Java");
            ps.setInt(3, 90);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Record inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error inserting record!");
            e.printStackTrace();
        }

        // Step 4: Update Operation
        try {
            String updateQuery = "UPDATE student SET marks = ? WHERE student_id = ?";
            PreparedStatement ps = con.prepareStatement(updateQuery);
            ps.setInt(1, 95);
            ps.setInt(2, 1); // update record with id=1

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Record updated successfully!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error updating record!");
            e.printStackTrace();
        }

        // Step 5: Select Operation
        try {
            String selectQuery = "SELECT * FROM student";
            PreparedStatement ps = con.prepareStatement(selectQuery);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n📘 Student Records:");
            System.out.println("---------------------------------------------------");
            System.out.println("ID\tName\t\tCourse\t\tMarks");
            System.out.println("---------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("student_name");
                String course = rs.getString("course");
                int marks = rs.getInt("marks");

                System.out.println(id + "\t" + name + "\t\t" + course + "\t\t" + marks);
            }

            System.out.println("---------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("❌ Error fetching records!");
            e.printStackTrace();
        }

        // Step 6: Delete Operation
        try {
            String deleteQuery = "DELETE FROM student WHERE student_id = ?";
            PreparedStatement ps = con.prepareStatement(deleteQuery);
            ps.setInt(1, 2); // delete record with id=2

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Record deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error deleting record!");
            e.printStackTrace();
        }

        // Step 7: Close Connection
        try {
            if (con != null) {
                con.close();
                System.out.println("\n🔒 Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
