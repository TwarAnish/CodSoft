package com.anish.codsoft1;
import java.util.Random;
import java.util.Scanner;

public class trial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int randomNumber = random.nextInt(100) + 1;
        int numberOfGuesses = 0;

        System.out.println("Welcome to Number Guessing Game");

        while (true) {
            System.out.print("Enter your guess: ");
            try {
                int guess = scanner.nextInt();
                numberOfGuesses++;

                if (guess < randomNumber) {
                    System.out.println("Try a higher number.");
                } else if (guess > randomNumber) {
                    System.out.println("Try a lower number.");
                } else {
                    System.out.println("Congratulations! You guessed the number in " + numberOfGuesses + " guesses.");
                    break;
                }
            } catch (java.util.InputMismatchException ex) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        scanner.close();
    }
}
