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

public class search extends JFrame implements ActionListener {
    private JTextField idField;
    private JButton searchButton;
    private JTextArea resultTextArea;
    private Connection connection;

    public search() {
        setTitle("Search Student by ID");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JLabel idLabel = new JLabel("Student ID:");
        idField = new JTextField(10);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(searchButton);

        resultTextArea = new JTextArea(10, 40);
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);


        try {
            String jdbcUrl = "jdbc:mysql://localhost:3306/cs5";
            String username = "root";
            String password = "Anish@230403";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchStudentByID();
        }
    }

    private void searchStudentByID() {
        String studentID = idField.getText();

        try {
            String query = "SELECT * FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(studentID));

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                resultTextArea.setText("Student ID: " + resultSet.getInt("id") + "\n"
                        + "First Name: " + resultSet.getString("first_name") + "\n"
                        + "Last Name: " + resultSet.getString("last_name") + "\n"
                        + "City: " + resultSet.getString("city") + "\n"
                        + "Date of Birth: " + resultSet.getString("dob") + "\n"
                        + "Blood Group: " + resultSet.getString("blood_group") + "\n"
                        + "Contact Number: " + resultSet.getString("contact_number"));
            } else {
                resultTextArea.setText("No student with the provided ID found.");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new search().setVisible(true);
        });
    }
}
