package abstraction.class_problems;

public abstract class Toy {

    private final String toyId;
    private static int counter = 1000;

    public Toy(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }

        counter++;
        toyId = "TOY-" + counter;
    }

    public abstract String makeSound();

    public String getToyId() {
        return toyId;
    }
}