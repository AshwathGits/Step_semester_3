package abstraction.assigment_problems;

import java.util.ArrayList;
import java.util.List;

public class CampusNoticeBroadcaster {

    interface NotificationChannel {
        String send(Student student, Notice notice);
        String getName();
    }

    static class EmailChannel implements NotificationChannel {

        public String send(Student student, Notice notice) {

            return "[Email → "
                    + student.getName()
                    + "] "
                    + notice.getTitle()
                    + ": "
                    + notice.getMessage();
        }

        public String getName() {
            return "Email";
        }
    }

    static class SmsChannel implements NotificationChannel {

        public String send(Student student, Notice notice) {

            return "[SMS → "
                    + student.getName()
                    + "] "
                    + notice.getTitle()
                    + ": "
                    + notice.getMessage();
        }

        public String getName() {
            return "SMS";
        }
    }

    static class AppChannel implements NotificationChannel {

        public String send(Student student, Notice notice) {

            return "[App → "
                    + student.getName()
                    + "] "
                    + notice.getTitle()
                    + ": "
                    + notice.getMessage();
        }

        public String getName() {
            return "App";
        }
    }

    static class Student {

        private final String name;
        private final String department;

        private final List<NotificationChannel> preferredChannels;

        public Student(String name,
                       String department) {

            this.name = name;
            this.department = department;
            this.preferredChannels = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public void addPreferredChannel(
                NotificationChannel channel) {

            preferredChannels.add(channel);
        }

        public List<NotificationChannel> getPreferredChannels() {
            return preferredChannels;
        }
    }

    static class Notice {

        private final String title;
        private final String message;
        private final List<String> targetDepartments;

        public Notice(String title,
                      String message,
                      List<String> targetDepartments) {

            this.title = title;
            this.message = message;
            this.targetDepartments =
                    new ArrayList<>(targetDepartments);
        }

        public String getTitle() {
            return title;
        }

        public String getMessage() {
            return message;
        }

        public List<String> getTargetDepartments() {
            return targetDepartments;
        }
    }

    static class NoticeBoard {

        private final List<Student> students;

        public NoticeBoard() {
            students = new ArrayList<>();
        }

        public void addStudent(Student student) {
            students.add(student);
        }

        public String postNotice(Notice notice) {

            if (notice.getTitle() == null
                    || notice.getTitle().trim().isEmpty()) {

                return "Cannot post notice: title is required.";
            }

            if (notice.getTargetDepartments().isEmpty()) {

                return "Cannot post notice: at least one target department is required.";
            }

            StringBuilder result = new StringBuilder();

            result.append("Notice '")
                    .append(notice.getTitle())
                    .append("' posted to ")
                    .append(String.join(
                            " and ",
                            notice.getTargetDepartments()
                    ))
                    .append(".\n");

            for (Student student : students) {

                if (!notice.getTargetDepartments()
                        .contains(student.getDepartment())) {
                    continue;
                }

                for (NotificationChannel channel
                        : student.getPreferredChannels()) {

                    result.append(
                            channel.send(student, notice)
                    ).append("\n");
                }
            }

            return result.toString();
        }
    }

    public static void main(String[] args) {

        Student asha =
                new Student("Asha", "CSE");

        Student ravi =
                new Student("Ravi", "ECE");

        asha.addPreferredChannel(
                new EmailChannel()
        );

        asha.addPreferredChannel(
                new AppChannel()
        );

        ravi.addPreferredChannel(
                new SmsChannel()
        );

        NoticeBoard board = new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        List<String> departments =
                new ArrayList<>();

        departments.add("CSE");
        departments.add("ECE");

        Notice notice = new Notice(
                "Lab Closed Tomorrow",
                "Lab Closed Tomorrow",
                departments
        );

        System.out.println(
                board.postNotice(notice)
        );

        Notice invalidNotice = new Notice(
                "Sports Day",
                "Sports Day",
                new ArrayList<>()
        );

        System.out.println(
                board.postNotice(invalidNotice)
        );
    }
}