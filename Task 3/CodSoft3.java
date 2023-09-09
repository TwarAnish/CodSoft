/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.anish.codsoft3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodSoft3 extends JFrame {
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton checkBalanceButton;
    private JButton addAccountButton;

    public CodSoft3() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        depositButton = new JButton("Deposit Money");
        withdrawButton = new JButton("Withdraw Money");
        checkBalanceButton = new JButton("Check Balance");
        addAccountButton = new JButton("Add Bank Account");

        //Set Colors
        depositButton.setBackground(new Color(169, 220, 227));
        withdrawButton.setBackground(new Color(118, 137, 222));
        checkBalanceButton.setBackground(new Color(169, 220, 227));
        addAccountButton.setBackground(new Color(118, 137, 222));

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDepositApp();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWithdrawalApp();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCheckBalanceApp();
            }
        });

        addAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBankAccountApp();
            }
        });

        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(checkBalanceButton);
        panel.add(addAccountButton);

        add(panel);
    }

    private void openDepositApp() {
        DepositApp depositApp = new DepositApp();
        depositApp.setVisible(true);
    }

    private void openWithdrawalApp() {
        WithdrawalApp withdrawalApp = new WithdrawalApp();
        withdrawalApp.setVisible(true);
    }

    private void openCheckBalanceApp() {
        CheckBalanceApp checkBalanceApp = new CheckBalanceApp();
        checkBalanceApp.setVisible(true);
    }

    private void openBankAccountApp() {
        BankAccountGUI bankAccountGUI = new BankAccountGUI();
        bankAccountGUI.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CodSoft3().setVisible(true);
            }
        });
    }
}
