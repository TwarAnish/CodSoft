package com.anish.codsoft3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckBalanceApp extends JFrame {
    private JTextField accountNumberField;
    private JButton checkBalanceButton;

    public CheckBalanceApp() {
        setTitle("Check Balance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberField = new JTextField();
        checkBalanceButton = new JButton("Check Balance");

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        panel.add(accountNumberLabel);
        panel.add(accountNumberField);
        panel.add(new JLabel()); // Placeholder
        panel.add(checkBalanceButton);

        add(panel);
    }

    private void checkBalance() {
        String accountNumber = accountNumberField.getText();

        if (accountNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an account number.");
            return;
        }

        // Connect to your database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3", "root", "Anish@230403")) {
            String query = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                JOptionPane.showMessageDialog(this, "Account Balance: Rs." + balance);
            } else {
                JOptionPane.showMessageDialog(this, "Account not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CheckBalanceApp().setVisible(true);
            }
        });
    }
}
