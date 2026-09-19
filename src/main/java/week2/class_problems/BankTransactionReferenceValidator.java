package week2.class_problems;

public class BankTransactionReferenceValidator {

    public static String normalizeReference(String raw) {

        String reference = raw.trim();

        if (reference.length() < 3) {
            return reference.toUpperCase();
        }

        String bankCode = reference.substring(0, 3).toUpperCase();
        String remaining = reference.substring(3);

        return bankCode + remaining;
    }

    public static String validateAndFormat(String reference) {

        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        String bankCode = reference.substring(0, 3);

        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(bankCode.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        String body = reference.substring(3);

        for (int i = 0; i < body.length(); i++) {

            if (!Character.isDigit(body.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        String date = reference.substring(3, 9);
        String sequence = reference.substring(9);

        StringBuilder result = new StringBuilder();

        result.append("[")
              .append(bankCode)
              .append("] DATE: ")
              .append(date.substring(0, 2))
              .append("/")
              .append(date.substring(2, 4))
              .append("/")
              .append(date.substring(4, 6))
              .append(" | SEQ: ")
              .append(sequence);

        return result.toString();
    }

    public static void main(String[] args) {

        String raw = " hdf03022600042 ";

        String normalized = normalizeReference(raw);

        System.out.println(validateAndFormat(normalized));

        String invalid = normalizeReference("12F03022600042");

        System.out.println(validateAndFormat(invalid));
    }
}