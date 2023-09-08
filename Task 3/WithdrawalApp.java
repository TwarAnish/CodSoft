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

public class WithdrawalApp extends JFrame {
    private JTextField accountNumberField;
    private JTextField amountField;
    private JButton withdrawButton;

    public WithdrawalApp() {
        setTitle("Withdraw Money");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberField = new JTextField();
        JLabel amountLabel = new JLabel("Amount to Withdraw:");
        amountField = new JTextField();
        withdrawButton = new JButton("Withdraw");

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawMoney();
            }
        });

        panel.add(accountNumberLabel);
        panel.add(accountNumberField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(new JLabel()); // Placeholder
        panel.add(withdrawButton);

        add(panel);
    }

    private void withdrawMoney() {
        String accountNumber = accountNumberField.getText();
        String amountStr = amountField.getText();

        if (accountNumber.isEmpty() || amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both fields.");
            return;
        }

        double amount = Double.parseDouble(amountStr);

        // Connect to your database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3", "root", "Anish@230403")) {
            String query = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");

                if (amount <= balance) {
                    // Withdraw money
                    balance -= amount;
                    query = "UPDATE accounts SET balance = ? WHERE account_number = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setDouble(1, balance);
                    preparedStatement.setString(2, accountNumber);
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Withdrawal successful. New balance: Rs." + balance);
                } else {
                    JOptionPane.showMessageDialog(this, "Not Enough Funds.");
                }
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
                new WithdrawalApp().setVisible(true);
            }
        });
    }
}
