package com.anish.codsoft5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CodSoft5 extends JFrame implements ActionListener {
    private JButton addStudentButton, removeStudentButton, updateStudentButton, searchStudentButton, exitButton;

    public CodSoft5() {
        setTitle("Student Management Menu");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        addStudentButton = new JButton("Add Student");
        removeStudentButton = new JButton("Remove Student");
        updateStudentButton = new JButton("Update Student");
        searchStudentButton = new JButton("Search Student");
        exitButton = new JButton("Exit");

        addStudentButton.addActionListener(this);
        removeStudentButton.addActionListener(this);
        updateStudentButton.addActionListener(this);
        searchStudentButton.addActionListener(this);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the exit button action (e.g., confirm and exit)
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    dispose(); // Close the main window
                    System.exit(0); // Terminate the application
                }
            }
        });

        add(addStudentButton);
        add(removeStudentButton);
        add(updateStudentButton);
        add(searchStudentButton);
        add(exitButton);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudentButton) {
            openAddStudentProgram();
        } else if (e.getSource() == removeStudentButton) {
            openRemoveStudentProgram();
        } else if (e.getSource() == updateStudentButton) {
            openUpdateStudentProgram();
        } else if (e.getSource() == searchStudentButton) {
            openSearchStudentProgram();
        }
    }

    private void openAddStudentProgram() {
        SwingUtilities.invokeLater(() -> {
            new add().setVisible(true);
        });
    }

    private void openRemoveStudentProgram() {
        SwingUtilities.invokeLater(() -> {
            new remove().setVisible(true);
        });
    }

    private void openUpdateStudentProgram() {
        SwingUtilities.invokeLater(() -> {
            new update().setVisible(true);
        });
    }

    private void openSearchStudentProgram() {
        SwingUtilities.invokeLater(() -> {
            new search().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CodSoft5().setVisible(true);
        });
    }
}
