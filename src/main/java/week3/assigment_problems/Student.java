package week3.assigment_problems;

public class Student {

    private static String collegeName;
    private static String academicYear;

    private String name;

    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026";
        System.out.println("College info loaded");
    }

    public Student(String name) {
        this.name = name;
    }

    public void printRecord() {
        System.out.println("Student record created: " + name);
    }

    public static void main(String[] args) {

        String[] names = {
            "Ravi",
            "Meera",
            "Karthik",
            "Divya",
            "Anitha"
        };

        Student[] students = new Student[names.length];

        for (int i = 0; i < students.length; i++) {

            students[i] = new Student(names[i]);
            students[i].printRecord();
        }
    }
}