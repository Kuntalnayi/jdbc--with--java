package jdbc_lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentJDBCSelect {

    public static void main(String[] args) {

        // Step 1: Load JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load MySQL JDBC Driver.");
            e.printStackTrace();
            return;
        }

        // Step 2: Establish Connection
        String url = "jdbc:mysql://localhost:3306/student_db";  
        String username = "bethu kuntal";   
        String password = "kunta1203";     

        try (Connection con = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connection established successfully!");

            // Step 3: Create Statement
            Statement st = con.createStatement();

            // Step 4: Execute Query
            String query = "SELECT * FROM student"; 
            ResultSet rs = st.executeQuery(query);

            // Step 5: Process ResultSet
            System.out.println("\nStudent Records:");
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

        } catch (Exception e) {
            System.out.println("Error while connecting or fetching data!");
            e.printStackTrace();
        }
    }
}
