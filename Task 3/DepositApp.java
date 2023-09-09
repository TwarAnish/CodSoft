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

public class DepositApp extends JFrame {
    private JTextField accountNumberField;
    private JTextField amountField;
    private JButton depositButton;

    public DepositApp() {
        setTitle("Deposit Money");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.setBackground(new Color(118, 137, 222));

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberField = new JTextField();
        JLabel amountLabel = new JLabel("Amount to Deposit:");
        amountField = new JTextField();
        depositButton = new JButton("Deposit");

        //Adding Colors
        accountNumberField.setBackground(new Color(169, 220, 227));
        amountField.setBackground(new Color(169, 220, 227));
        depositButton.setBackground(new Color(169, 220, 227));

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositMoney();
            }
        });

        panel.add(accountNumberLabel);
        panel.add(accountNumberField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(new JLabel()); // Placeholder
        panel.add(depositButton);

        add(panel);
    }

    private void depositMoney() {
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
                balance += amount;

                query = "UPDATE accounts SET balance = ? WHERE account_number = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDouble(1, balance);
                preparedStatement.setString(2, accountNumber);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Deposit successful. New balance: Rs." + balance);
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
                new DepositApp().setVisible(true);
            }
        });
    }
}
