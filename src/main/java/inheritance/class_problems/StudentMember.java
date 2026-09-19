package inheritance.class_problems;

public class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public void borrowBook() {
        super.borrowBook();
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Student Member | Course: " + course
                        + " | Books Borrowed: " + booksBorrowed
        );
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}