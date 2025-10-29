public package employee_database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeSwingCRUD extends JFrame implements ActionListener {

    // UI components
    JTextField txtId, txtFname, txtLname, txtEmail;
    JButton btnInsert, btnUpdate, btnDelete, btnSelect;
    Connection con;

    // Constructor
    EmployeeSwingCRUD() {
        setTitle("Employee CRUD Application");
        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(50, 40, 100, 30);
        add(lblId);

        JLabel lblFname = new JLabel("First Name:");
        lblFname.setBounds(50, 80, 100, 30);
        add(lblFname);

        JLabel lblLname = new JLabel("Last Name:");
        lblLname.setBounds(50, 120, 100, 30);
        add(lblLname);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 160, 100, 30);
        add(lblEmail);

        // Text Fields
        txtId = new JTextField();
        txtId.setBounds(150, 40, 180, 25);
        add(txtId);

        txtFname = new JTextField();
        txtFname.setBounds(150, 80, 180, 25);
        add(txtFname);

        txtLname = new JTextField();
        txtLname.setBounds(150, 120, 180, 25);
        add(txtLname);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 160, 180, 25);
        add(txtEmail);

        // Buttons
        btnInsert = new JButton("Insert");
        btnInsert.setBounds(30, 220, 80, 30);
        add(btnInsert);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(120, 220, 80, 30);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(210, 220, 80, 30);
        add(btnDelete);

        btnSelect = new JButton("Select");
        btnSelect.setBounds(300, 220, 80, 30);
        add(btnSelect);

        // Button actions
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnSelect.addActionListener(this);

        // Connect to Database
        connectDB();

        setVisible(true);
    }

    // Database Connection
    void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=employee;encrypt=true;trustServerCertificate=true;",
                "sa",
                "kunt@l1203"
            );
            System.out.println("✅ Database Connected");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed");
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == btnInsert) {
                String sql = "INSERT INTO emp_detail (empid, empname, address, pincode) VALUES (?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(txtId.getText()));
                ps.setString(2, txtFname.getText());
                ps.setString(3, txtLname.getText());
                ps.setInt(4, Integer.parseInt(txtEmail.getText())); // here you can replace with another column as needed
                int row = ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Record Inserted Successfully!");

            } else if (ae.getSource() == btnUpdate) {
                String sql = "UPDATE emp_detail SET empname=?, address=?, pincode=? WHERE empid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtFname.getText());
                ps.setString(2, txtLname.getText());
                ps.setInt(3, Integer.parseInt(txtEmail.getText()));
                ps.setInt(4, Integer.parseInt(txtId.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Record Updated Successfully!");

            } else if (ae.getSource() == btnDelete) {
                String sql = "DELETE FROM emp_detail WHERE empid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(txtId.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Record Deleted Successfully!");

            } else if (ae.getSource() == btnSelect) {
                String sql = "SELECT * FROM emp_detail WHERE empid=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(txtId.getText()));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    txtFname.setText(rs.getString("empname"));
                    txtLname.setText(rs.getString("address"));
                    txtEmail.setText(String.valueOf(rs.getInt("pincode")));
                    JOptionPane.showMessageDialog(this, "Record Found!");
                } else {
                    JOptionPane.showMessageDialog(this, "Record Not Found!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main Method
    public static void main(String[] args) {
        new EmployeeSwingCRUD();
    }
}
 {
    
}
