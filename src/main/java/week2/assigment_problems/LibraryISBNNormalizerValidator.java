package week2.assigment_problems;

public class LibraryISBNNormalizerValidator {

    public static String normalizeCode(String raw) {

        String code = raw.trim();

        if (code.length() < 3) {
            return code.toUpperCase();
        }

        String publisherCode = code.substring(0, 3).toUpperCase();
        String remaining = code.substring(3);

        return publisherCode + remaining;
    }

    public static String validateAndFormat(String code) {

        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        String publisherCode = code.substring(0, 3);

        for (int i = 0; i < publisherCode.length(); i++) {

            if (!Character.isLetter(publisherCode.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        String remaining = code.substring(3);

        for (int i = 0; i < remaining.length(); i++) {

            if (!Character.isDigit(remaining.charAt(i))) {
                return "Invalid: year/catalog must contain only digits";
            }
        }

        String year = code.substring(3, 7);
        String catalog = code.substring(7);

        StringBuilder result = new StringBuilder();

        result.append("[")
              .append(publisherCode)
              .append("] YEAR: ")
              .append(year)
              .append(" | CATALOG: ")
              .append(catalog);

        return result.toString();
    }

    public static void main(String[] args) {

        String raw = " pen2026004251 ";

        String normalized = normalizeCode(raw);

        System.out.println(validateAndFormat(normalized));

        String invalid = normalizeCode("12N2026004251");

        System.out.println(validateAndFormat(invalid));
    }
}