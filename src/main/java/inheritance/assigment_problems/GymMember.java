package inheritance.assigment_problems;

public class GymMember {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public final String membershipNumber;

    private static int memberCounter = 2000;

    private int[] lateFeeHistory = new int[10];
    private int lateFeeCount = 0;
    private int totalLateFees = 0;

    // Constructor for Problems 1-4
    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null || memberId.trim().isEmpty()
                || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;

        memberCounter++;
        this.membershipNumber = "GYM-" + memberCounter;
    }

    // Constructor for Problem 5
    public GymMember(int monthlyFee) {

        this.memberId = "GYM" + (memberCounter + 1);
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;

        memberCounter++;
        this.membershipNumber = "GYM-" + memberCounter;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println(
                "Standard Member | Sessions: " + sessionsAttended
        );
    }

    protected void chargeLateFee(int amount) {

        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }

        totalLateFees += amount;
    }

    public int[] getLateFeeHistory() {

        int[] copy = new int[lateFeeCount];

        for (int i = 0; i < lateFeeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }

    public int getTotalLateFees() {
        return totalLateFees;
    }

    public static String signUpBatch(
            String[] memberIds,
            int monthlyFee) {

        int signedUp = 0;
        int rejected = 0;

        for (String memberId : memberIds) {

            try {
                new GymMember(memberId, monthlyFee);
                signedUp++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp
                + " | Rejected: " + rejected;
    }

    public static int getMembersEnrolled() {
        return memberCounter - 2000;
    }

    public static boolean isValidReferralCode(String code) {

        if (code == null || code.length() != 4) {
            return false;
        }

        if (code.charAt(0) != 'G') {
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
}