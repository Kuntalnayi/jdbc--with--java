package New folder;

public package jdbc_lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentSwingJDBC extends JFrame implements ActionListener {

    // UI Components
    JTextField txtId, txtFname, txtLname, txtEmail;
    JButton btnInsert, btnUpdate, btnSelect, btnDelete;
    JTextArea txtArea;

    // Database Connection Variables
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Constructor
    public StudentSwingJDBC() {
        setTitle("Student Management System (Swing + JDBC)");
        setSize(600, 450);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(50, 30, 100, 30);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(150, 30, 150, 30);
        add(txtId);

        JLabel lblFname = new JLabel("First Name:");
        lblFname.setBounds(50, 80, 100, 30);
        add(lblFname);

        txtFname = new JTextField();
        txtFname.setBounds(150, 80, 150, 30);
        add(txtFname);

        JLabel lblLname = new JLabel("Last Name:");
        lblLname.setBounds(50, 130, 100, 30);
        add(lblLname);

        txtLname = new JTextField();
        txtLname.setBounds(150, 130, 150, 30);
        add(txtLname);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 180, 100, 30);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 180, 150, 30);
        add(txtEmail);

        // Buttons
        btnInsert = new JButton("Insert");
        btnInsert.setBounds(50, 240, 100, 30);
        add(btnInsert);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(160, 240, 100, 30);
        add(btnUpdate);

        btnSelect = new JButton("Select");
        btnSelect.setBounds(270, 240, 100, 30);
        add(btnSelect);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(380, 240, 100, 30);
        add(btnDelete);

        // Output Area
        txtArea = new JTextArea();
        txtArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtArea);
        scroll.setBounds(50, 290, 500, 100);
        add(scroll);

        // Add Action Listeners
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnSelect.addActionListener(this);
        btnDelete.addActionListener(this);

        // Establish DB Connection
        connectDB();

        setVisible(true);
    }

    // Database Connection Method
    public void connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_db",
                    "bethu kuntal",
                    "kunta1203"
            );
            txtArea.setText("✅ Database Connected Successfully!\n");
        } catch (Exception e) {
            txtArea.setText("❌ Database Connection Failed!\n" + e);
        }
    }

    // Button Click Actions
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == btnInsert) {
                String insert = "INSERT INTO student (fname, lname, email) VALUES (?, ?, ?)";
                ps = con.prepareStatement(insert);
                ps.setString(1, txtFname.getText());
                ps.setString(2, txtLname.getText());
                ps.setString(3, txtEmail.getText());
                ps.executeUpdate();
                txtArea.setText("✅ Record Inserted Successfully!\n");

            } else if (e.getSource() == btnUpdate) {
                String update = "UPDATE student SET fname=?, lname=?, email=? WHERE id=?";
                ps = con.prepareStatement(update);
                ps.setString(1, txtFname.getText());
                ps.setString(2, txtLname.getText());
                ps.setString(3, txtEmail.getText());
                ps.setInt(4, Integer.parseInt(txtId.getText()));
                ps.executeUpdate();
                txtArea.setText("✅ Record Updated Successfully!\n");

            } else if (e.getSource() == btnSelect) {
                String select = "SELECT * FROM student";
                ps = con.prepareStatement(select);
                rs = ps.executeQuery();
                txtArea.setText("📘 Student Records:\n---------------------------------\n");
                while (rs.next()) {
                    txtArea.append(
                            "ID: " + rs.getInt("id") +
                            " | Name: " + rs.getString("fname") + " " + rs.getString("lname") +
                            " | Email: " + rs.getString("email") + "\n"
                    );
                }

            } else if (e.getSource() == btnDelete) {
                String delete = "DELETE FROM student WHERE id=?";
                ps = con.prepareStatement(delete);
                ps.setInt(1, Integer.parseInt(txtId.getText()));
                ps.executeUpdate();
                txtArea.setText("✅ Record Deleted Successfully!\n");
            }

        } catch (Exception ex) {
            txtArea.setText("❌ Error: " + ex.getMessage());
        }
    }

    // Main Method
    public static void main(String[] args) {
        new StudentSwingJDBC();
    }
}
 {
    
}
