package week2.class_problems;

public class CSVStudentRecordParser {

    public static void parseStudentRecord(String csvLine) {

        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String name = fields[0];
        String rollNumber = fields[1];
        String department = fields[2];

        System.out.println("Name: " + name
                + " | Roll No: " + rollNumber
                + " | Dept: " + department);
    }

    public static void main(String[] args) {

        parseStudentRecord(
                "Ananya Verma,RA2211003010123,CSE");

        parseStudentRecord(
                "Ananya Verma,CSE");
    }
}