package week1.class_problems;

import java.util.HashMap;

public class FirstNonRepeatingCharacter {

    public static char findFirstNonRepeatingChar(String text) {

        HashMap<Character, Integer> frequency = new HashMap<>();

        // Count the frequency of each character
        for (char ch : text.toCharArray()) {
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }

        // Find the first character whose frequency is 1
        for (char ch : text.toCharArray()) {
            if (frequency.get(ch) == 1) {
                return ch;
            }
        }

        return '\0';
    }

    public static void main(String[] args) {

        String text = "swiss";

        char result = findFirstNonRepeatingChar(text);

        if (result != '\0') {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }
    }
}
