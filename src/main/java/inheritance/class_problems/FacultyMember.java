package inheritance.class_problems;

public class FacultyMember extends LibraryMember {

    private String department;

    // Problem 2 constructor
    public FacultyMember(
            String memberId,
            int borrowLimit,
            String department) {

        super(memberId, borrowLimit);
        this.department = department;
    }

    // Problem 5 constructor
    public FacultyMember(
            int borrowLimit,
            String department) {

        super(borrowLimit);
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Faculty Member | Department: " + department
                        + " | Books Borrowed: " + booksBorrowed
        );
    }
}