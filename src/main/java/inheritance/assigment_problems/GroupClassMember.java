package inheritance.assigment_problems;

public class GroupClassMember extends GymMember {

    private String className;

    public GroupClassMember(
            String memberId,
            int monthlyFee,
            String className) {

        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                "Group Class Member | Class: " + className
                        + " | Sessions: " + sessionsAttended
        );
    }
}