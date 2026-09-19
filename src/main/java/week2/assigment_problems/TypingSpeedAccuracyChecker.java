package week1.assigment_problems;

import java.util.Scanner;

public class TypingSpeedAccuracyChecker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the original text: ");
        String originalText = sc.nextLine();

        System.out.print("Enter the typed text: ");
        String typedText = sc.nextLine();

        int correctCharacters = 0;

        int length = Math.min(originalText.length(), typedText.length());

        for (int i = 0; i < length; i++) {
            if (originalText.charAt(i) == typedText.charAt(i)) {
                correctCharacters++;
            }
        }

        double accuracy = 0;

        if (originalText.length() > 0) {
            accuracy = (double) correctCharacters / originalText.length() * 100;
        }

        System.out.println("\n--- Typing Accuracy Result ---");
        System.out.println("Original Text: " + originalText);
        System.out.println("Typed Text: " + typedText);
        System.out.println("Correct Characters: " + correctCharacters);
        System.out.printf("Accuracy: %.2f%%\n", accuracy);

        sc.close();
    }
}