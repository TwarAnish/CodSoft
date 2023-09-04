//Without GUI

package com.anish.codsoft2;
import java.util.*;
public class try1 {
    public static void main(String[] args){
        Scanner S1 = new Scanner(System.in);
        int[] sub = new int[5];
        String[] grades = new String[5];
        int totalMarks = 0;
        
        for(int i = 0; i < 5; i++){
            System.out.println("Enter marks for subject "+ (i +1) + ": ");
            sub[i] = S1.nextInt();
            
            grades[i] = calculateGrade(sub[i]);
            totalMarks += sub[i];
        }
        
        for (int i = 0; i < 5; i++){
            System.out.println("Subject " + (i + 1) + ": Marks: " + sub[i] + ", Grade: " + grades[i]);
        }
        
        double percentage = (double)totalMarks / (5 * 100) * 100;
        System.out.println("Total Marks Obtained: " + totalMarks);
        System.out.println("Percentage: " + percentage + "%");
    }
    
    public static String calculateGrade(int marks){
        if (marks >= 95){
            return "O";
        }else if (marks >= 90){
            return "A+";
        }else if (marks >= 80){
            return "A";
        }else if (marks >= 70){
            return "B";
        }else if (marks >= 60){
            return "C";
        }else if (marks >= 50){
            return "D";
        }else{
            return "F";
        }
    }
}
