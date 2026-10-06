package abstraction.class_problems;

public class ToyCar extends Toy {

    public ToyCar(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return getName() + ": Vroom vroom!";
    }

    private String getName() {
        return getToyId().equals("") ? "" : "";
    }
}