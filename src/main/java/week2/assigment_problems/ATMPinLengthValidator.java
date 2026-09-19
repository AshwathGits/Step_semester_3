package week2.assigment_problems;

public class ATMPinLengthValidator {

    public static void checkPinLength(String pin) {

        int length = pin.length();

        if (length != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {

        checkPinLength("482");
        checkPinLength("4820");
    }
}