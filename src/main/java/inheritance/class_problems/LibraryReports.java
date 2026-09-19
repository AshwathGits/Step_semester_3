package inheritance.class_problems;

public class LibraryReports {

    public static String classifyGeneration(LibraryMember member) {

        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }

        if (member instanceof StudentMember) {
            return "Direct student subclass";
        }

        return "Base member";
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {

        int total = 0;

        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }

        return total;
    }

    public static String batchPrint(LibraryMember[] members) {

        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {

            report.append(member.getClass().getSimpleName())
                  .append(" | ");

            if (member instanceof StudentMember) {

                StudentMember student = (StudentMember) member;

                report.append(
                        "Course via downcast: "
                                .append(student.getCourse())
                );
            }

            report.append("\n");
        }

        return report.toString();
    }
}