package week1.class_problems;

public class ReverseCustomerName {

    public static String reverseCustomerName(String customerName) {

        String reversedName = "";

        for (int i = customerName.length() - 1; i >= 0; i--) {
            reversedName += customerName.charAt(i);
        }

        return reversedName;
    }

    public static void main(String[] args) {

        String customerName = "Sunil";

        String reversedName = reverseCustomerName(customerName);

        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversedName);
    }
}
