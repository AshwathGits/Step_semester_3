package access_modifiers.class_problems;

import java.util.Arrays;

public class LoanReceipt {

    private final String memberId;
    private final String[] books;

    public LoanReceipt(String memberId, String[] books) {

        this.memberId = memberId;
        this.books = Arrays.copyOf(books, books.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(books, books.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {

        String[] updatedBooks = Arrays.copyOf(books, books.length);

        if (index >= 0 && index < updatedBooks.length) {
            updatedBooks[index] = newId;
        }

        return new LoanReceipt(memberId, updatedBooks);
    }

    public static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + referenceOnly
                + " reference-only | "
                + regular
                + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt r =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]{"BK-100", "BK-101"}
                );

        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt corrected =
                r.withCorrectedBookId(1, "BK-102");

        System.out.println(Arrays.toString(r.getBookIds()));
        System.out.println(Arrays.toString(corrected.getBookIds()));
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] books,
            String roomNumber) {

        super(memberId, books);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}