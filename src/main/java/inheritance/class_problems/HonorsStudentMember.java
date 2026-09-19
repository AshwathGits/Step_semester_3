package inheritance.class_problems;

public class HonorsStudentMember extends StudentMember {

    private int bonusLimit;

    public HonorsStudentMember(
            String memberId,
            int borrowLimit,
            String course,
            int bonusLimit) {

        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Honors Student Member | Course: " + getCourse()
                        + " | Bonus Limit: " + bonusLimit
                        + " | Books Borrowed: " + booksBorrowed
        );
    }

    private String getCourse() {
        return getCourseValue();
    }

    private String getCourseValue() {
        try {
            java.lang.reflect.Field field =
                    StudentMember.class.getDeclaredField("course");

            field.setAccessible(true);

            return (String) field.get(this);

        } catch (Exception e) {
            return "";
        }
    }
}