package com.anish.codsoft5;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class update extends JFrame implements ActionListener {
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField cityField;
    private JTextField dobField;
    private JTextField bloodGroupField;
    private JTextField contactNumberField;
    private JButton updateButton;
    private Connection connection;

    public update() {
        this.setTitle("Update Student Information");
        this.setDefaultCloseOperation(3);
        this.setLayout(new GridLayout(8, 2));
        JLabel idLabel = new JLabel("Student ID:");
        this.idField = new JTextField();
        JLabel firstNameLabel = new JLabel("First Name:");
        this.firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        this.lastNameField = new JTextField();
        JLabel cityLabel = new JLabel("City:");
        this.cityField = new JTextField();
        JLabel dobLabel = new JLabel("Date of Birth (YYYY-MM-DD):");
        this.dobField = new JTextField();
        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        this.bloodGroupField = new JTextField();
        JLabel contactNumberLabel = new JLabel("Contact Number:");
        this.contactNumberField = new JTextField();
        this.updateButton = new JButton("Update Student");
        this.updateButton.addActionListener(this);
        this.add(idLabel);
        this.add(this.idField);
        this.add(firstNameLabel);
        this.add(this.firstNameField);
        this.add(lastNameLabel);
        this.add(this.lastNameField);
        this.add(cityLabel);
        this.add(this.cityField);
        this.add(dobLabel);
        this.add(this.dobField);
        this.add(bloodGroupLabel);
        this.add(this.bloodGroupField);
        this.add(contactNumberLabel);
        this.add(this.contactNumberField);
        this.add(this.updateButton);
        this.pack();
        this.setLocationRelativeTo((Component)null);

        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/cs5";
            String username = "root";
            String password = "Anish@230403";
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException | ClassNotFoundException var11) {
            var11.printStackTrace();
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.updateButton) {
            this.updateStudentInformation();
        }

    }

    private void updateStudentInformation() {
        String studentID = this.idField.getText();
        String firstName = this.firstNameField.getText();
        String lastName = this.lastNameField.getText();
        String city = this.cityField.getText();
        String dob = this.dobField.getText();
        String bloodGroup = this.bloodGroupField.getText();
        String contactNumber = this.contactNumberField.getText();

        try {
            String updateQuery = "UPDATE students SET first_name=?, last_name=?, city=?, dob=?, blood_group=?, contact_number=? WHERE id=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, dob);
            preparedStatement.setString(5, bloodGroup);
            preparedStatement.setString(6, contactNumber);
            preparedStatement.setInt(7, Integer.parseInt(studentID));
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student information updated successfully!");
                this.clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "No student with the provided ID found.");
            }

            preparedStatement.close();
        } catch (SQLException var11) {
            var11.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + var11.getMessage());
        }

    }

    private void clearFields() {
        this.idField.setText("");
        this.firstNameField.setText("");
        this.lastNameField.setText("");
        this.cityField.setText("");
        this.dobField.setText("");
        this.bloodGroupField.setText("");
        this.contactNumberField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            (new update()).setVisible(true);
        });
    }
}
