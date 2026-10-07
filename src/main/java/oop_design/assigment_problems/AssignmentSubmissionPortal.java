package abstraction.assigment_problems;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AssignmentSubmissionPortal {

    enum SubmissionStatus {
        SUBMITTED,
        GRADED
    }

    interface AssignmentType {
        double calculateFinalMarks(double marks, long lateDays);

        String getName();
    }

    static class CodingAssignment implements AssignmentType {

        public double calculateFinalMarks(double marks, long lateDays) {
            double penalty = 0.10 * lateDays;
            return Math.max(0, marks * (1 - penalty));
        }

        public String getName() {
            return "Coding";
        }
    }

    static class WrittenAssignment implements AssignmentType {

        public double calculateFinalMarks(double marks, long lateDays) {
            double penalty = 0.20 * lateDays;
            return Math.max(0, marks * (1 - penalty));
        }

        public String getName() {
            return "Written";
        }
    }

    static class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Assignment {
        private final String title;
        private final double maxMarks;
        private final LocalDate dueDate;
        private final AssignmentType type;

        public Assignment(String title,
                          double maxMarks,
                          LocalDate dueDate,
                          AssignmentType type) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDate = dueDate;
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public double getMaxMarks() {
            return maxMarks;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public AssignmentType getType() {
            return type;
        }
    }

    static class Submission {
        private final Student student;
        private final Assignment assignment;
        private final LocalDate submissionDate;

        private SubmissionStatus status;
        private double finalMarks;

        public Submission(Student student,
                          Assignment assignment,
                          LocalDate submissionDate) {

            this.student = student;
            this.assignment = assignment;
            this.submissionDate = submissionDate;
            this.status = SubmissionStatus.SUBMITTED;
        }

        public String submit() {

            long lateDays = 0;

            if (submissionDate.isAfter(assignment.getDueDate())) {
                lateDays = ChronoUnit.DAYS.between(
                        assignment.getDueDate(),
                        submissionDate
                );
            }

            return student.getName()
                    + "'s submission for '"
                    + assignment.getTitle()
                    + "' received ("
                    + (lateDays == 0
                    ? "on time"
                    : lateDays + " days late")
                    + "). Status: "
                    + status;
        }

        public String grade(double marks) {

            if (status == SubmissionStatus.GRADED) {
                return "Cannot grade again.";
            }

            long lateDays = 0;

            if (submissionDate.isAfter(assignment.getDueDate())) {
                lateDays = ChronoUnit.DAYS.between(
                        assignment.getDueDate(),
                        submissionDate
                );
            }

            finalMarks = assignment.getType()
                    .calculateFinalMarks(marks, lateDays);

            status = SubmissionStatus.GRADED;

            return student.getName()
                    + " graded: "
                    + format(finalMarks)
                    + "/"
                    + format(assignment.getMaxMarks())
                    + " after "
                    + (lateDays == 0
                    ? "0%"
                    : (assignment.getType() instanceof CodingAssignment
                    ? (lateDays * 10)
                    : (lateDays * 20)) + "%")
                    + " late penalty. Status: "
                    + status;
        }

        public String resubmit() {

            if (status == SubmissionStatus.GRADED) {
                return "Cannot resubmit: '"
                        + assignment.getTitle()
                        + "' has already been graded.";
            }

            return "Resubmission allowed.";
        }

        private String format(double value) {
            if (value == (int) value) {
                return String.valueOf((int) value);
            }

            return String.format("%.2f", value);
        }
    }

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new Assignment(
                "Linked List Lab",
                50,
                LocalDate.of(2026, 3, 10),
                new CodingAssignment()
        );

        Assignment written = new Assignment(
                "Design Essay",
                50,
                LocalDate.of(2026, 3, 12),
                new WrittenAssignment()
        );

        Submission ashaSubmission = new Submission(
                asha,
                coding,
                LocalDate.of(2026, 3, 10)
        );

        Submission raviSubmission = new Submission(
                ravi,
                written,
                LocalDate.of(2026, 3, 14)
        );

        System.out.println(ashaSubmission.submit());
        System.out.println(raviSubmission.submit());

        System.out.println(ashaSubmission.grade(45));
        System.out.println(raviSubmission.grade(40));

        System.out.println(ashaSubmission.resubmit());
    }
}