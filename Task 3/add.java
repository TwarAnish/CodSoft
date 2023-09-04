package com.anish.codsoft5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class add extends JFrame {
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField cityField;
    private JTextField dobField;
    private JTextField bloodGroupField;
    private JTextField contactNumberField;

    public add() {
        setTitle("Add Student");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField();
        JLabel dobLabel = new JLabel("Date of Birth (YYYY-MM-DD):");
        dobField = new JTextField();
        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        bloodGroupField = new JTextField();
        JLabel contactNumberLabel = new JLabel("Contact Number:");
        contactNumberField = new JTextField();

        JButton addButton = new JButton("Add Student");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudentToDatabase();
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(cityLabel);
        panel.add(cityField);
        panel.add(dobLabel);
        panel.add(dobField);
        panel.add(bloodGroupLabel);
        panel.add(bloodGroupField);
        panel.add(contactNumberLabel);
        panel.add(contactNumberField);
        panel.add(addButton);

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStudentToDatabase() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/cs5";
        String username = "root";
        String password = "Anish@230403";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "INSERT INTO students (id, first_name, last_name, city, dob, blood_group, contact_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            int id = Integer.parseInt(idField.getText());
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstNameField.getText());
            preparedStatement.setString(3, lastNameField.getText());
            preparedStatement.setString(4, cityField.getText());
            preparedStatement.setString(5, dobField.getText());
            preparedStatement.setString(6, bloodGroupField.getText());
            preparedStatement.setString(7, contactNumberField.getText());

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Student added successfully!");
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding student: " + e.getMessage());
        }
    }

    private void clearFields() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        cityField.setText("");
        dobField.setText("");
        bloodGroupField.setText("");
        contactNumberField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new add());
    }
}
