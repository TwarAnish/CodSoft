package com.anish.codsoft3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankAccountGUI extends JFrame implements ActionListener {

    private JTextField accountNumberField;
    private JTextField accountHolderField;
    private JTextField amountField;
    private JButton createAccountButton;

    public BankAccountGUI() {
        setTitle("Create Bank Account");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel accountNumberLabel = new JLabel("Account Number:");
        JLabel accountHolderLabel = new JLabel("Account Holder:");
        JLabel amountLabel = new JLabel("Initial Amount:");

        accountNumberField = new JTextField();
        accountHolderField = new JTextField();
        amountField = new JTextField();

        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(this);

        add(accountNumberLabel);
        add(accountNumberField);
        add(accountHolderLabel);
        add(accountHolderField);
        add(amountLabel);
        add(amountField);
        add(new JLabel()); // Empty space
        add(createAccountButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createAccountButton) {
            createBankAccount();
        }
    }

    private void createBankAccount() {
        String accountNumber = accountNumberField.getText();
        String accountHolder = accountHolderField.getText();
        double amount = Double.parseDouble(amountField.getText());

        // JDBC database connection code
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs3", "root", "Anish@230403");
            String sql = "INSERT INTO accounts (account_number, account_holder_name, balance) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountNumber);
            pstmt.setString(2, accountHolder);
            pstmt.setDouble(3, amount);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            JOptionPane.showMessageDialog(this, "Bank account created successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error creating bank account.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankAccountGUI());
    }
}
