package week3.assigment_problems;

public class Participant {

    private String name;
    private String teamName;
    private boolean registered;

    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    public Participant(String name) {
        this(name, "Unassigned");
    }

    public void printStatus() {
        System.out.println(
                name + " | " + teamName +
                " | Registered: " + registered
        );
    }

    public static void main(String[] args) {

        String[] names = {
            "Ravi",
            "Meera",
            "Karthik",
            "Divya"
        };

        String[] teamNames = {
            "ByteBusters",
            "",
            "CodeCrafters",
            ""
        };

        Participant[] participants = new Participant[names.length];

        for (int i = 0; i < participants.length; i++) {

            if (teamNames[i].isEmpty()) {
                participants[i] = new Participant(names[i]);
            } else {
                participants[i] =
                        new Participant(names[i], teamNames[i]);
            }

            participants[i].printStatus();
        }
    }
}