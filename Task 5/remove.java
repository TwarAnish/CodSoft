package com.anish.codsoft5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class remove extends JFrame {
    private JTextField idField;

    public remove() {
        setTitle("Remove Student");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("Enter ID to Remove:");
        idField = new JTextField();
        JButton removeButton = new JButton("Remove Student");

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeStudentFromDatabase();
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(removeButton);

        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void removeStudentFromDatabase() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/cs5";
        String username = "root";
        String password = "Anish@230403";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String idText = idField.getText();
            int id = Integer.parseInt(idText);

            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student removed successfully!");
                idField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Student with ID " + id + " not found.");
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error removing student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new remove());
    }
}
