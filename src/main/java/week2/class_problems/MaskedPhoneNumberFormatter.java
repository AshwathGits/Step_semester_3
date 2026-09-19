package week2.class_problems;

public class MaskedPhoneNumberFormatter {

    public static String maskPhoneNumber(String phone) {

        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {

            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        StringBuilder result = new StringBuilder();

        result.append("XXXXXX");
        result.append("-");
        result.append(phone.substring(6));

        return result.toString();
    }

    public static void main(String[] args) {

        System.out.println(
                maskPhoneNumber("9876543210"));

        System.out.println(
                maskPhoneNumber("98765"));
    }
}