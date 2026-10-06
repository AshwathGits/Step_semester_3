package abstraction.class_problems;

abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class Contractor extends Employee {

    public Contractor(String name) {
        super(name);
    }

    @Override
    public boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

class LeaveRequest {

    private Employee employee;
    private String startDate;
    private String endDate;
    private int days;

    private String status;

    public LeaveRequest(
            Employee employee,
            String startDate,
            String endDate,
            int days) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.status = "Pending";
    }

    public void approve() {

        if (!status.equals("Pending")) {
            System.out.println(
                    "Cannot approve request. Current status: "
                            + status
            );
            return;
        }

        if (!employee.canTakeLeave(days)) {
            System.out.println(
                    "Leave policy does not allow "
                            + days + " days."
            );
            return;
        }

        status = "Approved";

        System.out.println(
                "Leave request approved for "
                        + employee.getName()
                        + " (" + startDate
                        + "-" + endDate + ")"
        );
    }

    public void reject() {

        if (!status.equals("Pending")) {
            System.out.println(
                    "Cannot reject request. Current status: "
                            + status
            );
            return;
        }

        status = "Rejected";

        System.out.println(
                "Leave request rejected for "
                        + employee.getName()
        );
    }

    public void submit() {
        System.out.println(
                "Leave request submitted for "
                        + employee.getName()
                        + " (" + startDate
                        + "-" + endDate + ")"
                        + ". Status: " + status
        );
    }

    public void setPending() {
        System.out.println(
                "Cannot change status from "
                        + status + " to Pending."
        );
    }

    public String getStatus() {
        return status;
    }
}

public class EmployeeLeaveRequestWorkflow {

    public static void main(String[] args) {

        Employee john =
                new FullTimeEmployee("John");

        Employee jane =
                new PartTimeEmployee("Jane");

        LeaveRequest johnRequest =
                new LeaveRequest(
                        john,
                        "Jan 1",
                        "Jan 5",
                        5
                );

        LeaveRequest janeRequest =
                new LeaveRequest(
                        jane,
                        "Feb 10",
                        "Feb 11",
                        2
                );

        johnRequest.submit();
        johnRequest.approve();

        janeRequest.submit();
        janeRequest.reject();

        // Attempt to change approved request
        johnRequest.setPending();
    }
}