package com.anish.codsoft5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class update extends JFrame {
    private JTextField idField, firstNameField, lastNameField, cityField, dobField, bloodGroupField, contactNumberField;
    private JButton fetchButton, updateButton;
    private Connection connection;

    public update() {
        initializeUI();
        connectToDatabase();
    }

    private void initializeUI() {
        setTitle("Student Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left

        gbc.insets = new Insets(5, 5, 5, 5);

        // ID Label and Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        idField = new JTextField(10);
        mainPanel.add(idField, gbc);

        // First Name Label and Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("First Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        firstNameField = new JTextField(10);
        mainPanel.add(firstNameField, gbc);

        // Last Name Label and Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Last Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        lastNameField = new JTextField(10);
        mainPanel.add(lastNameField, gbc);

        // City Label and Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("City:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        cityField = new JTextField(10);
        mainPanel.add(cityField, gbc);

        // Date of Birth Label and Field
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Date of Birth:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        dobField = new JTextField(10);
        mainPanel.add(dobField, gbc);

        // Blood Group Label and Field
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Blood Group:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        bloodGroupField = new JTextField(10);
        mainPanel.add(bloodGroupField, gbc);

        // Contact Number Label and Field
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(new JLabel("Contact Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        contactNumberField = new JTextField(10);
        mainPanel.add(contactNumberField, gbc);

        // Fetch Button
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2; // Span two rows
        fetchButton = new JButton("Fetch");
        mainPanel.add(fetchButton, gbc);

        // Update Button
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 2; // Span two rows
        updateButton = new JButton("Update");
        mainPanel.add(updateButton, gbc);

        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fetchStudentInformation();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudentInformation();
            }
        });

        add(mainPanel);
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/cs5";
            String username = "root";
            String password = "Anish@230403";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchStudentInformation() {
        int studentId = Integer.parseInt(idField.getText());
        try {
            String query = "SELECT * FROM students WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                firstNameField.setText(resultSet.getString("first_name"));
                lastNameField.setText(resultSet.getString("last_name"));
                cityField.setText(resultSet.getString("city"));
                dobField.setText(resultSet.getString("dob"));
                bloodGroupField.setText(resultSet.getString("blood_group"));
                contactNumberField.setText(resultSet.getString("contact_number"));
            } else {
                JOptionPane.showMessageDialog(null, "Student not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateStudentInformation() {
        int studentId = Integer.parseInt(idField.getText());
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String city = cityField.getText();
        String dob = dobField.getText();
        String bloodGroup = bloodGroupField.getText();
        String contactNumber = contactNumberField.getText();

        try {
            String query = "UPDATE students SET first_name=?, last_name=?, city=?, dob=?, blood_group=?, contact_number=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, dob);
            preparedStatement.setString(5, bloodGroup);
            preparedStatement.setString(6, contactNumber);
            preparedStatement.setInt(7, studentId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Student information updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update student information");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            update gui = new update();
            gui.setVisible(true);
        });
    }
}
