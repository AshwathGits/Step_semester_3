package inheritance.class_problems;

public class LibraryMember {

    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public final String memberNumber;

    private static int memberCounter = 100;

    private int[] fineHistory = new int[10];
    private int fineCount = 0;
    private int totalFine = 0;

    // Constructor used in Problems 1–4
    public LibraryMember(String memberId, int borrowLimit) {

        if (memberId == null || memberId.trim().isEmpty()
                || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        memberCounter++;
        this.memberNumber = "LIB-" + memberCounter;
    }

    // Constructor required in Problem 5
    public LibraryMember(int borrowLimit) {

        this.memberId = "LIB" + (memberCounter + 1);
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;

        memberCounter++;
        this.memberNumber = "LIB-" + memberCounter;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.println(
                "General Member | Books Borrowed: " + booksBorrowed
        );
    }

    protected void chargeFine(int amount) {

        if (fineCount < fineHistory.length) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }

        totalFine += amount;
    }

    public int[] getFineHistory() {
        int[] copy = new int[fineCount];

        for (int i = 0; i < fineCount; i++) {
            copy[i] = fineHistory[i];
        }

        return copy;
    }

    public int getTotalFine() {
        return totalFine;
    }

    public static String enrollBatch(String[] memberIds, int borrowLimit) {

        int enrolled = 0;
        int rejected = 0;

        for (String memberId : memberIds) {
            try {
                new LibraryMember(memberId, borrowLimit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    public static int getMembersEnrolled() {
        return memberCounter - 100;
    }

    public static boolean isValidRenewalCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        char first = code.charAt(0);

        if (!Character.isUpperCase(first)) {
            return false;
        }

        if (!Character.isDigit(code.charAt(1))) {
            return false;
        }

        if (!Character.isDigit(code.charAt(2))) {
            return false;
        }

        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }

        return true;
    }

    public static String processNightlyAudit(LibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {

            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + faculty + " faculty | "
                + regular + " regular";
    }
}