//With GUI

package com.anish.codsoft1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CodSoft1 extends JFrame {
    private int randomNumber;
    private int numberOfGuesses;
    private JTextField guessField;
    private JLabel resultLabel;

    public CodSoft1() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        randomNumber = generateRandomNumber();
        numberOfGuesses = 0;

        JLabel titleLabel = new JLabel("Welcome to Number Guessing Game");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout());
        guessField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        resultLabel = new JLabel("Take a guess!");
        
        guessButton.addActionListener(new GuessButtonListener());

        centerPanel.add(new JLabel("Enter your guess:"));
        centerPanel.add(guessField);
        centerPanel.add(guessButton);
        add(centerPanel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                numberOfGuesses++;

                if (guess < randomNumber) {
                    resultLabel.setText("Try a higher number.");
                } else if (guess > randomNumber) {
                    resultLabel.setText("Try a lower number.");
                } else {
                    resultLabel.setText("Congratulations! You guessed the number in " + numberOfGuesses + " guesses.");
                    guessField.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CodSoft1::new);
    }
}
