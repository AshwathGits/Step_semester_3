package week3.assigment_problems;

public class MembershipCard {

    private static String libraryName;
    private static String validUntil;

    private String studentName;

    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";

        System.out.println("Library info loaded");
    }

    public MembershipCard(String studentName) {
        this.studentName = studentName;
    }

    public void printCard() {
        System.out.println(
                "Membership card issued: " + studentName
        );
    }

    public static void main(String[] args) {

        String[] names = {
            "Ananya",
            "Rohan",
            "Priya",
            "Arjun",
            
            "Sneha"
        };

        MembershipCard[] cards =
                new MembershipCard[names.length];

        for (int i = 0; i < cards.length; i++) {

            cards[i] = new MembershipCard(names[i]);
            cards[i].printCard();
        }
    }
}